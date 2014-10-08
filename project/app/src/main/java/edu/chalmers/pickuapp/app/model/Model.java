package edu.chalmers.pickuapp.app.model;

import android.util.*;
import edu.chalmers.pickuapp.app.model.MockSequence;
import edu.chalmers.pickuapp.app.model.Sequence;
import edu.chalmers.pickuapp.app.events.*;

import java.util.HashMap;

public class Model implements EventListener {

	private HashMap<Class<? extends Sequence>, Sequence> sequences;
	private Sequence activeSequence;

	public Model() {

		//Allocate and add every sequence to the sequences hashmap here
		sequences = new HashMap<Class<? extends Sequence>, Sequence>();
		sequences.put(Mode.class, new Mode());
        sequences.put(HitchhikerSetRoute.class, new HitchhikerSetRoute());
        sequences.put(DriverSetRoute.class, new DriverSetRoute());
        sequences.put(DriverMatchmaker.class, new DriverMatchmaker());
        sequences.put(DriverResponse.class, new DriverResponse());

		Sequence.setSequencesSource(sequences);

		activeSequence = sequences.get(Mode.class);

        EventBus.INSTANCE.registerListener(this);
    }

	@Override
	public void onEvent(Event event) {
        activeSequence.processEvent(event);

		if(activeSequence.isDone()) {
            activeSequence = activeSequence.getNextSequence();

			EventBus.INSTANCE.reportEvent(new ChangeViewEvent(activeSequence.getClass()));
		}
	}

}