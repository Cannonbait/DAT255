package edu.chalmers.pickuapp.app.server;

import edu.chalmers.pickuapp.app.model.RouteData;

/**
 * Created by cannonbait on 2014-10-23.
 */
public interface Network{
        public void startDriverMatchmaking(RouteData data);
        public void startHitchhikerMatchmaking(RouteData data);
        public void hitchhikerAcceptMatch();
        public void hitchhikerDeclineMatch();
        public void hitchhikerAbortMatchmaking();
}
