class SmartLock extends SmartDevice {
    private boolean locked;

    public SmartLock(String name, String roomName) {
        super(name, roomName);
        this.locked = true;
    }

    public void lock() {
        locked = true;
        System.out.println(name + " is now LOCKED");
    }

    public void unlock() {
        locked = false;
        System.out.println(name + " is now UNLOCKED");
    }

    public boolean isLocked() {
        return locked;
    }

    public String getStatus() {
        return name + " - Status: " + (isOn ? "ON" : "OFF") +
                ", Lock: " + (locked ? "LOCKED" : "UNLOCKED");
    }
}