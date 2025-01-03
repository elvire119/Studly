package com.isi.etudiantservice.controller;

import com.isi.etudiantservice.dto.EtudiantDto;
import com.isi.etudiantservice.entities.EtudiantEntity;
import com.isi.etudiantservice.services.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    public ResponseEntity<EtudiantDto> createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        EtudiantEntity etudiant = new EtudiantEntity();
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());

        EtudiantEntity savedEtudiant = etudiantService.save(etudiant);

        EtudiantDto responseDto = new EtudiantDto();
        responseDto.setNom(savedEtudiant.getNom());
        responseDto.setPrenom(savedEtudiant.getPrenom());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EtudiantDto>> getAllEtudiants() {
        List<EtudiantEntity> etudiants = etudiantService.findAll();

        List<EtudiantDto> dtos = etudiants.stream().map(etudiant -> {
            EtudiantDto dto = new EtudiantDto();
            dto.setNom(etudiant.getNom());
            dto.setPrenom(etudiant.getPrenom());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDto> getEtudiantById(@PathVariable Long id) {
        EtudiantEntity etudiant = etudiantService.findById(id);

        EtudiantDto dto = new EtudiantDto();
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDto> updateEtudiant(@PathVariable Long id, @RequestBody EtudiantDto etudiantDto) {
        EtudiantEntity etudiant = new EtudiantEntity();
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());

        etudiantService.updateEtudiant(id, etudiant);

        EtudiantEntity updatedEtudiant = etudiantService.findById(id);

        EtudiantDto responseDto = new EtudiantDto();
        responseDto.setNom(updatedEtudiant.getNom());
        responseDto.setPrenom(updatedEtudiant.getPrenom());

        return ResponseEntity.ok(responseDto);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}