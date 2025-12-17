import java.util.ArrayList;

public class SmartController {
    private ArrayList<Room> rooms;
    private String password;
    private double outsideTemperature;

    public SmartController(String password) {
        this.password = password;
        this.rooms = new ArrayList<Room>();
        this.outsideTemperature = 15.0;
    }

    public boolean authenticate(String inputPassword) {
        return password.equals(inputPassword);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setOutsideTemperature(double temp) {
        this.outsideTemperature = temp;
        System.out.println("Outside temperature set to " + temp + "°C");
        autoAdjustHeating();
    }

    // AUTOMATION FEATURE: Auto-adjust heating based on outside temperature
    private void autoAdjustHeating() {
        for (Room room : rooms) {
            for (SmartDevice device : room.getDevices()) {
                if (device instanceof HeatingDevice) {
                    HeatingDevice heater = (HeatingDevice) device;
                    heater.setCurrentTemperature(outsideTemperature);

                    // Auto turn on heating if it's cold outside
                    if (outsideTemperature < 10) {
                        if (!heater.isOn()) {
                            heater.turnOn();
                            System.out.println("AUTO: " + heater.getName() +
                                    " turned ON (cold outside)");
                        }
                        heater.setTemperature(26.0);
                    }
                    // Auto turn off if it's warm
                    else if (outsideTemperature > 28) {
                        if (heater.isOn()) {
                            heater.turnOff();
                            System.out.println("AUTO: " + heater.getName() +
                                    " turned OFF (warm outside)");
                        }
                    }
                }
            }
        }
    }

    public void displayAllRooms() {
        System.out.println("\n====== SMART HOUSE ROOMS ======");
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            System.out.println((i + 1) + ". " + room.getName() +
                    " (" + room.getType() + ") - " +
                    room.getDevices().size() + " devices");
        }
    }

    // ADD THIS NEW METHOD:
    public void displayEnergyConsumption() {
        System.out.println("\n=== HOUSE ENERGY CONSUMPTION ===");

        double totalPower = 0;
        for (Room room : rooms) {
            double roomPower = 0;
            System.out.println("\n" + room.getName() + ":");

            for (SmartDevice device : room.getDevices()) {
                double devicePower = device.isOn ? device.powerConsumption : 0;
                roomPower += devicePower;
                System.out.println("  • " + device.getName() + ": " +
                        (device.isOn ? device.powerConsumption + "W (ON)" : "0W (OFF)"));
            }

            System.out.println("  Room Total: " + roomPower + "W");
            totalPower += roomPower;
        }

        System.out.println("\n=== TOTAL HOUSE CONSUMPTION ===");
        System.out.println("Total Power: " + totalPower + "W");
        System.out.println("================================");
    }
}