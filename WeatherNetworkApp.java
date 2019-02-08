/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WeatherNetworkApp class has a WeatherQueue object receiving
 * elements from multiple WeatherReporter objects.
 * It has a WeatherQueue, BufferedWriter and Logger 
 */
public class WeatherNetworkApp {

    private WeatherQueue wq;
    private BufferedWriter out_;
    private Logger log_;

/**
 * WeatherNetworkApp constructor with
 * @param queueSize
 * @param outPath
 * @param logName
 * @param pathFiles
 */
    WeatherNetworkApp(int queueSize, String outPath, String logName, String[] pathFiles) {
        WeatherQueue wq = new WeatherQueue(queueSize);
        log_ = Logger.getLogger(logName);
        log_.setLevel(Level.FINER);
        try {
            out_ = new BufferedWriter(new FileWriter(outPath));
        } catch (Exception e) {
            log_.warning(e.getMessage());
        }

        int i = 0;
        ArrayList<Thread> threadsList = new ArrayList<>();

        for (String arquivo : pathFiles) {
            String path = logName + i + ".txt";
            Runnable task = new Runnable() {

                public void run() {
                    WeatherReporter reporter = new WeatherReporter(wq, arquivo, path, log_);
                    reporter.reportReadings();
                }
            };

            i++;
            Thread t = new Thread(task);
            t.start();
            threadsList.add(t);
        }

        for (Thread tt : threadsList) {
            try {
                tt.join();
            } catch (Exception ex) {

            }
        }
        Boolean extraction = true;
        while (extraction) {
            try {
                WeatherReading obj = wq.get();
                out_.write(obj.toString());
                out_.newLine();
            } catch (Exception ex) {
                extraction = false;
            }
        }
        try {
            out_.close();
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {

        String LOG_NAME = "wk3log";
        String[] PATH_FILES = {"temp.txt", "temp2.txt", "temp3.txt", "temp4.txt", "temp5.txt", "temp6.txt", "temp7.txt", "temp8.txt", "temp9.txt", "temp10.txt"};
        String PATH_OUTPUT = "output.txt";

        WeatherNetworkApp wna = new WeatherNetworkApp(100, PATH_OUTPUT, LOG_NAME, PATH_FILES);

    }
}