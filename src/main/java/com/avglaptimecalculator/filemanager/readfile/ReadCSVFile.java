package com.avglaptimecalculator.filemanager.readfile;

import com.avglaptimecalculator.dto.Driver;
import com.avglaptimecalculator.exceptions.InvalidFileDataException;
import com.avglaptimecalculator.exceptions.InvalidFileLocationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ReadCSVFile {

    /**
     * Read the driver list from the CSV filepath
     *
     * @param fileLocation
     * @return List<Driver>
     * @throws InvalidFileLocationException
     */
    public List<Driver> readDriversList(String fileLocation) throws InvalidFileLocationException, InvalidFileDataException, IOException {

        Path path = verifyFilePath(fileLocation);

        List<Driver> driverList = Files.lines(path)
                .map(ReadCSVFile::mapCSVLinesToDriver)
                .collect(Collectors.toList());

        if (driverList.isEmpty()) {
            throw new InvalidFileDataException("No records found in the file");
        }

        return driverList;
    }

    /**
     * Method to verify the file path passed as argument
     *
     * @param fileLocation
     * @return Path if valid
     * @throws InvalidFileLocationException
     */
    private Path verifyFilePath(String fileLocation) throws InvalidFileLocationException {
        String PROGRAM_DIRECTORY = getResourceDirectory();
        fileLocation = (fileLocation != null && !fileLocation.isEmpty()) ? fileLocation : PROGRAM_DIRECTORY + "FindBestF1Drivers/src/main/resources/inputFiles/defaultDriverLapInfo.csv";
        Path path = Path.of(fileLocation);
        if (!Files.exists(path)) {
            throw new InvalidFileLocationException("File not found in the location: " + fileLocation);
        }
        return path;
    }

    /**
     * Map lines from CSV to Driver model
     *
     * @param line
     * @return Driver model
     * @throws InvalidFileDataException if the data is not in expected format
     */
    private static Driver mapCSVLinesToDriver(String line) throws InvalidFileDataException {
        String[] elements = line.split(",");
        String driverName = elements[0];
        try {
            double lap = Double.parseDouble(elements[1]);
            return (new Driver(driverName, lap));
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidFileDataException("Please specify the data in correct format: " + line);
        }
    }

    /**
     * Get Project directory location
     *
     * @return projectPath as String
     */
    private String getResourceDirectory() {
        String projectPath;
        try {
            projectPath = System.getProperty("user.dir");
            //Find the last ! and cut it off at that location. If this isn't being run from a jar, there is no !, so it'll cause an exception, which is fine.
            try {
                projectPath = projectPath.substring(0, projectPath.lastIndexOf('!'));
            } catch (Exception e) {
            }

            //Find the last / and cut it off at that location.
            projectPath = projectPath.substring(0, projectPath.lastIndexOf('/') + 1);
            //If it starts with /, cut it off.
            if (projectPath.startsWith("/"))
                projectPath = projectPath.substring(1, projectPath.length());
            //If it starts with file:/, cut that off, too.
            if (projectPath.startsWith("file:/"))
                projectPath = projectPath.substring(6, projectPath.length());
        } catch (Exception e) {
            projectPath = ""; //Current working directory instead.
        }
        projectPath = "/" + projectPath;
        return projectPath;
    }
}
