package org.motechproject.bid.domain;

import org.joda.time.format.DateTimeFormat;
import org.motechproject.commons.date.util.DateUtil;

/**
 *  Represents one vaccine row in the vax schedule grid UI
 */
public class Vaccine {

    private String vaccineName;
    private String dueAtBirth;
    private String dueAt1Month;
    private String dueAt2Months;
    private String dueAt4Months;
    private String dueAt6Months;
    private String dueAt9Months;
    private String dueAt12Months;

    public Vaccine(String vaccineName, String dueAtBirth, String dueAt1Month, String dueAt2Months,
        String dueAt4Months, String dueAt6Months, String dueAt9Months, String dueAt12Months) {
        this.vaccineName = vaccineName;
        this.dueAtBirth = dueAtBirth;
        this.dueAt1Month = dueAt1Month;
        this.dueAt2Months = dueAt2Months;
        this.dueAt4Months = dueAt4Months;
        this.dueAt6Months = dueAt6Months;
        this.dueAt9Months = dueAt9Months;
        this.dueAt12Months = dueAt12Months;
    }


}