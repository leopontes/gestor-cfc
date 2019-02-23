package br.com.cfc.gestor.controller;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cfc.gestor.controller.filtro.ContaFiltro;
import br.com.cfc.gestor.model.Conta;
import br.com.cfc.gestor.service.ContaService;

@Controller
public class ContaController {

	@Resource
	private ContaService contaService;
	
	@GetMapping("/contabilidade/despesa/conta")
	public String findContas(Model model) {
		
		ContaFiltro filtro = new ContaFiltro();
		
		filtro.setInicio(LocalDate.now().withDayOfMonth(1));
		filtro.setFim(LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1));
		
		Iterable<Conta> contas = contaService.findBy(filtro.getInicio(), filtro.getFim());
		
		model.addAttribute("filtro", filtro);
		
		model.addAttribute("contas", contas);
		
		return "conta-lista";
	}
}
