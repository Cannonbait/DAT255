package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by cannonbait on 2014-10-16.
 */
public class NetworkWrapper implements EventListener{

    Thread matchMakingThread;

    private final MockNetworkClient networkClient = new MockNetworkClient();
    public NetworkWrapper(){
        EventBus.INSTANCE.registerListener(this);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof DriverStartMatchmakingEvent) {
            RouteData data = ((DriverStartMatchmakingEvent)event).getRouteData();
            networkClient.startDriverMatchmaking(data);
        } else if (event instanceof HitchhikerStartMatchmakingEvent){
            RouteData data = ((HitchhikerStartMatchmakingEvent)event).getRouteData();
            networkClient.startHitchhikerMatchmaking(data);
        } else if (event instanceof HitchhikerAcceptEvent){
            networkClient.acceptMatch();
        } else if (event instanceof HitchhikerDeclineMatchAndContinueEvent || event instanceof HitchhikerDeclineAndQuitEvent){
            networkClient.declineMatch();
        } else if (event instanceof AbortMatchmakingEvent) {
            networkClient.abortMatchmaking();
        }
    }


}
