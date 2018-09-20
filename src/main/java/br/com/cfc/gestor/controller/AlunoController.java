package br.com.cfc.gestor.controller;

import java.util.Collection;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.VeiculoService;

@Controller
public class AlunoController {

	@Resource
	private AlunoService alunoService;
	
	@Resource
	private VeiculoService veiculoService;
	
	@GetMapping("/gestao/aluno")
	public String matriculas(Model model) {
		
		Collection<Aluno> alunos = (Collection<Aluno>) alunoService.findAll();
		
		model.addAttribute("alunos", alunos);
		
		return "lista-aluno";
	}
	
	@GetMapping("/gestao/aluno/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Aluno matriculas(@PathVariable("id") Long id) {
		
		Aluno aluno = alunoService.get(id);
		
		return aluno;
	}
	
	@PostMapping("/gestao/aluno")
	public String cadastrarAluno(@ModelAttribute("aluno") @Valid Aluno aluno, BindingResult bindResult, Model model) {
		
		if(bindResult.hasErrors()) {
			return "aluno-form";
		}
		
		alunoService.save(aluno);
		
		Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("processo", new Processo());
		
		
		return "processo-form";
	}
	
	@PutMapping("/gestao/aluno")
	public String atualizarAluno(@Valid Aluno aluno) {
		
		alunoService.save(aluno);
		
		return "processo-form";
	}
	
	@RequestMapping(value="/gestao/aluno/delete/{id}", method=RequestMethod.GET)
	public String removerAluno(@PathVariable("id") Long id) {
		
		Aluno aluno = alunoService.get(id);
		
		alunoService.delete(aluno);
		
		return "lista-aluno";
	}
	
	@RequestMapping(value="/gestao/aluno/new", method=RequestMethod.GET)
	public String newAluno(Model model) {
		
		model.addAttribute("aluno", new Aluno());
		
		return "aluno-form";
	}
	
	@RequestMapping(value="/gestao/aluno/edit/{id}", method=RequestMethod.GET)
	public String editAluno(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		model.addAttribute("aluno", aluno);
		
		return "aluno-form";
	}
	
	@GetMapping("/gestao/aluno/processos/{id}")
	public String processos(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		model.addAttribute("aluno", aluno);
		
		return "processo-lista";
	}
	
	@RequestMapping(value="/gestao/aluno/{id}/processos/new", method=RequestMethod.GET)
	public String newProcesso(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("processo", new Processo());
		
		return "processo-form";
	}
}
