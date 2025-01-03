package com.isi.etudiantservice.entities;

import com.isi.etudiantservice.dto.Classe;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "etudiants")

public class EtudiantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false )
    private Long Id;

    @Column(nullable = false)
    private String Nom;

    @Column(nullable = false)
    private String Prenom;




    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }


}
