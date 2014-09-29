package edu.chalmers.pickuapp.app.model;

import edu.chalmers.pickuapp.app.model.MockSequence;
import edu.chalmers.pickuapp.app.model.Sequence;

import java.util.HashMap;

class Model {

	private HashMap<Class<? extends Sequence>, Sequence> sequences;

	public Model() {

		//Allocate and add every sequence to the sequences hashmap here
		sequences = new HashMap<Class<? extends Sequence>, Sequence>();
		sequences.put(Sequence.class, new MockSequence());

	}

	public void start() {
		Sequence s = sequences.get(Mode.class);

		while(s != null) {
			s.register();
			Sequence temp = s.execute();
			s.unregister();
			s = temp;
		}
	}

}