import java.util.*;
public class ColorLight extends SmartLight {
    private String color;

    public ColorLight(String name, String roomName) {
        super(name, roomName);
        this.color = "White";
    }

    public void setColor(String color) {
        Set<String> validColors = Set.of("yellow", "white", "red", "blue", "green");
        if (validColors.contains(color.toLowerCase())) {
            this.color = color;
            System.out.println(name + " color changed to " + color);
        }
        else {
            System.out.println("Invalid color: " + color);
        }

    }

    public String getColor() {
        return color;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Brightness: " + getBrightness() + "%, Color: " + color;
    }
}
