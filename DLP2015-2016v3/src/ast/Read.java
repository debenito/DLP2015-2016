/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	read:sentencia -> expresiones:expresion*

public class Read extends AbstractSentencia {

	public Read(List<Expresion> expresiones) {
		this.expresiones = expresiones;

		searchForPositions(expresiones);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Read(Object expresiones) {
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

