package generacionDeCodigo;

import ast.*;
import visitor.*;

/** 
 * Clase encargada de asignar direcciones a las variables 
 *
 * @author Raul Izquierdo
 */
public class GestionDeMemoria extends DefaultVisitor {

	// class Programa { List<DefVariable> definiciones; List<Sentencia> sentencias; }
	public Object visit(Programa node, Object param) {

		for (DefVariable child : node.getDefiniciones())
			child.accept(this, param);

		return null;
	}

	// class DefVariable { Tipo tipo; String nombre; }
	public Object visit(DefVariable node, Object param) {
		node.setDireccion(sumaTamañoVariables);
		sumaTamañoVariables += node.getTipo().getSize();
		return null;
	}

	private int sumaTamañoVariables = 0;

}
