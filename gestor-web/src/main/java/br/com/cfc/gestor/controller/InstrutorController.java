package br.com.cfc.gestor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cfc.gestor.model.Instrutor;
import br.com.cfc.gestor.service.InstrutorService;
import br.com.cfc.gestor.utils.MessageContext;

@Controller
public class InstrutorController {

	@Resource
	private InstrutorService instrutorService;
	
	@Resource
	private MessageContext messageContext;
	
	@GetMapping("/instrutor")
	public String find(Model model) {
		
		model.addAttribute("instrutores", instrutorService.findAll());
		
		return "instrutor-lista";
	}
	
	@GetMapping("/instrutor/new")
	public String novoPacote(Model model) {
		
		model.addAttribute("instrutor", new Instrutor());
		
		return "instrutor-form";
	}
	
	@PostMapping("/instrutor")
	public String salvar(Model model, Instrutor instrutor) {
		
		instrutorService.save(instrutor);
		
		model.addAttribute("instrutores", instrutorService.findAll());
		
		messageContext.add("Salvo com sucesso!");
		
		return "pacote-lista";
	}
}
