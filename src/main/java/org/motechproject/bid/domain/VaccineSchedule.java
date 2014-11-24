package org.motechproject.bid.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Vaccine schedule info for the grid UI
 */
public class VaccineSchedule implements Serializable {

    private static final long serialVersionUID = -5371928597516312539L;

    private final List<Vaccine> rows;

    public VaccineSchedule(List<Vaccine> vaccines) {
        List<Vaccine> records = new ArrayList<>();

        records.add(new Vaccine("BCG", "BCG 1", null, null, null, null, null, null));
        records.add(new Vaccine("Hepatitis B", "HepB 1", null, null, null, null, null, null));
        records.add(new Vaccine("Polio", "OPV 1", "OPV 2", "OPV 3", "OPV 4", null, null, null));
        records.add(new Vaccine("DTP", null, "DTP 1", "DTP 2", "DPT 3", null, null, null));
        records.add(new Vaccine("Hib", null, "Hib 1", "Hib 2", "Hib 3", null, null, null));

        // todo, add the rest

        rows = records;
    }

    public List<Vaccine> getRows() {
        return rows;
    }

}
