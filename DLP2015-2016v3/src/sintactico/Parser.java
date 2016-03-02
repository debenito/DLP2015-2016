//### This file created by BYACC 1.8(/Java extension  1.14)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "sintac.y"
package sintactico;

import java.io.*;
import java.util.*;
import ast.*;
import main.*;
//#line 24 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short INTEGER=257;
public final static short REAL=258;
public final static short CODE=259;
public final static short DATA=260;
public final static short PRINT=261;
public final static short LITERALREAL=262;
public final static short LITERALINT=263;
public final static short IDENT=264;
public final static short CHARACTER=265;
public final static short LOWERTHAN=266;
public final static short GREATERTHAN=267;
public final static short GREATEREQUALS=268;
public final static short LOWEREQUALS=269;
public final static short EQUALS=270;
public final static short NOTEQUALS=271;
public final static short AND=272;
public final static short OR=273;
public final static short NOT=274;
public final static short TYPES=275;
public final static short GLOBALS=276;
public final static short FUNCTION=277;
public final static short ENDFUNCTION=278;
public final static short AS=279;
public final static short MAIN=280;
public final static short ENDMAIN=281;
public final static short WHILE=282;
public final static short ENDWHILE=283;
public final static short IF=284;
public final static short THEN=285;
public final static short ELSE=286;
public final static short ENDIF=287;
public final static short RETURN=288;
public final static short READ=289;
public final static short WRITE=290;
public final static short CHAR=291;
public final static short STRUCT=292;
public final static short ENDSTRUCT=293;
public final static short PROCEDURES=294;
public final static short VOID=295;
public final static short UNARIO=296;
public final static short IFSINELSE=297;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    6,    7,    7,    8,    2,    2,    9,
    3,    3,   12,   13,   13,   15,   11,   11,   11,   11,
   16,   16,   10,   10,   10,   10,   10,   14,    4,    4,
    5,    5,   17,   17,   18,   18,   18,   18,   18,   18,
   18,   25,   23,   21,   20,   19,   19,   24,   24,   22,
   22,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,
};
final static short yylen[] = {                            2,
   12,    2,    0,    4,    2,    1,    1,    2,    0,    3,
    2,    0,    9,    1,    0,    2,    2,    1,    3,    4,
    4,    3,    1,    1,    1,    2,    1,    2,    2,    0,
    1,    0,    2,    1,    1,    1,    1,    5,    1,    5,
    1,    5,    4,    5,    6,    7,    9,    1,    0,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    3,    3,    4,    2,    4,    3,    4,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    3,    0,    0,    9,    0,    2,    0,    0,   23,   24,
   25,    0,   12,   27,    8,    0,    0,    6,    7,   26,
    0,   18,    0,    0,    0,    4,    5,    0,    0,   11,
    0,   10,    0,   17,    0,    0,    0,   22,   19,    0,
    0,    0,   30,   20,   21,    0,    0,   14,    0,    0,
    0,   71,   72,    0,    0,    0,    0,    0,    0,    0,
   73,    0,    0,    0,   29,    0,   34,   35,   36,   37,
   39,   41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,   33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   68,   43,   28,   13,    0,   40,   66,    0,    0,   42,
   44,   38,   45,    0,    0,   46,    0,   47,
};
final static short yydgoto[] = {                          2,
    3,    7,   21,   49,   64,    6,   17,   18,   19,   16,
   24,   30,   47,  131,   48,   25,   66,   67,   68,   69,
   70,  104,   71,  105,   72,   73,
};
final static short yysindex[] = {                      -271,
    0,    0, -273,    0, -250,    0, -199,  -97,    0,    0,
    0, -248,    0,    0,    0,  -90,   42,    0,    0,    0,
 -259,    0, -241,  -27,  -85,    0,    0, -227,    1,    0,
  -50,    0,  -80,    0, -219,   54,    8,    0,    0,  -78,
  -19,  -97,    0,    0,    0,  -90,   19,    0,  -40,   41,
 -181,    0,    0,   59,  -33,   61,   63,   65,   67,   68,
    0,  -33,  -97, -172,    0,  -17,    0,    0,    0,    0,
    0,    0,  112,  -97,  -33,   72,   75,  -33,  -33,  -33,
  -33,  -33,   75,   81,    0,    0,  -33,  -33,  -33,  -33,
  -33,  -33,  -33,  -33,  -33,  -33,  -33,  -33,  -33,  -33,
 -136,  -33,    0,   86,   90,  319,  -33,  135,  146,  163,
    4,   32,  -33,  559,  559,  559,  559,  559,  559,   -7,
   -7,  -22,  -22,  -44,  -44,  -44,  349,    0,  401,  -40,
 -145,  -33,   76,   96,  -17, -146,   83,   92,   94,    0,
    0,    0,    0,    0,  319,    0,    0, -127,  -17,    0,
    0,    0,    0, -278,  -17,    0, -124,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  124,    0,    0,    0,    0,    0,    0, -114,  128,
    0,    0,    0,  286,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -67,    0,    0,    0,    0,
    0,    0,    0,    0,  129,    9,   20,    0,    0,    0,
    0,    0,   45,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  130,    0,   34,  129,    0,    0,    0,
    0,    0,    0,  428,  436,  447,  455,  482,  501,  505,
  536,  265,  420,   73,   82,  103,    0,    0,    0,  -93,
    0,    0,  412,    0,  -89,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   39,    0,    0,    0, -260,    0,
    0,    0,    0,    0,  -88,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   87,  -58,    0,    0,  180,   22,  -32,
  155,    0,    0,    0,    0,  169,    0,  141,    0,    0,
    0,  -48,    0,  105,    0,  704,
};
final static int YYTABLESIZE=836;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         63,
   23,  101,    4,    1,   62,   35,   63,  155,  156,   46,
   23,   62,   35,    8,   99,   20,   33,   28,    5,   97,
   29,   31,   63,  101,   98,   32,   32,   62,   15,   99,
   84,   32,  111,  112,   97,   95,   36,   96,  101,   98,
   37,  103,   38,   41,  138,   74,  100,  132,   43,   74,
   74,   74,   74,   74,   74,   74,   67,    9,   10,   51,
   67,   67,   67,   67,   67,   11,   67,   74,  100,   74,
   65,  143,  139,   45,   51,  132,  148,   51,   67,   50,
   67,   63,   50,  100,   33,   63,   63,   63,   63,   63,
  154,   63,   12,   42,   13,   14,  157,   74,   75,   74,
   78,   74,   79,   63,   80,   63,   81,   82,   85,   54,
   67,  107,   67,   54,   54,   54,   54,   54,   55,   54,
  101,  113,   55,   55,   55,   55,   55,  128,   55,  132,
  133,   54,  144,   54,  146,   63,  147,   63,  149,   56,
   55,  150,   55,   56,   56,   56,   56,   56,   99,   56,
  151,   65,  152,   97,   95,  153,   96,  101,   98,    9,
   10,   56,  158,   56,   15,   54,   32,   11,   16,   49,
   48,   99,  102,   22,   55,  135,   97,   95,   34,   96,
  101,   98,   99,   39,   32,   44,  136,   97,   95,  130,
   96,  101,   98,   32,   12,   56,   27,   14,   32,   99,
   50,   40,  100,  137,   97,   95,   86,   96,  101,   98,
   31,  134,    0,   31,    0,   31,    9,   10,   31,   31,
    0,   52,   53,   54,   11,  100,    0,    0,   52,   53,
   76,    0,    0,   55,    0,    0,  100,    0,    0,    0,
   55,   56,    0,   57,   52,   53,   54,   58,   59,   60,
   61,   12,    0,  100,   14,    0,   55,   61,   87,   88,
   89,   90,   91,   92,   56,    0,   57,    0,    0,    0,
   58,   59,   60,   61,   74,   74,   74,   74,   74,   74,
   74,   74,    0,    0,    0,   67,   67,   67,   67,   67,
   67,   67,   67,    0,    0,    0,    0,    0,    9,   10,
    0,    0,    0,    0,    0,   52,   11,   52,   52,   52,
   63,   63,   63,   63,   63,   63,   63,   63,    0,    0,
    0,    0,   74,   52,    0,   52,    0,   74,   74,    0,
   74,   74,   74,   12,   26,    0,   14,    0,   54,   54,
   54,   54,   54,   54,   54,   54,   74,   55,   55,   55,
   55,   55,   55,   55,   55,   99,    0,   52,    0,    0,
   97,   95,    0,   96,  101,   98,    0,    0,   56,   56,
   56,   56,   56,   56,   56,   56,   74,   87,   88,   89,
   90,   91,   92,   93,   94,   99,    0,    0,    0,    0,
   97,   95,    0,   96,  101,   98,    0,    0,    0,    0,
   87,   88,   89,   90,   91,   92,   93,   94,    0,  100,
    0,   87,   88,   89,   90,   91,   92,   93,   94,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   87,   88,
   89,   90,   91,   92,   93,   94,    0,   99,    0,  100,
    0,  141,   97,   95,    0,   96,  101,   98,   66,    0,
    0,    0,    0,   66,   66,    0,   66,   66,   66,  142,
   53,    0,   53,   53,   53,    0,    0,    0,   58,    0,
    0,   58,   66,    0,    0,    0,   57,    0,   53,   57,
   53,    0,    0,    0,    0,    0,   58,   64,   58,    0,
   64,  100,    0,    0,   57,   65,   57,    0,   65,    0,
    0,    0,   66,    0,    0,   64,    0,   64,    0,    0,
    0,    0,   53,   65,    0,   65,    0,    0,    0,    0,
   58,    0,   59,    0,    0,   59,    0,    0,   57,    0,
   52,   52,   52,   52,   52,   52,   52,   52,    0,   64,
   59,   60,   59,    0,   60,   61,    0,   65,   61,    0,
    0,   74,   74,   74,   74,   74,   74,   74,   74,   60,
    0,   60,    0,   61,    0,   61,    0,    0,    0,    0,
    0,    0,    0,    0,   59,    0,   62,    0,    0,   62,
    0,    0,    0,    0,   87,   88,   89,   90,   91,   92,
   93,   94,    0,   60,   62,   99,   62,   61,    0,    0,
   97,   95,    0,   96,  101,   98,    0,    0,    0,    0,
    0,    0,    0,    0,   87,   88,   89,   90,   91,   92,
   93,   94,    0,    0,    0,    0,    0,    0,   62,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  100,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   87,   88,   89,   90,
   91,   92,   93,   94,    0,    0,    0,   66,   66,   66,
   66,   66,   66,   66,   66,   53,   53,   53,   53,   53,
   53,   53,   53,   58,   58,   58,   58,   58,   58,   58,
   58,   57,   57,   57,   57,   57,   57,   57,   57,    0,
    0,    0,   64,   64,   64,   64,   64,   64,   64,   64,
   65,   65,   65,   65,   65,   65,   65,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   59,   59,
   59,   59,   59,   59,   59,    0,    0,    0,   77,    0,
    0,    0,    0,    0,    0,   83,   60,   60,   60,   60,
   60,   60,   60,   60,    0,    0,   61,   61,  106,    0,
    0,  108,  109,  110,  106,  106,    0,    0,    0,    0,
  114,  115,  116,  117,  118,  119,  120,  121,  122,  123,
  124,  125,  126,  127,    0,  129,    0,   62,   62,    0,
  106,    0,    0,    0,    0,    0,  140,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  145,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,   46,  276,  275,   45,   91,   40,  286,  287,   42,
   91,   45,   91,  264,   37,  264,   44,  277,  292,   42,
  280,  263,   40,   46,   47,  286,  287,   45,    7,   37,
   63,   59,   81,   82,   42,   43,  264,   45,   46,   47,
   40,   74,   93,  263,   41,   37,   91,   44,   41,   41,
   42,   43,   44,   45,   46,   47,   37,  257,  258,   41,
   41,   42,   43,   44,   45,  265,   47,   59,   91,   61,
   49,  130,   41,   93,   41,   44,  135,   44,   59,   41,
   61,   37,   44,   91,   44,   41,   42,   43,   44,   45,
  149,   47,  292,   40,  294,  295,  155,  279,   40,   91,
   40,   93,   40,   59,   40,   61,   40,   40,  281,   37,
   91,   40,   93,   41,   42,   43,   44,   45,   37,   47,
   46,   41,   41,   42,   43,   44,   45,  264,   47,   44,
   41,   59,  278,   61,   59,   91,   41,   93,  285,   37,
   59,   59,   61,   41,   42,   43,   44,   45,   37,   47,
   59,  130,   59,   42,   43,  283,   45,   46,   47,  257,
  258,   59,  287,   61,   41,   93,  281,  265,   41,   41,
   41,   37,   61,  264,   93,   41,   42,   43,  264,   45,
   46,   47,   37,  264,  278,  264,   41,   42,   43,  103,
   45,   46,   47,  283,  292,   93,   17,  295,  287,   37,
   46,   33,   91,   41,   42,   43,   66,   45,   46,   47,
  278,  107,   -1,  281,   -1,  283,  257,  258,  286,  287,
   -1,  262,  263,  264,  265,   91,   -1,   -1,  262,  263,
  264,   -1,   -1,  274,   -1,   -1,   91,   -1,   -1,   -1,
  274,  282,   -1,  284,  262,  263,  264,  288,  289,  290,
  291,  292,   -1,   91,  295,   -1,  274,  291,  266,  267,
  268,  269,  270,  271,  282,   -1,  284,   -1,   -1,   -1,
  288,  289,  290,  291,  266,  267,  268,  269,  270,  271,
  272,  273,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,  273,   -1,   -1,   -1,   -1,   -1,  257,  258,
   -1,   -1,   -1,   -1,   -1,   41,  265,   43,   44,   45,
  266,  267,  268,  269,  270,  271,  272,  273,   -1,   -1,
   -1,   -1,   37,   59,   -1,   61,   -1,   42,   43,   -1,
   45,   46,   47,  292,  293,   -1,  295,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   61,  266,  267,  268,
  269,  270,  271,  272,  273,   37,   -1,   93,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   91,  266,  267,  268,
  269,  270,  271,  272,  273,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
  266,  267,  268,  269,  270,  271,  272,  273,   -1,   91,
   -1,  266,  267,  268,  269,  270,  271,  272,  273,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   -1,   37,   -1,   91,
   -1,   93,   42,   43,   -1,   45,   46,   47,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   59,
   41,   -1,   43,   44,   45,   -1,   -1,   -1,   41,   -1,
   -1,   44,   61,   -1,   -1,   -1,   41,   -1,   59,   44,
   61,   -1,   -1,   -1,   -1,   -1,   59,   41,   61,   -1,
   44,   91,   -1,   -1,   59,   41,   61,   -1,   44,   -1,
   -1,   -1,   91,   -1,   -1,   59,   -1,   61,   -1,   -1,
   -1,   -1,   93,   59,   -1,   61,   -1,   -1,   -1,   -1,
   93,   -1,   41,   -1,   -1,   44,   -1,   -1,   93,   -1,
  266,  267,  268,  269,  270,  271,  272,  273,   -1,   93,
   59,   41,   61,   -1,   44,   41,   -1,   93,   44,   -1,
   -1,  266,  267,  268,  269,  270,  271,  272,  273,   59,
   -1,   61,   -1,   59,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,
  272,  273,   -1,   93,   59,   37,   61,   93,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,  266,  267,  268,  269,  270,
  271,  272,  273,  266,  267,  268,  269,  270,  271,  272,
  273,  266,  267,  268,  269,  270,  271,  272,  273,   -1,
   -1,   -1,  266,  267,  268,  269,  270,  271,  272,  273,
  266,  267,  268,  269,  270,  271,  272,  273,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,   -1,   -1,   -1,   55,   -1,
   -1,   -1,   -1,   -1,   -1,   62,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,  272,  273,   75,   -1,
   -1,   78,   79,   80,   81,   82,   -1,   -1,   -1,   -1,
   87,   88,   89,   90,   91,   92,   93,   94,   95,   96,
   97,   98,   99,  100,   -1,  102,   -1,  272,  273,   -1,
  107,   -1,   -1,   -1,   -1,   -1,  113,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  132,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=297;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'",null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"INTEGER","REAL","CODE","DATA","PRINT",
"LITERALREAL","LITERALINT","IDENT","CHARACTER","LOWERTHAN","GREATERTHAN",
"GREATEREQUALS","LOWEREQUALS","EQUALS","NOTEQUALS","AND","OR","NOT","TYPES",
"GLOBALS","FUNCTION","ENDFUNCTION","AS","MAIN","ENDMAIN","WHILE","ENDWHILE",
"IF","THEN","ELSE","ENDIF","RETURN","READ","WRITE","CHAR","STRUCT","ENDSTRUCT",
"PROCEDURES","VOID","UNARIO","IFSINELSE",
};
final static String yyrule[] = {
"$accept : program",
"program : TYPES dectypes GLOBALS decvariables PROCEDURES decfunciones MAIN '(' ')' defvariablesLocales sentenciasOpcionales ENDMAIN",
"dectypes : dectypes decstruct",
"dectypes :",
"decstruct : STRUCT IDENT defvarstructs ENDSTRUCT",
"defvarstructs : defvarstructs defparamstruct",
"defvarstructs : defparamstruct",
"defparamstruct : defvariables",
"decvariables : decvariables defvariables",
"decvariables :",
"defvariables : tipo identifierList ';'",
"decfunciones : decfunciones decfuncion",
"decfunciones :",
"decfuncion : FUNCTION IDENT '(' parametosfunc ')' AS tipo cuerpofuncion ENDFUNCTION",
"parametosfunc : listaparam",
"parametosfunc :",
"listaparam : tipo identifierList",
"identifierList : tipoArray IDENT",
"identifierList : IDENT",
"identifierList : identifierList ',' IDENT",
"identifierList : identifierList ',' tipoArray IDENT",
"tipoArray : tipoArray '[' LITERALINT ']'",
"tipoArray : '[' LITERALINT ']'",
"tipo : INTEGER",
"tipo : REAL",
"tipo : CHARACTER",
"tipo : STRUCT IDENT",
"tipo : VOID",
"cuerpofuncion : defvariablesLocales sentenciasOpcionales",
"defvariablesLocales : defvariablesLocales defvariables",
"defvariablesLocales :",
"sentenciasOpcionales : listaSentencias",
"sentenciasOpcionales :",
"listaSentencias : listaSentencias sentencia",
"listaSentencias : sentencia",
"sentencia : condicional",
"sentencia : bucle",
"sentencia : print",
"sentencia : WRITE '(' ListaExpresion ')' ';'",
"sentencia : asignacion",
"sentencia : IDENT '(' listaExpresionOpcional ')' ';'",
"sentencia : retorno",
"retorno : RETURN '(' expresion ')' ';'",
"asignacion : expresion '=' expresion ';'",
"print : READ '(' ListaExpresion ')' ';'",
"bucle : WHILE '(' expresion ')' sentenciasOpcionales ENDWHILE",
"condicional : IF '(' expresion ')' THEN sentenciasOpcionales ENDIF",
"condicional : IF '(' expresion ')' THEN sentenciasOpcionales ELSE sentenciasOpcionales ENDIF",
"listaExpresionOpcional : ListaExpresion",
"listaExpresionOpcional :",
"ListaExpresion : ListaExpresion ',' expresion",
"ListaExpresion : expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion GREATERTHAN expresion",
"expresion : expresion LOWERTHAN expresion",
"expresion : expresion EQUALS expresion",
"expresion : expresion NOTEQUALS expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : '-' expresion",
"expresion : expresion GREATEREQUALS expresion",
"expresion : expresion LOWEREQUALS expresion",
"expresion : IDENT '(' listaExpresionOpcional ')'",
"expresion : NOT expresion",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' IDENT",
"expresion : '(' tipo ')' expresion",
"expresion : LITERALREAL",
"expresion : LITERALINT",
"expresion : CHAR",
"expresion : IDENT",
};

//#line 231 "sintac.y"
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
//#line 536 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 4:
//#line 84 "sintac.y"
{}
break;
//#line 688 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
