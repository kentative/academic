package ksl.academic.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(Communicator.class).toInstance(new Communicator("kent"));
//        bind(Communication.class).toInstance(new Communication(true));
    }

    @Provides
    @Singleton
    public Communicator provideCommunicator() {
        return new Communicator("kent2");
    }


    @Provides
    @Singleton
    public Communication provideCommunication() {
        return new Communication();
    }
}
