package ftp.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ftp.sockets.server.MyServer;

public class Cliente {
	
	public static void main(String[] args) {
		Socket s=null;
		try {
			 s=new Socket("localhost", MyServer.PORT);// puerto 1100
			 
			 //Herramientas para comunicarse con el servidor
			 PrintWriter pw=new PrintWriter(s.getOutputStream(),true);//Permite escribir en servidor
			 BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));//Permite leer del servidor
			 
			 Scanner teclado =new Scanner(System.in);
			 
			 System.out.println("Client started...");
			 
			 String frase;
			 
			 do{
				 
				 String ln=br.readLine();
				 System.out.println("From server: \n"+ln);
				 
				 frase=teclado.nextLine();
				 pw.println(frase);
				 
			 }while(!frase.equalsIgnoreCase("END"));
			 
			 
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
