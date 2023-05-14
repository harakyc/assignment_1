package LineSearcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		boolean shutdown = false;
		
		try 
		{
			server = new ServerSocket(1239);
			System.out.println("Port Bound. Accepting Connections...");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} 
		
		while(!shutdown) 
		{	
			try{
				
				Socket client = server.accept();
				System.out.println("Client Connected: " + client.getInetAddress());
				
				ObjectInputStream input = new ObjectInputStream(client.getInputStream());
			
				
				String inputString = null;
				try {
					inputString = (String) input.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				int lineNum = Integer.parseInt(inputString);
				
				client.close();
				
				if (inputString.equalsIgnoreCase("Shutdown"))
				{
					System.out.println("Shutting down...");
					shutdown = true;
				}
				
			
			} catch (IOException e) {
				e.printStackTrace();
				continue; //until a client connects
			}
		}
			
		}
	}


