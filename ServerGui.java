/*yaron yannay id:031996515
 */
package maman16ex1server;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.*;    
import java.awt.event.*;   
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;

public class ServerGui  extends JPanel {
      private JButton cmdModifyComputer;
      private JTextArea    messageText ;
      private final String onLineMessage="Server on line:\n ";
      
       public ServerGui() {
           setLayout(new BorderLayout());
           cmdModifyComputer = new JButton("Modify");
            add(cmdModifyComputer,BorderLayout.WEST);
             ButtonsListener listener = new ButtonsListener();
                 messageText = new JTextArea(10, 30);
                messageText.setEditable(false);
                JScrollPane MessagesArea = new JScrollPane(messageText);
                messageText.setFont(new java.awt.Font("Courier New", 0, 18));
    
                 MessagesArea.setViewportView(messageText);
        
                  messageText.setText(onLineMessage);
                  add(MessagesArea,BorderLayout.CENTER);
                  
         cmdModifyComputer.addActionListener(listener);
        
       }
       
           private class ButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == cmdModifyComputer) {
                 String computerName = JOptionPane.showInputDialog(null, "Enter computer name:"); 
                 String currMessage=onLineMessage+"computer name:"+computerName;
                 messageText.setText(currMessage);
            }
            
           
         }
      }
}
