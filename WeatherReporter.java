/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * WeatherReporter class is responsible to store the file information and read
 * it.
 */
public class WeatherReporter {

    private WeatherQueue wr;
    private String filePath;
    private Logger log;
    private String logOnDisk;

    /**
     * WeatherReporter with those parameters
     * @param wr_
     * @param filePath_
     * @param logOnDisk_
     * @param log_
     */
    public WeatherReporter(WeatherQueue wr_, String filePath_, String logOnDisk_, Logger log_) {
        wr = wr_;
        filePath = filePath_;
        logOnDisk = logOnDisk_;
        log = log_;

        try {
            log.addHandler(new FileHandler(logOnDisk));
        } catch (IOException ex) {
            //Logger.getLogger(WeatherReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is responsible for read the file and break the message into
     * parts, also synchronize the put method recording a log when failing or
     * successful.
     */
    public void reportReadings() {
        File arquivo;
        String linha;
        String[] breakedMsg;
        BufferedReader br;

        try {

            arquivo = new File(filePath);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

            while ((linha = br.readLine()) != null) {
                breakedMsg = linha.split(",");

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                Date time = sdf.parse(breakedMsg[0]);
                double temp = Double.parseDouble(breakedMsg[1]);
                String city = breakedMsg[2];

                WeatherReading obj = new WeatherReading(time, temp, city);

                synchronized (wr) {
                    wr.put(obj);
                }
                logSuccess("information was add susccesfully");
            }

        } catch (Exception e) {
            logFail("File could not be read:" + e.getMessage());
        }

    }

    /**
     * This method synchronize the successful logs.
     *
     * @param texto
     */
    private synchronized void logSuccess(String texto) {
        log.finer(texto);
    }

    /**
     * This method synchronize the failing logs.
     *
     * @param texto
     */
    private synchronized void logFail(String texto) {
        log.warning(texto);
    }
}