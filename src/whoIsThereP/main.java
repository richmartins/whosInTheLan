package whoIsThereP;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String localhost = Inet4Address.getLocalHost().getHostAddress();
		String goodlocalhost = localhost.length() < 9 ? localhost: localhost.substring(0, 10);

		checkHosts(goodlocalhost);
	}
	
	public static void checkHosts(String subnet) throws UnknownHostException, IOException{
		int timeout = 1000;
		for(int i = 1; i < 255; i++){
			String host = subnet+"."+i;
			if(InetAddress.getByName(host).isReachable(timeout)){
				InetAddress hostname = InetAddress.getByName(host);
				String name = hostname.getHostName();
				System.out.println("IP : " + host + " Host_Name : " + name + " is Reachable");			
			}else{

				System.out.println("is not Reachable : " + host); //testligne
			}
		}
		System.out.println("Scan finished");
	}
}
