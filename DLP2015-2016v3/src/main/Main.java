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
 * - En An�lisis Sint�ctico: 'sintactico/sintac.y' y 'sintactico/lexico.l'
 * - En An�lisis Sem�ntico: 'semantico/FaseIdentificaci�n.java' y 'semantico/FaseInferencia.java'
 * - En Generaci�n de C�digo: 'codigo/GestionDeMemoria.java' y 'codigo/SeleccionDeInstrucciones.java'
 */
public class Main {
	public static final String sourceFile = "src/programaejemplo.txt";

	public static void main(String[] args) throws Exception {
		GestorErrores gestor = new GestorErrores();

		AST raiz = compile(sourceFile, gestor); // Poner args[0] en vez de sourceFile en la versi�n final
		if (!gestor.hayErrores())
			System.out.println("El programa de entrada no tiene errores.");

		ASTPrinter.toHtml(sourceFile, raiz, "Traza arbol"); // Utilidad generada por VGen (opcional)
	}

	/**
	 * M�todo que coordina todas las fases del compilador
	 */
	public static AST compile(String sourceName, GestorErrores gestor) throws Exception {

		// 1. Fases de An�lisis L�xico y Sint�ctico
		Yylex lexico = new Yylex(new FileReader(sourceName), gestor);
		Parser sint�ctico = new Parser(lexico, gestor, false);
		sint�ctico.parse();

		AST raiz = sint�ctico.getAST();
		if (raiz == null) // Hay errores o AST no implementado a�n
			return null;

		// 2. Fase de An�lisis Sem�ntico
		Semantico sem�ntico = new Semantico(gestor);
		sem�ntico.analiza(raiz);
		if (gestor.hayErrores())
			return raiz;

		// 3. Fase de Generaci�n de C�digo
		File sourceFile = new File(sourceName);
		Writer out = new FileWriter(new File(sourceFile.getParent(), "salida.txt"));

		GeneracionDeCodigo generador = new GeneracionDeCodigo();
		generador.genera(sourceFile.getName(), raiz, out);
		out.close();

		return raiz;
	}
}
