package com.aya.jee2.service;

import com.aya.jee2.entities.Consultation;
import com.aya.jee2.entities.Medecin;
import com.aya.jee2.entities.Patient;
import com.aya.jee2.entities.RendezVous;

public interface IHospitalService {

    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
