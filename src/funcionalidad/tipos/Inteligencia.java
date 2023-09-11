package funcionalidad.tipos;

import java.io.Serializable;

import funcionalidad.General;
import funcionalidad.enumeraciones.Ataque;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.interfaces.Defensable;

/**
 * Clase para pokemons de tipo Inteligencia. Hijo que hereda de Pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class Inteligencia extends Pokemon implements Serializable, Defensable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Inteligencia(String nombre, int vida) throws EnergiaNoValidaException {
		super(8, 15, nombre, vida);
		danioBase = 14;
		defensa = 11;
		precision = 83;
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
		case ANALISIS:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 7;
			break;
		case ALGEBRA_LINEAL:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 5;
			break;
		case DIVISION_ENTRE_0:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 6;
			break;
		case DERIVAR:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 11;
			break;
		case INTEGRAR:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 10;
			break;
		case VOLERTE_ATEO:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 8;
			break;
		case BOFETADA_FINA:
			danioAtaque = danioBase + General.generarAleatorio(30, 90) + 12;
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
	 * @return Cadena
	 * @throws VidaNoValidaException
	 */
	@Override
	public String getDefensa(Pokemon enemigo, int ataqueEnemigo) {
		String cadena;
		int aleatorio = General.generarAleatorio(0, 100);

		if (aleatorio > precision) {
			return enemigo.getNombre() + " ha fallado el ataque";
		}

		if (enemigo.getClass() == Animal.class || enemigo.getClass() == Peleador.class) {
			setVida(getVida() - (ataqueEnemigo - defensa * 2));
			cadena = "ES POCO EFECTIVO\nSe ha reducido en " + (ataqueEnemigo - defensa * 2) + " PS la salud de "
					+ getNombre();
		} else if (enemigo.getClass() == Perturbador.class || enemigo.getClass() == Inteligencia.class) {
			setVida(getVida() - (ataqueEnemigo - defensa));
			cadena = "Se ha reducido en " + (ataqueEnemigo - defensa) + " PS la salud de " + getNombre();
		} else {
			setVida(getVida() - (ataqueEnemigo + defensa * 2));
			cadena = "ATAQUE CRITICO\nSe ha reducido en " + (ataqueEnemigo + defensa * 2) + " PS la salud de "
					+ getNombre();
		}

		if (getVida() <= 0) {
			cadena += "\n\t" + getNombre() + " se ha debilitado!";
		}

		return cadena;

	}
}
