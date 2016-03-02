/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	accesoStruct:expresion -> leftExpresion:expresion  expr:expresion

public class AccesoStruct extends AbstractExpresion {

	public AccesoStruct(Expresion leftExpresion, Expresion expr) {
		this.leftExpresion = leftExpresion;
		this.expr = expr;

		searchForPositions(leftExpresion, expr);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoStruct(Object leftExpresion, Object expr) {
		this.leftExpresion = (Expresion) leftExpresion;
		this.expr = (Expresion) expr;

		searchForPositions(leftExpresion, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getLeftExpresion() {
		return leftExpresion;
	}
	public void setLeftExpresion(Expresion leftExpresion) {
		this.leftExpresion = leftExpresion;
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

	private Expresion leftExpresion;
	private Expresion expr;
}

