package com.isi.classeservice.service;

import com.isi.classeservice.entities.Classe;
import com.isi.classeservice.repository.ClasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe createClasse(Classe classe) {
        return classeRepository.save(classe);
    }



    public boolean deleteClasse(Long id) {
        if (classeRepository.existsById(id)) {
            classeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
