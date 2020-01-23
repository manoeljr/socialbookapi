package com.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.socialbooks.domain.DetalheErro;
import com.socialbooks.services.exceptions.AutorExistenteException;
import com.socialbooks.services.exceptions.AutorNaoEncontradoException;
import com.socialbooks.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handleLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request) {
		DetalheErro erro = new DetalheErro();
		erro.setStatus(404l);
		erro.setTitulo("O Livro não pode ser encontrado");
		erro.setMensagemDesenvolvedor("https://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalheErro> handleAutorExistenteException(AutorExistenteException e, HttpServletRequest request) {
		DetalheErro erro = new DetalheErro();
		erro.setStatus(409l);
		erro.setTitulo("Autor já existente");
		erro.setMensagemDesenvolvedor("https://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handleAutorNaoEncontradoException(AutorNaoEncontradoException e, HttpServletRequest request) {
		DetalheErro erro = new DetalheErro();
		erro.setStatus(409l);
		erro.setTitulo("Autor não encontrado");
		erro.setMensagemDesenvolvedor("https://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalheErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		DetalheErro erro = new DetalheErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição Inválida");
		erro.setMensagemDesenvolvedor("https://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
}
