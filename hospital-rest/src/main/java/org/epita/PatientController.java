package org.epita;

import jakarta.transaction.Transactional;
import org.epita.datamodels.Patient;
import org.epita.messages.PatientDTO;
import org.epita.services.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

@RestController
public class PatientController {

    @Autowired
    PatientDAO dao;

    @GetMapping(value="/patients/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatientByID(@PathVariable String id) {
        try {
            Patient patient = dao.get(id);
            System.out.println(patient);
            if (patient == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PostMapping(value = "/patients",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createPatient(@RequestBody PatientDTO patient) {
        try {
            Patient patientModel = new Patient(patient.getId(), patient.getLastname(), patient.getFirstname(), patient.getAddress(), patient.getTelephone(), 123, new Date());
            System.out.println(patientModel);
            dao.create(patientModel);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        String id = String.valueOf(patient.getId());
        URI location = URI.create(id);
        return ResponseEntity.created(location).build();
    }
}
