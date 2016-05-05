using EyeXFramework;
using Tobii.EyeX.Framework;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace VizLabEyeTrackMiddleware.VizLab.FIU.Eye
{
    class TobiiGazeHandler: IGazeHandler 
    {
        public static readonly string HOST = "127.0.0.1";
        public static readonly int PORT = 9876;


        private StreamWriter swSender;
        private TcpClient tcpServer;
        private EyeXHost eyeXHost;
        private GazePointDataStream gazeDataStream;
        private FixationDataStream fixationDataStream;

        private string host;
        private int port;

        private bool isGazeDataActive;

        public bool IsGazeDataActive
        {
            get { return isGazeDataActive; }
            set { isGazeDataActive = value; }
        }
        private bool isFixationDataActive;

        public bool IsFixationDataActive
        {
            get { return isFixationDataActive; }
            set { isFixationDataActive = value; }
        }

        public TobiiGazeHandler()
            : this(TobiiGazeHandler.HOST, TobiiGazeHandler.PORT)
        { }
        public TobiiGazeHandler(string host, int port)
        {
            this.host = host;
            this.port = port;
        }
        public void StartServer()
        {
            this.eyeXHost = new EyeXHost();
            this.eyeXHost.Start();
            this.gazeDataStream = eyeXHost.CreateGazePointDataStream(GazePointDataMode.LightlyFiltered);
            this.gazeDataStream.Next += new System.EventHandler<EyeXFramework.GazePointEventArgs>(this.HandleEyeGazeEvent);

            this.fixationDataStream = eyeXHost.CreateFixationDataStream(FixationDataMode.Slow);
            this.fixationDataStream.Next += new System.EventHandler<EyeXFramework.FixationEventArgs>(this.HandleEyeFixationEvent);

            try
            {
                IPAddress ipAddr = IPAddress.Parse(host);
                tcpServer = new TcpClient();
                tcpServer.Connect(ipAddr, port);
                swSender = new StreamWriter(tcpServer.GetStream());
            }
            catch
            {
                this.StopServer();
            }

        }
        public  void HandleEyeGazeEvent(object sender, GazePointEventArgs e)
        {
            //Console.WriteLine("Gaze point at\t{0:0.0}\t{1:0.0}\t\t{2:0}", e.X, e.Y, e.Timestamp);
            
            if (this.tcpServer.Connected && this.isGazeDataActive)
            {
                SendGaze((int)e.X, (int)e.Y, 0);
            }
            
        }
        public void HandleEyeFixationEvent(object sender, FixationEventArgs e)
        {
            //Console.WriteLine("Fixation ({3})point at\t{0:0.0}\t{1:0}\t\t{2}", e.X, e.Y, e.Timestamp, e.EventType);
            
            if (this.tcpServer.Connected && this.isFixationDataActive )
            {

                SendGaze((int)e.X, (int)e.Y, 0, IGazeHandler.METHOD_FIXATION, new string[]{""+e.EventType});
            }
        }

        public void SendGaze(int x, int y, double pupilSize)
        {
            try
            {
                string s = x + "," + y + "," + pupilSize + "," + x + "," + y + "," + pupilSize;
                Byte[] sendBytes = Encoding.ASCII.GetBytes(s);

                swSender.WriteLine(s);
                swSender.Flush();
            }
            catch
            {
                this.StopServer();
                
            }
            
        }


        public void Calibrate()
        {
            // Do nothing
        }
        

        public void StopServer()
        {
            if(this.swSender != null)
            {
                this.swSender.Close();
            }
            
            this.tcpServer.Close();
            this.gazeDataStream.Dispose();
            this.fixationDataStream.Dispose();
            this.eyeXHost.Dispose();
            MessageBox.Show("Server stopped");            
        }


        public void SendGaze(int x, int y, double pupilSize, string method, string[] args)
        {
            try
            {
                string s = x + "," + y + "," + pupilSize + "," + x + "," + y + "," + pupilSize+","+method;
                if(args != null)
                {
                    foreach(String arg in args)
                    {
                        s += "," + arg;
                    }
                }

                Byte[] sendBytes = Encoding.ASCII.GetBytes(s);

                swSender.WriteLine(s);
                swSender.Flush();
            }
            catch
            {
                this.StopServer();

            }
        }
    }
}
