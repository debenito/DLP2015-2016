/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defFunction:definicion -> ident:String  type:tipo  definiciones:definicion*  sentencias:sentencia*

public class DefFunction extends AbstractDefinicion {

	public DefFunction(String ident, Tipo type, List<Definicion> definiciones, List<Sentencia> sentencias) {
		this.ident = ident;
		this.type = type;
		this.definiciones = definiciones;
		this.sentencias = sentencias;

		searchForPositions(type, definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefFunction(Object ident, Object type, Object definiciones, Object sentencias) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.type = (Tipo) type;
		this.definiciones = (List<Definicion>) definiciones;
		this.sentencias = (List<Sentencia>) sentencias;

		searchForPositions(ident, type, definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	public Tipo getType() {
		return type;
	}
	public void setType(Tipo type) {
		this.type = type;
	}

	public List<Definicion> getDefiniciones() {
		return definiciones;
	}
	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private Tipo type;
	private List<Definicion> definiciones;
	private List<Sentencia> sentencias;
}

