package br.com.cfc.gestor.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cfc.gestor.controller.form.AlunoForm;
import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;
import br.com.cfc.gestor.utils.MessageContext;

@Controller
public class AlunoController {
	
	private static final String ABSOLUTE_PATH_SHARED_FOLDER = "C:\\Desenvolvimento\\servidores\\shared_folder\\images\\";
	
	private static final String RELATIVE_PATH_SHARED_FOLDER = "/gestao-cfc/files/images/";
	
	private static int currentPage = 1;
	private static int pageSize = 7;

	@Resource
	private MessageContext messageContext;
	
	@Resource
	private AlunoService alunoService;
	
	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private ProcessoService processoService;
	
	@PostMapping("/aluno/search")
	public String fullSearch(Model model, 
			 @RequestParam("page") Optional<Integer> page, 
			 @RequestParam("size") Optional<Integer> size,
			 @RequestParam("nomeCpf") Optional<String> findBy) {
		
		return matriculas(model, page, size, findBy);
	}
	
	@GetMapping("/aluno")
	public String matriculas(Model model, 
							 @RequestParam("page") Optional<Integer> page, 
							 @RequestParam("size") Optional<Integer> size,
							 @RequestParam("nomeCpf") Optional<String> findBy) {
		
		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		
		if(currentPage <= 0) {
			currentPage = 1;
		}
		
		Page<Aluno> alunosPage = alunoService.findPaginated(findBy, PageRequest.of(currentPage-1, pageSize));
		
		model.addAttribute("alunos", alunosPage);

		int totalPages = alunosPage.getTotalPages();

		if(totalPages > 0) {
			
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			SortedSet<Integer> colecao = new TreeSet<>(pageNumbers);
			
			model.addAttribute("pageNumbers", pageNumbers);
			model.addAttribute("firstPageNumber", colecao.first());
			model.addAttribute("lastPageNumber", colecao.last());
			model.addAttribute("nextPageNumber", currentPage+1);
			model.addAttribute("previusPageNumber", currentPage-1);
		}
		
		return "lista-aluno";
	}
	
	@GetMapping("/aluno/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Aluno matriculas(@PathVariable("id") Long id) {
		
		Aluno aluno = alunoService.get(id);
		
		return aluno;
	}
	
	@PostMapping("/aluno")
	@Transactional
	public String cadastrarAluno(@ModelAttribute("aluno") @Valid AlunoForm alunoForm, BindingResult bindResult, Model model) {
		
		if(bindResult.hasErrors()) {
			return "aluno-form";
		}
		
		try {
			
			Aluno aluno = alunoForm.toAluno();
			aluno.setCadastradoEm(LocalDate.now());
			
			alunoService.save(aluno);
			
			aluno.setPathFoto(RELATIVE_PATH_SHARED_FOLDER+"foto-" + aluno.getId()+".png");
			
			alunoService.save(aluno);
			
	        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(alunoForm.getFoto().replaceAll("data:image/.+;base64,", ""));
	        
	        File foto1 = new File(ABSOLUTE_PATH_SHARED_FOLDER+"foto-" + aluno.getId() +".png");
	        
	        BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
	        ImageIO.write(bfi , "png", foto1);
	        bfi.flush();
	        
			Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
			
			model.addAttribute("aluno", aluno);
			model.addAttribute("veiculos", veiculos);
			model.addAttribute("processo", new Processo());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "processo-form";
	}
	
	@PutMapping("/aluno")
	@Transactional
	public String atualizarAluno(@Valid Aluno aluno) {
		
		alunoService.save(aluno);
		
		return "processo-form";
	}
	
	@RequestMapping(value="/aluno/delete/{id}", method=RequestMethod.GET)
	@Transactional
	public String removerAluno(@PathVariable("id") Long id) {
		
		Aluno aluno = alunoService.get(id);
		
		alunoService.delete(aluno);
		
		return "lista-aluno";
	}
	
	@RequestMapping(value="/aluno/new", method=RequestMethod.GET)
	public String newAluno(Model model) {
		
		model.addAttribute("aluno", new AlunoForm());
		
		return "aluno-form";
	}
	
	@RequestMapping(value="/aluno/edit/{id}", method=RequestMethod.GET)
	public String editAluno(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		AlunoForm alunoForm = new AlunoForm();
		alunoForm.toForm(aluno);
		
		model.addAttribute("aluno", alunoForm);
		
		return "aluno-form";
	}
	
	@GetMapping("/aluno/processos/{id}")
	public String processos(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Collection<Processo> processos = processoService.find(aluno);
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("processos", processos);
		
		return "processo-lista";
	}
	
	@RequestMapping(value="/aluno/{id}/processos/new", method=RequestMethod.GET)
	public String newProcesso(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Collection<Veiculo> veiculos = (Collection<Veiculo>) veiculoService.findAll();
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("veiculos", veiculos);
		model.addAttribute("processo", new Processo());
		
		return "processo-form";
	}
	
	@PostMapping("/aluno/{id}/processos/")
	@Transactional
	public String cadastrarProcesso(@PathVariable("id") Long id, @ModelAttribute("processo") @Valid Processo processo, BindingResult bindResult, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		processo.setDataInicio(LocalDate.now());
		processo.setAluno(aluno);
		
		processoService.save(processo);
		
		Collection<Processo> processos = processoService.find(aluno);
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("processos", processos);
		
		return "processo-lista";
	}
}
