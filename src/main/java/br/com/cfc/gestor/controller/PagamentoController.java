package br.com.cfc.gestor.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.annotation.Resource;
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
import br.com.cfc.gestor.model.enuns.TipoDocumentoEnum;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.DocumentoService;
import br.com.cfc.gestor.service.PacoteService;
import br.com.cfc.gestor.service.ProcessoPagamentoPacoteService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;

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
	
	@RequestMapping(value="/aluno/{idAluno}/pagamento/new", method=RequestMethod.GET)
	public String newPagamento(@PathVariable("idAluno") Long idAluno, Model model) {
		
		Aluno    aluno    = alunoService.get(idAluno);
		Processo processo = processoService.getVigente(aluno);
		
		model.addAttribute("aluno",    aluno);
		model.addAttribute("processo", processo);
		model.addAttribute("pagamento", new PagamentoForm());
		
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
				File file = new File(path + "/docs/" + documento.getOriginalFilename());
				documento.transferTo(file);
				Documento doc = new Documento();
				doc.setNome(documento.getOriginalFilename());
				doc.setPath(relativePath + "/docs/" + documento.getOriginalFilename());
				doc.setTipoDocumento(TipoDocumentoEnum.CONTRATO);
				doc.setAluno(aluno);
				documentoService.save(doc);
			}
		}
		
		return "redirect:aluno";
		
		//return lista(model, processo.getId());
	}
}
