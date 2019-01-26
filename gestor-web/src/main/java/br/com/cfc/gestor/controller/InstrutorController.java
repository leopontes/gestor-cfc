package br.com.cfc.gestor.controller;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cfc.gestor.controller.form.InstrutorForm;
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
	
	@RequestMapping(value="/instrutor/delete/{id}", method=RequestMethod.GET)
	@Transactional
	public String removerInstrutor(@PathVariable("id") Long id) {
		
		Instrutor instrutor = instrutorService.get(id);
		
		instrutorService.delete(instrutor);
		
		return "aluno-lista";
	}
	
	@RequestMapping(value="/instrutor/edit/{id}", method=RequestMethod.GET)
	public String editInstrutor(@PathVariable("id") Long id, Model model) {
		
		Instrutor instrutor = instrutorService.get(id);
		
		InstrutorForm form = new InstrutorForm();
		form.toForm(instrutor);
		
		model.addAttribute("instrutor", form);
		
		return "aluno-form";
	}
}
