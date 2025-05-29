package logic;

import проект.*;

import java.io.*;

public class FileHandler {
    public static void saveToFile(String filePath, LinkedList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            students.forEach(s -> writer.println(s.getName() + ";" + s.getFacultyNumber()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile(String filePath, LinkedList<Student> students) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
