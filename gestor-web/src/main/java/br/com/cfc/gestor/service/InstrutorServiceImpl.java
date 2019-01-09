package br.com.cfc.gestor.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.cfc.gestor.model.Instrutor;
import br.com.cfc.gestor.repository.InstrutorRepository;

@Service
public class InstrutorServiceImpl implements InstrutorService {

	@Resource
	private InstrutorRepository instrutorRepository;
	
	@Override
	public Iterable<Instrutor> findAll() {
		return instrutorRepository.findAll();
	}

	@Override
	public Instrutor get(Long instrutorId) {
		
		Optional<Instrutor> instrutor = instrutorRepository.findById(instrutorId);
		
		if(instrutor.isPresent()) {
			return instrutor.get();
		}
		
		return null;
	}

	@Override
	public void save(Instrutor instrutor) {
		instrutorRepository.save(instrutor);
	}
}
