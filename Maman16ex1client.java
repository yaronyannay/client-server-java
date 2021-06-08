/* yaron yannay id:031996515
 */
package maman16ex1b;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author me
 */
public class Maman16ex1client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       JFrame frame = new JFrame("my chat room");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1200, 400);
       
       ClientGui myGui = new ClientGui();
       
       
       frame.add(myGui);
       frame.setVisible(true);
       
         Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = "localhost";
        try{
            socket = new Socket(host, 7777);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            myGui.addInputStream(in);
            myGui.addOutputStream(out);
            myGui.addSocket(socket);
            
            System.out.println("After connection");
            // get on-line users in chat room 
           
            String onLineUsers="On line users:\n";

            onLineUsers = in.readLine();
          
            String DisplayString="online users:\n"+onLineUsers;
            myGui.showOnlineUsers(DisplayString);
            myGui.setOnlineUsers(onLineUsers);
            
       
             String s="";
            
            while(!s.matches("quit") )
            {
               
               s=in.readLine();
               String tempText="\n"+s;
               myGui.showMessages(tempText);
             
               if (s.contains("New user:")&&!s.contains(myGui.getClientName())){
                   String textToAdd=s.substring(s.indexOf(":")+1);
                   String curentText=myGui.getOnlineUsers();
                   curentText+=textToAdd;
                   DisplayString="online users:\n"+curentText;
                   // update on line users
                   myGui.showOnlineUsers(DisplayString);
               }
               else{
                   // left chat room
                   if (s.contains("left the chat room")&&!s.contains(myGui.getClientName())){
                   
                   String textTodelete=s.substring(s.indexOf("client")+1,s.indexOf("left")-1);
                   
                   String curentText=myGui.getOnlineUsers();
                   
                   curentText.replace(textTodelete, "");
                
                     // update on line users
                   DisplayString="online users:\n"+curentText;
                   myGui.showOnlineUsers(DisplayString);
               }
               }
            }
            
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
   
    
}
