import java.util.Scanner;

public class Main {
    private static Admin systemAdmin;
    private static Scanner scanner;

    public static void main(String[] args) {
        systemAdmin = new Admin("System Administrator", User.ADMIN_ID);
        systemAdmin.initializeSampleData();

        scanner = new Scanner(System.in);

        System.out.println("\n========================================");
        System.out.println("  Educational Management System v2.0");
        System.out.println("  Fixed Time Slots: 6 slots per day");
        System.out.println("========================================\n");

        while (true) {
            System.out.println("========================================");
            System.out.println("           Main Menu");
            System.out.println("========================================");
            System.out.println("  1. Student");
            System.out.println("  2. Teaching Assistant (TA)");
            System.out.println("  3. Doctor");
            System.out.println("  4. Admin");
            System.out.println("  5. Exit");
            System.out.println("========================================");
            System.out.print("  Choose option: ");

            int option = scanner.nextInt();

            if (option == 1) {
                System.out.print("\n  Enter your Student ID: ");
                int studentId = scanner.nextInt();

                User user = systemAdmin.findUser(studentId);
                if (user instanceof Student) {
                    studentMenu((Student) user);
                } else {
                    System.out.println("  Student not found!");
                    System.out.println("\n  Press Enter to continue...");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            } else if (option == 2) {
                System.out.print("\n  Enter your TA ID: ");
                int taId = scanner.nextInt();

                User user = systemAdmin.findUser(taId);
                if (user instanceof TA) {
                    taMenu((TA) user);
                } else {
                    System.out.println("  TA not found!");
                    System.out.println("\n  Press Enter to continue...");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            } else if (option == 3) {
                System.out.print("\n  Enter your Doctor ID: ");
                int doctorId = scanner.nextInt();

                User user = systemAdmin.findUser(doctorId);
                if (user instanceof Doctor) {
                    doctorMenu((Doctor) user);
                } else {
                    System.out.println("  Doctor not found!");
                    System.out.println("\n  Press Enter to continue...");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            } else if (option == 4) {
                System.out.print("\n  Enter Admin ID: ");
                int enteredId = scanner.nextInt();

                if (enteredId == User.ADMIN_ID) {
                    adminMenu(systemAdmin);
                } else {
                    System.out.println("  Invalid Admin ID!");
                    System.out.println("\n  Press Enter to continue...");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            } else if (option == 5) {
                System.out.println("\n  Exiting system... Goodbye!");
                System.out.println("========================================");
                break;
            } else {
                System.out.println("\n  Invalid option!");
                System.out.println("\n  Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayTimeSlots() {
        System.out.println("\n  Available Time Slots:");
        System.out.println("  Slot 1: 08:00 - 09:30");
        System.out.println("  Slot 2: 09:40 - 11:10");
        System.out.println("  Slot 3: 11:20 - 12:50");
        System.out.println("  Slot 4: 13:00 - 14:30");
        System.out.println("  Slot 5: 14:40 - 16:10");
        System.out.println("  Slot 6: 16:20 - 17:50");
    }

    private static void studentMenu(Student student) {
        while (true) {
            student.displayMenu();

            int choice = scanner.nextInt();

            if (choice == 1) {
                student.viewSchedule();
            } else if (choice == 2) {
                student.displayInfo();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("  Invalid option.");
            }

            if (choice != 3) {
                System.out.println("\n  Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }

    private static void taMenu(TA ta) {
        while (true) {
            ta.displayMenu();

            int choice = scanner.nextInt();

            if (choice == 1) {
                ta.viewSchedule();
            } else if (choice == 2) {
                TimeSlot slot = new TimeSlot();

                displayTimeSlots();

                System.out.print("\n  Enter day (0-5: Sat-Thu): ");
                int day = scanner.nextInt();
                if (day < 0 || day > 5) {
                    System.out.println("  Invalid day! Must be 0-5.");
                    break;
                }
                slot.setDay(Day.values()[day]);

                System.out.print("  Enter slot number (1-6): ");
                int slotNum = scanner.nextInt();
                if (slotNum < 1 || slotNum > 6) {
                    System.out.println("  Invalid slot number! Must be 1-6.");
                    break;
                }
                slot.setSlotNumber(slotNum);

                slot.setOwnerId(ta.getId());
                slot.setOwnerName(ta.getName());
                slot.setOwnerType(ta.getUserType());

                ta.Booking(slot, systemAdmin.getMasterSchedule());

            } else if (choice == 3) {
                break;
            } else {
                System.out.println("  Invalid option.");
            }

            if (choice != 3) {
                System.out.println("\n  Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }

    private static void doctorMenu(Doctor doctor) {
        while (true) {
            doctor.displayMenu();

            int choice = scanner.nextInt();

            if (choice == 1) {
                doctor.viewSchedule();
            } else if (choice == 2) {
                TimeSlot slot = new TimeSlot();

                displayTimeSlots();

                System.out.print("\n  Enter day (0-5: Sat-Thu): ");
                int day = scanner.nextInt();
                if (day < 0 || day > 5) {
                    System.out.println("  Invalid day! Must be 0-5.");
                    break;
                }
                slot.setDay(Day.values()[day]);

                System.out.print("  Enter slot number (1-6): ");
                int slotNum = scanner.nextInt();
                if (slotNum < 1 || slotNum > 6) {
                    System.out.println("  Invalid slot number! Must be 1-6.");
                    break;
                }
                slot.setSlotNumber(slotNum);

                slot.setOwnerId(doctor.getId());
                slot.setOwnerName(doctor.getName());
                slot.setOwnerType(doctor.getUserType());

                doctor.Booking(slot, systemAdmin.getMasterSchedule());

            } else if (choice == 3) {
                break;
            } else {
                System.out.println("  Invalid option.");
            }

            if (choice != 3) {
                System.out.println("\n  Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }

    private static void adminMenu(Admin admin) {
        while (true) {
            admin.displayMenu();

            int choice = scanner.nextInt();

            if (choice == 1) {
                admin.viewAllUsers();
            } else if (choice == 2) {
                admin.viewMasterSchedule();
            }
            else if (choice == 3) {
                System.out.println("\n  === Edit Master Schedule ===");
                System.out.println("  1. Add slot");
                System.out.println("  2. Remove slot");
                System.out.print("  Choose: ");

                int editChoice = scanner.nextInt();

                if (editChoice == 1 || editChoice == 2) {
                    TimeSlot slot = new TimeSlot();

                    admin.viewAllUsers();

                    System.out.print("\n  Enter user ID: ");
                    int userId = scanner.nextInt();

                    User user = admin.findUser(userId);
                    if (user == null) {
                        System.out.println("  User not found!");
                    } else {
                        if (user.getUserType().equals("Student") || user.getUserType().equals("Admin")) {
                            System.out.println("  Invalid User! Only TA and Doctor can have schedule slots.");
                        } else {
                            displayTimeSlots();

                            System.out.print("  Enter day (0-5: Sat-Thu): ");
                            int day = scanner.nextInt();
                            if (day < 0 || day > 5) {
                                System.out.println("  Invalid day!");
                            } else {
                                slot.setDay(Day.values()[day]);

                                System.out.print("  Enter slot number (1-6): ");
                                int slotNum = scanner.nextInt();
                                if (slotNum < 1 || slotNum > 6) {
                                    System.out.println("  Invalid slot number!");
                                } else {
                                    slot.setSlotNumber(slotNum);
                                    slot.setOwnerId(userId);
                                    slot.setOwnerName(user.getName());
                                    slot.setOwnerType(user.getUserType());

                                    if (editChoice == 1) {
                                        boolean slotExists = false;
                                        for (TimeSlot existingSlot : admin.getMasterSchedule().getSlots()) {
                                            if (existingSlot.getDay() == slot.getDay() &&
                                                    existingSlot.getSlotNumber() == slot.getSlotNumber()) {
                                                slotExists = true;
                                                System.out.println("  Cannot add: Slot " + slotNum + " on " +
                                                        slot.getDayString() + " is already taken by: " +
                                                        existingSlot.getOwnerName() + " (" + existingSlot.getOwnerType() + ")");
                                                break;
                                            }
                                        }

                                        if (!slotExists) {
                                            admin.forceAddSlot(slot);
                                            System.out.println("  Slot added successfully to master schedule.");
                                        }

                                    } else {
                                        boolean slotExists = false;

                                        for (TimeSlot existingSlot : admin.getMasterSchedule().getSlots()) {
                                            if (existingSlot.getOwnerId() == userId &&
                                                    existingSlot.getDay() == slot.getDay() &&
                                                    existingSlot.getSlotNumber() == slot.getSlotNumber()) {
                                                slotExists = true;
                                                break;
                                            }
                                        }

                                        if (slotExists) {
                                            System.out.print("  Are you sure you want to remove this slot for " +
                                                    user.getName() + "? (y/n): ");
                                            scanner.nextLine();
                                            String confirm = scanner.nextLine().trim().toLowerCase();

                                            if (confirm.equals("y") || confirm.equals("yes")) {
                                                if (admin.editMasterSchedule(slot, false)) {
                                                    System.out.println("  Slot removed from master schedule and user's schedule.");
                                                } else {
                                                    System.out.println("  Failed to remove slot.");
                                                }
                                            } else {
                                                System.out.println("  Removal cancelled.");
                                            }
                                        } else {
                                            System.out.println("  Slot not found in master schedule for this user.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (choice == 4) {
                System.out.println("\n  === Remove User ===");
                admin.viewAllUsers();
                System.out.print("\n  Enter user ID to remove: ");
                int userId = scanner.nextInt();
                scanner.nextLine();
                admin.removeUser(userId);

            } else if (choice == 5) {
                break;
            } else {
                System.out.println("  Invalid option.");
            }

            if (choice != 5) {
                System.out.println("\n  Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }
}