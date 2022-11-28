package ch.fhnw.galacticenergies.data;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PowerInput {
    public static void main(String[] args) throws FileNotFoundException {
        readCSV();
    }
    public static void readCSV() throws FileNotFoundException {
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/Power.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine());
            }

        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));

        }
    }
}


