package ftp.sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static final int PORT=21;
	public static final int MAX_CONCURRENT_CONN=100;
	public static final int DEFAULT_CONCURRENT_CONN=10;
	private ServerSocket srv;
	
	
	public static void main(String[]args){
		MyServer srv=new MyServer();
		try {
			srv.init();
			srv.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void init() throws IOException{
		srv=new ServerSocket(PORT,DEFAULT_CONCURRENT_CONN);
	}
	public void start(){
		while(true){
			try {
				System.out.println("waiting for a new connection...");
				Socket s=srv.accept();
				System.out.println("Incomming connection from " + s.getRemoteSocketAddress());
				ThreadForClient hiloCliente = new ThreadForClient(s);
				hiloCliente.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
