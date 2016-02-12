package semantico;

import main.*;
import ast.*;

/**
 * Esta clase coordina las dos fases del Análisis Semántico:
 * 1- Fase de Identificación
 * 2- Fase de Inferencia
 * 
 * No es necesario modificar esta clase.
 * En su lugar hay que modificar "FaseIdentificación.java" y "FaseInferencia.java"
 */
public class Semantico {
	
	public Semantico(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}
	
	public void analiza(AST raiz) {
		FaseIdentificacion identificacion = new FaseIdentificacion(gestorErrores);
		raiz.accept(identificacion, null);

		if (gestorErrores.hayErrores())
			return;

		FaseInferencia inferencia = new FaseInferencia(gestorErrores);
		raiz.accept(inferencia, null);
	}

	private GestorErrores gestorErrores;
}
