package com.isi.etudiantservice;

import com.isi.etudiantservice.entities.EtudiantEntity;
import com.isi.etudiantservice.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EtudiantServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(EtudiantServiceApplication.class, args);
    }




}
