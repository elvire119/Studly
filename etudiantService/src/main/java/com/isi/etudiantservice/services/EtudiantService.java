package com.isi.etudiantservice.services;


import com.isi.etudiantservice.repository.EtudiantRepository;
import com.isi.etudiantservice.entities.EtudiantEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository repository;

    public EtudiantService(EtudiantRepository repository) {
        this.repository = repository;
    }

    public List<EtudiantEntity> findAll() {
        return repository.findAll();
    }

    public EtudiantEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Letudiant n'existe pas"));
    }

    public EtudiantEntity save(EtudiantEntity etudiant) {
        return repository.save(etudiant);
    }


    public void updateEtudiant(Long id, EtudiantEntity etudiant) {
        Optional<EtudiantEntity> existingEtudiantOptional = repository.findById(id);
        if (existingEtudiantOptional.isEmpty()) {
            throw new RuntimeException("Etudiant avec lID " + id + " non trouvé");
        }

        EtudiantEntity existingEtudiant = existingEtudiantOptional.get();
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setPrenom(etudiant.getPrenom());

        repository.save(existingEtudiant);
    }



    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
