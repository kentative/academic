package ksl.academic.injection;

public class Communicator {

    private final String prefix;

    public Communicator(String prefix) {
        this.prefix = prefix;
    }

    public boolean sendMessage(String message) {
        System.out.println("Communicator Sending message: " + prefix + " " + message);
        return true;
    }
}
