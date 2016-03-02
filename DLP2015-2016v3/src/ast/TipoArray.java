/**
 * @generated VGen 1.3.3
 */

package ast;

import visitor.*;

//	tipoArray:tipo -> tipoArray:tipo  tam:int

public class TipoArray extends AbstractTipo {

	public TipoArray(Tipo tipoArray, int tam) {
		this.tipoArray = tipoArray;
		this.tam = tam;

		searchForPositions(tipoArray);	// Obtener linea/columna a partir de los hijos
	}

	public TipoArray(Object tipoArray, Object tam) {
		this.tipoArray = (Tipo) tipoArray;
		this.tam = (tam instanceof Token) ? Integer.parseInt(((Token)tam).getLexeme()) : (Integer) tam;

		searchForPositions(tipoArray, tam);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getTipoArray() {
		return tipoArray;
	}
	public void setTipoArray(Tipo tipoArray) {
		this.tipoArray = tipoArray;
	}

	public int getTam() {
		return tam;
	}
	public void setTam(int tam) {
		this.tam = tam;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo tipoArray;
	private int tam;
}

