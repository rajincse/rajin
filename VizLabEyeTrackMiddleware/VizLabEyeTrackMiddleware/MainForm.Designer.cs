namespace VizLabEyeTrackMiddleware
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.cbxDeviceSelection = new System.Windows.Forms.ComboBox();
            this.lblDeviceSelection = new System.Windows.Forms.Label();
            this.pnlTobii = new System.Windows.Forms.Panel();
            this.checkBoxFixationsTobii = new System.Windows.Forms.CheckBox();
            this.checkBoxSamplesTobii = new System.Windows.Forms.CheckBox();
            this.pbxTobii = new System.Windows.Forms.PictureBox();
            this.btnStopTobii = new System.Windows.Forms.Button();
            this.btnStartTobii = new System.Windows.Forms.Button();
            this.btnStopSMI = new System.Windows.Forms.Button();
            this.btnStartSMI = new System.Windows.Forms.Button();
            this.pnlSMI = new System.Windows.Forms.Panel();
            this.pbxSMI = new System.Windows.Forms.PictureBox();
            this.btnCalibrate = new System.Windows.Forms.Button();
            this.pnlTobii.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbxTobii)).BeginInit();
            this.pnlSMI.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbxSMI)).BeginInit();
            this.SuspendLayout();
            // 
            // cbxDeviceSelection
            // 
            this.cbxDeviceSelection.FormattingEnabled = true;
            this.cbxDeviceSelection.Location = new System.Drawing.Point(138, 12);
            this.cbxDeviceSelection.Name = "cbxDeviceSelection";
            this.cbxDeviceSelection.Size = new System.Drawing.Size(121, 21);
            this.cbxDeviceSelection.TabIndex = 0;
            this.cbxDeviceSelection.SelectedIndexChanged += new System.EventHandler(this.cbxDeviceSelection_SelectedIndexChanged);
            // 
            // lblDeviceSelection
            // 
            this.lblDeviceSelection.AutoSize = true;
            this.lblDeviceSelection.Location = new System.Drawing.Point(35, 15);
            this.lblDeviceSelection.Name = "lblDeviceSelection";
            this.lblDeviceSelection.Size = new System.Drawing.Size(77, 13);
            this.lblDeviceSelection.TabIndex = 1;
            this.lblDeviceSelection.Text = "Select Device:";
            // 
            // pnlTobii
            // 
            this.pnlTobii.Controls.Add(this.checkBoxFixationsTobii);
            this.pnlTobii.Controls.Add(this.checkBoxSamplesTobii);
            this.pnlTobii.Controls.Add(this.pbxTobii);
            this.pnlTobii.Controls.Add(this.btnStopTobii);
            this.pnlTobii.Controls.Add(this.btnStartTobii);
            this.pnlTobii.Location = new System.Drawing.Point(19, 39);
            this.pnlTobii.Name = "pnlTobii";
            this.pnlTobii.Size = new System.Drawing.Size(256, 100);
            this.pnlTobii.TabIndex = 2;
            // 
            // checkBoxFixationsTobii
            // 
            this.checkBoxFixationsTobii.AutoSize = true;
            this.checkBoxFixationsTobii.Location = new System.Drawing.Point(96, 80);
            this.checkBoxFixationsTobii.Name = "checkBoxFixationsTobii";
            this.checkBoxFixationsTobii.Size = new System.Drawing.Size(67, 17);
            this.checkBoxFixationsTobii.TabIndex = 5;
            this.checkBoxFixationsTobii.Text = "Fixations";
            this.checkBoxFixationsTobii.UseVisualStyleBackColor = true;
            this.checkBoxFixationsTobii.CheckedChanged += new System.EventHandler(this.checkBoxFixationsTobii_CheckedChanged);
            // 
            // checkBoxSamplesTobii
            // 
            this.checkBoxSamplesTobii.AutoSize = true;
            this.checkBoxSamplesTobii.Location = new System.Drawing.Point(15, 80);
            this.checkBoxSamplesTobii.Name = "checkBoxSamplesTobii";
            this.checkBoxSamplesTobii.Size = new System.Drawing.Size(66, 17);
            this.checkBoxSamplesTobii.TabIndex = 4;
            this.checkBoxSamplesTobii.Text = "Samples";
            this.checkBoxSamplesTobii.UseVisualStyleBackColor = true;
            this.checkBoxSamplesTobii.CheckedChanged += new System.EventHandler(this.checkBoxSamplesTobii_CheckedChanged);
            // 
            // pbxTobii
            // 
            this.pbxTobii.Image = ((System.Drawing.Image)(resources.GetObject("pbxTobii.Image")));
            this.pbxTobii.Location = new System.Drawing.Point(15, 4);
            this.pbxTobii.Name = "pbxTobii";
            this.pbxTobii.Size = new System.Drawing.Size(75, 28);
            this.pbxTobii.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxTobii.TabIndex = 2;
            this.pbxTobii.TabStop = false;
            // 
            // btnStopTobii
            // 
            this.btnStopTobii.Location = new System.Drawing.Point(165, 37);
            this.btnStopTobii.Name = "btnStopTobii";
            this.btnStopTobii.Size = new System.Drawing.Size(75, 23);
            this.btnStopTobii.TabIndex = 1;
            this.btnStopTobii.Text = "Stop";
            this.btnStopTobii.UseVisualStyleBackColor = true;
            this.btnStopTobii.Click += new System.EventHandler(this.btnStopTobii_Click);
            // 
            // btnStartTobii
            // 
            this.btnStartTobii.Location = new System.Drawing.Point(15, 38);
            this.btnStartTobii.Name = "btnStartTobii";
            this.btnStartTobii.Size = new System.Drawing.Size(75, 23);
            this.btnStartTobii.TabIndex = 0;
            this.btnStartTobii.Text = "Start";
            this.btnStartTobii.UseVisualStyleBackColor = true;
            this.btnStartTobii.Click += new System.EventHandler(this.btnStartTobii_Click);
            // 
            // btnStopSMI
            // 
            this.btnStopSMI.Location = new System.Drawing.Point(155, 37);
            this.btnStopSMI.Name = "btnStopSMI";
            this.btnStopSMI.Size = new System.Drawing.Size(75, 23);
            this.btnStopSMI.TabIndex = 1;
            this.btnStopSMI.Text = "Stop";
            this.btnStopSMI.UseVisualStyleBackColor = true;
            this.btnStopSMI.Click += new System.EventHandler(this.btnStopSMI_Click);
            // 
            // btnStartSMI
            // 
            this.btnStartSMI.Location = new System.Drawing.Point(12, 37);
            this.btnStartSMI.Name = "btnStartSMI";
            this.btnStartSMI.Size = new System.Drawing.Size(75, 23);
            this.btnStartSMI.TabIndex = 0;
            this.btnStartSMI.Text = "Start";
            this.btnStartSMI.UseVisualStyleBackColor = true;
            this.btnStartSMI.Click += new System.EventHandler(this.btnStartSMI_Click);
            // 
            // pnlSMI
            // 
            this.pnlSMI.Controls.Add(this.pbxSMI);
            this.pnlSMI.Controls.Add(this.btnCalibrate);
            this.pnlSMI.Controls.Add(this.btnStopSMI);
            this.pnlSMI.Controls.Add(this.btnStartSMI);
            this.pnlSMI.Location = new System.Drawing.Point(19, 39);
            this.pnlSMI.Name = "pnlSMI";
            this.pnlSMI.Size = new System.Drawing.Size(256, 100);
            this.pnlSMI.TabIndex = 3;
            // 
            // pbxSMI
            // 
            this.pbxSMI.Image = ((System.Drawing.Image)(resources.GetObject("pbxSMI.Image")));
            this.pbxSMI.Location = new System.Drawing.Point(15, 3);
            this.pbxSMI.Name = "pbxSMI";
            this.pbxSMI.Size = new System.Drawing.Size(75, 28);
            this.pbxSMI.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxSMI.TabIndex = 3;
            this.pbxSMI.TabStop = false;
            // 
            // btnCalibrate
            // 
            this.btnCalibrate.Location = new System.Drawing.Point(15, 67);
            this.btnCalibrate.Name = "btnCalibrate";
            this.btnCalibrate.Size = new System.Drawing.Size(75, 23);
            this.btnCalibrate.TabIndex = 2;
            this.btnCalibrate.Text = "Calibrate";
            this.btnCalibrate.UseVisualStyleBackColor = true;
            this.btnCalibrate.Click += new System.EventHandler(this.btnCalibrate_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(287, 144);
            this.Controls.Add(this.pnlTobii);
            this.Controls.Add(this.pnlSMI);
            this.Controls.Add(this.lblDeviceSelection);
            this.Controls.Add(this.cbxDeviceSelection);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "MainForm";
            this.Text = "VizLab EyeTracker";
            this.pnlTobii.ResumeLayout(false);
            this.pnlTobii.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbxTobii)).EndInit();
            this.pnlSMI.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pbxSMI)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox cbxDeviceSelection;
        private System.Windows.Forms.Label lblDeviceSelection;
        private System.Windows.Forms.Panel pnlTobii;
        private System.Windows.Forms.Button btnStopTobii;
        private System.Windows.Forms.Button btnStartTobii;
        private System.Windows.Forms.Button btnStopSMI;
        private System.Windows.Forms.Button btnStartSMI;
        private System.Windows.Forms.Panel pnlSMI;
        private System.Windows.Forms.Button btnCalibrate;
        private System.Windows.Forms.PictureBox pbxTobii;
        private System.Windows.Forms.PictureBox pbxSMI;
        private System.Windows.Forms.CheckBox checkBoxFixationsTobii;
        private System.Windows.Forms.CheckBox checkBoxSamplesTobii;
    }
}

