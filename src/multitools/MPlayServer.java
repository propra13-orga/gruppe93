package multitools;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class MPlayServer {
	private Map<Integer,MPlayClient> clients;
	private int clientCount;
	private ServerSocket server;
	public String sess;
 
 // Server constructor
	public MPlayServer(int port){
		clients = new HashMap<Integer,MPlayClient>();
		clientCount = 0;
  
		try
		{
			System.out.print("Starting server on port " + port + "...");
			server = new ServerSocket(port);
			System.out.println("Done!");
			System.out.println("Now handling incoming communications");
			handleIncoming();
		}
		catch(IOException e)
		{
			System.out.println("ERROR!\nCould not start server. Shutting down...");
			kill();
		}
	}
 
 // Handle incoming communications
	private void handleIncoming()
	{
		try
		{
			while (true)
			{
				Socket socket = server.accept();
				clientCount++;
				MPlayClient client = new MPlayClient(this, socket, clientCount);
				clients.put(clientCount, client);
				System.out.println("MplayClient " + clientCount + " joined");
				client.start();
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR!\nFailed to handle incoming communication. Shutting down...");
			kill();
		}
	}
 
 // Entry point
	public static void main(String[] args)
	{
		System.out.println("Multiplayer Server");
		MPlayServer mplayserver = new MPlayServer(Integer.parseInt("5000"));	//5000 hier statt args[0] weil ich keine args eingeben konnte
	}
 
 // Shut down the server
	private void kill()
	{
		try
		{
			for (Object i : clients.values())
			{
				((MPlayClient)i).kill();
			}
		}
		catch(Exception e)
		{
			System.out.println("WARNING! Server could not be killed reliably");
		}
	}
 
 // Remove a client
	public void removeMplayClient(int cid)
	{
		System.out.println("MPlayClient " + clients.get(cid).getUsername() + "(" + cid + ") removed");
		clients.remove(cid);
	}
}
