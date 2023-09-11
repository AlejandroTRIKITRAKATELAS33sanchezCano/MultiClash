package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para personajes tipo Animal. Hijo que hereda de Pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class Animal extends Pokemon implements Serializable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Animal(String nombre, int vida) throws EnergiaNoValidaException {
		super(0, 7, nombre, vida);
		danioBase = 15;
		defensa = 10;
		precision = 80;
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
		case EMBESTIDA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case RABIA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 13;
			break;
		case ARAÑAZO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		case GOLPE_COLA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case ATAQUE_MAXIMO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
			break;
		case COLA_HIERRO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case GRITO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		default:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 9;
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
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre() + "\n"
					+ getNombre() + ": " + getVida() + "PS";
		} else if (enemigo.getClass() == Perturbador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Guapo.class || enemigo.getClass() == Inteligencia.class) {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}

}
