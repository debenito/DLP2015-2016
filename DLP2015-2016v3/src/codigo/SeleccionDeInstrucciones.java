package codigo;

import java.io.*;
import java.util.*;

import ast.*;
import visitor.*;

public class SeleccionDeInstrucciones extends DefaultVisitor {

	private PrintWriter writer;
	private String sourceFile;

	public SeleccionDeInstrucciones(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/EsqueletoVisitor.java'.
	 */

	//	Ejemplo:
	//
	//	public Object visit(Programa node, Object param) {
	//		out("#source \"" + sourceFile + "\"");
	//		out("call main");
	//		out("halt");
	//		super.visit(node, param);	// Recorrer los hijos
	//		return null;
	//	}

	
	
	
	

	
	
	
	
	
	
	// Método auxiliar recomendado -------------
	private void out(String text) {
		writer.println(text);
	}

}
