/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

/**
 * WeatherQueueEmpty class Responsible to throw an exception when the queue is
 * empty.
 */
public class WeatherQueueEmpty extends Exception {

    /**
     * Default Constructor Creates an exception.
     */
    public WeatherQueueEmpty() {
        super("the list is empty");
    }
}