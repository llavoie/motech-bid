package org.motechproject.bid.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Patient record collection for the grid UI
 */
public class PatientRecords implements Serializable {

    private static final long serialVersionUID = -5371928597516312539L;

    private final List<PatientRecord> rows;

    public PatientRecords(List<Patient> patients) {
        List<PatientRecord> records = new ArrayList<>();
        for (Patient p : patients) {
            records.add(new PatientRecord(p));
        }

        rows = records;
    }

    public List<PatientRecord> getRows() {
        return rows;
    }

}
