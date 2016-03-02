/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	write:sentencia -> expresiones:expresion*

public class Write extends AbstractSentencia {

	public Write(List<Expresion> expresiones) {
		this.expresiones = expresiones;

		searchForPositions(expresiones);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Write(Object expresiones) {
		this.expresiones = (List<Expresion>) expresiones;

		searchForPositions(expresiones);	// Obtener linea/columna a partir de los hijos
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

	private List<Expresion> expresiones;
}

