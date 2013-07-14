package multitools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MPlayClient extends Thread {
	private int sessid;
	private String username = "penisfiend";
	private MPlayServer server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
 
 // Constructor
	public MPlayClient(MPlayServer server, Socket socket, int sessid){
		this.server = server;
		this.socket = socket;
		this.sessid = sessid;
  
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("New client created");
		}
		catch(IOException e)
		{
			System.out.println("ERROR!\nCould not create client");
			kill();
		}
	}
 
	public void run()
	{
		System.out.println("MplayClient " + sessid + " threaded. Listening to incoming");
		handleIncoming();
	}
 
 // Handle communications from client
	private void handleIncoming()
	{
		try
		{
			String thismsg;
			while ((thismsg = in.readLine()) != null) interpretData(thismsg);
		}
		catch(IOException e)
		{
			System.out.println("ERROR!\nCould not read data from client");
			kill();
		}
	}
 
 // Kill the client connection/object
	public void kill()
	{
		server.removeMplayClient(sessid);
	}
 
 // Return client ID
	public int getID()
	{
		return this.sessid;
	}
 
 // Change client ID
	public void setID(int sessid)
	{
		this.sessid = sessid;
	}
 
 // Return username
	public String getUsername()
	{
		return this.username;
	}
	
 // Change username
	public void setUsername(String username)
	{
		this.username = username;
	}
 
 // Decide what to do from input
	private void interpretData(String msg)
	{
		sendData(server.sess);
		System.out.println("MplayClient " + sessid + "> " + msg);
		server.sess=msg;
//		sendData("welcome");
	}
 
 // Send data to the client
	public void sendData(String msg)
	{
		out.println(msg);
		if (out.checkError())
		{
			System.out.println("ERROR!\nCould not deliver message to client");
			kill();
		}
	}
}