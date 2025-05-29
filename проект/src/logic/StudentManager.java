package logic;

import проект.*;

public class StudentManager {
    private final LinkedList<Student> students = new LinkedList<>();

    public void addStudent(String name, String number) {
        if (students.find(s -> s.getFacultyNumber().equals(number)) != null)
            throw new IllegalArgumentException("Student with this faculty number already exists.");
        students.add(new Student(name, number));
    }

    public void removeStudent(String number) {
        students.removeIf(s -> s.getFacultyNumber().equals(number));
    }

    public void sortByAverageGrade() {
        students.sort((a, b) -> Double.compare(b.getAverageGrade(), a.getAverageGrade()));
    }

    public void forEach(java.util.function.Consumer<Student> action) {
        students.forEach(action);
    }

    public void saveToFile(String filePath) {
        FileHandler.saveToFile(filePath, students);
    }

    public void loadFromFile(String filePath) {
        FileHandler.loadFromFile(filePath, students);
    }
}