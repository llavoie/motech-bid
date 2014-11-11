package org.motechproject.bid.service.impl;

import org.motechproject.bid.domain.Facility;
import org.motechproject.bid.repository.FacilityDataService;
import org.motechproject.bid.service.FacilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link org.motechproject.bid.service.FacilityService} interface. Uses
 * {@link org.motechproject.bid.repository.FacilityDataService} in order to retrieve and persist
 * persons.
 */
@Service("facilityService")
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDataService facilityDataService;

    @Override
    public void create(String name, String address) {
        facilityDataService.create(new Facility(name, address));
    }

    @Override
    public void add(Facility facility) {
        facilityDataService.create(facility);
    }

    @Override
    public Facility findFacilityByName(String name) {
        return facilityDataService.findByName(name);
    }

    @Override
    public List<Facility> getFacilities() {
        return facilityDataService.retrieveAll();
    }

    @Override
    public void update(Facility facility) {
        facilityDataService.update(facility);
    }

    @Override
    public void delete(Facility facility) {
        facilityDataService.delete(facility);
    }
}
