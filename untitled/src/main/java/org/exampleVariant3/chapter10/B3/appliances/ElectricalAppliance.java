package org.exampleVariant3.chapter10.B3.appliances;

import java.io.Serializable;

public class ElectricalAppliance implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int power;
    private boolean isOn;
    private transient String someTemporaryField;

    public ElectricalAppliance(String name, int power) {

        this.name = name;
        this.power = power;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public boolean isOn() {
        return isOn;
    }

    public void plugIn() {
        isOn = true;
    }

    public void plugOut() {
        isOn = false;
    }

    @Override
    public String toString() {
        return name + " (мощность: " + power + " Вт, включен: " + (isOn ? "да" : "нет") + ")";
    }


}