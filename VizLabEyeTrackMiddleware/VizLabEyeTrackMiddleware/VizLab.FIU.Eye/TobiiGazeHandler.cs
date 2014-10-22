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
        private GazePointDataStream dataStream;

        private string host;
        private int port;
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
            this.dataStream = eyeXHost.CreateGazePointDataStream(GazePointDataMode.LightlyFiltered);
            this.dataStream.Next += new System.EventHandler<EyeXFramework.GazePointEventArgs>(this.HandleEyeGazeEvent);
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
            if (this.tcpServer.Connected)
            {
                SendGaze((int)e.X, (int)e.Y, 0);
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
            this.dataStream.Dispose();
            this.eyeXHost.Dispose();
            MessageBox.Show("Server stopped");
        }
    }
}
