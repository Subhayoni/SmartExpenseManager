package persistence;

import model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // hardcoded for now, can make this configurable later
    private static final String FILE_PATH = "data/transactions.dat";

    public static void saveData(List<Transaction> txnList) {
        // make sure data/ folder exists
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {

            oos.writeObject(txnList);
            System.out.println("DEBUG: data saved to " + FILE_PATH);

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}