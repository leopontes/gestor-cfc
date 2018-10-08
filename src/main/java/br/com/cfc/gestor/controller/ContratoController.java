package br.com.cfc.gestor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.cfc.gestor.model.Clausula;
import br.com.cfc.gestor.model.Contrato;
import br.com.cfc.gestor.model.Paragrafo;
import br.com.cfc.gestor.model.Secao;
import br.com.cfc.gestor.service.ClausulaService;
import br.com.cfc.gestor.service.ContratoService;
import br.com.cfc.gestor.service.ParagrafoService;
import br.com.cfc.gestor.service.SecaoService;

@Controller
public class ContratoController {

	@Resource
	private ContratoService contratoService;
	
	@Resource
	private SecaoService secaoService;
	
	@Resource
	private ClausulaService clausulaService;
	
	@Resource
	private ParagrafoService paragrafoService;

	// ####### CONTRATOS ###############
	
	@GetMapping("/contrato")
	public String listar(Model model) {
		
		model.addAttribute("contratos", contratoService.findAll());
		
		return "contrato-lista";
	}
	
	@GetMapping("/contrato/new")
	public String newContrato(Model model) {
		
		model.addAttribute("contrato", new Contrato());
		
		return "contrato-form";
	}
	
	@PostMapping("/contrato")
	public String salvarContrato(Model model, Contrato contrato) {
		
		contratoService.save(contrato);
		
		return listar(model);
	}
	
	// ####### SECAO ###############
	
	@GetMapping("/secao")
	public String listarSecoes(Model model) {
		
		model.addAttribute("secoes", secaoService.findAll());
		
		return "secao-lista";
	}
	
	@GetMapping("/secao/new")
	public String newSecao(Model model) {
		
		model.addAttribute("secao", new Secao());
		
		return "secao-form";
	}
	
	@PostMapping("/secao")
	public String salvarSecao(Model model, Secao secao) {
		
		secaoService.save(secao);
		
		return listarSecoes(model);
	}
	
	// ####### CLAUSULAS ###############
	
	@GetMapping("/contrato/{id}/clausula")
	public String listar(Model model, @PathVariable("id") Long id) {
		
		contratoService.get(id).ifPresent(contrato -> {
			model.addAttribute("contrato", contrato);
			model.addAttribute("clausulas", clausulaService.findBy(contrato));
		});
		
		return "clausula-lista";
	}
	
	@GetMapping("/contrato/{id}/clausula/new")
	public String newClausula(Model model, @PathVariable("id") Long id) {
		
		contratoService.get(id).ifPresent(contrato -> {
			model.addAttribute("contrato", contrato);
		});
		
		model.addAttribute("secoes", secaoService.findAll());
		model.addAttribute("clausula", new Clausula());
		
		return "clausula-form";
	}
	
	@PostMapping("/contrato/{id}/clausula")
	public String salvarClausula(Model model, Clausula clausula, @PathVariable("id") Long id) {
		
		contratoService.get(id).ifPresent(contrato ->{
			clausula.setId(null);
			clausula.setContrato(contrato);
			clausulaService.save(clausula);
		});
		
		return listar(model, id);
	}
	
	@PutMapping("/contrato/{id}/clausula")
	public String atualizarClausula(Model model, Clausula clausula, @PathVariable("id") Long id) {

		clausulaService.get(id).ifPresent(clausulaPersistida -> {
			clausulaPersistida.setOrdem(clausula.getOrdem());
			clausulaPersistida.setSecao(clausula.getSecao());
			clausulaPersistida.setTexto(clausula.getTexto());
			
			clausulaService.save(clausulaPersistida);
		});
		
		return listar(model, id);
	}
	
	@GetMapping("/contrato/clausula/{id}")
	public String editarClausula(Model model, @PathVariable("id") Long id) {
		
		clausulaService.get(id).ifPresent(clausula ->{
			model.addAttribute("clausula", clausula);
			model.addAttribute("contrato", clausula.getContrato());
			model.addAttribute("update", Boolean.TRUE);
		});
		
		model.addAttribute("secoes", secaoService.findAll());
		
		return "clausula-form";
	}
	// ####### CLAUSULAS ###############
	
	@GetMapping("/contrato/clausula/{idClausula}/paragrafo")
	public String listarParagrafos(Model model, @PathVariable("idClausula") Long idClausula) {
		
		clausulaService.get(idClausula).ifPresent(clausula ->{
			model.addAttribute("clausula", clausula);
			model.addAttribute("paragrafos", paragrafoService.findAll(clausula));
		});
		
		return "paragrafo-lista";
	}
	
	@GetMapping("/contrato/clausula/{idClausula}/paragrafo/new")
	public String newParagrafo(Model model, @PathVariable("idClausula") Long idClausula) {
		
		Clausula clausula = clausulaService.get(idClausula).orElse(new Clausula());
		
		model.addAttribute("clausula", clausula);
		model.addAttribute("paragrafo", new Paragrafo());
		
		return "paragrafo-form";
	}
	
	@PostMapping("/contrato/clausula/{id}/paragrafo")
	public String salvarParagrafo(Model model, Paragrafo paragrafo, @PathVariable("id") Long idClausula) {
		
		clausulaService.get(idClausula).ifPresent(clausula -> {
			paragrafo.setId(null);
			paragrafo.setClausula(clausula);
			paragrafoService.save(paragrafo);
		});
		
		return listarParagrafos(model, idClausula);
	}
}
