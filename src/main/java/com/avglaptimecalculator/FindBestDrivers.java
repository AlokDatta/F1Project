package com.avglaptimecalculator;

import com.avglaptimecalculator.processor.LapTimingProcessor;

public class FindBestDrivers {

    public static void main(String[] args) {
        LapTimingProcessor lapTimingProcessor = new LapTimingProcessor();
        String inputFileLocation = "";
        if (args != null && args.length > 0) {
            inputFileLocation = args[0];
        }
        if (!lapTimingProcessor.findFastestDrivers(inputFileLocation)) {
            System.out.println("Sorry! we are unable to process your file at this moment");
        } else {
            System.out.println("Thank you! Data has been processed.");
        }
    }
}
