package src;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Adnan
 * @edited Jonathan
 */
public class ServicePage extends javax.swing.JFrame {

    Connection conn = null;//allows us to connect to the database
    ResultSet rs = null;
    PreparedStatement ps = null;
    int service_id;
    int userId;
    boolean bool;
    
    public ServicePage(int userId,int service_id) {
        initComponents();
        conn = connect.connect();
        this.service_id = service_id;
        this.userId = userId;
        Update_Name(); //calls update name, to get and display user name
        Update_table();//gets service info and displayes it in table
    }
    public void Getting_userId(int userId){
        this.userId = userId;
    }
    
    public void Getting_service_id(int service_id){
        this.service_id = service_id;
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
    private void Update_table(){//this is how you update a table
       try{
        //sql statement to get service info based on service id passed in
        String sql = "SELECT service_name,time_opens,time_close,time_week,time_day FROM services, times WHERE service_id = "+ service_id + " AND service_id = time_serviceid;";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Fav_Table.setModel(DbUtils.resultSetToTableModel(rs));
       
       
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } } 
    }
    /*
     * this function is to check if the serivice is already
     * a favorite of the user so the user doesnt have
     * the same favorite multiple times
     */
    private boolean exists(){
        
        
        try{
            
            String sql = "SELECT fav_serviceid FROM fav WHERE fav_userid = "+userId+" AND fav_serviceid = "+service_id+" ;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){//if the user has this serivice as a favorite
                bool = true;
            }
            else{//if its not already a favorite
                bool = false;
            }
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }finally {
            try{
                rs.close(); 
                ps.close(); }
            catch(Exception e) { } }  
        return bool;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        Fav_Table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        log_out = new javax.swing.JButton();
        name_field = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Fav_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Building", "Opening", "Closing", "Time of Day", "Day of Week"
            }
        ));
        jScrollPane2.setViewportView(Fav_Table);

        jLabel1.setText("Welcome:");

        log_out.setText("Log Out");
        log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                log_outActionPerformed(evt);
            }
        });

        name_field.setText("jLabel2");

        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        jButton1.setText("Make Favorite");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(home)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(name_field)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(log_out))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name_field)
                    .addComponent(log_out)
                    .addComponent(home))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
//user wants to go back to home page
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
//the user wants to add to favorites
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try{
            if(exists() == false){//checks if it is already a favorite
                String sql = "INSERT into fav VALUES(?,?);";
                ps = conn.prepareStatement(sql);
                String userid = Integer.toString(userId);
                ps.setString(1,userid);
                String servId = Integer.toString(service_id);
                ps.setString(2, servId);
                ps.execute();
                JOptionPane.showMessageDialog(null,"Added to Favorites");//displays message
            }
            else{
                JOptionPane.showMessageDialog(null, "Already in Favorites");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ServicePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServicePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServicePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServicePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ServicePage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Fav_Table;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton log_out;
    private javax.swing.JLabel name_field;
    // End of variables declaration//GEN-END:variables
}
