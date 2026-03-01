public class TimeSlot {
    private Day day;
    private int slotNumber;
    private int ownerId;
    private String ownerName;
    private String ownerType;

    public static final String[][] SLOT_TIMES = {
            {"08:00", "09:30"},
            {"09:40", "11:10"},
            {"11:20", "12:50"},
            {"13:00", "14:30"},
            {"14:40", "16:10"},
            {"16:20", "17:50"}
    };

    public TimeSlot() {
        this.day = Day.SATURDAY;
        this.slotNumber = 1;
        this.ownerId = 0;
        this.ownerName = "";
        this.ownerType = "";
    }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }

    public int getSlotNumber() { return slotNumber; }
    public void setSlotNumber(int slotNumber) { this.slotNumber = slotNumber; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerType() { return ownerType; }
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }

    public String getDayString() {
        return day.toString();
    }

    public String getSlotTimeString() {
        if (slotNumber >= 1 && slotNumber <= 6) {
            return SLOT_TIMES[slotNumber - 1][0] + " - " + SLOT_TIMES[slotNumber - 1][1];
        }
        return "Invalid slot";
    }

    @Override
    public String toString() {
        return String.format("%s Slot %d (%s) - %s: %s",
                getDayString(), slotNumber, getSlotTimeString(), ownerType, ownerName);
    }

    public boolean overlapsWith(TimeSlot other) {
        return this.day == other.day && this.slotNumber == other.slotNumber;
    }
}