package com.aya.jee2.repository;

import com.aya.jee2.entities.Consultation;
import com.aya.jee2.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
