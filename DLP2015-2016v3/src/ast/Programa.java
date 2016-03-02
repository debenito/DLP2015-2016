/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	programa -> defSruct:definicion*  defVariables:definicion*  defFunction:definicion*  defMain:definicion*  sentencias:sentencia*

public class Programa extends AbstractTraceable implements AST {

	public Programa(List<Definicion> defSruct, List<Definicion> defVariables, List<Definicion> defFunction, List<Definicion> defMain, List<Sentencia> sentencias) {
		this.defSruct = defSruct;
		this.defVariables = defVariables;
		this.defFunction = defFunction;
		this.defMain = defMain;
		this.sentencias = sentencias;

		searchForPositions(defSruct, defVariables, defFunction, defMain, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Programa(Object defSruct, Object defVariables, Object defFunction, Object defMain, Object sentencias) {
		this.defSruct = (List<Definicion>) defSruct;
		this.defVariables = (List<Definicion>) defVariables;
		this.defFunction = (List<Definicion>) defFunction;
		this.defMain = (List<Definicion>) defMain;
		this.sentencias = (List<Sentencia>) sentencias;

		searchForPositions(defSruct, defVariables, defFunction, defMain, sentencias);	// Obtener linea/columna a partir de los hijos
	}

	public List<Definicion> getDefSruct() {
		return defSruct;
	}
	public void setDefSruct(List<Definicion> defSruct) {
		this.defSruct = defSruct;
	}

	public List<Definicion> getDefVariables() {
		return defVariables;
	}
	public void setDefVariables(List<Definicion> defVariables) {
		this.defVariables = defVariables;
	}

	public List<Definicion> getDefFunction() {
		return defFunction;
	}
	public void setDefFunction(List<Definicion> defFunction) {
		this.defFunction = defFunction;
	}

	public List<Definicion> getDefMain() {
		return defMain;
	}
	public void setDefMain(List<Definicion> defMain) {
		this.defMain = defMain;
	}

	public List<Sentencia> getSentencias() {
		return sentencias;
	}
	public void setSentencias(List<Sentencia> sentencias) {
		this.sentencias = sentencias;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Definicion> defSruct;
	private List<Definicion> defVariables;
	private List<Definicion> defFunction;
	private List<Definicion> defMain;
	private List<Sentencia> sentencias;
}

