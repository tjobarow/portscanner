import java.net.*;
import java.util.*;

public class launch {
	
	static ArrayList<String> success = new ArrayList<>();
	
	public static void main(String[] args) {

		// create scanner and gather ip and ports
		Scanner input = new Scanner(System.in);
		System.out.println("Enter an IP Address (By host name or numerical): ");
		String address = input.nextLine();
		System.out.println("Enter the range of ports to scan (eg: 1 - 25): ");
		String ports = input.nextLine();
		handler(address, ports);
		for(String successes: success) {
			System.out.println(successes);
		}
	}

	// this method calls needed methods to check for open ports
	private static void handler(String address, String ports) {

		int[] portRange = portRegex(ports);
		InetAddress ip = ipHandler(address);
		if (ip == null) {
			System.out.println("Your IP address is unknown, try running again and entering a valid IP.");
			System.exit(1);
		}
		// this loops through all ports
		boolean successful = false;
		for (int i = portRange[0]; i <= portRange[1]; i++) {
			successful = connection(ip, i);
			if(successful) {
				success.add("Connection was accepted on port number: "+i);
			}
			if(successful){
				System.out.println("Address: "+ip.getHostAddress()+" | Port: "+i+":   "+successful);
			}
			else{
				System.out.println("Address: "+ip.getHostAddress()+" | Port: "+i+":   "+successful);
			}
		}
	}

	// this method applies a regex to the string of ports and extrats them as
	// integers into an array
	private static int[] portRegex(String ports) {
		// do regex and parse strings for min and max ports to scan for
		String[] portRangeString = ports.split(" *- *");

		// create int array to hold ranges parse portRange for actual integer
		int port;
		String portSt;
		int[] portRange = new int[portRangeString.length];
		for (int i = 0; i < portRangeString.length; i++) {
			portSt = portRangeString[i];
			port = Integer.parseInt(portSt);
			portRange[i] = port;
		}
		return portRange;
	}

	// this method handles creating the
	private static InetAddress ipHandler(String address) {
		// try to create Inet4Address object with the provided IP
		InetAddress ip = null;
		try {
			ip = Inet4Address.getByName(address);
			System.out.println("The IPV4 address is: " + ip.getHostAddress());
			return ip;
		} catch (UnknownHostException e) {
			System.out.println("INVALID IP");
			System.out.println(e.getStackTrace());
			return ip;
		}
	}

	private static boolean connection(InetAddress ip, int i) {
		// try to create the socket on that port
		Socket s = null;
		int timeout = 200;
		try {
			s = new Socket();
			s.connect(new InetSocketAddress(ip.getHostAddress(),i),timeout);
			s.close();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (Exception e) {
				}
		}
	}

}
