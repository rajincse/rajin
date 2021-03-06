/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.eyetracker.gui;

import eyetrack.EyeTrackServer;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import virtual.eyetracker.RecorderModule;
import virtual.eyetracker.SimulationModule;

/**
 *
 * @author rajin
 */
public class AppFrame extends javax.swing.JFrame implements ConsoleContainer{

    /**
     * Creates new form AppFrame
     */
    public AppFrame() {
       
        initComponents();
        init();
    }

    private void init()
    {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
                e1.printStackTrace();
        }
        this.txtPort.setText(eyetrack.EyeTrackServer.PORT+"");
        DefaultCaret caret = (DefaultCaret)this.txtConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.simulationModule = new SimulationModule(EyeTrackServer.PORT, this);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainToolBar = new javax.swing.JToolBar();
        btnOpenFile = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnPlay = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnStop = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnRecord = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnSave = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lblConsole = new javax.swing.JLabel();
        scrollConsole = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        chbxRecordMode = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vritual Eye Tracker");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        btnOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/virtual/eyetracker/gui/images/Open.gif"))); // NOI18N
        btnOpenFile.setText("Open");
        btnOpenFile.setFocusable(false);
        btnOpenFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });
        mainToolBar.add(btnOpenFile);
        mainToolBar.add(jSeparator1);

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/virtual/eyetracker/gui/images/Play.png"))); // NOI18N
        btnPlay.setText("Play");
        btnPlay.setFocusable(false);
        btnPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        mainToolBar.add(btnPlay);
        mainToolBar.add(jSeparator2);

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/virtual/eyetracker/gui/images/Stop.png"))); // NOI18N
        btnStop.setText("Stop");
        btnStop.setFocusable(false);
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        mainToolBar.add(btnStop);
        mainToolBar.add(jSeparator5);

        btnRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/virtual/eyetracker/gui/images/Record.png"))); // NOI18N
        btnRecord.setText("Record");
        btnRecord.setFocusable(false);
        btnRecord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordActionPerformed(evt);
            }
        });
        mainToolBar.add(btnRecord);
        mainToolBar.add(jSeparator3);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/virtual/eyetracker/gui/images/Save.gif"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        mainToolBar.add(btnSave);
        mainToolBar.add(jSeparator4);

        lblPort.setText("Port:");
        mainToolBar.add(lblPort);
        mainToolBar.add(txtPort);

        lblConsole.setText("Console:");

        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        scrollConsole.setViewportView(txtConsole);

        chbxRecordMode.setText("Record Mode");
        chbxRecordMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxRecordModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsole)
                .addGap(37, 37, 37)
                .addComponent(chbxRecordMode)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollConsole, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsole)
                    .addComponent(chbxRecordMode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
        String fileName = this.showOpenFileDialog();
        if(fileName != null)
        {
            this.simulationModule.openFile(fileName);
        }
        
        
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        this.simulationModule.play();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordActionPerformed
        if(this.recordModule == null)
        {
           int val = JOptionPane.showConfirmDialog(this, "Are you sure to start the recorder?", "Confirmation", JOptionPane.YES_NO_OPTION);
           if(val == JOptionPane.YES_OPTION)
           {
               if(RecorderModule.isPortOpen(EyeTrackServer.PORT))
               {
                   this.recordModule = new RecorderModule(EyeTrackServer.PORT, this);
                   this.recordModule.start();
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "Port "+EyeTrackServer.PORT+" is occupied");
               }
               
           }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Already recording!");
        }
        
        
    }//GEN-LAST:event_btnRecordActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       if(this.recordModule!= null && this.recordModule.getResultText().length() > 0)
       {   
        String fileName = this.showSaveFileDialog();
        if(fileName!=null)
        {
            this.recordModule.saveToFile(fileName);
        }
       }
       else
       {
           JOptionPane.showMessageDialog(this, "No data");
       }
       
    }//GEN-LAST:event_btnSaveActionPerformed

   
    
    private String showOpenFileDialog()
    {
        JFileChooser fc = getFileChooser();
              
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
               String fileName =fc.getSelectedFile().getAbsolutePath();			        
               
               return fileName;
        }
        else
        {
           return null;
        }
    }
    private String showSaveFileDialog()
    {
        JFileChooser fc = getFileChooser();
              
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
               String fileName =fc.getSelectedFile().getAbsolutePath();			        
               if(!fileName.toLowerCase().endsWith(".txt"))
               {
                   fileName+=".txt";
               }
               return fileName;
        }
        else
        {
           return null;
        }
    }
    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        this.simulationModule.stop();
    }//GEN-LAST:event_btnStopActionPerformed

    private void chbxRecordModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxRecordModeActionPerformed
        setRecordMode(this.chbxRecordMode.isSelected());
    }//GEN-LAST:event_chbxRecordModeActionPerformed

    private JFileChooser getFileChooser()
    {
        JFileChooser fc = new JFileChooser();
         
        FileNameExtensionFilter ff = new FileNameExtensionFilter("Text Files","txt");
        fc.setFileFilter(ff);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
        return fc;
    }
    private void setRecordMode(boolean isRecordMode)
    {
        if(isRecordMode)
        {
            this.btnOpenFile.setEnabled(false);
            this.btnPlay.setEnabled(false);
            this.btnStop.setEnabled(false);
            this.btnRecord.setEnabled(true);
            this.btnSave.setEnabled(true);            
        }
        else
        {
            this.btnOpenFile.setEnabled(true);
            this.btnPlay.setEnabled(true);
            this.btnStop.setEnabled(true);
            this.btnRecord.setEnabled(false);
            this.btnSave.setEnabled(false);            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppFrame().setVisible(true);
            }
        });
    }
    
   
    private RecorderModule recordModule;
    private SimulationModule simulationModule;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRecord;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStop;
    private javax.swing.JCheckBox chbxRecordMode;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JLabel lblConsole;
    private javax.swing.JLabel lblPort;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JScrollPane scrollConsole;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

    @Override
    public void printToConsole(String message) {        
        this.txtConsole.append(message+"\r\n");
    }

    
        
}
