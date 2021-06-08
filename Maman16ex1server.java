/*yaron yannay id:031996515
 */
package maman16ex1server;

import java.net.*;
import java.io.*;
import javax.swing.JFrame;

/**
 *
 * @author me
 */
public class Maman16ex1server  extends Thread{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    ServerSocket srv = null;
         ClientsHandler serverClientsDataBase= new ClientsHandler();
         
         
         JFrame frame = new JFrame("my server");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1200, 400);
     
       ServerGui myGui=new ServerGui();
       
         frame.add(myGui);
       frame.setVisible(true);
         
         
         
        boolean listening = true;
        
         
        try {
            srv = new ServerSocket(7777);
       
            System.out.println("Server's ready\n");
            Socket socket = null;
            
            while(listening)
            {
                socket = srv.accept();
                // add server thread
                new ServerThread(socket,serverClientsDataBase).start();
              
            }         
        }
        catch(InterruptedIOException e)
        {
            System.out.println("Time out");
        }
        catch(IOException e)
        { 
            e.printStackTrace(); 
            System.exit(1);
        }
       
        
   
        
    }
    
}
