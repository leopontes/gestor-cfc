package br.com.cfc.gestor.controller;

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

import br.com.cfc.gestor.controller.form.VeiculoForm;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.service.VeiculoService;
import br.com.cfc.gestor.utils.MessageContext;

@Controller
public class VeiculoController {

	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private MessageContext messageContext;
	
	@GetMapping("/veiculo")
	public String find(Model model) {
		
		model.addAttribute("veiculos", veiculoService.findAll());
		
		return "veiculo-lista";
	}
	
	@GetMapping("/veiculo/new")
	public String novoPacote(Model model) {
		
		model.addAttribute("veiculo", new Veiculo());
		
		return "veiculo-form";
	}
	
	@PostMapping("/veiculo")
	public String salvar(@ModelAttribute("veiculo") @Valid VeiculoForm veiculoForm, BindingResult bindResult, Model model) {
		
		if(bindResult.hasErrors()) {
			return "veiculo-form";
		}
		
		veiculoService.save(veiculoForm.toBean());
		
		model.addAttribute("veiculos", veiculoService.findAll());
		
		messageContext.add("Salvo com sucesso!");
		
		return "veiculo-lista";
	}
	
	@RequestMapping(value="/veiculo/delete/{id}", method=RequestMethod.GET)
	@Transactional
	public String removerveiculo(@PathVariable("id") Long id) {
		
		Veiculo veiculo = veiculoService.get(id);
		
		veiculoService.delete(veiculo);
		
		return "veiculo-lista";
	}
	
	@RequestMapping(value="/veiculo/edit/{id}", method=RequestMethod.GET)
	public String editveiculo(@PathVariable("id") Long id, Model model) {
		
		Veiculo veiculo = veiculoService.get(id);
		
		VeiculoForm form = new VeiculoForm();
		form.toForm(veiculo);
		
		model.addAttribute("veiculo", form);
		
		return "veiculo-form";
	}
}
