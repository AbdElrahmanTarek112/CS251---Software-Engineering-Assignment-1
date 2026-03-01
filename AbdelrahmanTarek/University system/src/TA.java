public class TA extends User {

    public TA(String name, int id) {
        super(name, id);
    }

    public void Booking(TimeSlot slot, Schedule masterSchedule) {
        if (this.schedule.hasConflict(slot)) {
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
        System.out.println("\n  === TA Menu ===");
        System.out.println("  1. View My Schedule");
        System.out.println("  2. Book Time Slot");
        System.out.println("  3. Back to Main Menu");
        System.out.print("  Choose option: ");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Total Booked Slots: " + schedule.getSlots().size());
    }

    @Override
    public String getUserType() { return "TA"; }
}