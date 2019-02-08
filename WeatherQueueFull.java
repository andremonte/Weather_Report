/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

/**
 * WeatherQueueFull class Responsible to throw an exception when the queue is
 * full.
 */
public class WeatherQueueFull extends Exception {

    /**
     * Default Constructor Creates an exception.
     */
    public WeatherQueueFull() {
        super("the list is alredy full");
    }
}