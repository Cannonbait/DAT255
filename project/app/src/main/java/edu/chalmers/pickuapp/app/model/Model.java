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
		sequences.put(MockSequence.class, new MockSequence());
        sequences.put(HitchhikerSetRoute.class, new HitchhikerSetRoute());
        sequences.put(DriverSetRoute.class, new DriverSetRoute());
        sequences.put(DriverMatchmaker.class, new DriverMatchmaker());
        sequences.put(HitchhikerMatchmaker.class, new HitchhikerMatchmaker());
        sequences.put(DriverResponse.class, new DriverResponse());
        sequences.put(HitchhikerResponse.class, new HitchhikerResponse());
        sequences.put(HitchhikerWaitResponse.class, new HitchhikerWaitResponse());
        sequences.put(DisplayInfo.class, new DisplayInfo());
        sequences.put(ExitApp.class, new ExitApp());

		Sequence.setSequencesSource(sequences);

		activeSequence = sequences.get(Mode.class);

        EventBus.INSTANCE.registerListener(this);
        EventBus.INSTANCE.reportEvent(new ChangeViewEvent(activeSequence.getClass()));

    }

	@Override
	public void onEvent(Event event) {
        activeSequence.processEvent(event);

        if(event instanceof ForwardClickedEvent) {
           if(activeSequence.isDone()) {
               activeSequence = activeSequence.getNextSequence();
           }

            EventBus.INSTANCE.reportEvent(new ChangeViewEvent(activeSequence.getClass()));
            activeSequence.onStart();
        } else if(event instanceof BackClickedEvent) {
            activeSequence = activeSequence.getBackSequence();

            EventBus.INSTANCE.reportEvent(new ChangeViewEvent(activeSequence.getClass()));
            activeSequence.onStart();
        }
	}

}