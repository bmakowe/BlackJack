package de.htwg.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Observerable
 */
public class Observable implements IObservable {

    private List<IObserver> subscribers = new ArrayList<IObserver>(2);

    /**
     * adds an observer
     *
     * @param s
     */
    public void addObserver(IObserver s) {
        subscribers.add(s);
    }

    /**
     * removes Observer
     *
     * @param s
     */
    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    /**
     * removes all Observers
     */
    public void removeAllObservers() {
        subscribers.clear();
    }

    /**
     * notifies observer
     */
    public void notifyObservers() {
        for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext(); ) {
            IObserver observer = iter.next();
            observer.update();
        }
    }
}
