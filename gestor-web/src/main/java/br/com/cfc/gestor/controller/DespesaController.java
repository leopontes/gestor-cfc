package br.com.cfc.gestor.controller;

import java.time.LocalDate;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.cfc.gestor.controller.filtro.ContaFiltro;
import br.com.cfc.gestor.controller.form.DespesaForm;
import br.com.cfc.gestor.model.Conta;
import br.com.cfc.gestor.model.Despesa;
import br.com.cfc.gestor.service.ContaService;
import br.com.cfc.gestor.service.DespesaService;

@Controller
public class DespesaController {
	
	@Resource
	private DespesaService despesaService;
	
	@GetMapping("/contabilidade/despesa")
	public String findDespesas(Model model) {
		
		model.addAttribute("despesas", despesaService.findAll());
		
		return "despesa-lista";
	}

	@GetMapping(value="/contabilidade/despesa/new")
	public String novaDespesa(Model model) {
		
		model.addAttribute("despesa", new DespesaForm());
		
		return "despesa-form";
	}
	
	@PostMapping("/contabilidade/despesa")
	@Transactional
	public String cadastrarDespesa(@ModelAttribute("despesa") @Valid DespesaForm despesaForm, BindingResult bindResult, Model model) {
		
		if(bindResult.hasErrors()) {
			return "despesa-form";
		}
		
		despesaService.save(despesaForm.toBean());
		
		return findDespesas(model);
	}
	
	@GetMapping(value="/contabilidade/despesa/{id}/edit")
	public String editarDespesa(Model model, @PathVariable("id") Long id) {
		
		Despesa     despesa     = despesaService.findById(id);
		DespesaForm despesaForm = new DespesaForm();
		
		despesaForm.toForm(despesa);
		
		model.addAttribute("despesa", despesaForm);
		
		return "despesa-form";
	}
	
	@PutMapping("/contabilidade/despesa")
	@Transactional
	public String salvarDespesa(@ModelAttribute("despesa") @Valid DespesaForm despesaForm, BindingResult bindResult, Model model) {
		
		if(bindResult.hasErrors()) {
			return "despesa-form";
		}
		
		Despesa despesa = despesaService.findById(despesaForm.getId());
		
		despesa.setDescricao(despesaForm.getDescricao());
		despesa.setTipoDespesa(despesaForm.getTipoDespesa());
		
		despesaService.save(despesa);
		
		return "despesa-lista";
	}
	
	@DeleteMapping("/contabilidade/despesa/{id}")
	@Transactional
	public String excluirDespesa(Model model, @PathVariable("id") Long id) {
		
		Despesa despesa = despesaService.findById(id);
		
		despesaService.delete(despesa);
		
		return findDespesas(model);
	}
	
}
