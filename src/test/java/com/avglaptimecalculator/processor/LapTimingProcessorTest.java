package com.avglaptimecalculator.processor;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class LapTimingProcessorTest {
    String outputFileLocation = "output.csv";
    String inputFileLocation = "src/main/resources/files/defaultDriverLapInfo.csv";

    private LapTimingProcessor lapTimingProcessor;

    @Test
    public void shouldBeAbleToTransformDataIntoAFile() throws IOException {
        lapTimingProcessor = new LapTimingProcessor();
        boolean result = lapTimingProcessor.findFastestDrivers(inputFileLocation);
        assertTrue(result);
        assertTrue(validateFileExists());
    }

    private boolean validateFileExists() throws IOException {
        Path path = Path.of(outputFileLocation);
        if (Files.exists(path)) {
            return true;
        }
        return false;
    }
}