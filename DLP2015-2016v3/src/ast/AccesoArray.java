/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	accesoArray:expresion -> identificador:expresion  expr:expresion

public class AccesoArray extends AbstractExpresion {

	public AccesoArray(Expresion identificador, Expresion expr) {
		this.identificador = identificador;
		this.expr = expr;

		searchForPositions(identificador, expr);	// Obtener linea/columna a partir de los hijos
	}

	public AccesoArray(Object identificador, Object expr) {
		this.identificador = (Expresion) identificador;
		this.expr = (Expresion) expr;

		searchForPositions(identificador, expr);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Expresion identificador) {
		this.identificador = identificador;
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

	private Expresion identificador;
	private Expresion expr;
}

