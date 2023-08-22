/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author nechi
 */
public class RentalHistory extends javax.swing.JFrame
{

    DefaultListModel<Rental> rentalIdList = new DefaultListModel();

    /**
     * Creates new form RentalHistory
     */
    public RentalHistory() throws ClassNotFoundException, SQLException
    {
        initComponents();
        this.setLocationRelativeTo(null);
        pullRentalHistory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane2 = new javax.swing.JScrollPane();
        lstRentals = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDetails = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstRentals.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lstRentals.setModel(rentalIdList);
        lstRentals.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                lstRentalsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstRentals);

        txaDetails.setColumns(20);
        txaDetails.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txaDetails.setRows(5);
        jScrollPane3.setViewportView(txaDetails);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstRentalsValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_lstRentalsValueChanged
    {//GEN-HEADEREND:event_lstRentalsValueChanged
        //get selected items index num
        int index = lstRentals.getSelectedIndex();

        //if selected show details
        if (index > -1)
        {
            Rental rent = rentalIdList.getElementAt(index);
            String output = "";
            try
            {
                dataIO data = new dataIO();
                ArrayList tools = data.getToolsList(rent.getRentalID());
                
                txaDetails.setText("");
                
                for (int i = 0; i < tools.size(); i++)
                {
                    output += tools.get(i);
                    output += "\n";
                }
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex)
            {
                Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            //lstDetails.setText(rent.showRentalDetails(rent));
            txaDetails.setText(output);
        }
    }//GEN-LAST:event_lstRentalsValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        EmployeeHomePage frame = new EmployeeHomePage();
        frame.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(RentalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RentalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RentalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RentalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //new RentalHistory().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<Rental> lstRentals;
    private javax.swing.JTextArea txaDetails;
    // End of variables declaration//GEN-END:variables

    private void pullRentalHistory()
            throws ClassNotFoundException, SQLException
    {
        dataIO dataResults = new dataIO();

        int id = Employee.getEmployeeID();
        ArrayList<Rental> rentals = dataResults.getRentalHistory(id);

        rentalIdList.clear();
        for (int i = 0; i < rentals.size(); i++)
        {
            rentalIdList.addElement(rentals.get(i));
        }

    }
}