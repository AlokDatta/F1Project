package com.avglaptimecalculator.filemanager.readfile;

import com.avglaptimecalculator.dto.Driver;
import com.avglaptimecalculator.exceptions.InvalidFileLocationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadCSVFileTest {

    @Test
    public void shouldBeAbleToReadDefaultDataFileWhenBlankPathSent() throws IOException {
        ReadCSVFile readCSVFile = new ReadCSVFile();
        final List<Driver> driverList = readCSVFile.readDriversList("");
        assertTrue(driverList.size() > 0);
    }

    @Test
    public void shouldBeAbleToExtractGivenFile() throws IOException {
        ReadCSVFile readCSVFile = new ReadCSVFile();
        final List<Driver> formulaOneDriverList = readCSVFile.readDriversList("src/main/resources/inputFiles/defaultDriverLapInfo.csv");
        assertTrue(formulaOneDriverList.size() > 0);
    }

    @Test
    public void shouldThrowIOExceptionIfFileNotFound() throws IOException {
        ReadCSVFile readCSVFile = new ReadCSVFile();
        InvalidFileLocationException exception = assertThrows(
                InvalidFileLocationException.class,
                () -> readCSVFile.readDriversList("wrogfilelocation"));
        assertTrue(exception.getMessage().contains("File not found in the location"));
    }
}
