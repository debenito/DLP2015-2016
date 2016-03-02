/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	cast:expresion -> type:tipo  expr:expresion

public class Cast extends AbstractExpresion {

	public Cast(Tipo type, Expresion expr) {
		this.type = type;
		this.expr = expr;

		searchForPositions(type, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Cast(Object type, Object expr) {
		this.type = (Tipo) type;
		this.expr = (Expresion) expr;

		searchForPositions(type, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getType() {
		return type;
	}
	public void setType(Tipo type) {
		this.type = type;
	}

	public Expresion getExpr() {
		return expr;
	}
	public void setExpr(Expresion expr) {
		this.expr = expr;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo type;
	private Expresion expr;
}

