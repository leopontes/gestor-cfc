package br.com.cfc.gestor.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.model.enuns.MarcaEnum;
import br.com.cfc.gestor.model.enuns.SemanaEnum;
import br.com.cfc.gestor.model.enuns.TipoAulaEnum;

@Controller
public class EscalaController {

	@RequestMapping(value="/gestao/escala", method=RequestMethod.GET)
	public String init(Model model) {
		
		Map<String, Collection<AulaProcessoVeiculo>> gradeHorarios = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		gradeHorarios.put(LocalTime.of(8, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(9, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(10, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(11, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(12, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(13, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(14, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(15, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(16, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(17, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(18, 0).format(formatter), new ArrayList<>());
		gradeHorarios.put(LocalTime.of(19, 0).format(formatter), new ArrayList<>());
		
		Aluno    aluno    = new Aluno();
		Processo processo = new Processo();
		Veiculo  veiculo  = new Veiculo();
		
		aluno.setNome("Leonado Lopes");
		aluno.setId(135l);
		aluno.setCadastradoEm(LocalDate.now());
		
		processo.setAluno(aluno);
		
		veiculo.setMarca(MarcaEnum.FIAT);
		veiculo.setModelo("Palio EXL Fire");
		veiculo.setAno(2017);
		veiculo.setCor("Vermelho");
		veiculo.setPlaca("KOY-3355");
		
		int hora = 8;
		
		LocalDateTime data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		data = LocalDateTime.of(2018, 9, 17, hora++, 0);
		
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data, TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(1), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(2), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(processo, data.plusDays(3), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(4), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(5), TipoAulaEnum.PRATICA, veiculo));
		gradeHorarios.get(data.toLocalTime().format(formatter)).add(new AulaProcessoVeiculo(null, data.plusDays(6), TipoAulaEnum.PRATICA, veiculo));
		
		LocalDate dataInicio = LocalDate.of(2018, 9, 17);
		
		
		DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		
		Collection<String> periodo = new ArrayList<>();
		
		for(int i=0; i < 7; i++) {
			periodo.add(dataInicio.format(formatterDT) + " (" + SemanaEnum.fromCodigo(dataInicio.getDayOfWeek().getValue()).getSigla() + ")");
			dataInicio = dataInicio.plusDays(1);
		}
		
		model.addAttribute("periodo", periodo);
		model.addAttribute("aluno", aluno);
		model.addAttribute("grade", gradeHorarios);
		model.addAttribute("keys",  new TreeSet<>(gradeHorarios.keySet()));
		
		return "grade-horarios";
	}
	
}
