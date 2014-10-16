package edu.chalmers.pickuapp.app.model;


import java.util.HashMap;
import edu.chalmers.pickuapp.app.events.*;

public abstract class Sequence {

	private static HashMap<Class<? extends Sequence>, Sequence> sequences;
    protected boolean isDone;
    protected Sequence nextSequence;

	public Sequence() {
		
	}

	public abstract void processEvent(Event event);
    public abstract Sequence getBackSequence();
    public Sequence getNextSequence() {
        return nextSequence;
    }

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

    public static HashMap<Class<? extends Sequence>, Sequence> getSequencesSource() {
        return sequences;
    }

    /*
     * Should run at the start of each sequence
     */
    public void onStart() {
        //Override this method if you want it to do anything else
        isDone = false;
    }

    public boolean isDone(){
        return isDone;
    }

}