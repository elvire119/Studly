package com.isi.classeservice.controller;

import com.isi.classeservice.entities.Classe;
import com.isi.classeservice.repository.ClasseRepository;
import com.isi.classeservice.service.ClasseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClasseController {

    private final ClasseService classeService;
    private final ClasseRepository classeRepository;

    public ClasseController(ClasseService classeService, ClasseRepository classeRepository) {
        this.classeService = classeService;
        this.classeRepository = classeRepository;
    }


    @QueryMapping
    public List<Classe> getAllClasses() {
        return classeService.getAllClasses();
    }

    @QueryMapping
    public Classe getClassesById(@Argument Long id) {
        return classeService.getClasseById(id);
    }

    @MutationMapping
    public Classe createClasses(@Argument String name, @Argument String description) {
        Classe classes = new Classe();
        classes.setName(name);
        classes.setDescription(description);
        return classeService.createClasse(classes);
    }

    @MutationMapping
    public Classe updateClasses(@Argument Long id, @Argument String name, @Argument String description) {
        Classe classe = new Classe();
        classe.setId(id);
        classe.setName(name);
        classe.setDescription(description);

        return classeRepository.save(classe);
    }

    @MutationMapping
    public boolean deleteClasses(@Argument Long id) {
        return classeService.deleteClasse(id);
    }
}
