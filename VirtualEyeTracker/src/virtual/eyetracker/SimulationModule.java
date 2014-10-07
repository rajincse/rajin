/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.eyetracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajin
 */
public class SimulationModule implements Runnable{
    
    private ArrayList<Long> delayList;
    
    private ArrayList<String> gazeDataLines;
    private int port;
    private Thread thread;
    private boolean running;
    private Socket socket;
    public SimulationModule(int port)
    {
        this.port = port;
        this.gazeDataLines = new ArrayList<String>();
        this.delayList = new ArrayList<Long>();
        this.thread = null  ;          
        this.running = false;
    }
    public void play()
    {
        synchronized(this)
        {
            if(this.thread == null)
            {

               
                try {
                    socket = new Socket("127.0.0.1", this.port);
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                running = true;             
                this.thread = new Thread(this);
                this.thread.start();   
            }
            else
            {
                    running = true;
                    notify();
            }
        }

    }
    
    public void stop()
    {
        synchronized(this)
        {
            running = false;
        }
    }
    public void openFile(String fileName)
    {
        
        try {
            File file = new File(fileName);
            FileReader fileStream = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileStream);
            this.gazeDataLines.clear();
            String line = reader.readLine();
            long lastTimestamp= Util.INVALID;
            
            while(line != null)
            {
                String split[] = line.split("\t");
                if(split.length>=4)
                {
                    long timestamp = Long.parseLong(split[0]);
                    long delay =0;
                    if(lastTimestamp != Util.INVALID)
                    {
                        delay = timestamp - lastTimestamp;
                    }
                    lastTimestamp = timestamp;
                    this.delayList.add(delay);
                    
                    String gazeX = split[1];
                    String gazeY = split[2];
                    String pupil = split[3];
                    String outputLine = gazeX+","+gazeY+","+pupil+","+gazeX+","+gazeY+","+pupil;
                    this.gazeDataLines.add(outputLine);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch(IOException ex)
        {
            ex.printStackTrace();
        }
        
        
    }

    @Override
    public void run() {
        while(true)
        { 
                try {

                    for(int i=0;i<gazeDataLines.size();i++)
                    {
                        synchronized(this)
                        {
                            if(!running)
                            {
                                    wait();
                            }
                        }
                        long delay = this.delayList.get(i);

                            Thread.sleep(delay);

                        String line = this.gazeDataLines.get(i);
                        transmitData(line);
                    }


                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                catch(IOException ex)
                {
                    System.out.println("Error sending data");
                    ex.printStackTrace();
                }
          }
    }
    
    private void transmitData(String line) throws IOException 
    {
        if(this.socket != null)
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(line);
            out.flush();
//            out.close();
        }
        
                
    }
}
