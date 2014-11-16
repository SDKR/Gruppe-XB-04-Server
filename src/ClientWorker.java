import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import Encryption.encryption;
import GUILogic.Logic;
import SwitchLogic.GiantSwitch;

public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	private GiantSwitch GS = new GiantSwitch();
	private encryption cryp = new encryption();
	private String incomingJson;
	
	ClientWorker(Socket connectionSocket){
		this.connectionSocketConected = connectionSocket;
	}
	
	@Override
	public void run(){
		try{
			System.out.println("forbindelse Oprettet!");
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			byte[] b = new byte[500];
			int count = connectionSocketConected.getInputStream().read(b);
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
			//Creates an object of the data which is to be send back to the client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
			System.out.println("Outtoclient oprettet!");
			//Sets client sentence equals input from client
			//incomingJson = inFromClient.readLine();	
			String ny1 = new String(b, "UTF-8").trim();
			System.out.println(ny1);
			String ny = encryption.xor_decrypt(ny1, "3.1470");
			//String ny = cryp.decrypt(b);
			//String ny = new String(b).trim();
			//cryp.StringEncryption(inFromClient.readLine());
			System.out.println("Besked modtaget!");
			System.out.println("Modtager vi ikke noget?");
			//Sysout recieved message
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);
			System.out.println(returnSvar);
			String stringToClient = encryption.xor_decrypt(returnSvar, "3.1470");
			//Sends the capitalized message back to client!!
			System.out.println(stringToClient);
			outToClient.writeBytes(stringToClient+"\n");
			System.out.println("Vi er ved fluuush");
			outToClient.flush();
			System.out.println("Vi er ved cloosoe!");
			
			
			//outToClient.close();
			System.out.println("svar sendt");
			//BufferedWriter writer = new BufferedWriter(arg0)
		}catch(Exception exception){
			System.err.print(exception);
		}
	}


}
