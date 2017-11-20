package whoIsThereP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class Window extends JFrame{
	
	//work with multi monitor
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	
	//Container
	private JPanel container = new JPanel();
	
	//Component
	private JButton btnScan = new JButton("Scan");
	private JTextField tfArray = new JTextField();
	
	//get local IP object
	private String localhost = Inet4Address.getLocalHost().getHostAddress();
	private String goodlocalhost = localhost.length() < 9 ? localhost: localhost.substring(0, 10);
	
	private String[] strIPs = new String[]{};
	private StringBuilder sb = new StringBuilder();
	
	public Window() throws UnknownHostException{
		
		
		System.out.println("local machine IP :" + goodlocalhost);
		
		this.setTitle("Scanner LAN");
		this.setSize((width/4), (height/2));
		System.out.println(width/4 + " " + height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		tfArray.setPreferredSize(new Dimension((this.getWidth()/2), (this.getHeight()-25)));
		tfArray.setEditable(false);
		
		this.setContentPane(container);
		SpringLayout layout = new SpringLayout();
		
		container.setLayout(new BorderLayout());
		container.add(btnScan, BorderLayout.AFTER_LAST_LINE);
		container.add(tfArray, BorderLayout.LINE_START);
		
		btnScan.addActionListener(new Action());
		
		this.setVisible(true);
	}
		
	public void checkHosts(String subnet) throws UnknownHostException, IOException{
		int timeout = 20;
		for(int i = 1; i < 255; i++){
			String host = subnet+"."+i;
			if(InetAddress.getByName(host).isReachable(timeout)){
				InetAddress hostname = InetAddress.getByName(host);
				String name = hostname.getHostName();
				sb.append(strIPs[i] = " IP  " + host + " Host_Name : " + name + " is Reachable");
				System.out.println("IP : " + host + " Host_Name : " + name + " is Reachable");			
			}else{
				sb.append(strIPs[i] = "is not Reachable : " + host);
				System.out.println("is not Reachable : " + host); //testligne
			}
		}
		System.out.println("Scan finished");
		tfArray.setText(sb.toString());
		tfArray.revalidate();
	}
	
	public class Action implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.out.println(goodlocalhost);
			//checkHosts(goodlocalhost);
		}
	}
}


