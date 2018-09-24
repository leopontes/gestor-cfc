package br.com.cfc.gestor.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cfc.gestor.controller.filtro.AgendamentoFiltro;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.enuns.SemanaEnum;
import br.com.cfc.gestor.model.enuns.TipoAulaEnum;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;

@Controller
public class EscalaController {

	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private AlunoService alunoService;
	
	@Resource
	private ProcessoService processoService;
	
	@Resource
	private AulaProcessoVeiculoService aulaProcessoVeiculoService;
	
	@RequestMapping(value="/escala", method=RequestMethod.POST)
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
		
		Aluno aluno = alunoService.get(filtro.getMatricula());
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
		model.addAttribute("aluno", aluno);
		model.addAttribute("grade", gradeHorarios);
		model.addAttribute("keys",  new TreeSet<>(gradeHorarios.keySet()));
		model.addAttribute("filtro", filtro);
		
		return "grade-horarios";
	}
	
	@RequestMapping(value="/escala", method=RequestMethod.GET)
	public String init(Model model) {
		
		model.addAttribute("veiculos", veiculoService.findAll());
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("filtro", new AgendamentoFiltro());
		
		return "grade-horarios";
	}
	
	@RequestMapping(value="/escala/agendar", method=RequestMethod.GET)
	public String agendar(@RequestParam("aluno") Long alunoId, @RequestParam("data") String data, @RequestParam("veiculo") Long veiculoId, @RequestParam("mesAno") String mesAno, Model model) {
		
		AgendamentoFiltro   filtro    = new AgendamentoFiltro();
		AulaProcessoVeiculo aula      = new AulaProcessoVeiculo();
		Aluno               aluno     = alunoService.get(alunoId);
		Veiculo             veiculo   = veiculoService.get(veiculoId);
		List<Processo>      processos = (List<Processo>) processoService.find(aluno);
		
		LocalDateTime dataAgendamento = LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		
		filtro.setMatricula(alunoId);
		filtro.setMesAno(mesAno);
		filtro.setVeiculo(veiculoId);
		filtro.setNome(aluno.getNome());
		
		aula.setData(dataAgendamento);
		aula.setProcesso(processos.get(0));
		aula.setTipo(TipoAulaEnum.PRATICA);
		aula.setVeiculo(veiculo);
		
		aulaProcessoVeiculoService.save(aula);
		
		return find(filtro, model);
	}
	
}
