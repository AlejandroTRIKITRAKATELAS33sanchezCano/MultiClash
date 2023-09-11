package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo peleador. Hijo que hereda de Pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class Peleador extends Pokemon implements Serializable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Peleador(String nombre, int vida) throws EnergiaNoValidaException {
		super(32, 39, nombre, vida);
		danioBase = 16;
		defensa = 9;
		precision = 81;
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
		case GOPLE_BAJO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case CON_LA_SILLA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case BOFETADA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
			break;
		case PATADA_RAPIDA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case GOLPE_AEREO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case KAMEKAMEHA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case SUPLEX:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 4;
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
		if (enemigo.getClass() == Animal.class || enemigo.getClass() == Perturbador.class
				|| enemigo.getClass() == Peleador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		} else if (enemigo.getClass() == Inteligencia.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
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
