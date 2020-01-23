package com.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialbooks.domain.Autor;
import com.socialbooks.repository.AutorRepository;
import com.socialbooks.services.exceptions.AutorExistenteException;
import com.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar() {
		return autorRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> a = autorRepository.findById(autor.getId());
			if (a != null) {
				throw new AutorExistenteException("O Autor já existe !");
			}
		}
		return autorRepository.save(autor);
	}
	
	public Optional<Autor> buscar(Long id) {
		Optional<Autor> autor = autorRepository.findById(id);
		if (autor == null) {
			throw new AutorNaoEncontradoException("Autor não encontrado !");
		}
		return autor;
	}
	
}
