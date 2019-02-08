/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * WeatherReading class is responsible to store time and temperature from a
 * specific location. It has time, temperature and location as attributes.
 */
public class WeatherReading {

    private Date time;
    private double temperature;
    private String location;

    /**
     * WeatherReading Constructor that has 3 arguments
     *
     * @param time_ - time of the day
     * @param temperature_ - temperature of the day
     * @param location_ - location
     */
    public WeatherReading(Date time_, double temperature_, String location_) {
        setTime(time_);
        setTemperature(temperature_);
        setLocation(location_);
    }

    /**
     * method responsible to get time
     *
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * method responsible to get temperature
     *
     * @return temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * method responsible to get location
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * method responsible to set a time generates an assert if the time_ is
     * after the current time
     *
     * @param time_
     */
    public void setTime(Date time_) {
        assert (new Date().getTime() >= time_.getTime()) : "Time entered is " + time_ + "\n"
                + "Time cannot be in future!";
        time = time_;
    }

    /**
     * method responsible to set a time generates an assert if the temperature_
     * is not between -40 and 40
     *
     * @param temperature_
     */
    public void setTemperature(double temperature_) {
        assert (temperature_ >= -40 && temperature_ <= 40) : "Temperature entered is "
                + temperature_ + "\n" + "temperature must be between -40 and 40";
        temperature = temperature_;
    }

    /**
     * method responsible to set the location
     *
     * @param location_
     */
    public void setLocation(String location_) {
        location = location_;
    }

    /**
     * method overrides toString() method and it is responsible to format time
     * and display object content.
     *
     * @return temperature formated.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return "Time: " + sdf.format(time) + ", temperature: " + temperature + "C, " + "location: " + location;
    }

    /**
     * method checks if two objects are equal
     *
     * @param obj
     * @return true if the objects are equal or false if they are not equal
     */
    public boolean equal(WeatherReading obj) {
        int thisHash = Objects.hash(getLocation(), getTemperature(), getTime());
        int objHash = Objects.hash(obj.getLocation(), obj.getTemperature(), obj.getTime());
        return (thisHash == objHash);
    }
}