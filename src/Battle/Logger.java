/**
 * Class that represents the application logger
 */
package Battle;

import java.io.*;

public class Logger {
    private BufferedWriter buffWr;
    private String logType;


    public Logger(String logType) {
        if (logType != null) {
            try {
                FileWriter fr = new FileWriter(new File(logType), true);
                this.buffWr = new BufferedWriter(fr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            buffWr = new BufferedWriter(new OutputStreamWriter(System.out));
        this.logType = logType;
    }



    public void log(String toLog) {
        try {
            buffWr.write(toLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logNewLine() {
        try {
            buffWr.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commitLog() {
        try {
            buffWr.flush();
            if (logType != null)
                buffWr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
