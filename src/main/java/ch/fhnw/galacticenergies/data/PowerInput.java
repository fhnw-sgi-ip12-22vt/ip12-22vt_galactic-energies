package ch.fhnw.galacticenergies.data;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class PowerInput {
    public static List readCSV(){
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(System.getProperty("user.dir")+"/Power.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(scanner.nextLine());
            }
        }catch(Exception e){
            System.err.println("Exception: " + e);
        }
        List<List> allItems = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            List<String> items = Arrays.asList(records.get(i).split(","));
            allItems.add(items);
        }
        return allItems;
    }
}


