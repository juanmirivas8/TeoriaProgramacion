package modelo;

import modelo.Nodo;
import java.lang.IllegalArgumentException;

public class ListaDobleEnlazada <T>{

	private Nodo <T> Primero;
	private int Size;
	
	public ListaDobleEnlazada() {
		Primero = null;
		this.Size = 0;
	}

	/**
	 * Insertar un nodo al final de la lista
	 * @param v dato a insertar
	 * @return el nuevo tamaño de la lista
	 */
	public int push (T v) {
		Nodo <T> newNodo = new Nodo<>(v);
		
		if(this.Size == 0) {
			this.Primero=newNodo;
		}else {
			Nodo <T> aux = find(this.Size - 1);
			newNodo.Anterior = aux;
			aux.Siguiente = newNodo;
		}
		this.Size++;
		return this.Size;
	}
	
	/**
	 * Insertar un nodo al principio de la lista
	 * @param v dato a insertar
	 * @return el nuevo tamaño de la lista
	 */
	public int unshift(T v) {
		Nodo <T> newNodo = new Nodo<>(v);
		
		if(this.Size == 0) {
			Primero = newNodo;
		}else {
			this.Primero.Anterior = newNodo;
			newNodo.Siguiente = this.Primero;
			this.Primero = newNodo;
		}
		this.Size++;
		return this.Size;
	}
	
	/**
	 * Devuelve el valor del ultimo nodo de la lista y lo elimina
	 * @return El valor del primer Nodo o null si la lista esta vacia
	 */
	public T pop() {
		T aux = null;
		
		if(this.Size > 1) {
			Nodo <T> nAux = find(this.Size - 1);
			aux = nAux.getDato();
			nAux = nAux.Anterior;
			nAux.Siguiente = null;
			Size--;
		}else if (this.Size == 1){
			aux = this.Primero.getDato();
			this.Primero = null;
			this.Size--;
		}
		return aux;
	}
	/**
	 * Devuelve el valor del primer nodo de la lista y lo elimina
	 * @return El valor del primer Nodo o null si la lista esta vacia
	 */
	public T shift() {
		T aux = null;
		
		if(this.Size > 0) {
			aux = this.Primero.getDato();
			this.Primero = this.Primero.Siguiente;
			
			if (this.Size > 1) {
				this.Primero.Anterior = null;
			}
			
			this.Size--;
		}
		return aux;
	}
	
	/**
	 * Busca el valor pasado como parametro y devuelve la posicion del primer nodo que lo contenga
	 * @param v valor a buscar
	 * @return posicion del nodo que lo contiene o -1 si la lista esta vacia o no es encontrado
	 */
	public int contains (T v) {
		int pos = -1;
		
		if(this.Size > 0) {
			Nodo <T> aux = this.Primero;
			for(int i = 0; i < this.Size; i++ ) {
				if(v.equals(aux.getDato())) {
					pos = i;
					i = this.Size;
				}
				
				aux=aux.Siguiente;
			}
		}
		
		return pos;
	}
	
	/**
	 * Devuelve el valor del nodo en la posicion indicada
	 * @param pos posicion del nodo
	 * @return su valor o null si la posicion es incorrecta o la lista esta vacia
	 */
	public T get (int pos) {
		try {
			Nodo <T> aux = find(pos);
			return aux.getDato();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/**
	 * Insertar un nodo con el valor pasado en la posicion indicada
	 * @param value el valor del nodo a insertar
	 * @param pos la posicion donde se desean insertar
	 * @return el nuevo tamaño de la lista
	 * @throws IllegalArgumentException Si la posicion no existe (si posicion == Size inserta al final)
	 */
	public int put (T value, int pos) throws IllegalArgumentException{
		
		if(pos == 0) {
			this.unshift(value);
		}else if (pos == this.Size ) {
			this.push(value);
		}else {
			try {
				Nodo <T> aux = find(pos);
				Nodo <T> newNodo = new Nodo<>(value);
				newNodo.Siguiente = aux;
				newNodo.Anterior = aux.Anterior;
				aux.Anterior.Siguiente = newNodo;
				aux.Anterior = newNodo;
				
				this.Size++;
			}catch(IllegalArgumentException e) {
				throw e;
			}
		}
			
		return this.Size;
	}
	
	/**
	 * Elimina el nodo que se encuentre en la posicion indicada
	 * @param pos posicion del nodo a eliminar
	 * @return el tamaño de la lista
	 * @throws IllegalArgumentException Excepcion si la posicion no existe
	 */
	public int remove(int pos) throws IllegalArgumentException{
		if(pos == 0) {
			this.shift();
		}else if (pos == this.Size - 1) {
			this.pop();
		}else {
			try {
				Nodo <T> aux = find(pos);
				aux.Anterior.Siguiente = aux.Siguiente; 
				aux.Siguiente.Anterior = aux.Anterior;
				
				this.Size--;
			}catch(IllegalArgumentException e) {
				throw e;
			}
		}
		
		return this.Size;
	}
	
	/**
	 * Remueve el nodo que contenga el mismo dato 
	 * @param v dato a buscar
	 * @return Tamaño de la lista
	 */
	public int removeElement(T v) {
		
		int pos = this.contains(v);
		
		if(this.Size > 0 && pos >=0 && pos < this.Size) {
			this.remove(pos);
		}
		
		return this.Size;
	}
	
	/**
	 * Devuelve el Nodo entero en la posicion indicada
	 * @param pos posicion a devolver
	 * @return Nodo en la posicion indicada
	 * @throws IllegalArgumentException Si la posicion esta fuera de rango lanza la excepcion
	 */
	private Nodo <T> find(int pos)throws IllegalArgumentException {
		
		if(pos < 0 || pos >= this.Size) {
			throw new IllegalArgumentException("Posicion fuera de rango");
		}
		
		Nodo <T> aux = this.Primero;
		if(pos > 0) {
			for(int i = 0; i < pos; i++) {
				aux=aux.Siguiente;
			}
		}
		
		return aux;
	}

	@Override
	public String toString() {
		
		String retCad=null;
		Nodo <T> aux = this.Primero;
		retCad = "Size: "+this.Size+"\n";
		
		if(this.Size > 0) {
			for(int i = 0; i < this.Size; i++) {
				retCad += aux.getDato();
				retCad += "\n";
				
				aux=aux.Siguiente;
			}
		}
		
		return retCad;
	}
	
	
}

