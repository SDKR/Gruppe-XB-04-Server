import java.net.*;

import GUILogic.Logic;

class TCPServer{    
	
	public static void main(String argv[]) throws Exception       {

		//AdminWorker admin = new AdminWorker();
		Logic GUILogic = new Logic();
		GUILogic.startApp();
		//Creates a socket to send and recieve messages in port 8888
		ServerSocket welcomeSocket = new ServerSocket(8888);
		
		//While something is true
		while(true){
			//Creates a socket and a buffered reader which recieves some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			ClientWorker client= new ClientWorker(connectionSocket);
			Thread thread = new Thread(client, "client");
			thread.start();
			/*HUSK AT �NDRE DATABASE SCRIPTET, S� DET PASSER MED DEN NUV�RENDE DATABASE STRUKTUR!*/
		}
	}
}