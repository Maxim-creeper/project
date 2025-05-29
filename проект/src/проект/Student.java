package проект;

import java.util.ArrayList;

public class Student {
    private String name;
    private String facultyNumber;
    private ArrayList<Grade> grades;

    public Student(String name, String facultyNumber) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.grades = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getFacultyNumber() { return facultyNumber; }
    public ArrayList<Grade> getGrades() { return grades; }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (Grade g : grades) {
            sum += g.getGrade();
        }
        return sum / grades.size();
    }

    @Override
    public String toString() {
        return name + " - " + facultyNumber;
    }
}