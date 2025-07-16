package com.pm.patient_service.Controller;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
@Tag(name="Patient", description="API used for managing the Patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/getPatient")
    @Operation(summary = "Get Patients")
    private ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> patientDto = patientService.getPatients();
        return ResponseEntity.ok().body(patientDto);
    }

    @PostMapping("/createPatient")
    @Operation(summary = "Create the new patient")
    private ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/updatePatient/{id}")
    @Operation(summary = "Updating the Patient details")
    private ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok().body(patientResponseDto);
    }

    @DeleteMapping("/deletePatient/{id}")
    @Operation(summary = "Deleting the Patient")
    private ResponseEntity<String> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Deleted Successfully");
    }

}
