package com.isi.classeservice.repository;

import com.isi.classeservice.entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
}

