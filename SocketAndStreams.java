/*yaron yannay id:031996515
 */
package maman16ex1server;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;


/**
 *
 * @author me
 */
public class SocketAndStreams {
    private Socket mySocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public void setSocket(Socket mySocket){
        this.mySocket=mySocket;
    }
    
    public void SetOutStream(PrintWriter out){
        this.out=out;
    }
    
     public void SetInStream(BufferedReader in){
        this.in=in;
    } 
     
    public Socket getSocket(){
        return this.mySocket;
    } 
    
    public BufferedReader getInStream(){
        return this.in;
    }
    
    public PrintWriter getOutStream(){
        return this.out;
    }
}
    
