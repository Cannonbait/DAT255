package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by cannonbait on 2014-10-16.
 */
public class NetworkWrapper implements EventListener{

    Thread matchMakingThread;

    private final MockNetworkClient server = new MockNetworkClient();
    public NetworkWrapper(){
        EventBus.INSTANCE.registerListener(this);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof DriverStartMatchmakingEvent) {
            DriverStartMatchmakingEvent sme = (DriverStartMatchmakingEvent)event;
            server.setData(sme.getRouteData());
            matchMakingThread = new Thread(server);
            matchMakingThread.start();
        }

        if (event instanceof AbortMatchmakingEvent) {
            matchMakingThread.interrupt();
        }
    }


}
