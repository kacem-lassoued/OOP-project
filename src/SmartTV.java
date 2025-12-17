public class SmartTV extends SmartDevice {
    private int volume;
    private int channel;

    public SmartTV(String name, String roomName) {
        super(name, roomName,220);
        this.volume = 50;
        this.channel = 1;
    }

    public void setVolume(int vol) {
        if (vol >= 0 && vol <= 100) {
            volume = vol;
            System.out.println(name + " volume set to " + vol);
        }
    }

    public void setChannel(int ch) {
        if (ch > 0) {
            channel = ch;
            System.out.println(name + " channel changed to " + ch);
        }
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Volume: " + volume + ", Channel: " + channel;
    }
}