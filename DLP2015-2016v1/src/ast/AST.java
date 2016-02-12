package ast;

import visitor.*;

public interface AST {
	public Object accept(Visitor visitor, Object param);
}

