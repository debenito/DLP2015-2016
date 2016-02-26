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
%token LOWEREQUALS 
%token EQUALS 
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
%token THEN 
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
/* Precedencias

/* Precedencias aquí --------------------------------------- */
%left  OR AND 
%left  LOWERTHAN  GREATERTHAN LOWEREQUALS GREATEREQUALS EQUALS NOTEQUALS 
%left '+' '-'
%left '*' '/' '%'
%left  '['  ']' 
%right NOT UNARIO 
%left  '.'
%left '(' ')'
%nonassoc IFSINELSE
%nonassoc ELSE
%%

/* Añadir las reglas en esta sección ----------------------- */

program: TYPES dectypes  GLOBALS decvariables PROCEDURES decfunciones MAIN '('')' listaSentencias ENDMAIN
			; 
/* Declaracion de type */

dectypes : dectypes decstruct  
		|decstruct		
		;
			
/*Declaracion de structuras */

 decstruct : STRUCT IDENT defvarstructs ENDSTRUCT  {}
 			;
 
 /*Declaracion de variables del Struct*/
 
 defvarstructs : defvarstructs defparamstruct					
             |defparamstruct 
             ;
             
 /* Parametro struct*/
 
 defparamstruct : defvariables 
 				;
 				
 /*Declaracion Variables Globales */				
 decvariables : decvariables  defvariables 
 				| defvariables
 				;
 				
 				
 defvariables:  tipo identifierList ';'  ;      


/*Declaracion de funciones en Procedures */

decfunciones: decfunciones decfuncion
			| decfuncion 
			;
			
decfuncion : FUNCTION IDENT '(' parametosfunc ')' AS tipo cuerpofuncion ENDFUNCTION
			;
			
/*Parametros de la funcion*/

parametosfunc : listaparam
				|
				;
				
listaparam: tipo identifierList  
			;
/*Declaracion multiple de variables  */

identifierList:  tipoArray IDENT
				| IDENT       
                | identifierList ',' IDENT    
                | identifierList ',' tipoArray IDENT
                  ;
/*Tipo array */ 
			
 tipoArray: tipoArray '[' LITERALINT ']' 
 			| '[' LITERALINT ']'
 			;
 			
/* Tipos simples */
 						
 tipo:  INTEGER
 	|REAL
 	|CHARACTER
 	|STRUCT IDENT
 	|VOID
 	;
 	                  			
/*Cuerpo de la funcion */

cuerpofuncion: defvariablesLocales	sentenciasOpcionales
			;
			
/* Definicion de variables locales */

defvariablesLocales : defvariablesLocales defvariables
					|	
					;		
/* Sentencias declaracion */

sentenciasOpcionales : listaSentencias 
					| 
					;
					
listaSentencias :listaSentencias sentencia
				| sentencia
				;				
/* Aqui esta la llamada a un Procedimiento entre las sentencias*/
sentencia : condicional
		| bucle
		| print
		| WRITE '(' ListaExpresion ')' ';'
		| asignacion 
		| IDENT '(' listaExpresionOpcional ')' ';'
		| retorno 
		;
retorno : RETURN '(' expresion ')' ';'
		;		

asignacion : expresion '=' expresion ';'
			;
			
print : READ '(' ListaExpresion ')' ';'
		;
		
bucle : WHILE '(' expresion ')' sentenciasOpcionales ENDWHILE
		;
		
condicional :  IF '('expresion ')' THEN sentenciasOpcionales ENDIF           %prec IFSINELSE		
		    | IF '('expresion ')' THEN sentenciasOpcionales ELSE sentenciasOpcionales ENDIF
			;
/* Expresiones */
			
listaExpresionOpcional : ListaExpresion 
						| /* Nada*/
						;
ListaExpresion: ListaExpresion ',' expresion
				| expresion 	
				;
/* Entre las expresiones estan las llamas a funciones, acceso a arrays, .. */				
expresion : expresion '+' expresion
			| expresion '-' expresion
			| expresion '*' expresion
			| expresion '/' expresion
			| expresion '%' expresion
			|expresion GREATERTHAN expresion
			|expresion LOWERTHAN expresion
			|expresion EQUALS expresion
			| expresion NOTEQUALS expresion
			|expresion AND expresion
			|expresion OR expresion
			| '-' expresion 					%prec UNARIO
			| expresion GREATEREQUALS expresion
			|expresion LOWEREQUALS expresion
			|IDENT '('listaExpresionOpcional ')'
			| NOT expresion
			| expresion '[' expresion ']'
			| expresion '.' IDENT
			|'('tipo')' expresion
			| LITERALREAL
			| LITERALINT
			| CHAR
			| IDENT
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
