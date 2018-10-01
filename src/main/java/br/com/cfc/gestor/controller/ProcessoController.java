package br.com.cfc.gestor.controller;

import java.time.LocalDate;
import java.util.Collection;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.PacoteService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;

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
		
		Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("processo", new Processo());
		
		return "processo-form";
	}
	

	@PostMapping("/aluno/{id}/processos/")
	@Transactional
	public String cadastrarProcesso(@PathVariable("id") Long id, @ModelAttribute("processo") @Valid Processo processo, BindingResult bindResult, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		processo.setDataInicio(LocalDate.now());
		processo.setAluno(aluno);
		
		processoService.save(processo);
		
		PagamentoForm pagamento = new PagamentoForm();
		pagamento.setAlunoId(aluno.getId());
		pagamento.setServico(processo.getServico());
		pagamento.setNome(aluno.getNome());
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("processo", processo);
		model.addAttribute("pacotes", pacoteService.findAll());
		model.addAttribute("pagamento", pagamento);
		
		return "pagamento-form";
	}
}
