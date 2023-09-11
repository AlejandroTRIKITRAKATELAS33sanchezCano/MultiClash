package funcionalidad.excepciones;

/**
 * Se lanza cuando ya existe un pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class PokemonNoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokemonNoExisteException(String message) {
		super(message);
	}

}
