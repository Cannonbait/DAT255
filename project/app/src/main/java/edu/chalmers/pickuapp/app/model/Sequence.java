package edu.chalmers.pickuapp.app.model;


import java.util.HashMap;
import edu.chalmers.pickuapp.app.events.*;

abstract class Sequence {

	private static HashMap<Class<? extends Sequence>, Sequence> sequences;

	public Sequence() {
		
	}

    public abstract void onRegister();
	public abstract void processEvent(Event event);
    public abstract Sequence getNextSequence();
    public abstract boolean isDone();

    /**
     * Give uniform access to HashMap of sequences for all sequences
     * @param sequence HashMap containing all sequences available
     */
    public static Sequence getSequence(Class<? extends Sequence> sequence) {
    	return sequences.get(sequence);
    }

    /**
     * Sets from what HashMap the sequences will fetch another sequence
     * @param sequencesSource what HashMap to get sequences from
     */
    public static void setSequencesSource(HashMap<Class<? extends Sequence>, Sequence> sequencesSource) {
    	sequences = sequencesSource;
    }

}