package funcionalidad.excepciones;

/**
 * Se lanza cuando la energía no es válida
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class EnergiaNoValidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnergiaNoValidaException(String msg) {
		super(msg);
	}

}
