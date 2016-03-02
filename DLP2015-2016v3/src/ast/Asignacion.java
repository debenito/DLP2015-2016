/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	asignacion:sentencia -> leftExpresion:expresion  rightExpresion:expresion

public class Asignacion extends AbstractSentencia {

	public Asignacion(Expresion leftExpresion, Expresion rightExpresion) {
		this.leftExpresion = leftExpresion;
		this.rightExpresion = rightExpresion;

		searchForPositions(leftExpresion, rightExpresion);	// Obtener linea/columna a partir de los hijos
	}

	public Asignacion(Object leftExpresion, Object rightExpresion) {
		this.leftExpresion = (Expresion) leftExpresion;
		this.rightExpresion = (Expresion) rightExpresion;

		searchForPositions(leftExpresion, rightExpresion);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getLeftExpresion() {
		return leftExpresion;
	}
	public void setLeftExpresion(Expresion leftExpresion) {
		this.leftExpresion = leftExpresion;
	}

	public Expresion getRightExpresion() {
		return rightExpresion;
	}
	public void setRightExpresion(Expresion rightExpresion) {
		this.rightExpresion = rightExpresion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion leftExpresion;
	private Expresion rightExpresion;
}

