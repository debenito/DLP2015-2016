/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	llamadaFuncion:expresion -> ident:String  expresiones:expresion*

public class LlamadaFuncion extends AbstractExpresion {

	public LlamadaFuncion(String ident, List<Expresion> expresiones) {
		this.ident = ident;
		this.expresiones = expresiones;

		searchForPositions(expresiones);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public LlamadaFuncion(Object ident, Object expresiones) {
		this.ident = (ident instanceof Token) ? ((Token)ident).getLexeme() : (String) ident;
		this.expresiones = (List<Expresion>) expresiones;

		searchForPositions(ident, expresiones);	// Obtener linea/columna a partir de los hijos
	}

	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}
	public void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = expresiones;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String ident;
	private List<Expresion> expresiones;
}

