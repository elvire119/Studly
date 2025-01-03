package com.isi.matiereservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matieres")
public class MatiereEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


}
