import java.util.*;

public class Doctor extends User {
    private String department;

    public Doctor(String name, int id, String department) {
        super(name, id);
        this.department = department;
    }

    public void Booking(TimeSlot slot, Schedule masterSchedule) {
        if (schedule.hasConflict(slot)) {
            System.out.println("  Cannot book: This slot conflicts with your existing schedule!");
            return;
        }

        if (masterSchedule.hasConflict(slot)) {
            System.out.println("  Cannot book: This slot is already taken in the master schedule!");
            return;
        }

        this.schedule.addSlot(slot);
        masterSchedule.addSlot(slot);

        System.out.println("  Slot booked successfully and added to master schedule.");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n  === Doctor Menu ===");
        System.out.println("  1. View My Schedule");
        System.out.println("  2. Book Time Slot");
        System.out.println("  3. Back to Main Menu");
        System.out.print("  Choose option: ");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Department: " + department);
        System.out.println("  Total Booked Slots: " + schedule.getSlots().size());
    }

    @Override
    public String getUserType() { return "Doctor"; }
}