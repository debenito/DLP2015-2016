/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementación base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementación por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Programa { List<Definicion> defSruct;  List<Definicion> defVariables;  List<Definicion> defFunction;  List<Definicion> defMain;  List<Sentencia> sentencias; }
	public Object visit(Programa node, Object param) {
		visitChildren(node.getDefSruct(), param);
		visitChildren(node.getDefVariables(), param);
		visitChildren(node.getDefFunction(), param);
		visitChildren(node.getDefMain(), param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class DefSruct { String ident;  Tipo type; }
	public Object visit(DefSruct node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class DefVariables { String ident;  Tipo type; }
	public Object visit(DefVariables node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class DefFunction { String ident;  Tipo type;  List<Definicion> definiciones;  List<Sentencia> sentencias; }
	public Object visit(DefFunction node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		visitChildren(node.getDefiniciones(), param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class DefMain { String ident;  List<Definicion> definiciones;  List<Sentencia> sentencias; }
	public Object visit(DefMain node, Object param) {
		visitChildren(node.getDefiniciones(), param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class DefCampo { String ident;  Tipo type; }
	public Object visit(DefCampo node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		return null;
	}

	//	class TipoInteger {  }
	public Object visit(TipoInteger node, Object param) {
		return null;
	}

	//	class TipoReal {  }
	public Object visit(TipoReal node, Object param) {
		return null;
	}

	//	class TipoStruct { List<Definicion> definiciones; }
	public Object visit(TipoStruct node, Object param) {
		visitChildren(node.getDefiniciones(), param);
		return null;
	}

	//	class TipoCharacter {  }
	public Object visit(TipoCharacter node, Object param) {
		return null;
	}

	//	class TipoVoid {  }
	public Object visit(TipoVoid node, Object param) {
		return null;
	}

	//	class TipoArray { Tipo tipoArray;  int tam; }
	public Object visit(TipoArray node, Object param) {
		if (node.getTipoArray() != null)
			node.getTipoArray().accept(this, param);
		return null;
	}

	//	class TipoFuncion { Tipo retorno;  List<Definicion> definiciones; }
	public Object visit(TipoFuncion node, Object param) {
		if (node.getRetorno() != null)
			node.getRetorno().accept(this, param);
		visitChildren(node.getDefiniciones(), param);
		return null;
	}

	//	class IfElse { Expresion condicion;  List<Sentencia> ifsentencia;  List<Sentencia> elsesentencia; }
	public Object visit(IfElse node, Object param) {
		if (node.getCondicion() != null)
			node.getCondicion().accept(this, param);
		visitChildren(node.getIfsentencia(), param);
		visitChildren(node.getElsesentencia(), param);
		return null;
	}

	//	class While { Expresion condicino;  List<Sentencia> sentencias; }
	public Object visit(While node, Object param) {
		if (node.getCondicino() != null)
			node.getCondicino().accept(this, param);
		visitChildren(node.getSentencias(), param);
		return null;
	}

	//	class Write { List<Expresion> expresiones; }
	public Object visit(Write node, Object param) {
		visitChildren(node.getExpresiones(), param);
		return null;
	}

	//	class Read { List<Expresion> expresiones; }
	public Object visit(Read node, Object param) {
		visitChildren(node.getExpresiones(), param);
		return null;
	}

	//	class Asignacion { Expresion leftExpresion;  Expresion rightExpresion; }
	public Object visit(Asignacion node, Object param) {
		if (node.getLeftExpresion() != null)
			node.getLeftExpresion().accept(this, param);
		if (node.getRightExpresion() != null)
			node.getRightExpresion().accept(this, param);
		return null;
	}

	//	class Return { Expresion expresion; }
	public Object visit(Return node, Object param) {
		if (node.getExpresion() != null)
			node.getExpresion().accept(this, param);
		return null;
	}

	//	class LlamadaProcedimiento { String ident;  List<Expresion> expresion; }
	public Object visit(LlamadaProcedimiento node, Object param) {
		visitChildren(node.getExpresion(), param);
		return null;
	}

	//	class ExprAritmetica { Expresion left;  String operador;  Expresion right; }
	public Object visit(ExprAritmetica node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class Variable { String ident; }
	public Object visit(Variable node, Object param) {
		return null;
	}

	//	class LiteralInt { String valor; }
	public Object visit(LiteralInt node, Object param) {
		return null;
	}

	//	class LiteralChar { String valor; }
	public Object visit(LiteralChar node, Object param) {
		return null;
	}

	//	class LiteralReal { String valor; }
	public Object visit(LiteralReal node, Object param) {
		return null;
	}

	//	class ExprCondicional { Expresion left;  String opreador;  Expresion right; }
	public Object visit(ExprCondicional node, Object param) {
		if (node.getLeft() != null)
			node.getLeft().accept(this, param);
		if (node.getRight() != null)
			node.getRight().accept(this, param);
		return null;
	}

	//	class LlamadaFuncion { String ident;  List<Expresion> expresiones; }
	public Object visit(LlamadaFuncion node, Object param) {
		visitChildren(node.getExpresiones(), param);
		return null;
	}

	//	class MenosUnario { Expresion expr; }
	public Object visit(MenosUnario node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class Not { Expresion expr; }
	public Object visit(Not node, Object param) {
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class Cast { Tipo type;  Expresion expr; }
	public Object visit(Cast node, Object param) {
		if (node.getType() != null)
			node.getType().accept(this, param);
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class AccesoArray { Expresion identificador;  Expresion expr; }
	public Object visit(AccesoArray node, Object param) {
		if (node.getIdentificador() != null)
			node.getIdentificador().accept(this, param);
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}

	//	class AccesoStruct { Expresion leftExpresion;  Expresion expr; }
	public Object visit(AccesoStruct node, Object param) {
		if (node.getLeftExpresion() != null)
			node.getLeftExpresion().accept(this, param);
		if (node.getExpr() != null)
			node.getExpr().accept(this, param);
		return null;
	}
	
	// Método auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
