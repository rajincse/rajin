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
import java.net.ServerSocket;
import virtual.eyetracker.gui.ConsoleContainer;

/**
 *
 * @author rajin
 */
public class RecorderModule implements EyeTrackerDataReceiver{
    private EyeTrackServer eyeTrackerServer;
    private StringBuffer resultText;
    private int port;
    private ConsoleContainer console;
    public RecorderModule(int port, ConsoleContainer console)
    {
        this.port = port;
        this.console = console;
        this.resultText = new StringBuffer();
        this.eyeTrackerServer = new EyeTrackServer(port,this);
                
    }
    public void start()
    {
        this.eyeTrackerServer.start();
        console.printToConsole("Recorder started");
    }
    public StringBuffer getResultText()
    {
        return this.resultText;
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
                console.printToConsole("Saved to :"+fileName);

        } catch (IOException e) {
                e.printStackTrace();
                console.printToConsole(e.getMessage());
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
         console.printToConsole("Got  :"+gazePoint);
    }
    public static boolean isPortOpen(int port)
    {
        boolean success = false;
        try {
            ServerSocket socket = new ServerSocket(port);
            success = true;
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return success;
    }
}
