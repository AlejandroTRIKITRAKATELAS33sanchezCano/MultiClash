package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo Perturbador. Hijo que hereda de Pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class Perturbador extends Pokemon implements Serializable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Perturbador(String nombre, int vida) throws EnergiaNoValidaException {
		super(16, 23, nombre, vida);
		danioBase = 16;
		defensa = 9;
		precision = 82;
	}

	/**
	 * Obtiene un valor para cada ataque
	 * 
	 * @throws EnergiaNoValidaException
	 */
	@Override
	public int getAtaque(Ataque ataque) throws EnergiaNoValidaException {
		int danioAtaque = 0;
		switch (ataque) {
		case EXE:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case SUSTO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case A_MIMIR:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		case PESADILLA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case MORDIDA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case RISA_MACABRA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90);
			break;
		case MALDICION:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		}
		setEnergia(getEnergia() - ataque.getEnergia());
		return danioAtaque;
	}

	/**
	 * Implementado de la interfaz Defensable. Obtiene una defensa según el tipo
	 * del atacante
	 * 
	 * @throws VidaNoValidaException
	 */
	@Override
	public String getDefensa(Pokemon enemigo, int ataqueEnemigo) {
		String cadena;
		int aleatorio = General.generarAleatorio(0, 100);

		if (aleatorio > precision) {
			return enemigo.getNombre() + " ha fallado el ataque";
		}

		if (enemigo.getClass() == Animal.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Perturbador.class || enemigo.getClass() == Inteligencia.class
				|| enemigo.getClass() == Peleador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}

}
