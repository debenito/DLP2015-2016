/**
 * @generated VGen 1.3.3
 */

package ast;

import java.util.*;
import visitor.*;

//	tipoFuncion:tipo -> retorno:tipo  definiciones:definicion*

public class TipoFuncion extends AbstractTipo {

	public TipoFuncion(Tipo retorno, List<Definicion> definiciones) {
		this.retorno = retorno;
		this.definiciones = definiciones;

		searchForPositions(retorno, definiciones);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public TipoFuncion(Object retorno, Object definiciones) {
		this.retorno = (Tipo) retorno;
		this.definiciones = (List<Definicion>) definiciones;

		searchForPositions(retorno, definiciones);	// Obtener linea/columna a partir de los hijos
	}

	public Tipo getRetorno() {
		return retorno;
	}
	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public List<Definicion> getDefiniciones() {
		return definiciones;
	}
	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private Tipo retorno;
	private List<Definicion> definiciones;
}

