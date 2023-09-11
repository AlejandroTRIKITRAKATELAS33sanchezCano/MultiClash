package funcionalidad.interfaces;

import funcionalidad.tipos.Pokemon;

/**
 * Interfaz que calcula la defensa
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public interface Defensable {
	String getDefensa(Pokemon enemigo, int danoAtaqueEnemigo);
}
