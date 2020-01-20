package com.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooks.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
