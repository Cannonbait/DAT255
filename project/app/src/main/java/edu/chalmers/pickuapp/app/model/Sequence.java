package edu.chalmers.pickuapp.app.model;


import java.util.HashMap;
import edu.chalmers.pickuapp.app.events.*;

abstract class Sequence implements EventListener{

	private static HashMap<Class<? extends Sequence>, Sequence> sequences;

	public Sequence() {
		
	}

	/**
	 * Register to EventBus and call hook-method onRegister
	 */
	public void register() {
        EventBus.INSTANCE.registerListener(this);
        onRegister();
    }
    public void unregister(){
        EventBus.INSTANCE.removeListener(this);
        onUnregister();
    }

    /**
     * hook-method called in register()
     */
    public abstract void onRegister();
	public abstract Sequence execute();

	/**
	 * hook-method called in unreguster()
	 */
    public abstract void onUnregister();


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