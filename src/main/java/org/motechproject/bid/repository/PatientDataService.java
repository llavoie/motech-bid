package org.motechproject.bid.repository;

import org.motechproject.bid.domain.Patient;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

import java.util.List;

public interface PatientDataService extends MotechDataService<Patient> {
    @Lookup
    Patient findByExternalId(@LookupField(name = "externalId") String externalId);

    @Lookup
    List<Patient> findByFirstName(@LookupField(name = "firstName") String firstName);

    @Lookup
    List<Patient> findByLastName(@LookupField(name = "lastName") String lastName);
}
