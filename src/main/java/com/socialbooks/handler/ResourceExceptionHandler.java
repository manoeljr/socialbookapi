package com.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.socialbooks.domain.DetalheErro;
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
	
}
