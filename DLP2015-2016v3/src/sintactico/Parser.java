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
public final static short IFSINELSE=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    5,    6,    6,    7,    2,    2,    8,
    3,    3,   11,   12,   12,   14,   10,   10,   10,   10,
   15,   15,    9,    9,    9,    9,    9,   13,   16,   16,
   17,   17,    4,    4,   18,   18,   18,   18,   18,   18,
   18,   25,   23,   21,   20,   19,   19,   24,   24,   22,
   22,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,
};
final static short yylen[] = {                            2,
   11,    2,    1,    4,    2,    1,    1,    2,    1,    3,
    2,    1,    9,    1,    0,    2,    2,    1,    3,    4,
    4,    3,    1,    1,    1,    2,    1,    2,    2,    0,
    1,    0,    2,    1,    1,    1,    1,    5,    1,    4,
    1,    5,    4,    5,    6,    7,    9,    1,    0,    3,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    2,    4,    3,    1,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    3,    0,    0,    2,   23,   24,
   25,    0,   27,    0,    6,    7,    0,    0,    9,   26,
    4,    5,   18,    0,    0,    0,    0,    8,    0,   10,
    0,   17,    0,    0,    0,   12,   22,   19,    0,    0,
    0,    0,   11,   20,   21,    0,    0,    0,    0,   14,
    0,    0,    0,   69,   70,    0,    0,    0,    0,    0,
    0,    0,   71,    0,   34,   35,   36,   37,   39,   41,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,   33,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   30,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   68,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   67,   43,   13,    0,   29,   28,
    0,   65,    0,    0,   42,   44,   38,   45,    0,    0,
   46,    0,   47,
};
final static short yydgoto[] = {                          2,
    4,   18,   35,  138,    5,   14,   15,   16,   17,   25,
   36,   49,  125,   50,   26,  126,  140,   65,   66,   67,
   68,  100,   69,  101,   70,   71,
};
final static short yysindex[] = {                      -273,
 -272,    0, -222, -251,    0,  168,  168,    0,    0,    0,
    0, -213,    0,   47,    0,    0,  -87, -179,    0,    0,
    0,    0,    0, -210,  -30,  -84, -211,    0,  -38,    0,
  -80,    0, -199, -172, -240,    0,    0,    0,  -79,  -25,
   45,   86,    0,    0,    0,  168,   63,  -87,   76,    0,
  484,   95, -134,    0,    0,  125,  -81,  127,  138,  146,
  149,  155,    0,  461,    0,    0,    0,    0,    0,    0,
   51,  168,  -81,  156,  366,  -81,  -81,  -81,  -81,  -81,
    0,    0,  -81,  -81,  -81,  -81,  -81,  -81,  -81,  -81,
  -81,  -81,  -81,  -81,  -81,  -81, -110,  -81,    0,  143,
  128,  172,  -81,   64,   77,   88,   40,   58,  -24,  -24,
  -24,  -24,  -24,  -24,  366,  366,  116,  116,   -8,   -8,
   -8,  101,    0,  114,  -78,  318,  -81,    0,  157,  484,
  -88,  140,  142,  144,    0,    0,    0,  484,    0,    0,
  172,    0,  -72,  484,    0,    0,    0,    0, -271,  484,
    0,  -85,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  163,    0,    0,    0,    0,
    0,  171,    0,    0,    0,  129,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  175,  -11,  147,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  180,
    0,   59,  175,    0,    0,    0,    0,    0,  387,  406,
  414,  430,  441,  449,  418,  465,  248,  373,    2,   15,
   28,    0,    0,    0,    0,  -65,    0,  -37,    0,  -60,
    0,    0,    0,    0,    0,    0,    0, -146,    0,    0,
  108,    0,    0, -196,    0,    0,    0,    0,    0,  -63,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  177,  218,    0,  224,   10,  -45,  191,
  207,    0,    0,    0,  212,    0,   20,  -61,    0,    0,
    0,   48,    0,  145,    0,  476,
};
final static int YYTABLESIZE=775;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
   48,    1,   82,   24,   65,   65,   33,   65,   65,   65,
   24,   33,   95,   31,  150,  151,   19,   93,   91,    3,
   92,   97,   94,   65,    7,   72,   99,   28,   30,   72,
   72,   72,   72,   72,   72,   72,   34,   97,   54,   42,
    3,    6,   54,   54,   54,   54,   54,   72,   54,   72,
   20,   55,   29,   65,   37,   55,   55,   55,   55,   55,
   54,   55,   54,   40,   56,   34,   96,   45,   56,   56,
   56,   56,   56,   55,   56,   55,   82,    9,   10,   72,
  133,   72,   96,  127,   46,   11,   56,   95,   56,   32,
   32,   41,   93,   91,   54,   92,   97,   94,  134,   51,
   95,  127,   51,   51,  130,   93,   91,   55,   92,   97,
   94,   98,   12,   95,   27,   13,   53,  131,   93,   91,
   56,   92,   97,   94,   95,   47,  107,  108,  132,   93,
   91,   31,   92,   97,   94,  139,   31,   95,   31,   31,
   31,   96,   93,   91,   72,   92,   97,   94,   50,  143,
   95,   50,   95,  123,   96,   93,   91,   93,   92,   97,
   94,   97,   94,  149,   73,   72,   76,   96,  128,  152,
   72,   72,  136,   72,   72,   72,   23,   77,   96,   32,
   54,   55,   74,   38,   44,   78,  127,   66,   79,   72,
   66,   96,   57,  135,   80,  103,  144,  142,  145,  137,
  146,  153,  147,   15,   96,   66,   96,   66,   95,   63,
  148,   16,   32,   93,   91,   49,   92,   97,   94,   72,
   48,    8,   32,   32,   40,   40,   40,   64,   65,   65,
   65,   65,   65,   65,   65,   65,   40,   22,   52,   66,
   40,   43,   39,   40,   40,   40,   40,  129,   40,   40,
   40,   40,   40,   40,   72,   72,   72,   72,   72,   72,
   72,   72,   96,    0,    0,    0,    0,   54,   54,   54,
   54,   54,   54,   54,   54,    0,    0,    0,    0,    0,
   55,   55,   55,   55,   55,   55,   55,   55,   52,    0,
   52,   52,   52,   56,   56,   56,   56,   56,   56,   56,
   56,    0,    0,    9,   10,    0,   52,    0,   52,    0,
    0,   11,    0,    0,    0,    0,   83,   84,   85,   86,
   87,   88,   89,   90,    0,    0,    0,    0,    0,   83,
   84,   85,   86,   87,   88,   89,   90,    0,   12,   21,
   52,   13,   83,   84,   85,   86,   87,   88,   89,   90,
    0,    0,    0,   83,   84,   85,   86,   87,   88,   89,
   90,    0,    0,    0,    0,    0,   83,   84,   85,   86,
   87,   88,   89,   90,    0,    0,    0,    0,    0,   83,
   84,   85,   86,   87,   88,   89,   90,    0,    0,    0,
    0,    0,    0,    0,   72,   72,   72,   72,   72,   72,
   72,   72,   95,    0,    0,    0,    0,   93,   91,    0,
   92,   97,   94,   53,    0,   53,   53,   53,   66,   66,
    0,    0,    0,    0,    9,   10,    0,   58,    0,    0,
   58,   53,   11,   53,    0,    0,    0,   83,   84,   85,
   86,   87,   88,   89,   90,   58,   57,   58,    0,   57,
    0,    0,    0,    0,   63,    0,   96,   63,   61,   12,
    0,   61,   13,    0,   57,   53,   57,    0,    0,    0,
   64,    0,   63,   64,   63,    0,   61,    0,   61,   58,
    0,   59,    0,    0,   59,    0,    0,    0,   64,   60,
   64,    0,   60,    0,    0,    0,    0,    0,   57,   59,
    0,   59,    0,    0,    0,   62,   63,   60,   62,   60,
   61,    0,    0,   52,   52,   52,   52,   52,   52,   52,
   52,    0,   64,   62,    0,   62,    0,    0,    0,    0,
    0,    0,   75,   59,    0,    0,    0,    0,    0,    0,
    0,   60,    0,    0,    0,    0,    0,    0,  102,    0,
    0,  104,  105,  106,  102,  102,    0,   62,  109,  110,
  111,  112,  113,  114,  115,  116,  117,  118,  119,  120,
  121,  122,    0,  124,    9,   10,    0,    0,  102,   54,
   55,   56,   11,    0,    0,    0,    0,    0,    0,    0,
    0,   57,    0,    0,    0,    0,    0,    0,    0,   58,
    0,   59,  141,    0,    0,   60,   61,   62,   63,   12,
    0,    0,   13,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   83,   84,   85,   86,   87,   88,    0,   53,   53,
   53,   53,   53,   53,   53,   53,    0,    0,    0,    0,
    0,    0,   58,   58,   58,   58,   58,   58,   58,   58,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   57,   57,   57,   57,   57,   57,   57,   57,   63,
   63,   63,   63,   63,   63,   63,   63,    0,    0,   61,
   61,    0,    0,    0,    0,   64,   64,   64,   64,   64,
   64,   64,   64,    0,    0,    0,   59,   59,   59,   59,
   59,   59,   59,   59,   60,   60,   60,   60,   60,   60,
   60,   60,   54,   55,   56,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,    0,   62,   62,    0,    0,
    0,   81,   58,    0,   59,   54,   55,   56,   60,   61,
   62,   63,    0,    0,    0,    0,    0,   57,    0,    0,
    0,    0,    0,    0,    0,   58,    0,   59,    0,    0,
    0,   60,   61,   62,   63,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   46,  275,   64,   91,   42,   43,   91,   45,   46,   47,
   91,   91,   37,   44,  286,  287,    7,   42,   43,  292,
   45,   46,   47,   61,  276,   37,   72,   18,   59,   41,
   42,   43,   44,   45,   46,   47,  277,   46,   37,  280,
  292,  264,   41,   42,   43,   44,   45,   59,   47,   61,
  264,   37,  263,   91,   93,   41,   42,   43,   44,   45,
   59,   47,   61,  263,   37,  277,   91,   93,   41,   42,
   43,   44,   45,   59,   47,   61,  138,  257,  258,   91,
   41,   93,   91,   44,   40,  265,   59,   37,   61,  286,
  287,  264,   42,   43,   93,   45,   46,   47,   41,   41,
   37,   44,   44,   41,   41,   42,   43,   93,   45,   46,
   47,   61,  292,   37,  294,  295,   41,   41,   42,   43,
   93,   45,   46,   47,   37,   40,   79,   80,   41,   42,
   43,  278,   45,   46,   47,  126,  283,   37,   44,  286,
  287,   91,   42,   43,  279,   45,   46,   47,   41,  130,
   37,   44,   37,  264,   91,   42,   43,   42,   45,   46,
   47,   46,   47,  144,   40,   37,   40,   91,   41,  150,
   42,   43,   59,   45,   46,   47,  264,   40,   91,  264,
  262,  263,  264,  264,  264,   40,   44,   41,   40,   61,
   44,   91,  274,   93,   40,   40,  285,   41,   59,  278,
   59,  287,   59,   41,   91,   59,   91,   61,   37,  291,
  283,   41,  278,   42,   43,   41,   45,   46,   47,   91,
   41,    4,  283,  287,  262,  263,  264,   51,  266,  267,
  268,  269,  270,  271,  272,  273,  274,   14,   48,   93,
  278,   35,   31,  281,  282,  283,  284,  103,  286,  287,
  288,  289,  290,  291,  266,  267,  268,  269,  270,  271,
  272,  273,   91,   -1,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,
  266,  267,  268,  269,  270,  271,  272,  273,   41,   -1,
   43,   44,   45,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,   -1,  257,  258,   -1,   59,   -1,   61,   -1,
   -1,  265,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,  272,  273,   -1,  292,  293,
   93,  295,  266,  267,  268,  269,  270,  271,  272,  273,
   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,   -1,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,   -1,   -1,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,  272,  273,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,
  272,  273,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   41,   -1,   43,   44,   45,  272,  273,
   -1,   -1,   -1,   -1,  257,  258,   -1,   41,   -1,   -1,
   44,   59,  265,   61,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,   59,   41,   61,   -1,   44,
   -1,   -1,   -1,   -1,   41,   -1,   91,   44,   41,  292,
   -1,   44,  295,   -1,   59,   93,   61,   -1,   -1,   -1,
   41,   -1,   59,   44,   61,   -1,   59,   -1,   61,   93,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   59,   41,
   61,   -1,   44,   -1,   -1,   -1,   -1,   -1,   93,   59,
   -1,   61,   -1,   -1,   -1,   41,   93,   59,   44,   61,
   93,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,   93,   59,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   -1,   57,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   73,   -1,
   -1,   76,   77,   78,   79,   80,   -1,   93,   83,   84,
   85,   86,   87,   88,   89,   90,   91,   92,   93,   94,
   95,   96,   -1,   98,  257,  258,   -1,   -1,  103,  262,
  263,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  274,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  282,
   -1,  284,  127,   -1,   -1,  288,  289,  290,  291,  292,
   -1,   -1,  295,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  266,  267,  268,  269,  270,  271,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   -1,   -1,   -1,   -1,
   -1,   -1,  266,  267,  268,  269,  270,  271,  272,  273,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  266,  267,  268,  269,  270,  271,  272,  273,  266,
  267,  268,  269,  270,  271,  272,  273,   -1,   -1,  272,
  273,   -1,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,  273,   -1,   -1,   -1,  266,  267,  268,  269,
  270,  271,  272,  273,  266,  267,  268,  269,  270,  271,
  272,  273,  262,  263,  264,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,   -1,  272,  273,   -1,   -1,
   -1,  281,  282,   -1,  284,  262,  263,  264,  288,  289,
  290,  291,   -1,   -1,   -1,   -1,   -1,  274,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  282,   -1,  284,   -1,   -1,
   -1,  288,  289,  290,  291,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
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
"PROCEDURES","VOID","IFSINELSE",
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
"sentencia : IDENT '(' listaExpresionOpcional ')'",
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
"expresion : expresion GREATEREQUALS expresion",
"expresion : expresion LOWEREQUALS expresion",
"expresion : IDENT '(' listaExpresionOpcional ')'",
"expresion : NOT expresion",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' IDENT",
"expresion : LITERALREAL",
"expresion : LITERALINT",
"expresion : CHAR",
"expresion : IDENT",
};

//#line 228 "sintac.y"
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
//#line 521 "Parser.java"
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
//#line 673 "Parser.java"
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
