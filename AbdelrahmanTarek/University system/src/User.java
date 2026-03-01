public abstract class User {
    protected String name;
    protected int id;
    protected Schedule schedule;

    public static final int ADMIN_ID = 11111111;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.schedule = new Schedule();
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public Schedule getSchedule() { return schedule; }

    public void viewSchedule() {
        System.out.println("\n  Schedule for " + name + " (ID: " + id + "):");
        schedule.display();
    }

    public void displayInfo() {
        System.out.println("  Name: " + name);
        System.out.println("  ID: " + id);
        System.out.println("  Role: " + getUserType());
    }

    public abstract void displayMenu();
    public abstract String getUserType();
}