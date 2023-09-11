package funcionalidad.excepciones;

/**
 * Se lanza cuando un elemento ya existe
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class ElementoYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoYaExisteException(String msg) {
		super(msg);
	}

}
