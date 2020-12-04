package ksl.academic.injection;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class Communication {


    @Inject
    private Communicator communicator;

    public static Injector injector;

    public Communication() {
        injector.injectMembers(this);
    }

    public Communication(Boolean keepRecord) {
        if (keepRecord) {
            System.out.println("Message logging enabled");
        }
    }

    public boolean sendMessage(String message) {
        return communicator.sendMessage(message);
    }

    public static void main(String... args) {
        Injector injector = Guice.createInjector(new BasicModule());
        Communication.injector = injector;
        Communication comms = injector.getInstance(Communication.class);
//        Communication comms = new Communication(true);
        comms.sendMessage("test");
    }

}
