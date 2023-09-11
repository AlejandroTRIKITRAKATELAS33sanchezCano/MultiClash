package pokemonGUI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonNoExisteException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Modificacion de pokemons
 * 
 * @author Miguel Ángel Gavilán Merino
 *
 */
public class Modificacion extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 * 
	 * @param pokemon
	 */
	@SuppressWarnings("unchecked")
	public Modificacion(Pokemon pokemon) {
		siguiente.setVisible(false);
		anterior.setVisible(false);
		comboBox_2.setVisible(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPokemon(pokemon);

				dispose();
			}
		});
		lblTipo.setText("Nuevo tipo:");
		lblNombre.setText("Nuevo nombre:");
		String[] tipos = { "Animal", "Perturbador", "Inteligencia", "Peleador", "Guapo" };
		comboBox.setModel(new DefaultComboBoxModel<>(tipos));
		textField.setText(pokemon.getNombre());
	}

	/**
	 * Modifica el pokemon
	 * 
	 * @param pokemon
	 */
	private void modificarPokemon(Pokemon pokemon) {
		try {
			switch (comboBox.getSelectedItem().toString()) {
			case "Animal":
				Principal.listaPokemon.annadir(new Animal(textField.getText(), pokemon.getVida()));
				break;

			case "Perturbador":
				Principal.listaPokemon.annadir(new Perturbador(textField.getText(), pokemon.getVida()));
				break;

			case "Inteligencia":
				Principal.listaPokemon.annadir(new Inteligencia(textField.getText(), pokemon.getVida()));
				break;

			case "Peleador":
				Principal.listaPokemon.annadir(new Guapo(textField.getText(), pokemon.getVida()));
				break;

			default:
				Principal.listaPokemon.annadir(new Peleador(textField.getText(), pokemon.getVida()));
				break;
			}

			Principal.listaPokemon.eliminar(pokemon);
			Principal.listaPokemon.escribir();

			JOptionPane.showMessageDialog(null, "Modificado correctamente");
		} catch (PokemonYaExisteException | PokemonNoExisteException | IOException | EnergiaNoValidaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}


	}
}
