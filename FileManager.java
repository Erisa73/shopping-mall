package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_DIR = "data/";
    private static final String PRODUCTS_FILE = DATA_DIR + "products.txt";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String ORDERS_FILE = DATA_DIR + "orders.txt";
    
    // Initialize data directory and files
    public static void initialize() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        
        createFileIfNotExists(PRODUCTS_FILE);
        createFileIfNotExists(USERS_FILE);
        createFileIfNotExists(ORDERS_FILE);
    }
    
    private static void createFileIfNotExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + filename);
                e.printStackTrace();
            }
        }
    }
    
    public static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            e.printStackTrace();
        }
        
        return lines;
    }
    
    // Write lines to a file 
    public static void writeLines(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
            e.printStackTrace();
        }
    }
    
    // Append a line to a file
    public static void appendLine(String filename, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error appending to file: " + filename);
            e.printStackTrace();
        }
    }
    
    // Get file paths
    public static String getProductsFile() {
        return PRODUCTS_FILE;
    }
    
    public static String getUsersFile() {
        return USERS_FILE;
    }
    
    public static String getOrdersFile() {
        return ORDERS_FILE;
    }
}
