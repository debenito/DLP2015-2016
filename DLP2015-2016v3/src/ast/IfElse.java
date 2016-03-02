/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	ifElse:sentencia -> condicion:expresion  ifsentencia:sentencia*  elsesentencia:sentencia*

public class IfElse extends AbstractSentencia {

	public IfElse(Expresion condicion, List<Sentencia> ifsentencia, List<Sentencia> elsesentencia) {
		this.condicion = condicion;
		this.ifsentencia = ifsentencia;
		this.elsesentencia = elsesentencia;

		searchForPositions(condicion, ifsentencia, elsesentencia);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public IfElse(Object condicion, Object ifsentencia, Object elsesentencia) {
		this.condicion = (Expresion) condicion;
		this.ifsentencia = (List<Sentencia>) ifsentencia;
		this.elsesentencia = (List<Sentencia>) elsesentencia;

		searchForPositions(condicion, ifsentencia, elsesentencia);	// Obtener linea/columna a partir de los hijos
	}

	public Expresion getCondicion() {
		return condicion;
	}
	public void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getIfsentencia() {
		return ifsentencia;
	}
	public void setIfsentencia(List<Sentencia> ifsentencia) {
		this.ifsentencia = ifsentencia;
	}

	public List<Sentencia> getElsesentencia() {
		return elsesentencia;
	}
	public void setElsesentencia(List<Sentencia> elsesentencia) {
		this.elsesentencia = elsesentencia;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Expresion condicion;
	private List<Sentencia> ifsentencia;
	private List<Sentencia> elsesentencia;
}

