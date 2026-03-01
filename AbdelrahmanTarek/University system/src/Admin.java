import java.util.*;

public class Admin extends User {
    private Schedule masterSchedule;
    private List<TA> allTAs;
    private List<Doctor> allDoctors;
    private List<Student> allStudents;

    public Admin(String name, int id) {
        super(name, id);
        this.masterSchedule = new Schedule();
        this.allTAs = new ArrayList<>();
        this.allDoctors = new ArrayList<>();
        this.allStudents = new ArrayList<>();
    }

    public void viewAllUsers() {
        System.out.println("\n  === All Users ===");
        System.out.println("  ID       | Type    | Name");
        System.out.println("  ---------|---------|----------------");

        System.out.printf("  %-8d | %-7s | %s (Admin)\n",
                User.ADMIN_ID, "Admin", getName());

        for (Student s : allStudents) {
            System.out.printf("  %-8d | %-7s | %s\n",
                    s.getId(), "Student", s.getName());
        }

        for (TA t : allTAs) {
            System.out.printf("  %-8d | %-7s | %s\n",
                    t.getId(), "TA", t.getName());
        }

        for (Doctor d : allDoctors) {
            System.out.printf("  %-8d | %-7s | %s\n",
                    d.getId(), "Doctor", d.getName());
        }

        System.out.println("\n  Total Users: " + (allStudents.size() + allTAs.size() + allDoctors.size() + 1));
    }

    public void removeUser(int id) {
        if (id == User.ADMIN_ID) {
            System.out.println("  Cannot remove admin account!");
            return;
        }

        boolean removed = false;

        for (Iterator<Student> it = allStudents.iterator(); it.hasNext(); ) {
            Student s = it.next();
            if (s.getId() == id) {
                System.out.println("  Found Student: " + s.getName());
                System.out.print("  Are you sure you want to remove this user? (y/n): ");
                Scanner scanner = new Scanner(System.in);
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("y") || confirm.equals("yes")) {
                    it.remove();
                    System.out.println("  Student with ID " + id + " has been removed successfully.");
                    removed = true;
                } else {
                    System.out.println("  Removal cancelled.");
                    return;
                }
                break;
            }
        }

        if (!removed) {
            for (Iterator<TA> it = allTAs.iterator(); it.hasNext(); ) {
                TA t = it.next();
                if (t.getId() == id) {
                    System.out.println("  Found TA: " + t.getName());
                    System.out.print("  Are you sure you want to remove this user? (y/n): ");
                    Scanner scanner = new Scanner(System.in);
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("y") || confirm.equals("yes")) {
                        it.remove();
                        System.out.println("  TA with ID " + id + " has been removed successfully.");
                        removed = true;
                    } else {
                        System.out.println("  Removal cancelled.");
                        return;
                    }
                    break;
                }
            }
        }

        if (!removed) {
            for (Iterator<Doctor> it = allDoctors.iterator(); it.hasNext(); ) {
                Doctor d = it.next();
                if (d.getId() == id) {
                    System.out.println("  Found Doctor: " + d.getName());
                    System.out.print("  Are you sure you want to remove this user? (y/n): ");
                    Scanner scanner = new Scanner(System.in);
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("y") || confirm.equals("yes")) {
                        it.remove();
                        System.out.println("  Doctor with ID " + id + " has been removed successfully.");
                        removed = true;
                    } else {
                        System.out.println("  Removal cancelled.");
                        return;
                    }
                    break;
                }
            }
        }

        if (!removed) {
            System.out.println("  User with ID " + id + " not found.");
        } else {
            removeUserSlotsFromMasterSchedule(id);
        }
    }

    private void removeUserSlotsFromMasterSchedule(int userId) {
        int removedCount = 0;

        List<TimeSlot> slotsToRemove = new ArrayList<>();

        for (TimeSlot slot : masterSchedule.getSlots()) {
            if (slot.getOwnerId() == userId) {
                slotsToRemove.add(slot);
            }
        }

        for (TimeSlot slot : slotsToRemove) {
            if (masterSchedule.removeSlot(userId, slot)) {
                removedCount++;
            }
        }

        System.out.println("  " + removedCount + " schedule slots have been removed from master schedule for user ID: " + userId);
    }

    public User findUser(int id) {
        for (Student s : allStudents) {
            if (s.getId() == id) return s;
        }
        for (TA t : allTAs) {
            if (t.getId() == id) return t;
        }
        for (Doctor d : allDoctors) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    public void viewMasterSchedule() {
        System.out.println("\n  === Master Schedule ===");
        masterSchedule.displayDetailed();
    }

    public boolean editMasterSchedule(TimeSlot slot, boolean add) {
        if (add) {
            masterSchedule.addSlot(slot);

            User user = findUser(slot.getOwnerId());
            if (user != null) {
                user.getSchedule().addSlot(slot);
            }

            return true;
        } else {
            if (masterSchedule.removeSlot(slot.getOwnerId(), slot)) {
                User user = findUser(slot.getOwnerId());
                if (user != null) {
                    user.getSchedule().removeSlot(slot.getOwnerId(), slot);
                }
                return true;
            }
        }
        return false;
    }

    public void forceAddSlot(TimeSlot slot) {
        editMasterSchedule(slot, true);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n  === Admin Menu ===");
        System.out.println("  1. View All Users");
        System.out.println("  2. View Master Schedule");
        System.out.println("  3. Edit Master Schedule");
        System.out.println("  4. Remove User");
        System.out.println("  5. Back to Main Menu");
        System.out.print("  Choose option: ");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Total Users: " + (allStudents.size() + allTAs.size() + allDoctors.size() + 1));
        System.out.println("  Students: " + allStudents.size());
        System.out.println("  TAs: " + allTAs.size());
        System.out.println("  Doctors: " + allDoctors.size());
    }

    @Override
    public String getUserType() { return "Admin"; }

    public void initializeSampleData() {
        allStudents.add(new Student("Ahmed Ali", 2023001, 3.5, 3));
        allStudents.add(new Student("Sara Mohamed", 2023002, 3.8, 2));
        allStudents.add(new Student("Omar Hassan", 2023003, 2.9, 4));
        allStudents.add(new Student("Nora Ahmed", 2023004, 3.2, 1));

        allTAs.add(new TA("Khaled Mahmoud", 2022001));
        allTAs.add(new TA("Amira Said", 2022002));

        allDoctors.add(new Doctor("Dr. Mohamed Ezz", 1001, "Computer Science"));
        allDoctors.add(new Doctor("Dr. Fatma Zahra", 1002, "Mathematics"));
    }

    public Schedule getMasterSchedule() { return masterSchedule; }
    public List<TA> getAllTAs() { return allTAs; }
    public List<Doctor> getAllDoctors() { return allDoctors; }
    public List<Student> getAllStudents() { return allStudents; }
}