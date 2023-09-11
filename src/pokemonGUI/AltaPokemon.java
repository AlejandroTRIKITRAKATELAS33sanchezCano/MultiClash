package pokemonGUI;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import funcionalidad.General;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Animal;
import funcionalidad.tipos.Inteligencia;
import funcionalidad.tipos.Perturbador;
import funcionalidad.tipos.Guapo;
import funcionalidad.tipos.Pokemon;
import funcionalidad.tipos.Peleador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Da de alta a un pokemon
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class AltaPokemon extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked" })
	public AltaPokemon() {

		setTitle("Alta de pokemons");
		lblTipo.setText("Seleccione el tipo:");
		String[] tipos = { "Animal", "Perturbador", "Inteligencia", "Peleador", "Guapo" };

		siguiente.setVisible(false);
		anterior.setVisible(false);
		lblIcono.setVisible(false);
		comboBox_2.setVisible(false);

		comboBox.setModel(new DefaultComboBoxModel<>(tipos));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					altaPokemon();
				} catch (EnergiaNoValidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});

	}

	/**
	 * Añade un personaje al archivo "pokemons.obj"
	 * @throws EnergiaNoValidaException 
	 */
	private static void altaPokemon() throws EnergiaNoValidaException {
		try {
			Principal.listaPokemon.annadir(pedirPokemon());
			Principal.listaPokemon.escribir();
			JOptionPane.showMessageDialog(null, "Pokemon añadido correctamente!");
		} catch (PokemonYaExisteException | IOException e) {
			JOptionPane.showMessageDialog(null, "Ese pokemon ya existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Pide un pokemon para posteriormente añadirlo
	 * 
	 * @return pokemon pedido
	 * @throws EnergiaNoValidaException 
	 */
	private static Pokemon pedirPokemon() throws EnergiaNoValidaException {

		switch (comboBox.getSelectedItem().toString()) {
		case "Animal":
			return new Animal(textField.getText(), General.generarAleatorio(300, 500));
		case "Perturbador":
			return new Perturbador(textField.getText(), General.generarAleatorio(300, 500));
		case "Inteligencia":
			return new Guapo(textField.getText(), General.generarAleatorio(300, 500));
		case "Peleador":
			return new Inteligencia(textField.getText(), General.generarAleatorio(300, 500));
		default:
			return new Peleador(textField.getText(), General.generarAleatorio(300, 500));
		}

	}
}
