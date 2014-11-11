package org.motechproject.bid.service.impl;

import org.motechproject.bid.domain.Patient;
import org.motechproject.bid.repository.PatientDataService;
import org.motechproject.bid.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.joda.time.DateTime;

/**
 * Implementation of the {@link org.motechproject.bid.service.PatientService} interface. Uses
 * {@link org.motechproject.bid.repository.PatientDataService} in order to retrieve and persist
 * persons.
 */
@Service("patientService")
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDataService patientDataService;

    @Override
    public void create(String externalId, String firstName, String lastName, DateTime dateOfBirth) {
        patientDataService.create(new Patient(externalId, firstName, lastName, dateOfBirth));
    }

    @Override
    public void add(Patient patient) {
        patientDataService.create(patient);
    }

    @Override
    public List<Patient> findPatientsByFirstName(String name) {
        return patientDataService.findByFirstName(name);
    }

    @Override
    public List<Patient> getPatients() {
        return patientDataService.retrieveAll();
    }

    @Override
    public void update(Patient patient) {
        patientDataService.update(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientDataService.delete(patient);
    }
}
