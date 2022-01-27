package modelo;

public class Nodo <T> {

	public Nodo <T> Anterior;
	private T dato;
	public Nodo <T> Siguiente;
	
	
	@SuppressWarnings("unused")
	private Nodo(){
		Anterior=null;
		dato=null;
		Siguiente=null;
	}
	public Nodo(T dato){
		Anterior=null;
		this.dato=dato;
		Siguiente=null;
	}
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	
}

