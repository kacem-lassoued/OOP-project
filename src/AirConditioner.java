public class AirConditioner extends SmartDevice implements TemperatureControlled {
    private double targetTemperature;
    private String mode; // cooling, heating, fan

    public AirConditioner(String name, String roomName) {
        super(name, roomName);
        this.targetTemperature = 22.0;
        this.mode = "cooling";
    }

    public void setTemperature(double temp) {
        if (temp >= 16 && temp <= 30) {
            targetTemperature = temp;
            System.out.println(name + " temperature set to " + temp + "°C");
        }
    }

    public double getTemperature() {
        return targetTemperature;
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(name + " mode set to " + mode);
    }

    public String getMode() {
        return mode;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Mode: " + mode + ", Temperature: " + targetTemperature + "°C";
    }
}
