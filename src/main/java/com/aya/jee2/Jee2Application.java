package com.aya.jee2;

import com.aya.jee2.entities.*;
import com.aya.jee2.repository.*;
import com.aya.jee2.service.IHospitalService;
import com.aya.jee2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Jee2Application{

    private ProductRepository productRepository;
    public static void main(String[] args) {

        SpringApplication.run(Jee2Application.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args ->{
            User u = new User();
            u.setUserName("user");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u2 = new User();
            u2.setUserName("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            Stream.of("STUDENT", "USER", "ADMIN").forEach(r -> {
                Role role1 = new Role();
                role1.setRoleName(r);
                userService.addNewRole(role1);

            });

            userService.addRoleToUser("user", "STUDENT");
            userService.addRoleToUser("user", "USER");
            userService.addRoleToUser("admin", "USER");
            userService.addRoleToUser("admin", "ADMIN");

            try{
                User user=userService.authentificate("user","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUserName());
                System.out.println("Role ==> ");
                user.getRoles().forEach(r->{
                    System.out.println(r.toString());
                });
            }catch(Exception e){
                e.printStackTrace();

            }
        };
    }
    /*CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository,
                            ConsultationRepository consultationRepository
                            ){
        return args -> {
            Stream.of("Aya","Sara","Yahya").forEach(name->{
                Patient patient=new Patient();
                patient.setName(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                hospitalService.savePatient(patient);
            });
            Stream.of("Saad","Ghita","Marwa").
                    forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        hospitalService.saveMedecin(medecin);
                    });
            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1=patientRepository.findByName("Aya");
            Medecin medecin=medecinRepository.findByNom("Saad");

            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            RendezVous saveDRDV=hospitalService.saveRDV(rendezVous);
            System.out.println(saveDRDV.getId());

            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);

            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation ...");
            hospitalService.saveConsultation(consultation);
        };
    }*/

}
