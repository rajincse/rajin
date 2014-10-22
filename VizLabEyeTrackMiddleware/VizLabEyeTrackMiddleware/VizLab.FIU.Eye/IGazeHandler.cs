using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VizLabEyeTrackMiddleware.VizLab.FIU.Eye
{
    interface IGazeHandler
    {
        
        void StartServer();
        void StopServer();
        void SendGaze(int x, int y, double pupilSize);

        void Calibrate();
    }
}
