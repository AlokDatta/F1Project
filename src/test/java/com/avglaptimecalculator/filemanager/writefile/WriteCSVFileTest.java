package com.avglaptimecalculator.filemanager.writefile;

import com.avglaptimecalculator.dto.Driver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteCSVFileTest {
    private List<Driver> driverList;

    @Test
    public void shouldBeAbleToLoadDriversToFile() {
        driverList = setDriverLapsMockData();
        WriteCSVFile writeCSVFile = new WriteCSVFile();
        boolean result = writeCSVFile.writeTopThreeDriversToCSV(driverList);
        assertTrue(result);
    }

    @Test
    public void shouldBeToSendNullWhenNoDrivers() {
        WriteCSVFile writeCSVFile = new WriteCSVFile();
        boolean result = writeCSVFile.writeTopThreeDriversToCSV(driverList);
        assertFalse(result);
    }

    private List<Driver> setDriverLapsMockData() {

        List<Driver> driverList = new ArrayList<>();
        driverList.add(new Driver("Alonzo", 4.00));
        driverList.add(new Driver("Verstrappen", 4.05));
        driverList.add(new Driver("Ocon", 4.10));
        driverList.add(new Driver("Hamilton", 4.15));
        driverList.add(new Driver("Raikkonen", 4.20));
        driverList.add(new Driver("Ricciardo", 4.25));
        driverList.add(new Driver("Leclerc", 4.30));
        driverList.add(new Driver("Mazepin", 4.35));
        driverList.add(new Driver("Carlos", 4.40));
        return driverList;
    }
}
