public class SecurityCamera extends SmartDevice {
    private boolean recording;

    public SecurityCamera(String name, String roomName) {
        super(name, roomName);
        this.recording = false;
    }

    public void startRecording() {
        recording = true;
        System.out.println(name + " started recording");
    }

    public void stopRecording() {
        recording = false;
        System.out.println(name + " stopped recording");
    }

    public boolean isRecording() {
        return recording;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Recording: " + (recording ? "YES" : "NO");
    }
}

