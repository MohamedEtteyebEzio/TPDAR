package clientServer1_1_2_3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_3 {

	public static void main(String[] args) throws IOException {
		try {
			int serverPort=1234;
			ServerSocket serverSocket = new ServerSocket(serverPort);
			System.out.println("je suis un serveur en attente la conexion d'un client");
			Socket s =serverSocket.accept();
			
			InputStream is=s.getInputStream();
			int nb=is.read()*5;
			System.out.println("la valeur a envoye a client : "+nb);
			OutputStream os=s.getOutputStream(); 
			os.write(nb);
			
			s.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
				
	}
}
	