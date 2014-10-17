package edu.chalmers.pickuapp.app.server;

import android.util.Log;
import edu.chalmers.pickuapp.app.events.*;

/**
 * Created by cannonbait on 2014-10-16.
 */
public class Network implements EventListener{

    Thread matchMakingThread;

    private final MockServer server = new MockServer();
    public Network(){
        EventBus.INSTANCE.registerListener(this);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof StartMatchmakingEvent) {

            Log.i("NetWork", "HALLÅÅÅÅ");
            StartMatchmakingEvent sme = (StartMatchmakingEvent)event;

            server.setData(sme.getRouteData());
            matchMakingThread = new Thread(server);
            matchMakingThread.start();
        }

        if (event instanceof AbortMatchmakingEvent) {
            //matchMakingThread.interrupt();
        }
    }


}
