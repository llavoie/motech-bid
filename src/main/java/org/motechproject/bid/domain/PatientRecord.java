package org.motechproject.bid.domain;

import org.joda.time.format.DateTimeFormat;
import org.motechproject.commons.date.util.DateUtil;

/**
 *  Represents one patient record in the UI
 */
public class PatientRecord {

    private String externalId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mothersName;
    private String dateOfBirth;
    private String gender;
    private String village;

    public PatientRecord(Patient p) {
        this.externalId = p.getExternalId();
        this.firstName = p.getFirstName();
        this.middleName = p.getMiddleName();
        this.lastName = p.getLastName();
        this.mothersName = p.getMothersName();
        this.dateOfBirth = DateTimeFormat.forPattern("Y-MM-dd hh:mm:ss").print(DateUtil.setTimeZone(
                p.getDateOfBirth()));
        this.gender = p.getGender().toString();
        this.village = p.getVillage();
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

}