import java.util.*;

public class Schedule {
    private List<TimeSlot> slots;

    public Schedule() {
        this.slots = new ArrayList<>();
    }

    public boolean addSlot(TimeSlot slot) {
        if (hasConflict(slot)) {
            return false;
        }
        slots.add(slot);
        slots.sort((a, b) -> {
            if (a.getDay() != b.getDay())
                return a.getDay().compareTo(b.getDay());
            return Integer.compare(a.getSlotNumber(), b.getSlotNumber());
        });
        return true;
    }

    public boolean removeSlot(int ownerId, TimeSlot slot) {
        for (Iterator<TimeSlot> it = slots.iterator(); it.hasNext(); ) {
            TimeSlot ts = it.next();
            if (ts.getOwnerId() == ownerId &&
                    ts.getDay() == slot.getDay() &&
                    ts.getSlotNumber() == slot.getSlotNumber()) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean hasConflict(TimeSlot slot) {
        for (TimeSlot existing : slots) {
            if (existing.overlapsWith(slot)) {
                return true;
            }
        }
        return false;
    }

    public void display() {
        if (slots.isEmpty()) {
            System.out.println("  No scheduled slots");
            return;
        }

        System.out.println("  Day       | Slot | Time        | Assigned To");
        System.out.println("  ----------|------|-------------|----------------");

        for (TimeSlot slot : slots) {
            System.out.printf("  %-9s |  %d   | %s | %s (%s)\n",
                    slot.getDayString(),
                    slot.getSlotNumber(),
                    slot.getSlotTimeString(),
                    slot.getOwnerName(),
                    slot.getOwnerType());
        }
    }

    public void displayDetailed() {
        if (slots.isEmpty()) {
            System.out.println("  Master schedule is empty");
            return;
        }

        Day currentDay = slots.get(0).getDay();
        System.out.println("\n  " + slots.get(0).getDayString() + ":");
        System.out.println("  Slot | Time        | Assigned To");
        System.out.println("  -----|-------------|----------------");

        for (TimeSlot slot : slots) {
            if (slot.getDay() != currentDay) {
                currentDay = slot.getDay();
                System.out.println("\n  " + slot.getDayString() + ":");
                System.out.println("  Slot | Time        | Assigned To");
                System.out.println("  -----|-------------|----------------");
            }
            System.out.printf("  %d    | %s | %s (%s)\n",
                    slot.getSlotNumber(),
                    slot.getSlotTimeString(),
                    slot.getOwnerName(),
                    slot.getOwnerType());
        }
    }

    public List<TimeSlot> getSlots() {
        return slots;
    }
}