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
import java.util.Optional;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cfc.gestor.controller.filtro.AgendamentoFiltro;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.enuns.NavigationEnum;
import br.com.cfc.gestor.model.enuns.SemanaEnum;
import br.com.cfc.gestor.model.enuns.TipoAulaEnum;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;
import br.com.cfc.gestor.utils.BusinessUtils;

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
		
		LocalDate dataReferencia = null;
		
		if(!StringUtils.isEmpty(mesAno)) {
			dataReferencia = LocalDate.of(Integer.valueOf(mesAno[1]), Integer.valueOf(mesAno[0]), 1);
		}
		
		if(NavigationEnum.NEXT.equals(filtro.getNavigation())) {
			dataReferencia = filtro.getFim().plusDays(1);
		}
		
		if(NavigationEnum.PREVIOUS.equals(filtro.getNavigation())) {
			dataReferencia = filtro.getInicio().minusDays(7);
		}
		
		filtro.setInicio(dataReferencia);
		
		while(!SemanaEnum.SEGUNDA.equals(SemanaEnum.fromCodigo(dataReferencia.getDayOfWeek().getValue()))){
			dataReferencia = dataReferencia.minusDays(1);
		}
		
		Collection<LocalDate> periodo       = new ArrayList<>();
		Collection<String>    periodoLabels = new ArrayList<>();
		
		Map<String, Collection<AulaProcessoVeiculo>> gradeHorarios = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		for(int i=0; i < 7; i++) {
			periodoLabels.add(dataReferencia.format(formatterDT) + " (" + SemanaEnum.fromCodigo(dataReferencia.getDayOfWeek().getValue()).getSigla() + ")");
			periodo.add(dataReferencia);
			dataReferencia = dataReferencia.plusDays(1);
		}
		
		filtro.setFim(dataReferencia);
		
		int hora = 8;
		
		Aluno aluno = alunoService.get(filtro.getMatricula());
		Veiculo veiculo = veiculoService.get(filtro.getVeiculo());
		
		for(int i =0 ; i < 13 ; i++) {
			
			LocalTime horario = LocalTime.of(hora++, 0);
			gradeHorarios.put(horario.format(formatter), new ArrayList<>());
			
			for(LocalDate data : periodo) {
				AulaProcessoVeiculo aula = aulaProcessoVeiculoService.find(filtro.getVeiculo(), data.atTime(horario));
				
				if(aula != null) {
					gradeHorarios.get(horario.format(formatter)).add(aula);
				}else {
					aula = new AulaProcessoVeiculo(null, data.atTime(horario), TipoAulaEnum.PRATICA, veiculo);
					gradeHorarios.get(horario.format(formatter)).add(aula);
				}
			}
		}
		
		filtro.setNavigation(NavigationEnum.CURRENT);
		
		model.addAttribute("veiculos", veiculoService.findAll());
		model.addAttribute("periodo", periodoLabels);
		model.addAttribute("aluno", aluno);
		model.addAttribute("grade", gradeHorarios);
		model.addAttribute("keys",  new TreeSet<>(gradeHorarios.keySet()));
		model.addAttribute("filtro", filtro);
		
		return "grade-horarios";
	}
	
	@RequestMapping(value="/escala", method=RequestMethod.GET)
	public String init(Model model, @RequestParam(value="matricula", required=false) Optional<String> matricula) {
		
		Aluno             aluno  = null;
		AgendamentoFiltro filtro = null;
		
		if(matricula.isPresent()) {
			Optional<Long> codigo = BusinessUtils.toCodigo(matricula.get());
			
			if(codigo.isPresent()) {
				aluno = alunoService.get(codigo.get());
			}
			
			filtro = new AgendamentoFiltro();
			filtro.setMatricula(aluno.getId());
			filtro.setNome(aluno.getNome());
		}
		
		if(aluno == null) {
			aluno = new Aluno();
		}
		
		if(filtro == null) {
			filtro = new AgendamentoFiltro();
		}
		
		model.addAttribute("veiculos", veiculoService.findAll());
		model.addAttribute("aluno", aluno);
		model.addAttribute("filtro", filtro);
		
		return "grade-horarios";
	}
	
	@RequestMapping(value="/escala/agendar", method=RequestMethod.GET)
	@Transactional
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
	
	@RequestMapping(value="/escala/desmarcar", method=RequestMethod.GET)
	@Transactional
	public String desmarcar(@RequestParam("agendamentoId") Long agendamentoId, @RequestParam("mesAno") String mesAno, Model model) {
		
		AgendamentoFiltro   filtro = new AgendamentoFiltro();
		AulaProcessoVeiculo aula   = aulaProcessoVeiculoService.get(agendamentoId);
		
		aulaProcessoVeiculoService.delete(aula);
		
		filtro.setMatricula(aula.getProcesso().getAluno().getId());
		filtro.setMesAno(mesAno);
		filtro.setVeiculo(aula.getVeiculo().getId());
		filtro.setNome(aula.getProcesso().getAluno().getNome());
		
		return find(filtro, model);
	}
	
}
