/**
 * @generated VGen 1.3.3
 */

package visitor;

import java.io.*;

import ast.*;
import java.util.*;

/**
 * ASTPrinter. Utilidad que ayuda a validar un arbol AST:
 * 	-	Muestra la estructura del árbol en HTML.
 * 	-	Destaca los hijos/propiedades a null.
 * 	-	Muestra a qué texto apuntan las posiciones de cada nodo (linea/columna)
 * 		ayudando a decidir cual de ellas usar en los errores y generación de código.
 * 
 * Esta clase se genera con VGen. El uso de esta clase es opcional (puede eliminarse del proyecto). 
 * 
 */
public class ASTPrinter extends DefaultVisitor {

	/**
	 * toHtml. Muestra la estructura del AST indicando qué hay en las posiciones (linea y columna) de cada nodo.
	 * 
	 * @param sourceFile	El fichero del cual se ha obtenido el AST
	 * @param raiz				El AST creado a partir de sourceFile
	 * @param filename		Nombre del fichero HMTL a crear con la traza del AST
	 */

	public static void toHtml(String sourceFile, AST raiz, String filename) {
		toHtml(sourceFile, raiz, filename, 4);
	}
	
	public static void toHtml(AST raiz, String filename) {
		toHtml(null, raiz, filename);
	}

	// tabWidth deberían ser los espacios correspondientes a un tabulador en eclipse.
	// Normalmente no será necesario especificarlo. Usar mejor los dos métodos anteriores.
	public static void toHtml(String sourceFile, AST raiz, String filename, int tabWidth) {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filename.endsWith(".html") ? filename : filename + ".html"));
			generateHeader(writer);
			writer.println("[ASTPrinter] -------------------------------- line:col  line:col");
			if (raiz != null) {
				ASTPrinter tracer = new ASTPrinter(writer, loadLines(sourceFile, tabWidth));
				raiz.accept(tracer, new Integer(0));
			} else
				writer.println("raiz == null");
			writer.println(ls + ls + "[ASTPrinter] --------------------------------");
			generateFooter(writer);
			writer.close();
			System.out.println(ls + "ASTPrinter: Fichero '" + filename + ".html' generado con éxito. Abra el fichero para validar el árbol AST generado.");
		} catch (IOException e) {
			System.out.println(ls + "ASTPrinter: No se ha podido crear el fichero " + filename);
			e.printStackTrace();
		}
	}

	private static void generateHeader(PrintWriter writer) {
		writer.println("<html>\r\n" +
				"<head>\r\n" +
				"<style type=\"text/css\">\r\n" +
				".value { font-weight: bold; }\r\n" +
				".dots { color: #888888; }\r\n" +
				".type { color: #BBBBBB; }\r\n" +
				".pos { color: #CCCCCC; }\r\n" +
				".sourceText { color: #BBBBBB; }\r\n" +
				".posText {\r\n" +
				"	color: #BBBBBB;\r\n" +
				"	text-decoration: underline; font-weight: bold;\r\n" +
				"}\r\n" +
				".null {\r\n" +
				"	color: #FF0000;\r\n" +
				"	font-weight: bold;\r\n" +
				"	font-style: italic;\r\n" +
				"}\r\n" +
			//	 "pre { font-family: Arial, Helvetica, sans-serif; font-size: 11px; }\r\n" +
			//	"pre { font-size: 11px; }\r\n" +
				"</style>\r\n" +
				"</head>\r\n" +
				"\r\n" +
				"<body><pre>");
	}

	private static void generateFooter(PrintWriter writer) {
		writer.println("</pre>\r\n" +
				"</body>\r\n" +
				"</html>");
	}

	private ASTPrinter(PrintWriter writer, List<String> sourceLines) {
		this.writer = writer;
		this.sourceLines = sourceLines;
	}

	// ----------------------------------------------

	//	class Programa { List<Definicion> defSruct;  List<Definicion> defVariables;  List<Definicion> defFunction;  List<Definicion> defMain;  List<Sentencia> sentencias; }
	public Object visit(Programa node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Programa", node, false);

		visit(indent + 1, "defSruct", "List<Definicion>",node.getDefSruct());
		visit(indent + 1, "defVariables", "List<Definicion>",node.getDefVariables());
		visit(indent + 1, "defFunction", "List<Definicion>",node.getDefFunction());
		visit(indent + 1, "defMain", "List<Definicion>",node.getDefMain());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class DefSruct { String ident;  Tipo type; }
	public Object visit(DefSruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefSruct", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "type", "Tipo",node.getType());
		return null;
	}

	//	class DefVariables { String ident;  Tipo type; }
	public Object visit(DefVariables node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefVariables", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "type", "Tipo",node.getType());
		return null;
	}

	//	class DefFunction { String ident;  Tipo type;  List<Definicion> definiciones;  List<Sentencia> sentencias; }
	public Object visit(DefFunction node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefFunction", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "type", "Tipo",node.getType());
		visit(indent + 1, "definiciones", "List<Definicion>",node.getDefiniciones());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class DefMain { String ident;  List<Definicion> definiciones;  List<Sentencia> sentencias; }
	public Object visit(DefMain node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefMain", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "definiciones", "List<Definicion>",node.getDefiniciones());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class DefCampo { String ident;  Tipo type; }
	public Object visit(DefCampo node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "DefCampo", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "type", "Tipo",node.getType());
		return null;
	}

	//	class TipoInteger {  }
	public Object visit(TipoInteger node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoInteger", node, true);

		return null;
	}

	//	class TipoReal {  }
	public Object visit(TipoReal node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoReal", node, true);

		return null;
	}

	//	class TipoStruct { List<Definicion> definiciones; }
	public Object visit(TipoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoStruct", node, false);

		visit(indent + 1, "definiciones", "List<Definicion>",node.getDefiniciones());
		return null;
	}

	//	class TipoCharacter {  }
	public Object visit(TipoCharacter node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoCharacter", node, true);

		return null;
	}

	//	class TipoVoid {  }
	public Object visit(TipoVoid node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoVoid", node, true);

		return null;
	}

	//	class TipoArray { Tipo tipoArray;  int tam; }
	public Object visit(TipoArray node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoArray", node, false);

		visit(indent + 1, "tipoArray", "Tipo",node.getTipoArray());
		print(indent + 1, "tam", "int", node.getTam());
		return null;
	}

	//	class TipoFuncion { Tipo retorno;  List<Definicion> definiciones; }
	public Object visit(TipoFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "TipoFuncion", node, false);

		visit(indent + 1, "retorno", "Tipo",node.getRetorno());
		visit(indent + 1, "definiciones", "List<Definicion>",node.getDefiniciones());
		return null;
	}

	//	class IfElse { Expresion condicion;  List<Sentencia> ifsentencia;  List<Sentencia> elsesentencia; }
	public Object visit(IfElse node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "IfElse", node, false);

		visit(indent + 1, "condicion", "Expresion",node.getCondicion());
		visit(indent + 1, "ifsentencia", "List<Sentencia>",node.getIfsentencia());
		visit(indent + 1, "elsesentencia", "List<Sentencia>",node.getElsesentencia());
		return null;
	}

	//	class While { Expresion condicino;  List<Sentencia> sentencias; }
	public Object visit(While node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "While", node, false);

		visit(indent + 1, "condicino", "Expresion",node.getCondicino());
		visit(indent + 1, "sentencias", "List<Sentencia>",node.getSentencias());
		return null;
	}

	//	class Write { List<Expresion> expresiones; }
	public Object visit(Write node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Write", node, false);

		visit(indent + 1, "expresiones", "List<Expresion>",node.getExpresiones());
		return null;
	}

	//	class Read { List<Expresion> expresiones; }
	public Object visit(Read node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Read", node, false);

		visit(indent + 1, "expresiones", "List<Expresion>",node.getExpresiones());
		return null;
	}

	//	class Asignacion { Expresion leftExpresion;  Expresion rightExpresion; }
	public Object visit(Asignacion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Asignacion", node, false);

		visit(indent + 1, "leftExpresion", "Expresion",node.getLeftExpresion());
		visit(indent + 1, "rightExpresion", "Expresion",node.getRightExpresion());
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Return", node, false);

		visit(indent + 1, "expresion", "Expresion",node.getExpresion());
		return null;
	}

	//	class LlamadaProcedimiento { String ident;  List<Expresion> expresion; }
	public Object visit(LlamadaProcedimiento node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LlamadaProcedimiento", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "expresion", "List<Expresion>",node.getExpresion());
		return null;
	}

	//	class ExprAritmetica { Expresion left;  String operador;  Expresion right; }
	public Object visit(ExprAritmetica node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprAritmetica", node, false);

		visit(indent + 1, "left", "Expresion",node.getLeft());
		print(indent + 1, "operador", "String", node.getOperador());
		visit(indent + 1, "right", "Expresion",node.getRight());
		return null;
	}

	//	class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "Variable", node, "ident", node.getIdent());
		return null;
	}

	//	class LiteralInt { String valor; }
	public Object visit(LiteralInt node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "LiteralInt", node, "valor", node.getValor());
		return null;
	}

	//	class LiteralChar { String valor; }
	public Object visit(LiteralChar node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "LiteralChar", node, "valor", node.getValor());
		return null;
	}

	//	class LiteralReal { String valor; }
	public Object visit(LiteralReal node, Object param) {
		int indent = ((Integer)param).intValue();

		printCompact(indent, "LiteralReal", node, "valor", node.getValor());
		return null;
	}

	//	class ExprCondicional { Expresion left;  String opreador;  Expresion right; }
	public Object visit(ExprCondicional node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "ExprCondicional", node, false);

		visit(indent + 1, "left", "Expresion",node.getLeft());
		print(indent + 1, "opreador", "String", node.getOpreador());
		visit(indent + 1, "right", "Expresion",node.getRight());
		return null;
	}

	//	class LlamadaFuncion { String ident;  List<Expresion> expresiones; }
	public Object visit(LlamadaFuncion node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "LlamadaFuncion", node, false);

		print(indent + 1, "ident", "String", node.getIdent());
		visit(indent + 1, "expresiones", "List<Expresion>",node.getExpresiones());
		return null;
	}

	//	class MenosUnario { Expresion expr; }
	public Object visit(MenosUnario node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "MenosUnario", node, false);

		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class Not { Expresion expr; }
	public Object visit(Not node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Not", node, false);

		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class Cast { Tipo type;  Expresion expr; }
	public Object visit(Cast node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "Cast", node, false);

		visit(indent + 1, "type", "Tipo",node.getType());
		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class AccesoArray { Expresion identificador;  Expresion expr; }
	public Object visit(AccesoArray node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoArray", node, false);

		visit(indent + 1, "identificador", "Expresion",node.getIdentificador());
		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	//	class AccesoStruct { Expresion leftExpresion;  Expresion expr; }
	public Object visit(AccesoStruct node, Object param) {
		int indent = ((Integer)param).intValue();

		printName(indent, "AccesoStruct", node, false);

		visit(indent + 1, "leftExpresion", "Expresion",node.getLeftExpresion());
		visit(indent + 1, "expr", "Expresion",node.getExpr());
		return null;
	}

	// -----------------------------------------------------------------
	// Métodos invocados desde los métodos visit -----------------------

	private void printName(int indent, String name, AST node, boolean empty) {
		String text = ls + tabula(indent) + name + " &rarr;  ";
		text = String.format("%1$-" + 93 + "s", text);
		if (empty)
			text = text.replace(name, valueTag(name));
		writer.print(text + getPosition(node));
	}

	private void print(int indent, String name, String type, Object value) {
		write(indent, formatValue(value) + "  " + typeTag(type));
	}

	private void print(int indent, String attName, String type, List<? extends Object> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (Object child : children)
				write(indent + 1, formatValue(child));
		else
			writer.print(" " + valueTag(null));
	}

	// Versión compacta de una linea para nodos que solo tienen un atributo String
	private void printCompact(int indent, String nodeName, AST node, String attName, Object value) {
		String fullName = nodeName + '.' + attName;
		String text = ls + tabula(indent) + '\"' + value + "\"  " + fullName;
		text = String.format("%1$-" + 88 + "s", text);
		// text = text.replace(value.toString(), valueTag(value));
		text = text.replace(fullName, typeTag(fullName));
		writer.print(text + getPosition(node));
	}

	private void visit(int indent, String attName, String type, List<? extends AST> children) {
		write(indent, attName + "  " + typeTag(type) + " = ");
		if (children != null)
			for (AST child : children)
				child.accept(this, indent + 1);
		else
			writer.print(" " + valueTag(null));
	}

	private void visit(int indent, String attName, String type, AST child) {
		if (child != null)
			child.accept(this, new Integer(indent));
		else
			write(indent, valueTag(null) + "  " + attName + ':' + typeTag(type));
	}

	// -----------------------------------------------------------------
	// Métodos auxiliares privados -------------------------------------

	private void write(int indent, String text) {
		writer.print(ls + tabula(indent) + text);
	}

	private static String tabula(int count) {
		StringBuffer cadena = new StringBuffer("<span class=\"dots\">");
		for (int i = 0; i < count; i++)
			cadena.append(i % 2 == 0 && i > 0 ? "|  " : "·  ");
		return cadena.toString() + "</span>";
	}

	private String typeTag(String type) {
		if (type.equals("String"))
			return "";
		return "<span class=\"type\">" + type.replace("<", "&lt;").replace(">", "&gt;") + "</span>";
	}

	private String valueTag(Object value) {
		if (value == null)
			return "<span class=\"null\">null</span>";
		return "<span class=\"value\">" + value + "</span>";
	}

	private String formatValue(Object value) {
		String text = valueTag(value);
		if (value instanceof String)
			text = "\"" + text + '"';
		return text;
	}


	// -----------------------------------------------------------------
	// Métodos para mostrar las Posiciones -----------------------------

	private String getPosition(Traceable node) {
		String text = node.getStart() + "  " + node.getEnd();
		text = "<span class=\"pos\">" + String.format("%1$-" + 13 + "s", text) + "</span>";
		text = text.replace("null", "<span class=\"null\">null</span>");
		String sourceText = findSourceText(node);
		if (sourceText != null)
			text += sourceText;
		return text;
	}

	private String findSourceText(Traceable node) {
		if (sourceLines == null)
			return null;

		Position start = node.getStart();
		Position end = node.getEnd();
		if (start == null || end == null)
			return null;

		String afterText, text, beforeText;
		if (start.getLine() == end.getLine()) {
			String line = sourceLines.get(start.getLine() - 1);
			afterText = line.substring(0, start.getColumn() - 1);
			text = line.substring(start.getColumn() - 1, end.getColumn());
			beforeText = line.substring(end.getColumn());
		} else {
			String firstLine = sourceLines.get(start.getLine() - 1);
			String lastLine = sourceLines.get(end.getLine() - 1);

			afterText = firstLine.substring(0, start.getColumn() - 1);

			text = firstLine.substring(start.getColumn() - 1);
			text += "</span><span class=\"sourceText\">" + " ... " + "</span><span class=\"posText\">";
			text += lastLine.substring(0, end.getColumn()).replaceAll("^\\s+", "");

			beforeText = lastLine.substring(end.getColumn());
		}
		return "<span class=\"sourceText\">" + afterText.replaceAll("^\\s+", "")
				+ "</span><span class=\"posText\">" + text
				+ "</span><span class=\"sourceText\">" + beforeText + "</span>";
	}

	private static List<String> loadLines(String sourceFile, int tabWidth) {
		if (sourceFile == null)
			return null;
		try {
			String spaces = new String(new char[tabWidth]).replace("\0", " ");
			
			List<String> lines = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
			String line;
			while ((line = br.readLine()) != null)
				lines.add(line.replace("\t", spaces));
			br.close();
			return lines;
		} catch (FileNotFoundException e) {
			System.out.println("Warning. No se pudo encontrar el fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		} catch (IOException e) {
			System.out.println("Warning. Error al leer del fichero fuente '" + sourceFile + "'. No se mostrará informaicón de posición.");
			return null;
		}
	}


	private List<String> sourceLines;
	private static String ls = System.getProperty("line.separator");
	private PrintWriter writer;
}

