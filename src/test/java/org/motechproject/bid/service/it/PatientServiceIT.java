package org.motechproject.bid.service.it;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTime;

import org.motechproject.bid.domain.Patient;
import org.motechproject.bid.repository.PatientDataService;
import org.motechproject.bid.service.PatientService;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.motechproject.testing.osgi.BasePaxIT;
import org.ops4j.pax.exam.spi.reactors.PerSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class PatientServiceIT extends BasePaxIT {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private PatientService patientService;
    @Inject
    private PatientDataService patientDataService;

    @Before
    public void setUp() {
        patientDataService.deleteAll();
    }

    @Test
    public void testPatientService() throws Exception {

        logger.info("testPatientService");

        Patient firstPatient = patientDataService.create(new Patient("123", "Marge", "Simpson", new DateTime()));
        Patient secondPatient = patientDataService.create(new Patient("124", "Homer", "Simpson", new DateTime()));

        logger.info("Created Patient id {}", patientDataService.getDetachedField(firstPatient, "id"));
        logger.info("Created Patient id {}", patientDataService.getDetachedField(secondPatient, "id"));

        Patient patient = patientService.findPatientsByFirstName(firstPatient.getFirstName()).get(0);
        logger.info("Found Patient id {} : {}", patientDataService.getDetachedField(patient, "id"), patient.toString());
        assertEquals(firstPatient, patient);

        List<Patient> patients = patientService.getPatients();
        assertTrue(patients.contains(firstPatient));
        assertTrue(patients.contains(secondPatient));

        patientService.delete(firstPatient);
        patients = patientService.findPatientsByFirstName(firstPatient.getFirstName());
        assertTrue(patients.isEmpty());
    }

    @After
    public void tearDown() {
        patientDataService.deleteAll();
    }
}
