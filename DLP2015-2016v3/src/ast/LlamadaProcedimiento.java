/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	llamadaProcedimiento:sentencia -> ident:String  expresion:expresion*

public class LlamadaProcedimiento extends AbstractSentencia {

	public LlamadaProcedimiento(String ident, List<Expresion> expresion) {
		this.ident = ident;
		this.expresion = expresion;

		searchForPositions(expresion);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public LlamadaProcedimiento(Object ident, Object expresion) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.expresion = (List<Expresion>) expresion;

		searchForPositions(ident, expresion);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	public List<Expresion> getExpresion() {
		return expresion;
	}
	public void setExpresion(List<Expresion> expresion) {
		this.expresion = expresion;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private List<Expresion> expresion;
}

