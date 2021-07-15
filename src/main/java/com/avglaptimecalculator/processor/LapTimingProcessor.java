package com.avglaptimecalculator.processor;

import com.avglaptimecalculator.dto.Driver;
import com.avglaptimecalculator.filemanager.readfile.ReadCSVFile;
import com.avglaptimecalculator.filemanager.writefile.WriteCSVFile;

import java.util.*;
import java.util.stream.Collectors;

public class LapTimingProcessor {
    public boolean findFastestDrivers(String inputFileLocation) {
        boolean returnValue = false;
        ReadCSVFile readCSVFile = new ReadCSVFile();
        WriteCSVFile formulaOneDriverLoad = new WriteCSVFile();

        try {
            List<Driver> driverList = readCSVFile.readDriversList(inputFileLocation);
            if (driverList != null) {
                List driverListWithAverage = getAverageLapTimingForDrivers(driverList);
                formulaOneDriverLoad.writeTopThreeDriversToCSV(driverListWithAverage);
                returnValue = true;
            } else {
                returnValue = false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return returnValue;
    }

    private List<Driver> getAverageLapTimingForDrivers(List<Driver> driverList) {

        List<Driver> driversListWithAverage = new ArrayList<>();

        Map<String, Double> driverAverageLaps = driverList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Driver::getName,
                                TreeMap::new,
                                Collectors.averagingDouble(Driver::getLap))
                );

        for (Map.Entry<String, Double> driverAverageLap : driverAverageLaps.entrySet()) {
            double round = Math.round(driverAverageLap.getValue() * 100.0);
            driversListWithAverage.add(new Driver(driverAverageLap.getKey(), round / 100));
        }

        return driversListWithAverage.stream()
                .sorted(Comparator.comparingDouble(Driver::getLap))
                .collect(Collectors.toList());
    }
}
