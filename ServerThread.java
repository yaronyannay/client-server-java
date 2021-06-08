/*yaron yannay id:031996515
 */
package maman16ex1server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import  java.util.concurrent.ConcurrentHashMap.KeySetView;

/**
 *
 * @author me
 */
public class ServerThread  extends Thread{
    private Socket socket = null;
    private SocketAndStreams mySocketAndStreams;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;
    private ClientsHandler clientsHandler;
    private boolean online=true;
    
    public ServerThread(Socket s,ClientsHandler myClients ) {
        socket = s;
        
        this.clientName="";
        this.clientsHandler=myClients;
    } 
    
    public void run()
    {
        String input;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e){ e.printStackTrace(); }
        try {
            input = in.readLine();
            // get client name
            System.out.printf("client name:%s\n",input);
            this.clientName=input;
            this.online=true;
            // put socket and stream in database
            mySocketAndStreams  = new SocketAndStreams();
            mySocketAndStreams.SetInStream(in);
            mySocketAndStreams.SetOutStream(out);
            mySocketAndStreams.setSocket(socket);
            this.clientsHandler.addClient(clientName,mySocketAndStreams);
            
            // send  users online list in chat room to client
             out.println(clientsHandler.toString());
             
             
           
                      
                  
          
            
            // notify all clients user is  online
            for (String entry : clientsHandler.getKeySet()) { 
                       clientsHandler.getOutStream(entry).println("New user:"+clientName );
                   }
                       
            
            while(online){
               input = in.readLine();
               if (input.matches("quit")){
                   this.online=false;
               }
               
               else{ // got a message form client, echo it to all clients
                   for (String entry : clientsHandler.getKeySet()) { 
                       clientsHandler.getOutStream(entry).println(clientName+" said :"+input );
                   }
                }

                   
            }
            
            in.close();
            out.close();
            socket.close();
            System.out.printf("client %s left the chat room\n",this.clientName);
              // notify all clients user is  online
            for (String entry : clientsHandler.getKeySet()) { 
                       clientsHandler.getOutStream(entry).println("User "+clientName +" left the chat room");
                   }
            clientsHandler.removeClient(clientName);
         }
         catch(IOException e) { e.printStackTrace(); }
        }
    
    
    
}
