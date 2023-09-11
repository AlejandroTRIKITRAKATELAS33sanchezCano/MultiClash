package funcionalidad.excepciones;

/**
 * Se lanza cuando un pokemon existe
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class PokemonYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokemonYaExisteException(String msg) {
		super(msg);
	}

}
