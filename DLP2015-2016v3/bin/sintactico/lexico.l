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

	// Traza para probar el léxico de manera independiente al sintáctico
	public static void main(String[] args) throws Exception {
		Yylex lex = new Yylex(new FileReader(Main.sourceFile), new GestorErrores());
		int token;
		while ((token = lex.yylex()) != 0)
			System.out.println("\t[" + lex.line() + ":" + lex.column() + "] Token: " + token + ". Lexema: " + lex.lexeme());
	}

	private GestorErrores gestor;
%}

LETRA=[a-zñA-ZÑ] 
LITENTERO =[0-9]+
IDENT={LETRA}+({LETRA}|{LITENTERO})*
LITREAL =[+\-]? [0-9]+"."[0-9]+ [E]?
CHAR= \'.\'
%%

/* -- Modificar aquí. Añadir reglas en esta sección ----------------------------------------- */

[+\-*//;\()=%\[\]\,\.] {return yytext().charAt(0); }
DATA { return Parser.DATA; }
CODE { return Parser.CODE; }
{LITREAL} { return Parser.LITERALREAL;}
{CHAR}    {return Parser.CHAR;}
{LITENTERO} { return Parser.LITERALINT;}

/*--OPERADORES DE COMPARACION-- */
eq    {return Parser.EQUALS;}
lt		{return Parser.LOWERTHAN;}
le		{return Parser.LOWEREQUALS;}
gt		{return Parser.GREATERTHAN;}
ge      {return Parser.GREATEREQUALS;}
ne 		{return Parser.NOTEQUALS;}

/*--OPERADORES LOGICOS--*/
and 	{return Parser.AND;}
or		{return Parser.OR;}
not		{return Parser.NOT;}
/*--Palabras Reservadas del Lenguaje --*/
types   		{return Parser.TYPES;}
globals		  	{return Parser.GLOBALS;}
procedures		 {return Parser.PROCEDURES;}
function		{return Parser.FUNCTION;}
endfunction			{return Parser.ENDFUNCTION;}
as					{return Parser.AS;}
main			{return Parser.MAIN;}
endmain		{return Parser.ENDMAIN;}
while			{return Parser.WHILE;}
endwhile		{return Parser.ENDWHILE;}
if			{return Parser.IF;}
then        {return Parser.THEN;}
else			{return Parser.ELSE;}
endif			{return Parser.ENDIF;}
return		{return Parser.RETURN;}
read			{return Parser.READ;}
write			{return Parser.WRITE;}
integer 			{ return Parser.INTEGER; }
character			{return Parser.CHARACTER; }
real 		{return Parser.REAL; }
struct		{return Parser.STRUCT;}
endstruct		{return Parser.ENDSTRUCT;}
void 				{return Parser.VOID;}
{IDENT} { return Parser.IDENT; }
/* -- Se aconseja dejar estas reglas al final ----------------------------------------------- */

[\/\*~\*\/]		{ }		/* Comentario de varias lineas como este */
"#". *							{ }		// Comentario de una linea como este

[ \n\r]		{ }
"\t"		{ yycolumn += 3; } // Para que coincida con la info del editor de Eclipse (opcional). En eclipse: \t = 4 espacios. En Jflex: \t = 1 carácter.

.			{ gestor.error("Léxico", "Cadena \"" + yytext() +"\" no reconocida.", new Position(line(), column())); }
