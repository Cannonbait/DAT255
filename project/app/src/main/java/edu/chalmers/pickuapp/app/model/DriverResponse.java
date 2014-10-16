package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.events.*;

public class DriverResponse extends Sequence{

	private RouteData routeData;
	private Date date;

	public DriverResponse(){
		super();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void processEvent(Event event) {
		if(event instanceof DriverPicksUpHitchhikerEvent){
			nextSequence = Sequence.getSequence(DisplayInfo.class);
			((DisplayInfo)nextSequence).insert(routeData.getOrigin(), date);
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

    public void insert(RouteData routeData, Date date){
		this.routeData = routeData;
		this.date = date;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	public RouteData getRouteData(){
		return routeData;
	}

	public Date getDate(){
		return date;
	}
}//end DriverResponse