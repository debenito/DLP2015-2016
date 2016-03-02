/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	defVariables:definicion -> ident:String  type:tipo

public class DefVariables extends AbstractDefinicion {

	public DefVariables(String ident, Tipo type) {
		this.ident = ident;
		this.type = type;

		searchForPositions(type);	// Obtener linea/columna a partir de los hijos
	}

	public DefVariables(Object ident, Object type) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.type = (Tipo) type;

		searchForPositions(ident, type);	// Obtener linea/columna a partir de los hijos
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

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private Tipo type;
}

