/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.eyetracker;

import eyetrack.EyeTrackServer;
import eyetrack.EyeTrackerDataReceiver;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author rajin
 */
public class RecorderModule implements EyeTrackerDataReceiver{
    private EyeTrackServer eyeTrackerServer;
    private StringBuffer resultText;
    private int port;
    public RecorderModule(int port)
    {
        this.port = port;
        this.resultText = new StringBuffer();
        this.eyeTrackerServer = new EyeTrackServer(port,this);
                
    }
    public void start()
    {
        this.eyeTrackerServer.start();
    }
    public void saveToFile(String fileName)
    {
        try {
                

                FileWriter fstream = new FileWriter(new File(fileName), false);
                BufferedWriter br = new BufferedWriter(fstream);

                br.write(resultText.toString());

                br.close();
                synchronized (this) {
                        resultText.setLength(0);
                }


        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    @Override
    public void processGaze(Point gazePoint, double pupilDiameter) {
        resultText.append(System.currentTimeMillis());
        resultText.append("\t");
        resultText.append(gazePoint.x);
        resultText.append("\t");
        resultText.append(gazePoint.y);
        resultText.append("\t");
        resultText.append(String.format("%.2f", pupilDiameter));
        resultText.append("\r\n");
        
    }
}
