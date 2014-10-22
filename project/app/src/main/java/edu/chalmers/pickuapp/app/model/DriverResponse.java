package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

public class DriverResponse extends Sequence{

	private RouteData routeData;

	public DriverResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
        EventBus.INSTANCE.reportEvent(new SetupDriverResponseViewEvent(routeData));
	}

	@Override
	public void processEvent(Event event) {
		if(event instanceof DriverPicksUpHitchhikerEvent){
			nextSequence = Sequence.getSequence(DisplayInfo.class);
			((DisplayInfo)nextSequence).insert(routeData.getOrigin(), routeData.getDestination(), routeData.getStartDate());
			setSequenceDoneAndReportForward();
		}
		if(event instanceof DriverDeclineKeepSearchEvent){
			nextSequence = Sequence.getSequence(DriverMatchmaker.class);
			setSequenceDoneAndReportForward();
		}
		if(event instanceof DriverDeclineHitchhikerEvent){
			nextSequence = Sequence.getSequence(DriverSetRoute.class);
			setSequenceDoneAndReportForward();
		}
	}

	private void setSequenceDoneAndReportForward(){
		isDone = true;
		EventBus.INSTANCE.reportEvent(new ForwardClickedEvent());
	}

    @Override
    public Sequence getBackSequence() {
        return getSequence(Mode.class); //TODO where should this lead to?
    }

    public void insert(RouteData routeData){
		this.routeData = routeData;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	public RouteData getRouteData(){
		return routeData;
	}

}//end DriverResponse