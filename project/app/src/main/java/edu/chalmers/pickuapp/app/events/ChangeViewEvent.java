package edu.chalmers.pickuapp.app.events;

public class ChangeViewEvent implements Event {
	public Class<? extends Sequence> sequenceClass;
	ChangeViewEvent(Class<? extends Sequence> sequenceClass) {
		this.sequenceClass = sequenceClass;
	}
}
