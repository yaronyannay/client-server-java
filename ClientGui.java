/* yaron yannay id:031996515
 */
package maman16ex1b;



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



/**
 *
 * @author me
 */
public class ClientGui extends JPanel {
    private JButton cmdConnect;
    private JButton cmdDisconnect;
    private JButton cmdSendMessage;
    
    private JPanel connectbuttons;
    private JPanel sendbutton;
    private JTextArea messageText;
    private JTextArea userConnectedText;
    private JScrollPane MessagesArea;
    private JScrollPane activeUserNamesArea;
    private JTextField messageToSend = new JTextField("Enter message here");
    
    private PrintWriter out;
    private BufferedReader in;
    private Socket mySocket;
    
    private String messagesAreaString="";
    
     private String ActiveUserText="";
     
     private String clientName;
     
    
    
    
    public ClientGui()  {
        cmdConnect = new JButton("Connect");
        cmdDisconnect= new JButton("Disconenct");
        cmdSendMessage= new JButton("Send message");
        
        connectbuttons = new JPanel();
        connectbuttons.add(cmdConnect);
        connectbuttons.add(cmdDisconnect);
        
        
        setLayout(new BorderLayout());
       
        
        
        add(connectbuttons,BorderLayout.WEST);
       
        
        // messages area
        messageText = new JTextArea(10, 30);
        messageText.setEditable(false);
        MessagesArea = new JScrollPane(messageText);
        messageText.setFont(new java.awt.Font("Courier New", 0, 18));
    
        MessagesArea.setViewportView(messageText);
        
        
        add(MessagesArea,BorderLayout.CENTER);
        
        
        // Active user name area
        userConnectedText = new JTextArea(2, 30);
        userConnectedText.setEditable(false);
        activeUserNamesArea = new JScrollPane(userConnectedText);
        userConnectedText.setFont(new java.awt.Font("Courier New", 0, 18));
    
        activeUserNamesArea.setViewportView(userConnectedText);
        
        
        add(activeUserNamesArea,BorderLayout.EAST);
        
        
        sendbutton = new JPanel();
        sendbutton.setLayout(new  GridLayout(1,2,20,20));

        sendbutton.add(cmdSendMessage);
      
      
     
        
        messageToSend.setColumns(20);
        sendbutton.add(messageToSend,BorderLayout.SOUTH);
        
        
          add(sendbutton,BorderLayout.SOUTH);
          
         ButtonsListener listener = new ButtonsListener();
         cmdConnect.addActionListener(listener);
         cmdDisconnect.addActionListener(listener);
         cmdSendMessage.addActionListener(listener);
    }
    
      private class ButtonsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(e.getSource() == cmdConnect) {
                // conect to chat room
                clientName = JOptionPane.showInputDialog(null, "Enter user name:");
               out.println(clientName);
               messagesAreaString+="Connected to server .. getting on-line users\n";
               messageText.setText(messagesAreaString);
              
           
             
            }
            else 
                if(e.getSource() == cmdDisconnect){
                    // inform server you are quiting 
                    out.println("quit");
                    
                    out.close();
                    messagesAreaString+="Diconnected from server .. \n";
                    messageText.setText(messagesAreaString);
               
                    try {
                     in.close();
                    } catch (IOException ex) {
                      Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    try {
                       mySocket.close();
                     } catch (IOException ex) {
                       Logger.getLogger(ClientGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                }
            else
                    if(e.getSource() == cmdSendMessage){
                        
                       out.println(messageToSend.getText()); 
                    }
           
         }
      }
    
      public void addInputStream(BufferedReader in){
          this.in=in;
      }
    
      
       public void addOutputStream(PrintWriter out){
          this.out=out;
      }
      
       
        public void addSocket(Socket mySocket){
          this.mySocket=mySocket;
      }
        
        
        public void showOnlineUsers(String OnLineUsers){
            userConnectedText.setText(OnLineUsers); 
            
            
        }
        
        public void showMessages(String message){
            String currentText=messageText.getText();
            currentText+=message;
            messageText.setText(currentText); 
            
            
        }
        
        
         public String getOnlineUsers(){
            return ActiveUserText; 
            
            
        }
         
          public void setOnlineUsers(String onLineUserText){
             this.ActiveUserText=onLineUserText; 
            
            
        }     
        
      public String getClientName(){
             return this.clientName; 
            
            
        }     
                
}
