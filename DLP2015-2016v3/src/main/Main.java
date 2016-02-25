package main;

import java.io.*;
import sintactico.*;
import visitor.*;
import ast.*;
import semantico.*;
import codigo.*;

/**
 * Clase que inicia el compilador e invoca a todas sus fases.
 * 
 * No es necesario modificar este fichero. 
 * En su lugar hay modificar:
 * - En Análisis Sintáctico: 'sintactico/sintac.y' y 'sintactico/lexico.l'
 * - En Análisis Semántico: 'semantico/FaseIdentificación.java' y 'semantico/FaseInferencia.java'
 * - En Generación de Código: 'codigo/GestionDeMemoria.java' y 'codigo/SeleccionDeInstrucciones.java'
 */
public class Main {
	public static final String sourceFile = "src/programaejemplo.txt";

	public static void main(String[] args) throws Exception {
		GestorErrores gestor = new GestorErrores();

		AST raiz = compile(sourceFile, gestor); // Poner args[0] en vez de sourceFile en la versión final
		if (!gestor.hayErrores())
			System.out.println("El programa de entrada no tiene errores.");

		ASTPrinter.toHtml(sourceFile, raiz, "Traza arbol"); // Utilidad generada por VGen (opcional)
	}

	/**
	 * Método que coordina todas las fases del compilador
	 */
	public static AST compile(String sourceName, GestorErrores gestor) throws Exception {

		// 1. Fases de Análisis Léxico y Sintáctico
		Yylex lexico = new Yylex(new FileReader(sourceName), gestor);
		Parser sintáctico = new Parser(lexico, gestor, false);
		sintáctico.parse();

		AST raiz = sintáctico.getAST();
		if (raiz == null) // Hay errores o AST no implementado aún
			return null;

		// 2. Fase de Análisis Semántico
		Semantico semántico = new Semantico(gestor);
		semántico.analiza(raiz);
		if (gestor.hayErrores())
			return raiz;

		// 3. Fase de Generación de Código
		File sourceFile = new File(sourceName);
		Writer out = new FileWriter(new File(sourceFile.getParent(), "salida.txt"));

		GeneracionDeCodigo generador = new GeneracionDeCodigo();
		generador.genera(sourceFile.getName(), raiz, out);
		out.close();

		return raiz;
	}
}
