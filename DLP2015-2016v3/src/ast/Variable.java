/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	variable:expresion -> ident:String

public class Variable extends AbstractExpresion {

	public Variable(String ident) {
		this.ident = ident;
	}

	public Variable(Object ident) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;

		searchForPositions(ident);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
}

