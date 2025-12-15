public class SmartLight extends SmartDevice implements Dimmable {
    private int brightness;

    public SmartLight(String name, String roomName) {
        super(name, roomName);
        this.brightness = 100;
    }

    public void setBrightness(int level) {
        if (level >= 0 && level <= 100) {
            brightness = level;
            System.out.println(name + " brightness set to " + level + "%");
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Brightness: " + brightness + "%";
    }
}
