package br.com.cfc.gestor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Aluno;
import br.com.cfc.gestor.repository.AlunoRepository;
import br.com.cfc.gestor.utils.MessageContext;

@Service
public class AlunoServiceImpl implements AlunoService{

	@Resource
	private MessageContext messageContext;
	
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

	@Override
	public Page<Aluno> findPaginated(Optional<String> findBy, Pageable pageable) {
		
		int pageSize    = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem   = currentPage*pageSize;
		
		List<Aluno> alunos = null;
		
		if(findBy.isPresent()) {
			alunos = (List<Aluno>) findFullSearch("%" + findBy.get().toString().toLowerCase() + "%");
		}else {
			alunos = (List<Aluno>) findAll();
		}
		
		List<Aluno> list;
		
		if(alunos.size() < startItem) {
			list = new ArrayList<Aluno>();
		}else {
			int toIndex = Math.min(startItem+pageSize, alunos.size());
			list = alunos.subList(startItem, toIndex);
		}
		
		Page<Aluno> alunoPage = new PageImpl<Aluno>(list, PageRequest.of(currentPage, pageSize), alunos.size());
		
		return alunoPage;
	}

	private List<Aluno> findFullSearch(String findBy) {
		return alunoRepository.fullSearch(findBy);
	}

}
