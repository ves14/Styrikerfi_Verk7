import java.net.*;
import java.io.*;

public class Verkefni7_Client {
    public static void main(String[] args) {
        try {
            //System.out.println("Hello World");
            CLC(args);
        } catch (Exception e) {
            // TODO: handle exception
        }
    } 
    public static void CLC(String args[]) { 
        // args[0]: message contents,
        // args[1]: destination hostname
        DatagramSocket aSocket = null;
        try {
            int counter = 1;
            aSocket = new DatagramSocket();
            //InetAddress aHost = InetAddress.getByName(args[0]);
            //InetAddress aHost = InetAddress.getLocalHost();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789; // agreed port
            while (true){
                String temp = String.valueOf(counter);
                byte[] message = temp.getBytes();
                DatagramPacket request = new DatagramPacket(message, message.length,
                aHost, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()).replaceAll("^\\x00*", ""));
                counter++;
                //try {
                //    Thread.sleep(2000);
                //} 
                //catch(InterruptedException e)
                //{}
            }
        } 
        catch (SocketException e) {
            	System.out.println("Socket: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } 
        finally {
            if (aSocket != null)
            aSocket.close();
        }
    }
    
   
}
