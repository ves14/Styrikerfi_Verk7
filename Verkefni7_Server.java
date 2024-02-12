import java.net.*;
import java.io.*;

public class Verkefni7_Server {
    public static void main(String[] args) {
        try {
            
            System.out.println("Server is running on port 6789");
            CLS();
        } catch (Exception e) {
            // TODO: handle exception
        }
    } 
    public static void CLS(){
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789); // create socket at agreed port
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println(new String(request.getData()));
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(),
                request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }   
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if (aSocket != null){
                aSocket.close();
            }    
        }
    }
}
