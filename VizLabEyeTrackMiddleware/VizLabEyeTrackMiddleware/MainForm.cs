using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using VizLabEyeTrackMiddleware.VizLab.FIU.Eye;

namespace VizLabEyeTrackMiddleware
{
    public partial class MainForm : Form
    {
        public static readonly string DEVICE_TOBII_EYEX="Tobii EyeX";
        public static readonly string DEVICE_SMI_RED = "SMI RED";

        public static readonly int INDEX_TOBII = 0;
        public static readonly int INDEX_SMI = 1;

        private TobiiGazeHandler tobiiClient;
        public MainForm()
        {
            InitializeComponent();
            Init();
        }

        private void Init()
        {
            this.cbxDeviceSelection.Items.Add(DEVICE_TOBII_EYEX);
            this.cbxDeviceSelection.Items.Add(DEVICE_SMI_RED);
            HideAll();

            this.tobiiClient = new TobiiGazeHandler();
            this.checkBoxSamplesTobii.Checked = true;
            this.checkBoxFixationsTobii.Checked = false;
        }
        private void HideAll()
        {
            this.pnlSMI.Visible = false;
            this.pnlTobii.Visible = false;
        }
        private void cbxDeviceSelection_SelectedIndexChanged(object sender, EventArgs e)
        {
            HideAll();
            if(this.cbxDeviceSelection.SelectedIndex == INDEX_TOBII)
            {
                this.pnlTobii.Visible = true;
            }
            else if(this.cbxDeviceSelection.SelectedIndex == INDEX_SMI)
            {
                this.pnlSMI.Visible = true;
            }
        }

        private void btnStartTobii_Click(object sender, EventArgs e)
        {
            this.tobiiClient.StartServer();
        }

        private void btnStopTobii_Click(object sender, EventArgs e)
        {
            this.tobiiClient.StopServer();
        }

        private void btnStartSMI_Click(object sender, EventArgs e)
        {

        }

        private void btnCalibrate_Click(object sender, EventArgs e)
        {

        }

        private void btnStopSMI_Click(object sender, EventArgs e)
        {

        }

        private void checkBoxSamplesTobii_CheckedChanged(object sender, EventArgs e)
        {
            this.tobiiClient.IsGazeDataActive = this.checkBoxSamplesTobii.Checked;
            Console.WriteLine(" Check box changed:" + e.ToString());
        }

        private void checkBoxFixationsTobii_CheckedChanged(object sender, EventArgs e)
        {
            this.tobiiClient.IsFixationDataActive = this.checkBoxFixationsTobii.Checked;
        }

    }
}
