package br.com.cfc.gestor.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class AlunoTest {
	
	@Test
	public void deveComporMatriculaCorretamente() {
		
		String matriculaEsperada = "1/" + LocalDate.now().getYear();
		
		Aluno aluno = new Aluno();
		aluno.setId(1l);
		aluno.setCadastradoEm(LocalDate.now());
		
		assertEquals(matriculaEsperada, aluno.getMatricula());
	}
	
	@Test
	public void naoDeveComporMatriculaQuandoFaltarAlgumParametro() {
		Aluno aluno = new Aluno();
		assertEquals("", aluno.getMatricula());
	}

	@Test
	public void deveGerarIdentificadorCorretamente() {
		
		String valorEsperado = "088_470_917_56_1_2018";
		
		Aluno aluno = new Aluno();
		aluno.setCpf("088.470.917-56");
		aluno.setId(1l);
		aluno.setCadastradoEm(LocalDate.now());
		
		assertEquals(valorEsperado, aluno.getIdentificador());
	}
	
	@Test
	public void deveGerarIdentificadorCorretamenteQuandoNaoExistirCPF() {
		
		String valorEsperado = "SEM_CPF_1_2018";
		
		Aluno aluno = new Aluno();
		aluno.setId(1l);
		aluno.setCadastradoEm(LocalDate.now());
		
		assertEquals(valorEsperado, aluno.getIdentificador());
	}
	
	@Test
	public void deveGerarIdentificadorCorretamenteQuandoNaoExistirMatricula() {
		
		String valorEsperado = "088_470_917_56_SEM_MATRICULA";
		
		Aluno aluno = new Aluno();
		aluno.setCpf("088.470.917-56");
		
		assertEquals(valorEsperado, aluno.getIdentificador());
	}
	
	@Test
	public void deveGerarIdentificadorPadraoQuandoNaoExistirMatriculaCPF() {
		
		String valorEsperado = "SEM_CPF_SEM_MATRICULA";
		
		Aluno aluno = new Aluno();
		
		assertEquals(valorEsperado, aluno.getIdentificador());
	}
}

