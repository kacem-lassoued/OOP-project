public abstract class SmartDevice implements Controllable {
    protected String name;
    protected boolean isOn;
    protected String roomName;

    public SmartDevice(String name, String roomName) {
        this.name = name;
        this.roomName = roomName;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(name + " is now ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " is now OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public String getName() {
        return name;
    }

    public abstract String getStatus();
}

