/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	exprCondicional:expresion -> left:expresion  opreador:String  right:expresion

public class ExprCondicional extends AbstractExpresion {

	public ExprCondicional(Expresion left, String opreador, Expresion right) {
		this.left = left;
		this.opreador = opreador;
		this.right = right;

		searchForPositions(left, right);	// Obtener linea/columna a partir de los hijos
	}

	public ExprCondicional(Object left, Object opreador, Object right) {
		this.left = (Expresion) left;
		this.opreador = (opreador instanceof Token) ? ((Token)opreador).getLexeme() : (String) opreador;
		this.right = (Expresion) right;

		searchForPositions(left, opreador, right);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getLeft() {
		return left;
	}
	public void setLeft(Expresion left) {
		this.left = left;
	}

	public String getOpreador() {
		return opreador;
	}
	public void setOpreador(String opreador) {
		this.opreador = opreador;
	}

	public Expresion getRight() {
		return right;
	}
	public void setRight(Expresion right) {
		this.right = right;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion left;
	private String opreador;
	private Expresion right;
}

