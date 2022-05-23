package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvReader {
    private static  final String path = "src/main/resources/ProductsList.csv";

    public static void csvFile() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
//                Accessing values by column index
                String product = csvRecord.get(0);
                String productType = csvRecord.get(1);
                String titleOfProduct = csvRecord.get(2);
                String quantity = csvRecord.get(3);
                String amount = csvRecord.get(4);

                System.out.println("Record N0 - " + csvRecord.getRecordNumber());
                System.out.println("----------------");
                System.out.println("Product " + product);
                System.out.println("Product Type " + productType);
                System.out.println("Product Title " + titleOfProduct);
                System.out.println("Quantity " + quantity);
                System.out.println("Amount " + amount);
                System.out.println("----------------\n\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
