package com.isi.etudiantservice.repository;

import com.isi.etudiantservice.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity, Long> {


}
