package br.com.cfc.gestor.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
import br.com.cfc.gestor.model.AulaProcessoVeiculo;
import br.com.cfc.gestor.model.Documento;
import br.com.cfc.gestor.model.Processo;
import br.com.cfc.gestor.model.Veiculo;
import br.com.cfc.gestor.service.AlunoService;
import br.com.cfc.gestor.service.AulaProcessoVeiculoService;
import br.com.cfc.gestor.service.DocumentoService;
import br.com.cfc.gestor.service.ProcessoService;
import br.com.cfc.gestor.service.VeiculoService;
import br.com.cfc.gestor.utils.BematechNFiscal;
import br.com.cfc.gestor.utils.MessageContext;

@Controller
public class AlunoController {
	
	private static int currentPage = 1;
	private static int pageSize = 7;
	
	@Value("${server.file.path}")
	private String path;
	
	@Value("${server.relative.path}")
	private String relativePath;

	@Resource
	private MessageContext messageContext;
	
	@Resource
	private AlunoService alunoService;
	
	@Resource
	private VeiculoService veiculoService;
	
	@Resource
	private ProcessoService processoService;
	
	@Resource
	private AulaProcessoVeiculoService aulaProcessoVeiculoService;
	
	@Resource
	private DocumentoService documentoService;
	
	@PostMapping("/aluno/search")
	public String fullSearch(Model model, 
			 @RequestParam("page") Optional<Integer> page, 
			 @RequestParam("size") Optional<Integer> size,
			 @RequestParam("search") Optional<String> search) {
		
		return matriculas(model, page, size, search);
	}
	
	@GetMapping("/aluno")
	public String matriculas(Model model, 
							 @RequestParam("page") Optional<Integer> page, 
							 @RequestParam("size") Optional<Integer> size,
							 @RequestParam("search") Optional<String> search) {
		
		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		
		if(currentPage <= 0) {
			currentPage = 1;
		}
		
		Page<Aluno> alunosPage = alunoService.findPaginated(search, PageRequest.of(currentPage-1, pageSize));
		
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
		
		model.addAttribute("search", search);
		
		return "aluno-lista";
	}
	
	@GetMapping("/aluno/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Aluno matriculas(@PathVariable("id") Long id) {
		
		Aluno aluno = alunoService.get(id);
		
		return aluno;
	}
	
	@GetMapping("/aluno/find/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String consultar(@PathVariable("id") Long id, Model model) {
		
		Aluno aluno = alunoService.get(id);
		
		Iterable<Documento> documentos = documentoService.findByAluno(aluno);
		
		model.addAttribute("aluno", aluno);
		model.addAttribute("documentos", documentos);
		
		return "aluno-info";
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
			
			if(!StringUtils.isEmpty(alunoForm.getFoto())) {
				aluno.setPathFoto(relativePath + "/images/foto-" + aluno.getIdentificador()+".png");
				
				alunoService.save(aluno);
				
				byte[] decodedBytes = DatatypeConverter.parseBase64Binary(alunoForm.getFoto().replaceAll("data:image/.+;base64,", ""));
				
				File foto1 = new File(path + "/images/foto-" + aluno.getIdentificador() +".png");
				
				BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
				ImageIO.write(bfi , "png", foto1);
				bfi.flush();
			}
			
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
		
		return "aluno-lista";
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
	
	@GetMapping(value="/aluno/{id}/comprovante")
	public String gerarComprovante(Model model,
			                       @PathVariable("id") Long id, 
			                       HttpServletResponse response) throws FileNotFoundException, IOException{
		
		Aluno aluno = alunoService.get(id);
		
		if(aluno == null) {
			messageContext.add("Aluno nao encontrado!");
		}
		
		Collection<AulaProcessoVeiculo> aulas = (Collection<AulaProcessoVeiculo>) aulaProcessoVeiculoService.findByAluno(aluno);
		
		if(CollectionUtils.isEmpty(aulas)) {
			messageContext.add("As aulas ainda nao foram agendadas!");
		}
		
		enviarComprovanteParaImpressora(aluno, aulas);
		
		return matriculas(model, Optional.empty(), Optional.empty(), Optional.empty());
	}

	private void enviarComprovanteParaImpressora(Aluno aluno, Collection<AulaProcessoVeiculo> aulas) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		BematechNFiscal cupom = null;
		
		try {
			cupom = BematechNFiscal.Instance;
		}catch(Exception e) {
			messageContext.add("Erro de configuraçao da impressora!");
		}
		
		cupom.ConfiguraModeloImpressora(8);
		cupom.IniciaPorta("USB");
		
		if(cupom.Le_Status() == BematechNFiscal.ERRO_NO_COMANDO) {
			messageContext.add("O sistema não conseguiu se comunicar com a impressora, verifica se o equipamento está ligado!");
			return;
		}
		
		if(cupom.VerificaPapelPresenter() == BematechNFiscal.ERRO_NO_COMANDO) {
			messageContext.add("Impressora sem papel!");
			return;
		}
		cupom.AjustaLarguraPapel(80);
		
		cupom.FormataTX("CFC Marquinho de Jesus Ltda\r\n", 2, 0, 0, 0, 0);
		cupom.FormataTX("---------------------------\r\n", 2, 0, 0, 0, 0);
		cupom.FormataTX("Aluno: " + aluno.getNome() + "\r\n", 2, 0, 0, 0, 0);
		
		boolean first = true;
		
		for(AulaProcessoVeiculo aula : aulas) {
			
			if(first) {
				cupom.FormataTX("Veiculo: " + aula.getVeiculo().toString() + "\r\n", 2, 0, 0, 0, 0);
				cupom.FormataTX("Instrutor: " + aula.getInstrutor().getNome() + "\r\n", 2, 0, 0, 0, 0);
				cupom.FormataTX("===========================\r\n", 2, 0, 0, 0, 0);
				cupom.FormataTX("Controle de aulas:\r\n", 2, 0, 0, 0, 0);
				cupom.FormataTX("---------------------------\r\n", 2, 0, 0, 0, 0);
			}
			
			cupom.FormataTX(aula.getData().format(formatter) + "       []\r\n", 2, 0, 0, 0, 0);
			first = false;
		}
		
		cupom.AcionaGuilhotina(BematechNFiscal.CORTE_PARCIAL);
		cupom.FechaPorta();
	}
}
