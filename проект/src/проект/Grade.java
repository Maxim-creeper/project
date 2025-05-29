package проект;

public class Grade {
    private Course course;
    private double grade;

    public Grade(Course course, double grade) {
    	
    	
        if (grade < 2.0 || grade > 6.0) {
        	
            throw new IllegalArgumentException("Оценката трябва да е между 2.0 и 6.0");
        }
        this.course = course;
        this.grade = grade;
    		}

    public Course getCourse() { 
    	
    	return course; 
    }
    public double getGrade() {
    	return grade; 
    }
    
}