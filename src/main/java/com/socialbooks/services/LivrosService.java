package com.socialbooks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.socialbooks.domain.Comentario;
import com.socialbooks.domain.Livro;
import com.socialbooks.repository.ComentariosRepository;
import com.socialbooks.repository.LivrosRepository;
import com.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livroRepository;
	
	@Autowired
	private ComentariosRepository comentarioRepository;
	
	public List<Livro> listar() {
		return livroRepository.findAll();
	}
	
	public Optional<Livro> buscar(Long id) {
		
		Optional<Livro> livro = livroRepository.findById(id);
		
		if (livro == null) {
			throw new LivroNaoEncontradoException("Livro não encontrado !");
		}
		
		return livro;
	}

	public Livro salvar(Livro livro) {
		livro.setId(null);
		return livroRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livroRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não encontrado !");
		}
	}
	
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livroRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro) {
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Optional<Livro> livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentarioRepository.save(comentario); 
	}
	
	public List<Comentario> listarComentarios(Long livroId) {
		Object livro = buscar(livroId);
		return ((Livro) livro).getComentarios();
	}
	
}
