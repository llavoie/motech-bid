package org.motechproject.bid.web;

import org.motechproject.bid.domain.Patient;
import org.motechproject.bid.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VaxController {

    private PatientService  patientService;

    @Autowired
    public VaxController(PatientService patientService) {
        this.patientService = patientService;
    }

    public String getPatientName() {
        List<Patient> patients = patientService.getPatients();
        if (patients.size() > 0) {
            return patients.get(0).getFirstName();
        }
        return "no patients";
    }

    @RequestMapping(value = "/vax", method = RequestMethod.GET)
    @ResponseBody
    public String getPatients() {
        return patientService.getPatients();
    }

}