package src;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Dinesh
 * @edited Jonathan 
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null; 
    
    public Login() {
        initComponents();
        conn=connect.connect();
    }
    public void close() {
              WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        text_username = new javax.swing.JTextField();
        text_password = new javax.swing.JPasswordField();
        login_button = new javax.swing.JButton();
        register = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Login Page");

        jLabel2.setText("Username");

        jLabel3.setText("Password");

        text_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_usernameActionPerformed(evt);
            }
        });

        text_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_passwordActionPerformed(evt);
            }
        });

        login_button.setText("Login");
        login_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_buttonActionPerformed(evt);
            }
        });

        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(login_button)
                                .addGap(18, 18, 18)
                                .addComponent(register))
                            .addComponent(text_username, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_password, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(text_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_button)
                    .addComponent(register))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_passwordActionPerformed

    private void text_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_usernameActionPerformed
/*
 * this is when the user presses the login button
 * will check the database if the user exists and 
 * if the password is the same thats stored in the database
 * 
 * **not a secure login**
 */
    private void login_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_buttonActionPerformed
       
        try{ 
            String sql = "SELECT * FROM user WHERE user_name = ? AND user_pass = ?;";
           ps = conn.prepareStatement(sql);
           //the setStrings will replace the "?" in the sql
           //statement with what is in the username text box
           //and the password text box
           ps.setString(1,text_username.getText());
           ps.setString(2,text_password.getText());
           rs=ps.executeQuery();
            if(rs.next()) {//if username and password is correct will go to the home screen
                JOptionPane.showMessageDialog(null, "Username and password is correct");
               String id = rs.getString("user_id");
                int userID = Integer.parseInt(id);
                ps.close();
                rs.close();
                Home x = new Home(userID);
                x.setVisible(true);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Username and password is incorrect.");
            }
        }
        
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_login_buttonActionPerformed
/*
 * if user wants to create an account he/she will select register
 * and will bring them to the register page
 * 
 */
    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        try{
            conn.close();
            rs.close();
            ps.close(); }
        catch(Exception e) { }
        NewUser x = new NewUser();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login_button;
    private javax.swing.JButton register;
    private javax.swing.JPasswordField text_password;
    private javax.swing.JTextField text_username;
    // End of variables declaration//GEN-END:variables
}
