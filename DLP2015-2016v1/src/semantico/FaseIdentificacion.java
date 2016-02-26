package semantico;

import java.util.*;

import ast.*;
import main.*;
import visitor.*;

public class FaseIdentificacion extends DefaultVisitor {

	public FaseIdentificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/EsqueletoVisitor.java'.
	 */

	// public Object visit(Programa prog, Object param) {
	// ...
	// }


	
	
	// --------------------------------------------------------
	// Métodos auxiliares recomendados
	//
	// Ejemplo de uso:
	//		error("Variable no definida", node.getStart());
	//
	// NOTA: El método getStart() indica la linea/columna del fichero fuente de donde se leyó el nodo.
	// Si se usa VGen dicho método será generado en todos los nodos AST.
	// Si no se quiere usar getStart() y por tanto omitir la información de linea/columna se puede invocar sin dicho argumento:
	//		error("Variable no definida");
	//

	private void error(String mensajeError, Position posicionError) {
		gestorErrores.error("Identificación", mensajeError, posicionError);
	}

	private void error(String mensajeError) {
		error(mensajeError, null);
	}

	private GestorErrores gestorErrores;
}
