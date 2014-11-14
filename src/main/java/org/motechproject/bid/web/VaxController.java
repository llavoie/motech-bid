package org.motechproject.bid.web;

import org.motechproject.bid.domain.Patient;
import org.motechproject.bid.service.PatientService;
import org.motechproject.bid.domain.PatientRecords;
import org.motechproject.scheduletracking.service.ScheduleTrackingService;
import org.motechproject.scheduletracking.service.EnrollmentRequest;

import org.motechproject.scheduletracking.domain.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.motechproject.commons.date.model.Time;
import java.util.List;
import java.util.HashMap;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;

@Controller
public class VaxController {

    private PatientService  patientService;
    private ScheduleTrackingService scheduleTrackingService;
    private String scheduleName;

    @Autowired
    public VaxController(PatientService patientService, ScheduleTrackingService scheduleTrackingService) {
        this.patientService = patientService;
        this.scheduleTrackingService = scheduleTrackingService;
        scheduleName = "Vaccination Schedule";
        createVaccineSchedule();
    }

    @RequestMapping(value = "/vax", method = RequestMethod.GET)
    @ResponseBody
    public PatientRecords getPatients() {
        final List<Patient> patientEntities = patientService.getPatients();

        // temp hackiness to work around lack of MDS CRUD events; will move this to a better home
        Runnable r = new Runnable() {
            public void run() {
                for(Patient p : patientEntities) {
                    if(scheduleTrackingService.getEnrollment(p.getExternalId(), scheduleName) == null) {
                        scheduleTrackingService.enroll(
                            new EnrollmentRequest().setExternalId(p.getExternalId()).
                            setScheduleName(scheduleName).setPreferredAlertTime(new Time(8, 10)).
                            setReferenceDate(p.getDateOfBirth().toLocalDate()).setEnrollmentDate(null).
                            setEnrollmentTime(null).setStartingMilestoneName("Rotavirus1").
                            setMetadata(null));
                    }
                }
            }
        };

        Thread t = new Thread(r);
        t.start();

        PatientRecords patients = new PatientRecords(patientEntities);
        return patients;
    }

    private void createVaccineSchedule() {
        Schedule vaxSchedule = scheduleTrackingService.getScheduleByName(scheduleName);
        if(vaxSchedule == null) {
            try {
                URL address = new URL(getClass().getResource("/vax-schedule.json").toString());
                String scheduleJson = IOUtils.toString(address.openStream(), "UTF-8");
                scheduleTrackingService.add(scheduleJson);
            } catch(IOException e) {

            }
        }
    }


}
