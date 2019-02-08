/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

import java.util.ArrayList;

/**
 * WeatherQueue class Implements a fixed-size queue using first-in, first-out
 * notation. It has an ArrayList of WeatherReading and the maxSize of the Array.
 */
public class WeatherQueue {

    private ArrayList<WeatherReading> fila;
    private int maxSize;

    /**
     * One argument constructor,it sets the maxSize for the array and creates an
     * arrayList of WeatherReading.
     *
     * @param maxSize_
     */
    public WeatherQueue(int maxSize_) {
        fila = new ArrayList<>();
        maxSize = maxSize_;
    }

    /**
     * Method that checks if there is any space into the arrayList, if there is
     * adds a new element into it, otherwise throws an exception.
     *
     * @param wr it is one record passed as an argument
     * @throws WeatherQueueFull
     */
    public void put(WeatherReading wr) throws WeatherQueueFull {
        if (fila.size() < maxSize) {
            fila.add(wr);
        } else {
            throw new WeatherQueueFull();
        }
    }

    /**
     * method that obtains a reference to the WeatherReading object at the head
     * of the queue. The object is also removed from the structure.
     *
     * @return wr
     * @throws WeatherQueueEmpty
     */
    public WeatherReading get() throws WeatherQueueEmpty {
        if (fila.isEmpty()) {
            throw new WeatherQueueEmpty();
        } else {
            WeatherReading wr;
            wr = fila.get(0);
            fila.remove(0);
            return wr;
        }
    }
}