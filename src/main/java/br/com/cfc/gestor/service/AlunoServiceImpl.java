package br.com.cfc.gestor.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService{

	@Resource
	private AlunoRepository alunoRepository;
	
	@Override
	public Iterable<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	@Override
	public void save(Aluno aluno) {
		alunoRepository.save(aluno);
	}

	@Override
	public void delete(Aluno aluno) {
		alunoRepository.delete(aluno);
	}
	

	@Override
	public Aluno get(Long id) {
		
		Optional<Aluno> retorno = alunoRepository.findById(id);
		
		if(retorno.isPresent()) {
			return retorno.get();
		}
		
		return null;
	}

}
