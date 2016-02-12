package semantico;

import ast.*;
import main.*;
import visitor.*;

public class FaseInferencia extends DefaultVisitor {

	public FaseInferencia(GestorErrores gestor) {
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
	// 		predicado(): Método para ayudar a implementar los predicados de la Gramática Atribuida.
	//
	// Ejemplo de uso (suponiendo implementado el método "esPrimitivo"):
	//		predicado(esPrimitivo(expr.tipo), "La expresión debe ser de un tipo primitivo", expr.getStart());
	//
	// NOTA: El método getStart() indica la linea/columna del fichero fuente de donde se leyó el nodo.
	// Si se usa VGen dicho método será generado en todos los nodos AST.
	// Si no se quiere usar getStart() y por tanto omitir la información de linea/columna se puede invocar sin dicho argumento:
	//		predicado(esPrimitivo(expr.tipo), "La expresión debe ser un tipo primitivo");
	//
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Inferencia", mensajeError, posicionError);
	}

	private void predicado(boolean condicion, String mensajeError) {
		predicado(condicion, mensajeError, null);
	}

	private GestorErrores gestorErrores;
}
