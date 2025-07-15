package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    private PatientMapper patientMapper;

    public List<PatientResponseDto> getPatients(){
        List<Patient> patient = patientRepository.findAll();

        List<PatientResponseDto> patientResponseDto = patient.stream().
                map(PatientMapper::toDto).toList();

        return patientResponseDto;
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
        return PatientMapper.toDto(patient);
    }


}
