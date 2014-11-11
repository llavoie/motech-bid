package org.motechproject.bid.service;

import java.util.List;
import org.joda.time.DateTime;

import org.motechproject.bid.domain.Patient;

/**
 * Service interface for CRUD on simple repository Patients.
 */
public interface PatientService {

    void create(String externalId, String firstName, String lastName, DateTime dateOfBirth);

    void add(Patient patient);

    List<Patient> findPatientsByFirstName(String firstName);

    List<Patient> getPatients();

    void delete(Patient Patient);

    void update(Patient Patient);
}
