package org.motechproject.bid.web;

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
public class ScheduleGridController {

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    @ResponseBody
    public VaccineSchedule getVaxScheduleGridContents() {

    }
}