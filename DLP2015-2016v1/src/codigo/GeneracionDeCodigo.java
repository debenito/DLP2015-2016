package codigo;

import java.io.*;

import ast.*;

/**
 * Esta clase coordina las dos fases principales de la Generación de Código:
 * 1- Gestión de Memoria (asignación de direcciones)
 * 2- Selección de Instrucciones
 * 
 * No es necesario modificar esta clase.
 * En su lugar hay que modificar "GestiónDeMemoria.java" y "SelecciónDeInstrucciones.java".
 */
public class GeneracionDeCodigo {

	public void genera(String sourceFile, AST raiz, Writer out) {
		GestionDeMemoria gestion = new GestionDeMemoria();
		raiz.accept(gestion, null);

		SeleccionDeInstrucciones selecciona = new SeleccionDeInstrucciones(out, sourceFile);
		raiz.accept(selecciona, null);
	}

}
