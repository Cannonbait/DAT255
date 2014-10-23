package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by cannonbait on 2014-10-16.
 */
public class NetworkWrapper implements EventListener{

    Thread matchMakingThread;

    private final Network network = new MockNetwork();
    public NetworkWrapper(){
        EventBus.INSTANCE.registerListener(this);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof DriverStartMatchmakingEvent) {
            RouteData data = ((DriverStartMatchmakingEvent)event).getRouteData();
            network.startDriverMatchmaking(data);
        } else if (event instanceof HitchhikerStartMatchmakingEvent){
            RouteData data = ((HitchhikerStartMatchmakingEvent)event).getRouteData();
            network.startHitchhikerMatchmaking(data);
        } else if (event instanceof HitchhikerAcceptEvent){
            network.acceptMatch();
        } else if (event instanceof HitchhikerDeclineMatchAndContinueEvent || event instanceof HitchhikerDeclineAndQuitEvent){
            network.declineMatch();
        } else if (event instanceof AbortMatchmakingEvent) {
            network.abortMatchmaking();
        }
    }


}
