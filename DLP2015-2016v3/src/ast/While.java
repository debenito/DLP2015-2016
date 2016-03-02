/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	while:sentencia -> condicino:expresion  sentencias:sentencia*

public class While extends AbstractSentencia {

	public While(Expresion condicino, List<Sentencia> sentencias) {
		this.condicino = condicino;
		this.sentencias = sentencias;

		searchForPositions(condicino, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public While(Object condicino, Object sentencias) {
		this.condicino = (Expresion) condicino;
		this.sentencias = (List<Sentencia>) sentencias;

		searchForPositions(condicino, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getCondicino() {
		return condicino;
	}
	public void setCondicino(Expresion condicino) {
		this.condicino = condicino;
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

	private Expresion condicino;
	private List<Sentencia> sentencias;
}

