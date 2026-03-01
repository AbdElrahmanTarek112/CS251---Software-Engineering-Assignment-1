public enum Day {
    SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY;

    @Override
    public String toString() {
        switch(this) {
            case SATURDAY: return "Saturday";
            case SUNDAY: return "Sunday";
            case MONDAY: return "Monday";
            case TUESDAY: return "Tuesday";
            case WEDNESDAY: return "Wednesday";
            case THURSDAY: return "Thursday";
            default: return super.toString();
        }
    }
}