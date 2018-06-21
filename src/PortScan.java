
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScan 
{
	private String ip;
	
	public PortScan(String ip)
	{
		this.ip = ip;
	}
	public void setIP( String ip )
	{
		this.ip = ip;	
	}

	public String getIP()
	{
		return this.ip;
	}
	public void scan()
	{
		int i = 1;
		String ip = this.getIP();
		while(i < 50000)
		{
			System.out.println(i);
		try
		{
			Socket sock = new Socket(ip, i);
			InputStream in = sock.getInputStream();
			OutputStream out = sock.getOutputStream();
			BufferedInputStream bins = new BufferedInputStream(in);
			BufferedReader bin = new BufferedReader(new InputStreamReader(bins));
			String response = bin.readLine();
			System.out.println(response);
			System.out.println(ip + ":" + i);
			sock.close();
		}
		catch( UnknownHostException e )
		{
			
			//System.out.println("No host found.");
		}
		catch(IOException e)
		{
			
			//System.out.println("Problem connection to host.");
		}
		i++;
		}
	} 
	public static void main(String[] args) 
	{
		PortScan aPortScan = new PortScan("127.1.1.1");

		aPortScan.scan();
	}
}