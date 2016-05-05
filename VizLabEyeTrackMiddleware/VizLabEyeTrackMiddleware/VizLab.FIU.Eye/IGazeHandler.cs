using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VizLabEyeTrackMiddleware.VizLab.FIU.Eye
{
    interface IGazeHandler
    {
        public static readonly string METHOD_GAZE = "Gaze";
        public static readonly string METHOD_FIXATION = "Fixation";
        void StartServer();
        void StopServer();
        void SendGaze(int x, int y, double pupilSize);

        void SendGaze(int x, int y, double pupilSize, string method, string[] args);

        void Calibrate();
    }
}
