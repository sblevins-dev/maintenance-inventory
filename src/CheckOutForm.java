
import java.util.ArrayList;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author nechi
 */
public class CheckOutForm extends javax.swing.JFrame
{

    DefaultListModel toolList = new DefaultListModel();
    ArrayList<Tool> tools = new ArrayList();

    private void populateList() throws ClassNotFoundException, SQLException
    {
        DataIO data = new DataIO();

//        ArrayList<Tool> tools = new ArrayList();
        ResultSet td = data.getTools();

        while (td.next())
        {
            Tool tool = new Tool();

            tool.setToolID(td.getInt(1));
            tool.setToolName(td.getString(2));
            tool.setToolLocation(td.getString(3));
            tool.setToolQuantity(td.getInt(4));

            tools.add(tool);
        }

        for (int i = 0; i < tools.size(); i++)
        {
            toolList.addElement(tools.get(i));
        }
    }

    /**
     * Creates new form CheckOutForm
     */
    public CheckOutForm() throws ClassNotFoundException, SQLException
    {
        initComponents();
        this.setLocationRelativeTo(null);
        populateList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEmpID = new javax.swing.JLabel();
        txtEmpID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstTools = new javax.swing.JList<>();
        lblTools = new javax.swing.JLabel();
        btnCheckOut = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEmpID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEmpID.setText("Employee ID:");

        txtEmpID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lstTools.setModel(toolList);
        jScrollPane1.setViewportView(lstTools);

        lblTools.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTools.setText("Tools:");

        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCheckOut.setText("Check Out");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCheckOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTools)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblEmpID)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpID)
                    .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTools))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnCheckOut))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBackActionPerformed
    {//GEN-HEADEREND:event_btnBackActionPerformed
        EmployeeLogin frame = new EmployeeLogin();
        frame.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCheckOutActionPerformed
    {//GEN-HEADEREND:event_btnCheckOutActionPerformed
        JFrame frame = new JFrame();
        if (validateInputs())
        {
            int empID = Integer.parseInt(txtEmpID.getText());
            Integer[] tools = getSelectedRows();
            Random rand = new Random();
            int rentalID = rand.nextInt(10000);

            DataIO data = new DataIO();
            try
            {
                data.checkOutTools(empID, tools, rentalID);

                JOptionPane.showMessageDialog(frame,
                        "Successfully checked out rental!");
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(
                        CheckOutForm.class.getName())
                        .log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(frame, ex,
                        "Class Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex)
            {
                Logger.getLogger(CheckOutForm.class.getName())
                        .log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(frame, ex,
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
        {
            JOptionPane.showMessageDialog(frame,
                    "Please select correct information",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCheckOutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
            throws ClassNotFoundException, SQLException
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
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new CheckOutForm().setVisible(true);
                } catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(CheckOutForm.class.getName())
                            .log(Level.SEVERE, null, ex);
                } catch (SQLException ex)
                {
                    Logger.getLogger(CheckOutForm.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel lblTools;
    private javax.swing.JList<String> lstTools;
    private javax.swing.JTextField txtEmpID;
    // End of variables declaration//GEN-END:variables

    private Integer[] getSelectedRows()
    {
        int[] indices = lstTools.getSelectedIndices();
        int rowCount = indices.length;
        Integer[] toolIDs = new Integer[rowCount];

        for (int i = 0; i < indices.length; i++)
        {
            Tool tool = tools.get(indices[i]);
            toolIDs[i] = tool.getToolID();
        }

        return toolIDs;
    }

    private boolean validateInputs()
    {
        if (txtEmpID.getText().isEmpty()
                || lstTools.getSelectedIndices().length == 0)
        {
            return false;
        }

        return true;
    }
}
