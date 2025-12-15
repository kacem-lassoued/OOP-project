public class HeatingDevice extends SmartDevice implements TemperatureControlled {
    private double targetTemperature;
    private double currentTemperature;

    public HeatingDevice(String name, String roomName) {
        super(name, roomName);
        this.targetTemperature = 20.0;
        this.currentTemperature = 18.0;
    }

    public void setTemperature(double temp) {
        if (temp >= 15 && temp <= 30) {
            targetTemperature = temp;
            System.out.println(name + " target temperature set to " + temp + "°C");
        }
    }

    public double getTemperature() {
        return targetTemperature;
    }

    public void setCurrentTemperature(double temp) {
        currentTemperature = temp;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Target: " + targetTemperature + "°C, Current: " + currentTemperature + "°C"; }}