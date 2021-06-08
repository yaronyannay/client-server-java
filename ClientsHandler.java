/*yaron yannay id:031996515
 */
package maman16ex1server;


import java.util.concurrent.ConcurrentHashMap;
import java.net.Socket;
import java.util.Map.Entry;
import java.util.Set;
import  java.util.concurrent.ConcurrentHashMap.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;


public class ClientsHandler {
    private ConcurrentHashMap<String, SocketAndStreams> serverClients ;  
    
 
    public ClientsHandler(){
        serverClients = new ConcurrentHashMap<>();
    }
    
      public void addClient(String clientName, SocketAndStreams ClientSocketAndStraem ){
        serverClients.put(clientName,ClientSocketAndStraem);
       
        
    }
      
            public void removeClient(String clientName ){
        serverClients.remove(clientName);
       
        
    }
      
      
      
      public Socket getSocket(String  clientName){
          return serverClients.get(clientName).getSocket();
      }
      
      
      public BufferedReader getInStream(String  clientName){
          return serverClients.get(clientName).getInStream();
      }
      
      public PrintWriter getOutStream(String  clientName){
          return serverClients.get(clientName).getOutStream();
      }
      
      public void setSocket(String  clientName,Socket mySocket){
          serverClients.get(clientName).setSocket(mySocket);
      }
      
      
      public void setInStream(String  clientName,BufferedReader in){
           serverClients.get(clientName).SetInStream(in);
      }
      
      public void setoutStream(String  clientName,PrintWriter out){
           serverClients.get(clientName).SetOutStream(out);
      }
      
      
      public int getNumberOfClients(){
          return serverClients.size();
      }
     
    
      
      public String toString(){
          String msg=""; //users in chat room:\n";
        
          for (Map.Entry<String, SocketAndStreams> entry : serverClients.entrySet()) {
              msg+=entry.getKey()+" ";
          }
          return msg; 
      }
      
      
     public KeySetView<String,SocketAndStreams> getKeySet(){
         return serverClients.keySet();
     } 
      
      public int getNumOfClients(){
          return serverClients.size();
          
        
      }
      
      
      public Set<Entry<String,SocketAndStreams>>  EntrySet(){
          return serverClients.entrySet();
      }
      
}