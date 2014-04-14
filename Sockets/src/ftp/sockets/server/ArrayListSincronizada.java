package ftp.sockets.server;


import java.util.ArrayList;

public class ArrayListSincronizada<T> {

	private ArrayList<T> lista;
	
	public ArrayListSincronizada(){
		lista = new ArrayList<T>();
	}
	
	
	public synchronized void add(T elemento){
		lista.add(elemento);
	}

	
	public synchronized String mostrar(){
		String listado = "";
		for(T elemento : lista){
			listado = listado+" - " + elemento;
		}
		
		return listado;
	}
	
	
	public synchronized void remove(int i){
		lista.remove(i);
	}
	
	public synchronized T get(int i){
		return lista.get(i);
	}
	
	/**
	 * Para que este m?todo funcione correctamente el tipo T
	 * tiene que tener implementado el m?todo equals.
	 * @param elem
	 * @return
	 */
	public synchronized boolean esta(T elem){
		
		for(T elemento : lista){
			if(elem.equals(elemento)){
				return true;
			}
		}
		
		return false;
	}
}
