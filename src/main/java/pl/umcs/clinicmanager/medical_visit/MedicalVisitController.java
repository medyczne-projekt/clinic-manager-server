package pl.umcs.clinicmanager.medical_visit;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.clinicmanager.medical_visit.domain.MedicalVisit;
import pl.umcs.clinicmanager.medical_visit.dto.MedicalVisitDTO;
import pl.umcs.clinicmanager.receipt.dto.ReceiptDTO;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class MedicalVisitController {

    private final MedicalVisitService medicalVisitService;


    @PostMapping("/visit/add")
    ResponseEntity<MedicalVisit> addMedicalVisit(@RequestBody MedicalVisitDTO newMedicalVisit) {
        final MedicalVisit addedVisit;
        try {
            addedVisit = medicalVisitService.addMedicalVisit(newMedicalVisit);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addedVisit, HttpStatus.CREATED);
    }

    @GetMapping("/visit/patient/{patientId}")
    ResponseEntity<List<MedicalVisitDTO>> getAllVisitsByPatientId(@PathVariable Long patientId) {
        final List<MedicalVisitDTO> patientVisits;
        try {
            patientVisits = medicalVisitService.getAllVisitsByPatientId(patientId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientVisits, HttpStatus.FOUND);
    }

    @GetMapping("/visit/{id}")
    ResponseEntity<MedicalVisitDTO> getVisitById(@PathVariable Long id) {
        final MedicalVisitDTO visit;
        try {
            visit = medicalVisitService.getVisitById(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visit, HttpStatus.FOUND);
    }



    @GetMapping("/visit/doctor/{doctorId}")
    ResponseEntity<List<MedicalVisitDTO>> getAllVisitsByDoctorId(@PathVariable Long doctorId) {
        final List<MedicalVisitDTO> doctorVisits;
        try {
            doctorVisits = medicalVisitService.getAllVisitsByDoctorId(doctorId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctorVisits, HttpStatus.FOUND);
    }

    @DeleteMapping("/visit/{id}")
    ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        try {
            medicalVisitService.deleteVisit(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
