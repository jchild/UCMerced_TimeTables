package src;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jonathan
 */
public class Contact_Us extends javax.swing.JFrame {

    Connection conn = null;//allows us to connect to the database
    ResultSet rs = null;
    PreparedStatement ps = null;
    int userId;
    String feedid;
    
    public Contact_Us(int userId) {
        this.userId = userId;
        initComponents();
        conn = connect.connect();
        Update_Name();
    }
    
        public void Getting_userName(int userId){
        this.userId = userId;
    }
    private void Update_Name(){//this is changing the text field to the user's name
        try{
            String sql = "SELECT user_name FROM user WHERE user_id = " +userId+";";//sql statement
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                String i = rs.getString("user_name");
                name_field.setText(i);//setting the name_field to the user name, will change the text of that field
            }
        }
        catch(Exception e){//if the sql stament is wrong or errors it will throw exception
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();//always close these after every sql statment to prevent locks
                ps.close();
            }
            catch(Exception e){//if cannot close will throw exception
                JOptionPane.showMessageDialog(null, e);
            }
        }
            
    }
    /*
     * this will check for the next avaliable feedbackId number
     */
    private void getfeedId(){
        try{
            String sql = "SELECT Max(feed_id)+1 FROM feedback";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.getString("max(feed_id)+1") == null){
                feedid = "1";
            }
            else{
                feedid = rs.getString("max(feed_id)+1");
                
              
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    /*
     * this will store the feeback the user provided into the database
     * will call getfeedId() to get the next avaliable feedback Id number
     */
    private void feedback(){
        getfeedId();
        String sql = "INSERT into feedback values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, feedid);
            String uid = Integer.toString(userId);
            ps.setString(2, uid);
            ps.setString(3, feedback.getText());
            ps.execute();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();//always close these after every sql statment to prevent locks
                ps.close();
            }
            catch(Exception e){//if cannot close will throw exception
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        home = new javax.swing.JButton();
        log_out = new javax.swing.JButton();
        name_field = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedback = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel1.setText("Contact Us");

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        log_out.setText("Log Out");
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });

        name_field.setText("jLabel2");

        jLabel2.setText("Welcome:");

        jLabel3.setText("Have questions? Find something wrong?");

        jLabel4.setText("Leave us a message and we will get to it as soon as we possible");

        feedback.setColumns(20);
        feedback.setRows(5);
        jScrollPane1.setViewportView(feedback);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(home)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(name_field)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(log_out))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submit)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(home)
                        .addComponent(jLabel2)
                        .addComponent(name_field)
                        .addComponent(log_out)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
 * when the user hits submit
 * will store the feedback into
 * the database
 */
    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        try{
            feedback();
            conn.close();
            rs.close();
            ps.close();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Thank you for the Feedback, we will respond shortly");
        Home x = new Home(userId);
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_submitActionPerformed
//log out button
    private void log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_log_outActionPerformed
        try{
            conn.close();
            rs.close();
            ps.close(); }
        catch(Exception e) { }
        Login x = new Login();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_log_outActionPerformed
// this brings the user back to home page
    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        try{
            conn.close();
            rs.close();
            ps.close(); }
        catch(Exception e) { }
        Home x = new Home(userId);
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

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
            java.util.logging.Logger.getLogger(Contact_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contact_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contact_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contact_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Contact_Us().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea feedback;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton log_out;
    private javax.swing.JLabel name_field;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
