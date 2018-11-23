package br.com.cfc.gestor.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cfc.gestor.controller.form.PagamentoForm;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Contrato;
import br.com.cfc.gestor.model.Paragrafo;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.vo.ContratoVO;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.ClausulaService;
import br.com.cfc.gestor.service.ContratoService;
import br.com.cfc.gestor.service.PacoteService;
import br.com.cfc.gestor.service.ParagrafoService;
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
public class ProcessoController {

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
	private ContratoService contratoService;
	
	@Resource
	private ClausulaService clausulaService;
	
	@Resource
	private ParagrafoService paragrafoService;
	
	@Resource
	private MessageContext messageContext;
	
	@GetMapping("/aluno/processos/{id}")
	public String processos(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Collection<Processo> processos = processoService.find(aluno);
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("processos", processos);
		
		return "processo-lista";
	}
	

	@RequestMapping(value="/aluno/{id}/processos/new", method=RequestMethod.GET)
	public String newProcesso(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Collection<Veiculo> veiculos  = (Collection<Veiculo>) veiculoService.findAll();
		Iterable<Contrato>  contratos = contratoService.findAll();
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("contratos", contratos);
		model.addAttribute("processo", new Processo());
		
		return "processo-form";
	}
	
	@RequestMapping(value="/aluno/processo/{id}/edit", method=RequestMethod.GET)
	public String editarProcesso(@PathVariable("id") Long id, Model model) {
		
		Processo processo = processoService.getId(id);
		
		Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
		Iterable<Contrato>  contratos = contratoService.findAll();
		
		model.addAttribute("contratos", contratos);
		model.addAttribute("aluno", processo.getAluno());
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("processo", processo);
		
		return "processo-form";
	}
	

	@PostMapping("/aluno/{id}/processos/")
	@Transactional
	public String cadastrarProcesso(@PathVariable("id") Long id, @ModelAttribute("processo") @Valid Processo processo, BindingResult bindResult, Model model) {
		
		Aluno aluno = alunoService.get(id);
		Processo processoGravado = processoService.getId(processo.getId());
		
		if(processoGravado == null) {
			return cadastrarNovoProcesso(id, processo, model);
		}else {
			return editarProcesso(id, processoGravado, aluno, model);
		}
		
	}

	private String editarProcesso(Long id, Processo processo, Aluno aluno, Model model) {
		
		if(processo != null) {
			processo.setDataTermino(processo.getDataTermino());
			processo.setStatus(processo.getStatus());
			
			processoService.save(processo);
		}
		
		Collection<Processo> processos = processoService.find(aluno);
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("processos", processos);
		
		return "processo-lista";
	}


	private String cadastrarNovoProcesso(Long id, Processo processo, Model model) {
		Aluno    aluno    = alunoService.get(id);
		
		contratoService.get(processo.getContrato().getId()).ifPresent(crt -> processo.setContrato(crt)); 
		
		Processo processoEmAberto = processoService.getVigente(aluno);
		Iterable<Contrato>  contratos = contratoService.findAll();
		
		if(processoEmAberto != null) {
			messageContext.add("O aluno possui um processo em aberto, será necessário encerrá-lo primeiro.");
			
			Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
			
			model.addAttribute("contratos", contratos);
			model.addAttribute("processo", processo);
			model.addAttribute("aluno", aluno);
			model.addAttribute("veiculos", veiculos);
			
			return "processo-form";
		}
		
		processo.setDataInicio(LocalDate.now());
		processo.setAluno(aluno);
		
		processoService.save(processo);
		
		PagamentoForm pagamento = new PagamentoForm();
		pagamento.setAlunoId(aluno.getId());
		pagamento.setServico(processo.getServico());
		pagamento.setNome(aluno.getNome());
		pagamento.setMatricula(aluno.getMatricula());
		
		model.addAttribute("contratos", contratos);
		model.addAttribute("aluno", aluno);
		model.addAttribute("processo", processo);
		model.addAttribute("pacotes", pacoteService.findAll());
		model.addAttribute("pagamento", pagamento);
		
		return "pagamento-form";
	}
	
	@GetMapping("/aluno/processo/{id}/contrato")
	public void imprimirContrato(Model model, @PathVariable("id") Long idProcesso, HttpServletResponse response) throws JRException, IOException {
		
		Processo processo = processoService.getId(idProcesso);
		Aluno aluno = processo.getAluno();
		
		
		if(processo != null) {
			messageContext.add("O aluno possui um processo em aberto, será necessário encerrá-lo primeiro.");
		}
		
		Contrato contrato = processo.getContrato();
		
		Iterable<Clausula> clausulas = clausulaService.findBy(contrato);
		
		Collection<ContratoVO> contratoDS = new ArrayList<ContratoVO>();
		
		for(Clausula clausula : clausulas) {
			
			Collection<Paragrafo> paragrafos = (Collection<Paragrafo>) paragrafoService.findAll(clausula);
			
			ContratoVO contratoVO = new ContratoVO();
			
			contratoVO.setIntroducao(contrato.getTexto());
			contratoVO.setClausula(clausula.getTexto());
			contratoVO.setClausulaOrdem(clausula.getOrdem().intValue());
			contratoVO.setSecao(clausula.getSecao().getDescricao());
			contratoVO.setSecaoOrdem(clausula.getSecao().getOrdem());
			
			if(CollectionUtils.isNotEmpty(paragrafos)){
				
				for(Paragrafo paragrafo : paragrafos) {
					contratoVO.setParagrafo(paragrafo.getTexto());
					contratoVO.setParagrafoOrdem(paragrafo.getOrdem());
					contratoVO.setUnico(paragrafo.isUnico());
				}
				
			}
			
			contratoDS.add(contratoVO);
		}
		
		Collections.sort((List<ContratoVO>)contratoDS);
				
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contratoDS);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("nome_aluno", aluno.getNome());
		parameters.put("cpf_aluno", aluno.getCpf());
		parameters.put("endereco_aluno", aluno.getEndereco() + ", " + aluno.getNumero() + " - " + aluno.getBairro() + " " + aluno.getCidade() + " - " + aluno.getUf());
		parameters.put("identidade_aluno", aluno.getIdentidade());
		parameters.put("dia", LocalDate.now().getDayOfMonth());
		parameters.put("mes", LocalDate.now().getMonthValue());
		parameters.put("ano", LocalDate.now().getYear());
		
		InputStream  jasperStream = this.getClass().getResourceAsStream("/report/contrato_prestacao_servico.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint  jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
				
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline;filename=carne.pdf");
				
		final OutputStream outputStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
}
