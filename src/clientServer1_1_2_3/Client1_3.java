package clientServer1_1_2_3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1_3 {

	public static void main(String[] args) throws IOException{
		
		try {
			int serverPort=1234;
			Socket socket= new Socket("localhost",serverPort);
			
			System.out.println("je suis un client connecté");
            Scanner sc = new Scanner(System.in);
            System.out.println("Donner un nombre : ");
            int i =sc.nextInt();
			OutputStream os=socket.getOutputStream(); 
			os.write(i);
			InputStream is=socket.getInputStream();
			int nb=is.read();
			
			System.out.println("le Resultat : "+nb);
			socket.close();
		   } catch (IOException e) {
		
			e.printStackTrace();
		   }		
	}
}

	