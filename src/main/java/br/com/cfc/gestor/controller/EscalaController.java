package br.com.cfc.gestor.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cfc.gestor.controller.filtro.AgendamentoFiltro;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.enuns.SemanaEnum;
import br.com.cfc.gestor.model.enuns.TipoAulaEnum;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.VeiculoService;

@Controller
public class EscalaController {

	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private AulaProcessoVeiculoService aulaProcessoVeiculoService;
	
	@RequestMapping(value="/gestao/escala", method=RequestMethod.POST)
	public String find(AgendamentoFiltro filtro, Model model) {
		
		DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String[] mesAno = filtro.getMesAno().split("/");
		
		LocalDate dataInicio = LocalDate.of(Integer.valueOf(mesAno[1]), Integer.valueOf(mesAno[0]), 1);
		
		while(!SemanaEnum.SEGUNDA.equals(SemanaEnum.fromCodigo(dataInicio.getDayOfWeek().getValue()))){
			dataInicio = dataInicio.minusDays(1);
		}
		
		Collection<LocalDate> periodo       = new ArrayList<>();
		Collection<String>    periodoLabels = new ArrayList<>();
		
		Map<String, Collection<AulaProcessoVeiculo>> gradeHorarios = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		for(int i=0; i < 7; i++) {
			periodoLabels.add(dataInicio.format(formatterDT) + " (" + SemanaEnum.fromCodigo(dataInicio.getDayOfWeek().getValue()).getSigla() + ")");
			periodo.add(dataInicio);
			dataInicio = dataInicio.plusDays(1);
		}
		
		int hora = 8;
		
		Veiculo veiculo = veiculoService.get(filtro.getVeiculo());
		
		for(int i =0 ; i < 13 ; i++) {
			
			LocalTime horario = LocalTime.of(hora++, 0);
			gradeHorarios.put(horario.format(formatter), new ArrayList<>());
			
			for(LocalDate dataReferencia : periodo) {
				AulaProcessoVeiculo aula = aulaProcessoVeiculoService.find(filtro.getVeiculo(), dataReferencia.atTime(horario));
				
				if(aula != null) {
					gradeHorarios.get(horario.format(formatter)).add(aula);
				}else {
					aula = new AulaProcessoVeiculo(null, dataReferencia.atTime(horario), TipoAulaEnum.PRATICA, veiculo);
					gradeHorarios.get(horario.format(formatter)).add(aula);
				}
			}
		}
		
		model.addAttribute("veiculos", veiculoService.findAll());
		model.addAttribute("periodo", periodoLabels);
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("grade", gradeHorarios);
		model.addAttribute("keys",  new TreeSet<>(gradeHorarios.keySet()));
		model.addAttribute("filtro", filtro);
		
		return "grade-horarios";
	}
	
	@RequestMapping(value="/gestao/escala", method=RequestMethod.GET)
	public String init(Model model) {
		
		model.addAttribute("veiculos", veiculoService.findAll());
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("filtro", new AgendamentoFiltro());
		
		return "grade-horarios";
	}
	
}
