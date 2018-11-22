package br.com.cfc.gestor.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cfc.gestor.model.Pacote;
import br.com.cfc.gestor.service.PacoteService;
import br.com.cfc.gestor.utils.MessageContext;

@Controller
public class PacoteController {

	@Resource
	private MessageContext messageContext;
	
	@Resource
	private PacoteService pacoteService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		DecimalFormat        df    = new DecimalFormat();
		DecimalFormatSymbols dfs   = new DecimalFormatSymbols();
		
		dfs.setGroupingSeparator('.');
		dfs.setDecimalSeparator(',');
		df.setDecimalFormatSymbols(dfs);
		df.setParseBigDecimal(true);
		
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, df, true));
	}
	
	@GetMapping("/pacote")
	public String listar(Model model) {
		
		model.addAttribute("pacotes", pacoteService.findAll());
		
		return "pacote-lista";
	}
	
	@GetMapping("/pacote/new")
	public String novoPacote(Model model) {
		
		model.addAttribute("pacote", new Pacote());
		
		return "pacote-form";
	}
	
	@PostMapping("/pacote")
	public String salvar(Model model, Pacote pacote) {
		
		pacoteService.save(pacote);
		
		model.addAttribute("pacotes", pacoteService.findAll());
		
		messageContext.add("Salvo com sucesso!");
		
		return "pacote-lista";
	}

	@GetMapping("/pacote/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Pacote findById(@PathVariable("id") Long id) {
		Pacote pacote = pacoteService.get(id);
		return pacote;
	}
}
