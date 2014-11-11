package org.motechproject.bid.domain;

import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.UIDisplayable;
import javax.jdo.annotations.Unique;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Patient {

    @Field(required = true)
    @UIDisplayable(position = 0)
    @Unique
    private String externalId;

    @Field(required = true)
    @UIDisplayable(position = 1)
    private String firstName;

    @Field
    @UIDisplayable(position = 2)
    private String middleName;

    @Field(required = true)
    @UIDisplayable(position = 3)
    private String lastName;

    @Field
    @UIDisplayable(position = 4)
    private String mothersName;

    @Field(required = true)
    @UIDisplayable(position = 5)
    private DateTime dateOfBirth;

    @Field
    @UIDisplayable(position = 6)
    private Gender gender;

    @Field
    @UIDisplayable(position = 7)
    private String village;

    public Patient(String externalId, String firstName, String lastName, DateTime dateOfBirth) {
        this.externalId = externalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getExternalId() {
        return externalId;
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

    public DateTime getDateOfBirth() {
        return new DateTime(dateOfBirth);
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Patient)) {
            return false;
        }

        Patient other = (Patient) o;

        return equalNameData(other) && Objects.equals(dateOfBirth, other.dateOfBirth)
                && Objects.equals(village, other.village) && Objects.equals(gender, other.gender)
                && Objects.equals(externalId, other.externalId);
    }

    public boolean equalNameData(Patient other) {
        return Objects.equals(firstName, other.firstName) && Objects.equals(middleName, other.middleName)
                && Objects.equals(lastName, other.lastName);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + ObjectUtils.hashCode(firstName);
        hash = hash * 31 + ObjectUtils.hashCode(middleName);
        hash = hash * 31 + ObjectUtils.hashCode(lastName);
        hash = hash * 31 + ObjectUtils.hashCode(village);
        hash = hash * 31 + ObjectUtils.hashCode(dateOfBirth);
        hash = hash * 31 + ObjectUtils.hashCode(gender);
        hash = hash * 31 + ObjectUtils.hashCode(externalId);
        return hash;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName +
                '}';
    }
}