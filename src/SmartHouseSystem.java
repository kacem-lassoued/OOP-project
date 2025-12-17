import java.util.*;

public class SmartHouseSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the smart controller
        SmartController controller = new SmartController("password");

        // Create rooms and add devices
        setupHouse(controller);

        // Login screen
        System.out.println("======================================");
        System.out.println("   WELCOME TO SMART HOUSE SYSTEM");
        System.out.println("======================================");

        boolean authenticated = false;
        int attempts = 0;

        while (!authenticated && attempts < 3) {
            System.out.print("\nEnter password: ");
            String inputPassword = scanner.nextLine();

            if (controller.authenticate(inputPassword)) {
                authenticated = true;
                System.out.println("\n✓ Access granted!");
            } else {
                attempts++;
                System.out.println("✗ Wrong password! Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("\nAccess denied. Too many failed attempts.");
            scanner.close();
            return;
        }

        // Main menu
        boolean running = true;
        while (running) {
            System.out.println("\n====== MAIN MENU ======");
            System.out.println("1. View all rooms");
            System.out.println("2. Control room devices");
            System.out.println("3. Set outside temperature (for automation)");
            System.out.println("4. View energy consumption");  // ADD THIS LINE
            System.out.println("5. Exit");                     // CHANGE 4 → 5
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    controller.displayAllRooms();
                    break;
                case 2:
                    controlRoomDevices(controller, scanner);
                    break;
                case 3:
                    System.out.print("Enter outside temperature (°C): ");
                    double temp = getDoubleInput(scanner);
                    controller.setOutsideTemperature(temp);
                    break;
                case 4:  // ADD THIS NEW CASE
                    controller.displayEnergyConsumption();
                    break;
                case 5:  // CHANGE 4 → 5
                    running = false;
                    System.out.println("\nGoodbye! Smart house secured.");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }

    private static void setupHouse(SmartController controller) {
        // Living Room
        Room livingRoom = new Room("Living Room", "Common Area");
        livingRoom.addDevice(new ColorLight("Main Light", "Living Room"));
        livingRoom.addDevice(new SmartTV("Samsung TV", "Living Room"));
        livingRoom.addDevice(new HeatingDevice("Floor Heater", "Living Room"));
        controller.addRoom(livingRoom);

        // Bedroom
        Room bedroom = new Room("Bedroom", "Private");
        bedroom.addDevice(new SmartLight("Bedside Lamp", "Bedroom"));
        bedroom.addDevice(new AirConditioner("AC Unit", "Bedroom"));
        bedroom.addDevice(new SmartLock("Door Lock", "Bedroom"));
        controller.addRoom(bedroom);

        // Kitchen
        Room kitchen = new Room("Kitchen", "Utility");
        kitchen.addDevice(new SmartLight("Ceiling Light", "Kitchen"));
        kitchen.addDevice(new SecurityCamera("Security Cam", "Kitchen"));
        controller.addRoom(kitchen);

        // Garage
        Room garage = new Room("Garage", "Storage");
        garage.addDevice(new SmartLight("Garage Light", "Garage"));
        garage.addDevice(new SmartLock("Garage Door", "Garage"));
        garage.addDevice(new SecurityCamera("Outdoor Cam", "Garage"));
        controller.addRoom(garage);
    }

    private static void controlRoomDevices(SmartController controller, Scanner scanner) {
        while (true) {
            controller.displayAllRooms();
            System.out.println("0. Return to main menu");
            System.out.print("\nSelect a room: ");

            int roomChoice = getIntInput(scanner);

            if (roomChoice == 0) {
                return;
            }

            if (roomChoice < 1 || roomChoice > controller.getRooms().size()) {
                System.out.println("Invalid room selection!");
                continue;
            }

            Room selectedRoom = controller.getRooms().get(roomChoice - 1);
            manageDevices(selectedRoom, scanner);
        }
    }

    private static void manageDevices(Room room, Scanner scanner) {
        while (true) {
            room.displayDevices();
            System.out.println("0. Return to room selection");
            System.out.print("\nSelect a device to control: ");

            int deviceChoice = getIntInput(scanner);

            if (deviceChoice == 0) {
                return;
            }

            if (deviceChoice < 1 || deviceChoice > room.getDevices().size()) {
                System.out.println("Invalid device selection!");
                continue;
            }

            SmartDevice device = room.getDevices().get(deviceChoice - 1);
            controlDevice(device, scanner);
        }
    }

    private static void controlDevice(SmartDevice device, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Controlling: " + device.getName() + " ===");
            System.out.println("Current status: " + device.getStatus());
            System.out.println("\n1. Turn ON");
            System.out.println("2. Turn OFF");

            // Type-specific options
            if (device instanceof ColorLight) {
                System.out.println("3. Set brightness");
                System.out.println("4. Change color");
            } else if (device instanceof SmartLight) {
                System.out.println("3. Set brightness");
            } else if (device instanceof HeatingDevice) {
                System.out.println("3. Set target temperature");
            } else if (device instanceof AirConditioner) {
                System.out.println("3. Set temperature");
                System.out.println("4. Change mode");
            } else if (device instanceof SmartTV) {
                System.out.println("3. Set volume");
                System.out.println("4. Change channel");
            } else if (device instanceof SecurityCamera) {
                System.out.println("3. Start recording");
                System.out.println("4. Stop recording");
            } else if (device instanceof SmartLock) {
                System.out.println("3. Lock");
                System.out.println("4. Unlock");
            }

            System.out.println("0. Return to device list");
            System.out.print("Choose action: ");

            int action = getIntInput(scanner);

            if (action == 0) {
                return;
            }

            switch (action) {
                case 1:
                    device.turnOn();
                    break;
                case 2:
                    device.turnOff();
                    break;
                case 3:
                    handleAction3(device, scanner);
                    break;
                case 4:
                    handleAction4(device, scanner);
                    break;
                default:
                    System.out.println("Invalid action!");
            }
        }
    }

    private static void handleAction3(SmartDevice device, Scanner scanner) {
        if (device instanceof Dimmable) {
            System.out.print("Enter brightness (0-100): ");
            int brightness = getIntInput(scanner);
            ((Dimmable) device).setBrightness(brightness);
        } else if (device instanceof TemperatureControlled) {
            System.out.print("Enter temperature: ");
            double temp = getDoubleInput(scanner);
            ((TemperatureControlled) device).setTemperature(temp);
        } else if (device instanceof SmartTV) {
            System.out.print("Enter volume (0-100): ");
            int vol = getIntInput(scanner);
            ((SmartTV) device).setVolume(vol);
        } else if (device instanceof SecurityCamera) {
            ((SecurityCamera) device).startRecording();
        } else if (device instanceof SmartLock) {
            ((SmartLock) device).lock();
        }
    }

    private static void handleAction4(SmartDevice device, Scanner scanner) {
        if (device instanceof ColorLight) {
            System.out.print("Enter color (White/Red/Blue/Green/Yellow): ");
            String color = scanner.nextLine();
            ((ColorLight) device).setColor(color);
        } else if (device instanceof AirConditioner) {
            System.out.print("Enter mode (cooling/heating/fan): ");
            String mode = scanner.nextLine();
            ((AirConditioner) device).setMode(mode);
        } else if (device instanceof SmartTV) {
            System.out.print("Enter channel number: ");
            int ch = getIntInput(scanner);
            ((SmartTV) device).setChannel(ch);
        } else if (device instanceof SecurityCamera) {
            ((SecurityCamera) device).stopRecording();
        } else if (device instanceof SmartLock) {
            ((SmartLock) device).unlock();
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}