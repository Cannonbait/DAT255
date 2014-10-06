package edu.chalmers.pickuapp.app.events;

import edu.chalmers.pickuapp.app.model.Sequence;

public class ChangeViewEvent implements Event {
	public Class<? extends Sequence> sequenceClass;
	public ChangeViewEvent(Class<? extends Sequence> sequenceClass) {
		this.sequenceClass = sequenceClass;
	}
}
