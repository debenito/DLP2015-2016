/**
 * @generated VGen 1.3.3
 */

package visitor;

import ast.*;

public interface Visitor {
	public Object visit(Programa node, Object param);
	public Object visit(DefSruct node, Object param);
	public Object visit(DefVariables node, Object param);
	public Object visit(DefFunction node, Object param);
	public Object visit(DefMain node, Object param);
	public Object visit(DefCampo node, Object param);
	public Object visit(TipoInteger node, Object param);
	public Object visit(TipoReal node, Object param);
	public Object visit(TipoStruct node, Object param);
	public Object visit(TipoCharacter node, Object param);
	public Object visit(TipoVoid node, Object param);
	public Object visit(TipoArray node, Object param);
	public Object visit(TipoFuncion node, Object param);
	public Object visit(IfElse node, Object param);
	public Object visit(While node, Object param);
	public Object visit(Write node, Object param);
	public Object visit(Read node, Object param);
	public Object visit(Asignacion node, Object param);
	public Object visit(Return node, Object param);
	public Object visit(LlamadaProcedimiento node, Object param);
	public Object visit(ExprAritmetica node, Object param);
	public Object visit(Variable node, Object param);
	public Object visit(LiteralInt node, Object param);
	public Object visit(LiteralChar node, Object param);
	public Object visit(LiteralReal node, Object param);
	public Object visit(ExprCondicional node, Object param);
	public Object visit(LlamadaFuncion node, Object param);
	public Object visit(MenosUnario node, Object param);
	public Object visit(Not node, Object param);
	public Object visit(Cast node, Object param);
	public Object visit(AccesoArray node, Object param);
	public Object visit(AccesoStruct node, Object param);
}
