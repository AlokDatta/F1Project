package com.avglaptimecalculator.filemanager.writefile;

import com.avglaptimecalculator.dto.Driver;
import com.avglaptimecalculator.exceptions.WritingCSVException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteCSVFile {
    public boolean writeTopThreeDriversToCSV(List<Driver> driverList) {
        if (driverList == null)
            return false;
        Path path = Path.of("output.csv");

        //Will write only 3 records as per the requirement
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (int i = 0; i < 3; i++) {
                String line = (i + 1) + ". " + driverList.get(i).toString() + "\n";
                bufferedWriter.write(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new WritingCSVException("Sorry! Error occurred while creating the output. Please try again.");
        }

        return true;
    }
}
