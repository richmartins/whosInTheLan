package whoIsThereP;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//Window w1 = new Window();
		String localhost = Inet4Address.getLocalHost().getHostAddress();
		String goodlocalhost = localhost.length() < 9 ? localhost: localhost.substring(0, 9);
		

		//localhost.substring(0, 9);
		//System.out.println(localhost);
		checkHosts("172.22.10");
		
		int toto = 120;
		String tata = "titi";
		
	}
	
	public static void checkHosts(String subnet) throws UnknownHostException, IOException{
		int timeout = 20;
		for(int i = 1; i<256; i++){
			String host = subnet+"."+i;
			if(InetAddress.getByName(host).isReachable(timeout)){
				InetAddress hostname = InetAddress.getByName(host);
				String name = hostname.getHostName();
				System.out.println("IP : " + host + " Host_Name : " + name + " is Reachable");
				
				
			}else{
				//System.out.println("is not in the if : " + host);
			}
		}
		System.out.println("Scan finished");
	}
}
