package client_ServerTP3;

import clientserverobject_TP2_2.Operation; // Importation de la classe "Operation" du TP2.

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	public static void main(String[] args) {
		new Server().start(); // Démarrage du serveur en créant une nouvelle instance de la classe "ClientServerPac4.ACT4_3.Server".
	}

	@Override
	public void run() {
		System.out.println("Je suis un serveur");
		try {
			ServerSocket ss = new ServerSocket(1234); // Création d'un socket serveur écoutant sur le port 1234.
			System.out.println("J'attends un client");
			int index = 1;

			while (true) {
				Socket clientSocket = ss.accept(); // Attente de la connexion d'un client
				// Création d'une instance de "ClientProcess" pour gérer le client et démarrage
				// d'un thread.
				new ClientProcess(clientSocket, index).start();
				System.out.println("Le client est connecté");
				index++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class ClientProcess extends Thread {
	private Socket socket;
	private int index;

	public ClientProcess(Socket socket, int index) {
		this.socket = socket;
		this.index = index;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

			String clientMessage = br.readLine(); // Lecture du message du client.
			System.out.println("Message du client " + index + ": " + clientMessage);

			// Réponse au client
			String response = "Bienvenue, vous êtes le client numéro " + index + ". Votre @IP: "
					+ socket.getRemoteSocketAddress();
			pw.println(response);

			OutputStream os = socket.getOutputStream(); // Obtient un flux de sortie.
			InputStream is = socket.getInputStream(); 
			// Obtient un flux d'entrée.
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);

			// Lecture de l'objet "Operation" envoyé par le client
			clientserverobject_TP2_2.Operation op1 = (Operation) ois.readObject();
			int nb1 = op1.getNb1();
			int nb2 = op1.getNb2();
			String op = op1.getOp();
			double res = 0;

			// Effectue l'opération mathématique en fonction de l'opérateur
			if (op.equals("+")) {
				res = nb1 + nb2;
			} else if (op.equals("-")) {
				res = nb1 - nb2;
			} else if (op.equals("*")) {
				res = nb1 * nb2;
			} else if (op.equals("/")) {
				if (nb2 != 0) {
					res = (double) nb1 / nb2;
					
				} 
				
				else
				{
					System.out.println("Division par zéro impossible.");
				}
			}
			
			op1.setRes(res);

			// Envoie le résultat au client
			oos.writeObject(op1);

			socket.close(); // Fermeture de la connexion avec le client.
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		}
}
