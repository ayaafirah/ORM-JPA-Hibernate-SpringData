package com.aya.jee2.repository;

import com.aya.jee2.entities.Medecin;
import com.aya.jee2.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
}
