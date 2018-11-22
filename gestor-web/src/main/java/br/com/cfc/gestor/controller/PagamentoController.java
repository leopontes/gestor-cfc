package br.com.cfc.gestor.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import br.com.cfc.gestor.controller.form.PagamentoForm;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Documento;
import br.com.cfc.gestor.model.Pacote;
import br.com.cfc.gestor.model.Pagamento;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.ProcessoPagamentoPacote;
import br.com.cfc.gestor.model.enuns.FormaDePagamentoEnum;
import br.com.cfc.gestor.model.enuns.TipoDocumentoEnum;
import br.com.cfc.gestor.model.enuns.TipoPagamentoEnum;
import br.com.cfc.gestor.model.vo.PagamentoVO;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.DocumentoService;
import br.com.cfc.gestor.service.PacoteService;
import br.com.cfc.gestor.service.ProcessoPagamentoPacoteService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;
import br.com.cfc.gestor.utils.MessageContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class PagamentoController {

	@Value("${server.file.path}")
	private String path;
	
	@Value("${server.relative.path}")
	private String relativePath;

	@Resource
	private AlunoService alunoService;
	
	@Resource
	private ProcessoService processoService;
	
	@Resource
	private AulaProcessoVeiculoService aulaProcessoVeiculoService;
	
	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private PacoteService pacoteService;
	
	@Resource
	private ProcessoPagamentoPacoteService processoPagamentoPacoteServive;
	
	@Resource
	private DocumentoService documentoService;
	
	@Resource
	private MessageContext messageContext;
	
	@RequestMapping(value="/aluno/{idAluno}/pagamento/new", method=RequestMethod.GET)
	public String newPagamento(@PathVariable("idAluno") Long idAluno, Model model) {
		
		Aluno            aluno    = alunoService.get(idAluno);
		Processo         processo = processoService.getVigente(aluno);
		Iterable<Pacote> pacotes  = pacoteService.findAll();
		
		PagamentoForm pagamento = new PagamentoForm();
		pagamento.setAlunoId(idAluno);
		pagamento.setNome(aluno.getNome());
		pagamento.setMatricula(aluno.getMatricula());
		
		model.addAttribute("aluno",     aluno);
		model.addAttribute("processo",  processo);
		model.addAttribute("pagamento", pagamento);
		model.addAttribute("pacotes",   pacotes);
		
		return "pagamento-form";
	}
	
	@GetMapping("/aluno/{id}/processo/pagamento")
	public String lista(Model model, @PathVariable("id") Long id) {
		
		Aluno    aluno    = alunoService.get(id);
		Processo processo = processoService.getVigente(aluno);
		
		Iterable<ProcessoPagamentoPacote> pagamentos = processoPagamentoPacoteServive.findByProcesso(processo);
		
		model.addAttribute("pagamentos", pagamentos);
		model.addAttribute("aluno", aluno);
		model.addAttribute("processo", processo);
		
		return "pagamento-lista";
	}
	
	@PostMapping("/pagamento")
	@Transactional
	public String cadastrarPagamento(Model model, PagamentoForm form) throws IllegalStateException, IOException {
		
		ProcessoPagamentoPacote processoPagamentoPacote = null;
		
		Aluno    aluno    = alunoService.get(form.getAlunoId());
		Processo processo = processoService.getVigente(aluno);
		Pacote   pacote   = pacoteService.get(form.getPacote());
		
		Integer parcelas = form.getParcela();
		
		LocalDate dataVencimento = LocalDate.now();
		
		if(parcelas !=null) {
			for(int i=0; i<parcelas; i++) {
				
				Pagamento pagamento = new Pagamento();
				pagamento.setDataVencimento(dataVencimento);
				pagamento.setFormaPagamento(form.getFormaPagamento());
				pagamento.setTipoPagamento(form.getTipoPagamento());
				pagamento.setValor(form.getValorParcela());
				
				processoPagamentoPacote = new ProcessoPagamentoPacote();
				processoPagamentoPacote.setPacote(pacote);
				processoPagamentoPacote.setPagamento(pagamento);
				processoPagamentoPacote.setProcesso(processo);
				
				processoPagamentoPacoteServive.save(processoPagamentoPacote);
				
				dataVencimento = dataVencimento.plusMonths(1);
			}
		}
		
		if(form.getDocumentos() != null && form.getDocumentos().length > 0) {
			for(MultipartFile documento : form.getDocumentos()) {
				File directory = new File(path + "docs\\" + aluno.getIdentificador() + "\\");
				
				boolean isCreated = false;
				
				if(!directory.exists()) {
					isCreated = directory.mkdirs();
				}
				
				if(isCreated) {
					File document = new File(path + "docs\\" + aluno.getIdentificador() + "\\" + documento.getOriginalFilename());
					documento.transferTo(document);
					Documento doc = new Documento();
					doc.setNome(documento.getOriginalFilename());
					doc.setPath(relativePath + "/docs/" + aluno.getIdentificador() + "/" + documento.getOriginalFilename());
					doc.setTipoDocumento(TipoDocumentoEnum.CONTRATO);
					doc.setAluno(aluno);
					documentoService.save(doc);
				}
			}
		}
		
		return "redirect:aluno";
		
		//return lista(model, processo.getId());
	}
	
	@GetMapping("/aluno/{id}/pagamento/boleto")
	public void emitirCarneDePagamento(Model model, @PathVariable("id") Long idAluno, HttpServletResponse response) throws JRException, IOException {
		
		Aluno                             aluno      = alunoService.get(idAluno);
		Processo                          processo   = processoService.getVigente(aluno);
		Collection<ProcessoPagamentoPacote> pagamentos = (Collection<ProcessoPagamentoPacote>) processoPagamentoPacoteServive.findByProcesso(processo);
		
		if(aluno == null) {
			messageContext.add("Aluno não encontrado!");
			return;
		}
		
		if(processo == null) {
			messageContext.add("O aluno não possui processo em andamento!");
			return;
		}
		
		if(pagamentos == null || pagamentos.isEmpty()) {
			messageContext.add("O aluno não possui parcelamentos em seu processo!");
			return;
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nome_aluno", aluno.getNome());
		parameters.put("cpf",        aluno.getCpf());
		parameters.put("aluno_id",   aluno.getId());
		
		
		List<PagamentoVO> pagamentosReport = new ArrayList<PagamentoVO>();
		
		PagamentoVO parcela1 = new PagamentoVO();
		PagamentoVO parcela2 = new PagamentoVO();
		PagamentoVO parcela3 = new PagamentoVO();
		PagamentoVO parcela4 = new PagamentoVO();
		
		parcela1.setId(1l);
		parcela1.setDataPagamento(org.joda.time.LocalDate.now());
		parcela1.setDataVencimento(org.joda.time.LocalDate.now());
		parcela1.setFormaPagamento(FormaDePagamentoEnum.PARCELADO);
		parcela1.setTipoPagamento(TipoPagamentoEnum.CARNE);
		parcela1.setValor(BigDecimal.valueOf(2234));
		
		parcela2.setId(2l);
		parcela2.setDataPagamento(org.joda.time.LocalDate.now());
		parcela2.setDataVencimento(org.joda.time.LocalDate.now());
		parcela2.setFormaPagamento(FormaDePagamentoEnum.PARCELADO);
		parcela2.setTipoPagamento(TipoPagamentoEnum.CARNE);
		parcela2.setValor(BigDecimal.valueOf(2234));
		
		parcela3.setId(3l);
		parcela3.setDataPagamento(org.joda.time.LocalDate.now());
		parcela3.setDataVencimento(org.joda.time.LocalDate.now());
		parcela3.setFormaPagamento(FormaDePagamentoEnum.PARCELADO);
		parcela3.setTipoPagamento(TipoPagamentoEnum.CARNE);
		parcela3.setValor(BigDecimal.valueOf(2234));
		
		parcela4.setId(4l);
		parcela4.setDataPagamento(org.joda.time.LocalDate.now());
		parcela4.setDataVencimento(org.joda.time.LocalDate.now());
		parcela4.setFormaPagamento(FormaDePagamentoEnum.PARCELADO);
		parcela4.setTipoPagamento(TipoPagamentoEnum.CARNE);
		parcela4.setValor(BigDecimal.valueOf(2234));
		
		pagamentosReport.add(parcela1);
		pagamentosReport.add(parcela2);
		pagamentosReport.add(parcela3);
		pagamentosReport.add(parcela4);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource( pagamentosReport );
		
		InputStream  jasperStream = this.getClass().getResourceAsStream("/report/carne_pagamento.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint  jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
				
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline;filename=carne.pdf");
				
		final OutputStream outputStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
