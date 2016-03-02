/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	defMain:definicion -> ident:String  definiciones:definicion*  sentencias:sentencia*

public class DefMain extends AbstractDefinicion {

	public DefMain(String ident, List<Definicion> definiciones, List<Sentencia> sentencias) {
		this.ident = ident;
		this.definiciones = definiciones;
		this.sentencias = sentencias;

		searchForPositions(definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public DefMain(Object ident, Object definiciones, Object sentencias) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.definiciones = (List<Definicion>) definiciones;
		this.sentencias = (List<Sentencia>) sentencias;

		searchForPositions(ident, definiciones, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
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
	private List<Definicion> definiciones;
	private List<Sentencia> sentencias;
}

