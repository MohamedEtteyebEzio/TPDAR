package clientServer1_1_2_3;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket; 
 class Server { 
public static void main (String[] args) { 
try { 
         ServerSocket serverSocket = new ServerSocket(1243); 
         System.out.println("I am a server waiting for a client connexion..."); 
         Socket socket = serverSocket.accept(); 
         
         InputStream inputStream = socket.getInputStream(); 
         OutputStream outputStream = socket.getOutputStream();
         
         System.out.println("Waiting for a number ... "); 
         int nb = inputStream.read(); 
         System.out.println("Data received : " + nb); 
         int choice = inputStream.read(); 
         int rep; 
   switch (choice) { 
               case 1: 
                   System.out.println("The client selected Addition operation"); 
                   rep = nb + 5; 
                   outputStream.write(rep); 
                   break; 
               case 2: 
                   System.out.println("The client selected Soustraction operation"); 
                   rep = nb - 5; 
                   outputStream.write(rep); 
                   break; 
               case 3: 
                   System.out.println("The client selected Multiplication operation"); 
                   rep = nb * 5; 
                   outputStream.write(rep); 
                   break; 
               case 4: 
                   System.out.println("The client selected Division operation"); 
                   rep = nb / 5; 
                   outputStream.write(rep); 
                   break; 
               default: 
                   System.out.println("Return to Client."); 
           } 
 
 
           System.out.println("Closing the socket..."); 
 
           socket.close(); 
           serverSocket.close(); 
 
       } catch (Exception e) { 
           e.printStackTrace(); 
       } 
   } 
} 
 
