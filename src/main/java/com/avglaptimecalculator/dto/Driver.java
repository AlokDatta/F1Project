package com.avglaptimecalculator.dto;

/**
 * POJO to get Driver name and lap timings
 */
public class Driver {
    private String name;
    private double lap;

    public Driver(String name, double lap) {
        this.name = name;
        this.lap = lap;
    }

    public String getName() {
        return name;
    }

    public double getLap() {
        return lap;
    }

    public void setLap(double lap) {
        this.lap = lap;
    }

    @Override
    public String toString() {
        return "Driver: " +
                "Name='" + name + '\'' +
                ", Average lap time=" + lap;
    }
}
