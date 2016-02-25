/* No es necesario modificar esta sección ------------------ */
%{
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
%}
%token INTEGER 
%token REAL 

%token CODE 
%token DATA 

%token PRINT 

%token LITERALREAL 
%token LITERALINT 
%token IDENT 
%token CHARACTER 
	
	
%token LOWERTHAN 
%token GREATERTHAN 
%token GREATEREQUALS 
%token NOTEQUALS 
%token AND 
%token OR 
%token NOT 
	
%token TYPES 
%token GLOBALS 
%token FUNCTION 
%token ENDFUNCTION 
%token AS 
%token MAIN 
%token ENDMAIN 
%token WHILE 
%token ENDWHILE 
%token IF 
%token ELSE 
%token ENDIF 
%token RETURN 
%token READ 
%token WRITE 
%token CHAR 
%token STRUCT 
%token ENDSTRUCT 
%token PROCEDURES 
%token LOWEREQUALS 
%token VOID 
%token EQUALS 
/* Precedencias aquí --------------------------------------- */
%left '+' '-'
%left '*' '/'

%%

/* Añadir las reglas en esta sección ----------------------- */

program: TYPES dectypes 
			; 
/* Declaracion de type */
dectypes : dectypes decstruct  
		|decstruct		
		;
			
/*Declaracion de structuras */
 decstruct : STRUCT IDENT decvariables ENDSTRUCT  {}
 			;
 
 /*Declaracion de variables*/
 decvariables : decvariables defvariables					
             |defvariables
             ;
             
 /* Definicion de variables*/
 defvariables : tipo IDENT ';' 	            		
 				| tipo IDENT ',' defvariables 
 				;             
tipo: tipoSimple
   	| tipoComplejo
        ;
 tipoSimple: INTEGER
 			|REAL
 			|CHARACTER
 			;
 tipoComplejo : 	
 			;			       	
;	







%%
/* No es necesario modificar esta sección ------------------ */

public Parser(Yylex lex, GestorErrores gestor, boolean debug) {
	this(debug);
	this.lex = lex;
	this.gestor = gestor;
}

// Métodos de acceso para el main -------------
public int parse() { return yyparse(); }
public AST getAST() { return raiz; }

// Funciones requeridas por Yacc --------------
void yyerror(String msg) {
	Token lastToken = (Token) yylval;
	gestor.error("Sintáctico", "Token = " + lastToken.getToken() + ", lexema = \"" + lastToken.getLexeme() + "\". " + msg, lastToken.getStart());
}

int yylex() {
	try {
		int token = lex.yylex();
		yylval = new Token(token, lex.lexeme(), lex.line(), lex.column());
		return token;
	} catch (IOException e) {
		return -1;
	}
}

private Yylex lex;
private GestorErrores gestor;
private AST raiz;
