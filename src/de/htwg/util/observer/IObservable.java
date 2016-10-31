package de.htwg.util.observer;

/**
 * IObservable
 */
public interface IObservable {

    /**
     * adds an observer
     *
     * @param s
     */
    void addObserver(IObserver s);

    /**
     * removes an observer
     *
     * @param s
     */
    void removeObserver(IObserver s);

    /**
     * removes All Observers
     */
    void removeAllObservers();

    /**
     * notify Observers
     */
    void notifyObservers();
}
