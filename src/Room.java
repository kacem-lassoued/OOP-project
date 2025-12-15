import java.util.ArrayList;

public class Room {
    private String name;
    private String type;
    private ArrayList<SmartDevice> devices;

    public Room(String name, String type) {
        this.name = name;
        this.type = type;
        this.devices = new ArrayList<SmartDevice>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<SmartDevice> getDevices() {
        return devices;
    }

    public void displayDevices() {
        System.out.println("\n=== Devices in " + name + " ===");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ". " + devices.get(i).getStatus());
        }
    }
}