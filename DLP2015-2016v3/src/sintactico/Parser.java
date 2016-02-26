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
    0,    1,    1,    5,    6,    6,    7,    2,    2,    8,
    3,    3,   11,   12,   12,   14,   10,   10,   10,   10,
   15,   15,    9,    9,    9,    9,    9,   13,   16,   16,
   17,   17,    4,    4,   18,   18,   18,   18,   18,   18,
   18,   25,   23,   21,   20,   19,   19,   24,   24,   22,
   22,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,
};
final static short yylen[] = {                            2,
   11,    2,    1,    4,    2,    1,    1,    2,    1,    3,
    2,    1,    9,    1,    0,    2,    2,    1,    3,    4,
    4,    3,    1,    1,    1,    2,    1,    2,    2,    0,
    1,    0,    2,    1,    1,    1,    1,    5,    1,    5,
    1,    5,    4,    5,    6,    7,    9,    1,    0,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    3,    3,    4,    2,    4,    3,    4,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    3,    0,    0,    2,   23,   24,
   25,    0,   27,    0,    6,    7,    0,    0,    9,   26,
    4,    5,   18,    0,    0,    0,    0,    8,    0,   10,
    0,   17,    0,    0,    0,   12,   22,   19,    0,    0,
    0,    0,   11,   20,   21,    0,    0,    0,    0,   14,
    0,    0,    0,   71,   72,    0,    0,    0,    0,    0,
    0,    0,   73,    0,    0,    0,   34,   35,   36,   37,
   39,   41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,   33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   68,   43,   13,    0,   29,   28,    0,   40,   66,    0,
    0,   42,   44,   38,   45,    0,    0,   46,    0,   47,
};
final static short yydgoto[] = {                          2,
    4,   18,   35,  144,    5,   14,   15,   16,   17,   25,
   36,   49,  130,   50,   26,  131,  146,   67,   68,   69,
   70,  104,   71,  105,   72,   73,
};
final static short yysindex[] = {                      -271,
 -284,    0, -250, -265,    0,  211,  211,    0,    0,    0,
    0, -221,    0,  -62,    0,    0,  -85,   11,    0,    0,
    0,    0,    0, -218,   -2,  -82, -226,    0,  -37,    0,
  -81,    0, -203, -202, -225,    0,    0,    0,  -71,  -25,
   55,   59,    0,    0,    0,  211,   35,  -85,   43,    0,
   -4,   60, -176,    0,    0,   65,   19,   66,   69,   76,
   78,   80,    0,   19,  211,  -17,    0,    0,    0,    0,
    0,    0,  286,  211,   19,   82,   77,   19,   19,   19,
   19,   19,   77,   83,    0,    0,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
 -139,   19,    0,   86,   85,  413,   19,  144,  165,  307,
   36,   37,   19,   -8,   -8,   -8,   -8,   -8,   -8,  -30,
  -30,    7,    7,  -44,  -44,  -44,  328,    0,  349, -151,
  -40,   19,   84,   96,   -4, -146,   87,   88,   89,    0,
    0,    0,    0,   -4,    0,    0,  413,    0,    0, -142,
   -4,    0,    0,    0,    0, -268,   -4,    0, -143,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  110,    0,    0,    0,    0,
    0,  117,    0,    0,    0,  381,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  119,   28,   49,    0,    0,    0,
    0,    0,   70,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  121,    0,   44,  119,    0,    0,    0,
    0,    0,    0,  440,  451,  467,  475,  488,  543,  -11,
  547,  421,  430,   91,  112,  133,    0,    0,    0,    0,
 -114,    0,  402,    0, -118,    0,    0,    0,    0,    0,
    0,    0,    0, -220,    0,    0,   56,    0,    0,    0,
 -261,    0,    0,    0,    0,    0, -121,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  116,  164,    0,  155,   14,  -43,  124,
  153,    0,    0,    0,  166,    0, -111,  -65,    0,    0,
    0,   20,    0,   92,    0,  743,
};
final static int YYTABLESIZE=875;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
   86,  101,   48,    1,   64,   24,   99,    3,   33,   24,
    7,   97,   95,    6,   96,  101,   98,  157,  158,   33,
   19,   84,   65,  150,   32,   32,    3,   64,   99,   61,
  103,   28,   61,   97,   95,   65,   96,  101,   98,  156,
   64,   31,   20,   99,   29,  159,  100,   61,   97,   61,
   34,   34,  101,   98,   42,   37,   30,   31,   65,   40,
  100,   41,   31,   64,   74,   31,   31,   45,   74,   74,
   74,   74,   74,   74,   74,   51,  138,  139,   86,  132,
  132,   61,  100,   53,   51,   67,   74,   51,   74,   67,
   67,   67,   67,   67,   46,   67,   50,  100,   47,   50,
  111,  112,   74,   31,   75,   78,   63,   67,   79,   67,
   63,   63,   63,   63,   63,   80,   63,   81,   74,   82,
   74,  107,  101,  113,  128,  133,  143,   54,   63,  132,
   63,   54,   54,   54,   54,   54,  149,   54,  151,   67,
  155,   67,  148,  160,  145,  152,  153,  154,   55,   54,
   15,   54,   55,   55,   55,   55,   55,   16,   55,   49,
   63,   48,   63,   32,   32,   32,   66,    8,   22,   56,
   55,   52,   55,   56,   56,   56,   56,   56,   23,   56,
   99,   32,   38,   54,  135,   97,   95,   43,   96,  101,
   98,   56,   44,   56,    9,   10,   39,    0,  134,    0,
    0,   99,   11,    0,   55,  136,   97,   95,    0,   96,
  101,   98,    0,    0,    0,    0,    9,   10,    0,    0,
    0,   54,   55,   56,   11,   56,    0,    0,    0,   12,
   21,    0,   13,   57,  100,   87,   88,   89,   90,   91,
   92,   58,    0,   59,   54,   55,   56,   60,   61,   62,
   63,   12,    0,    0,   13,  100,   57,   54,   55,   56,
   61,   61,    0,   85,   58,    0,   59,    9,   10,   57,
   60,   61,   62,   63,    0,   11,    0,   58,    0,   59,
   54,   55,   76,   60,   61,   62,   63,    0,    0,    0,
    0,    0,   57,   74,   74,   74,   74,   74,   74,   74,
   74,    0,   12,    0,   27,   13,    0,    0,    0,   63,
    0,    0,    0,    0,   67,   67,   67,   67,   67,   67,
   67,   67,   99,    0,    0,    0,    0,   97,   95,    0,
   96,  101,   98,    0,    0,   63,   63,   63,   63,   63,
   63,   63,   63,   99,    0,    0,  102,  137,   97,   95,
    0,   96,  101,   98,    0,    0,   54,   54,   54,   54,
   54,   54,   54,   54,   99,    0,    0,    0,    0,   97,
   95,    0,   96,  101,   98,    0,  100,   55,   55,   55,
   55,   55,   55,   55,   55,   99,    0,    0,    0,    0,
   97,   95,    0,   96,  101,   98,    0,  100,   56,   56,
   56,   56,   56,   56,   56,   56,    0,  142,    0,   87,
   88,   89,   90,   91,   92,   93,   94,   74,  100,    0,
  141,    0,   74,   74,    0,   74,   74,   74,    0,    0,
   87,   88,   89,   90,   91,   92,   93,   94,   66,  100,
    0,   74,    0,   66,   66,    0,   66,   66,   66,   99,
    0,    0,    0,    0,   97,   95,    0,   96,  101,   98,
    0,   52,   66,   52,   52,   52,    0,    9,   10,    0,
   53,   74,   53,   53,   53,   11,    0,    0,    0,   52,
   58,   52,    0,   58,    0,    0,    0,    0,   53,    0,
   53,   57,   66,    0,   57,    0,    0,    0,   58,    0,
   58,    0,   12,  100,    0,   13,    0,   64,    0,   57,
   64,   57,    0,   52,    0,   65,    0,    0,   65,    0,
    0,    0,   53,    0,    0,   64,    0,   64,   59,    0,
    0,   59,   58,   65,    0,   65,    0,    0,    0,    0,
    0,    0,    0,   57,    0,    0,   59,    0,   59,    0,
    0,   87,   88,   89,   90,   91,   92,   93,   94,   64,
    0,    0,    0,    0,    0,    0,    0,   65,    0,    0,
    0,    0,   87,   88,   89,   90,   91,   92,   93,   94,
   59,    0,    0,   60,    0,    0,   60,   62,    0,    0,
   62,    0,    0,   87,   88,   89,   90,   91,   92,   93,
   94,   60,    0,   60,    0,   62,    0,   62,    0,    0,
    0,    0,    0,    0,   87,   88,   89,   90,   91,   92,
   93,   94,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   60,    0,    0,    0,   62,
    0,    0,    0,    0,    0,    0,   74,   74,   74,   74,
   74,   74,   74,   74,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   66,   66,   66,
   66,   66,   66,   66,   66,    0,    0,    0,   87,   88,
   89,   90,   91,   92,   93,   94,   52,   52,   52,   52,
   52,   52,   52,   52,    0,   53,   53,   53,   53,   53,
   53,   53,   53,    0,    0,   58,   58,   58,   58,   58,
   58,   58,   58,    0,    0,    0,   57,   57,   57,   57,
   57,   57,   57,   57,    0,    0,    0,    0,    0,    0,
    0,    0,   64,   64,   64,   64,   64,   64,   64,   64,
   65,   65,   65,   65,   65,   65,   65,   65,    0,    0,
    0,    0,    0,   59,   59,   59,   59,   59,   59,   59,
   59,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   77,
    0,    0,    0,    0,    0,    0,   83,    0,   60,   60,
   60,   60,   60,   60,   60,   60,    0,  106,   62,   62,
  108,  109,  110,  106,  106,    0,    0,    0,    0,  114,
  115,  116,  117,  118,  119,  120,  121,  122,  123,  124,
  125,  126,  127,    0,  129,    0,    0,    0,    0,  106,
    0,    0,    0,    0,    0,  140,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  147,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   66,   46,   46,  275,   45,   91,   37,  292,   91,   91,
  276,   42,   43,  264,   45,   46,   47,  286,  287,   91,
    7,   65,   40,  135,  286,  287,  292,   45,   37,   41,
   74,   18,   44,   42,   43,   40,   45,   46,   47,  151,
   45,   44,  264,   37,  263,  157,   91,   59,   42,   61,
  277,  277,   46,   47,  280,   93,   59,  278,   40,  263,
   91,  264,  283,   45,   37,  286,  287,   93,   41,   42,
   43,   44,   45,   46,   47,   41,   41,   41,  144,   44,
   44,   93,   91,   41,   41,   37,   59,   44,   61,   41,
   42,   43,   44,   45,   40,   47,   41,   91,   40,   44,
   81,   82,  279,   44,   40,   40,   37,   59,   40,   61,
   41,   42,   43,   44,   45,   40,   47,   40,   91,   40,
   93,   40,   46,   41,  264,   41,  278,   37,   59,   44,
   61,   41,   42,   43,   44,   45,   41,   47,  285,   91,
  283,   93,   59,  287,  131,   59,   59,   59,   37,   59,
   41,   61,   41,   42,   43,   44,   45,   41,   47,   41,
   91,   41,   93,  278,  283,  287,   51,    4,   14,   37,
   59,   48,   61,   41,   42,   43,   44,   45,  264,   47,
   37,  264,  264,   93,   41,   42,   43,   35,   45,   46,
   47,   59,  264,   61,  257,  258,   31,   -1,  107,   -1,
   -1,   37,  265,   -1,   93,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,  257,  258,   -1,   -1,
   -1,  262,  263,  264,  265,   93,   -1,   -1,   -1,  292,
  293,   -1,  295,  274,   91,  266,  267,  268,  269,  270,
  271,  282,   -1,  284,  262,  263,  264,  288,  289,  290,
  291,  292,   -1,   -1,  295,   91,  274,  262,  263,  264,
  272,  273,   -1,  281,  282,   -1,  284,  257,  258,  274,
  288,  289,  290,  291,   -1,  265,   -1,  282,   -1,  284,
  262,  263,  264,  288,  289,  290,  291,   -1,   -1,   -1,
   -1,   -1,  274,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,  292,   -1,  294,  295,   -1,   -1,   -1,  291,
   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,
  272,  273,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,  273,   37,   -1,   -1,   61,   41,   42,   43,
   -1,   45,   46,   47,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   91,  266,  267,  268,
  269,  270,  271,  272,  273,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   91,  266,  267,
  268,  269,  270,  271,  272,  273,   -1,   59,   -1,  266,
  267,  268,  269,  270,  271,  272,  273,   37,   91,   -1,
   93,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
  266,  267,  268,  269,  270,  271,  272,  273,   37,   91,
   -1,   61,   -1,   42,   43,   -1,   45,   46,   47,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   41,   61,   43,   44,   45,   -1,  257,  258,   -1,
   41,   91,   43,   44,   45,  265,   -1,   -1,   -1,   59,
   41,   61,   -1,   44,   -1,   -1,   -1,   -1,   59,   -1,
   61,   41,   91,   -1,   44,   -1,   -1,   -1,   59,   -1,
   61,   -1,  292,   91,   -1,  295,   -1,   41,   -1,   59,
   44,   61,   -1,   93,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   93,   -1,   -1,   59,   -1,   61,   41,   -1,
   -1,   44,   93,   59,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   59,   -1,   61,   -1,
   -1,  266,  267,  268,  269,  270,  271,  272,  273,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,  266,  267,  268,  269,  270,  271,  272,  273,
   93,   -1,   -1,   41,   -1,   -1,   44,   41,   -1,   -1,
   44,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
  273,   59,   -1,   61,   -1,   59,   -1,   61,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,   -1,   -1,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,  266,  267,  268,  269,  270,
  271,  272,  273,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,  273,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  266,  267,  268,  269,  270,  271,  272,  273,
  266,  267,  268,  269,  270,  271,  272,  273,   -1,   -1,
   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   57,
   -1,   -1,   -1,   -1,   -1,   -1,   64,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   -1,   75,  272,  273,
   78,   79,   80,   81,   82,   -1,   -1,   -1,   -1,   87,
   88,   89,   90,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,   -1,  102,   -1,   -1,   -1,   -1,  107,
   -1,   -1,   -1,   -1,   -1,  113,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  132,
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
"program : TYPES dectypes GLOBALS decvariables PROCEDURES decfunciones MAIN '(' ')' listaSentencias ENDMAIN",
"dectypes : dectypes decstruct",
"dectypes : decstruct",
"decstruct : STRUCT IDENT defvarstructs ENDSTRUCT",
"defvarstructs : defvarstructs defparamstruct",
"defvarstructs : defparamstruct",
"defparamstruct : defvariables",
"decvariables : decvariables defvariables",
"decvariables : defvariables",
"defvariables : tipo identifierList ';'",
"decfunciones : decfunciones decfuncion",
"decfunciones : decfuncion",
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
//#line 544 "Parser.java"
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
//#line 696 "Parser.java"
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
