/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	menosUnario:expresion -> expr:expresion

public class MenosUnario extends AbstractExpresion {

	public MenosUnario(Expresion expr) {
		this.expr = expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
	}

	public MenosUnario(Object expr) {
		this.expr = (Expresion) expr;

		searchForPositions(expr);	// Obtener linea/columna a partir de los hijos
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

	private Expresion expr;
}

