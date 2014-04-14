package ftp.sockets.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadForClient extends Thread{
	
	private Socket s;
	
	public ThreadForClient(Socket s){
		this.s=s;
	}
	
	public void run(){
		BufferedReader in=null;
		PrintWriter out=null;
		
		try {
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new PrintWriter(s.getOutputStream(),true);
			String fromClient=null;
			out.println("ok\r\n");
			out.flush();
			out.println("220 connected");
//			out.println("220 welcome to my ftp server");
//			out.println("220 you can use commands doing right click and select 'Enter custom command'");
//			out.println("220 For example:");
//			out.println("220 CREATE name_dir creates a new directory named name_dir");
			
//			out.println("211-Features:");
//			out.println("MDTM");
//			out.println("REST STREAM");
//			out.println("SIZE");
//			out.println("MLST type*;size*;modify*;");
//			out.println("MLSD");
//			out.println("UTF8");
//			out.println("CLNT");
//			out.println("MFMT");
//			out.println("211 End");
			//out.println("257 OK. Current directory is \"/public\"");
			
			boolean connected = true;
			
			while (connected) {
				fromClient = in.readLine();
				System.out.println("From client: "+fromClient);
				
				
				if(fromClient!=null ){
					if(fromClient.equals("END")){
						connected = false;
					}
					else{
						processCommand(fromClient, out);
					}
					
				}
			}
			
			
			
			
		} catch (IOException e) {
			String msg = e.getMessage();
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)
					in.close();
				if(out!=null)
					out.close();
				if(s!=null)
					s.close();
			} catch (IOException e) {
				String msg = e.getMessage();
				e.printStackTrace();
			}
		}
	}
	
	private void processCommand(String command, PrintWriter out){
		
		String[] args = command.split(" ");
		
		switch(args[0]){
		
		case "USER"		:	out.println("331 Anonym no password needed");
							break;
		case "PASS"		:	out.println("230 User logged in, proceed");
							break;
		
		case "CREATE"	:	createDirectory(args[1]);
							break;
							
		case "PWD"		:	out.println("257 \"/public\" is current directory");
							break;
							
		case "TYPE"		:	out.println("200 TYPE is now 8-bit binary");
							break;
							
		case "OPTS"		:	out.println("200 OK, UTF-8 enabled");
							break;					
		
		case "PASV"		:	out.println("227 Entering Passive Mode (127,0,0,1,171,128)");
							break;
							
		case "EPSV"		:	out.println("Entering Extended Passive Mode (|||6446|)");
							break;
						
		default			:	out.println("Invalid command");
						break;
		}
		
	}
	
	private void createDirectory(String directoryName) {
		File theDir = new File(directoryName);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + directoryName);
			boolean result = theDir.mkdir();

			if (result) {
				System.out.println("DIR created");
			}
		}
	}
}
