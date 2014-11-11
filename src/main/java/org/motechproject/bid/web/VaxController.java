package org.motechproject.bid.web;

import org.motechproject.bid.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VaxController {

    private PatientService  patientService;

    @Autowired
    public VaxController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/vax", method = RequestMethod.GET)
    @ResponseBody
    public String getDisplay() {
        return "hello";
    }

}