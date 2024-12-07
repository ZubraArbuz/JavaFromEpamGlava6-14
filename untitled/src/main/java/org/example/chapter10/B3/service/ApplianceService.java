package org.example.chapter10.B3.service;

import org.example.chapter10.B3.appliances.ElectricalAppliance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ApplianceService {
    private List<ElectricalAppliance> appliances;

    public ApplianceService() {
        appliances = new ArrayList<>();
    }

    public void addAppliance(ElectricalAppliance appliance) {
        appliances.add(appliance);
    }

    public List<ElectricalAppliance> getAllAppliances() {
        return appliances;
    }

    public int calculateTotalPower() {
        return appliances.stream()
                .filter(ElectricalAppliance::isOn)
                .mapToInt(ElectricalAppliance::getPower)
                .sum();
    }

    public List<ElectricalAppliance> sortByPower() {
        appliances.sort(Comparator.comparingInt(ElectricalAppliance::getPower));
        return appliances;
    }

    public List<ElectricalAppliance> findByPowerRange(int minPower, int maxPower) {
        List<ElectricalAppliance> result = new ArrayList<>();
        for (ElectricalAppliance appliance : appliances) {
            if (appliance.getPower() >= minPower && appliance.getPower() <= maxPower) {
                result.add(appliance);
            }
        }
        return result;
    }
}