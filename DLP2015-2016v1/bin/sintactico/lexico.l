/* -- No es necesario modificar esta parte ----------------------------------------------- */
package sintactico;

import java.io.*;
import main.*;
import ast.Position;

%%
%byaccj
%unicode
%line
%column
%public
%{
	public Yylex(Reader in, GestorErrores gestor) {
		this(in);
		this.gestor = gestor;
	}

	public int line() { return yyline + 1; }
	public int column() { return yycolumn + 1; }
	public String lexeme() { return yytext(); }

	// Traza para probar el l�xico de manera independiente al sint�ctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.sourceFile), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;
%}

LETRA=[a-z�A-Z�] 
LITENTERO =[0-9]+
IDENT={LETRA}+({LETRA}|{LITENTERO})*
LITREAL =[+\-]? [0-9]+"."[0-9]+
CHARACTER= \'.\'
%%

/* -- Modificar aqu�. A�adir reglas en esta secci�n ----------------------------------------- */

[+\-*/;\()=%\[\[\,\.] {return yytext().charAt(0); }
DATA { return Tokens.DATA; }
CODE { return Tokens.CODE; }
{LITREAL} { return Tokens.LITERALREAL;}
{CHARACTER}    {return Tokens.CHARACTER;}
{LITENTERO} { return Tokens.LITERALINT;}

/*--OPERADORES DE COMPARACION-- */
eq    {return Tokens.EQUALS;}
lt		{return Tokens.LOWERTHAN;}
le		{return Tokens.LOWEREQUALS;}
gt		{return Tokens.GREATERTHAN;}
ge      {return Tokens.GREATEREQUALS;}
ne 		{return Tokens.NOTEQUALS;}

/*--OPERADORES LOGICOS--*/
and 	{return Tokens.AND;}
or		{return Tokens.OR;}
not		{return Tokens.NOT;}
/*--Palabras Reservadas del Lenguaje --*/
types   {return Tokens.TYPES;}
globals  {return Tokens.GLOBALS;}
procedures {return Tokens.PROCEDURES;}
function		{return Tokens.FUNCTION;}
endfunction	{return Tokens.ENDFUNCTION;}
as			{return Tokens.AS;}
main			{return Tokens.MAIN;}
endmain		{return Tokens.ENDMAIN;}
while			{return Tokens.WHILE;}
endwhile		{return Tokens.ENDWHILE;}
if			{return Tokens.IF;}
else			{return Tokens.ELSE;}
endif			{return Tokens.ENDIF;}
return		{return Tokens.RETURN;}
read			{return Tokens.READ;}
write			{return Tokens.WRITE;}
int 			{ return Tokens.INT; }
char			{return Tokens.CHAR; }
float 		{return Tokens.REAL; }
struct		{return Tokens.STRUCT;}
endstruct		{return Tokens.ENDSTRUCT;}
void 				{return Tokens.VOID;}
{IDENT} { return Tokens.IDENT; }
/* -- Se aconseja dejar estas reglas al final ----------------------------------------------- */

[\/\*~\*\/]		{ }		/* Comentario de varias lineas como este */
"#". *							{ }		// Comentario de una linea como este

[ \n\r]		{ }
"\t"		{ yycolumn += 3; } // Para que coincida con la info del editor de Eclipse (opcional). En eclipse: \t = 4 espacios. En Jflex: \t = 1 car�cter.

.			{ gestor.error("L�xico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column())); }
