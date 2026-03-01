import java.util.*;
public class Student extends User {
    private double gpa;
    private int level;

    public Student(String name, int id, double gpa, int level) {
        super(name, id);
        this.gpa = gpa;
        this.level = level;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n  === Student Menu ===");
        System.out.println("  1. View My Schedule");
        System.out.println("  2. View My Information");
        System.out.println("  3. Back to Main Menu");
        System.out.print("  Choose option: ");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("  GPA: %.2f\n", gpa);
        System.out.println("  Level: " + level);
    }

    @Override
    public String getUserType() { return "Student"; }
}