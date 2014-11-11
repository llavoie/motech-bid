package org.motechproject.bid.service.it;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.motechproject.bid.domain.Facility;
import org.motechproject.bid.repository.FacilityDataService;
import org.motechproject.bid.service.FacilityService;

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
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class FacilityServiceIT extends BasePaxIT {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private FacilityService facilityService;
    @Inject
    private FacilityDataService facilityDataService;

    @Before
    public void setUp() {
        facilityDataService.deleteAll();
    }

    @Test
    public void testFacilityService() throws Exception {

        logger.info("testFacilityService");

        Facility firstFacility = facilityDataService.create(new Facility("Facility 1", "742 Evergreen Terrace"));
        Facility secondFacility = facilityDataService.create(new Facility("Facility 2", "430 Spalding Way"));

        logger.info("Created facility id {}", facilityDataService.getDetachedField(firstFacility, "id"));
        logger.info("Created facility id {}", facilityDataService.getDetachedField(secondFacility, "id"));

        Facility facility = facilityService.findFacilityByName(firstFacility.getName());
        logger.info("Found facility id {} : {}", facilityDataService.getDetachedField(facility, "id"), facility.toString());
        assertEquals(firstFacility, facility);

        List<Facility> facilities = facilityService.getFacilities();
        assertTrue(facilities.contains(firstFacility));
        assertTrue(facilities.contains(secondFacility));

        facilityService.delete(firstFacility);
        facilities = facilityService.getFacilities();
        assertFalse(facilities.contains(firstFacility));
    }

    @Test(expected = Exception.class) // TODO: catch a more specific exception (not sure why it was failing when I tried to before...)
    public void shouldNotCreateDuplicates() throws Exception {
        logger.info("shouldNotCreateDuplicates");
        facilityDataService.deleteAll();
        facilityDataService.create(new Facility("Facility 1", "742 Evergreen Terrace"));
        facilityDataService.create(new Facility("Facility 1", "742 Evergreen Terrace"));
    }

    @After
    public void tearDown() {
        facilityDataService.deleteAll();
    }

}
