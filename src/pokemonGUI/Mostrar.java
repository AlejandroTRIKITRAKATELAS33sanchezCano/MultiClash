package pokemonGUI;

import funcionalidad.tipos.Pokemon;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

/**
 * Muestra todos los pokemons
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class Mostrar extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListIterator<Pokemon> iterador;
	private Pokemon pokemonCopia;
	private Pokemon pokemonActual;

	/**
	 * Create the dialog.
	 */
	public Mostrar() {
		iterador = Principal.listaPokemon.getLista().listIterator();
		siguiente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (!anterior.isEnabled()) {
						return;
					}
					mostrarAnterior();

					anterior.grabFocus();

				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					mostrarSiguiente();
					anterior.grabFocus();

				}
			}
		});

		anterior.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					mostrarAnterior();

				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (!siguiente.isEnabled()) {
						return;
					}
					mostrarSiguiente();
					siguiente.grabFocus();

				}
			}
		});

		setResizable(false);
		setTitle("Listado de pokemons");

		comboBox_2.setVisible(false);

		if (Principal.listaPokemon.size() == 0) {
			JOptionPane.showMessageDialog(null, "No existen pokemons", "ERROR", JOptionPane.ERROR_MESSAGE);
			dispose();
		} else {
			siguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarSiguiente();
				}
			});

			anterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarAnterior();
				}
			});

			lblTipo.setText("Tipo:");
			lblNombre.setText("Nombre:");
			comboBox.setEnabled(false);
			textField.setEnabled(false);
			okButton.setVisible(false);
			cancelButton.setText("Volver");
			actualizar();
		}
	}

	void actualizar() {
		if (Principal.listaPokemon.size() == 1) {
			siguiente.setEnabled(false);
			anterior.setEnabled(false);
		} else {
			siguiente.setEnabled(true);
			anterior.setEnabled(false);
		}

		pokemonCopia = iterador.next();
		pokemonActual = pokemonCopia;
		mostrarPokemon(pokemonCopia);

	}

	/**
	 * Muestra el siguiente
	 */
	private void mostrarSiguiente() {

		if (iterador.hasNext()) {
			pokemonCopia = iterador.next();
			if (pokemonActual == pokemonCopia) {
				pokemonCopia = iterador.next();
			}
			mostrarPokemon(pokemonCopia);
			comprobarBotones();
			pokemonActual = pokemonCopia;
			if (!iterador.hasNext()) {
				iterador.previous();
			}

		}

	}

	/**
	 * Muestra el anterior
	 */
	private void mostrarAnterior() {

		if (iterador.hasPrevious()) {
			pokemonCopia = iterador.previous();
			if (pokemonActual == pokemonCopia) {
				pokemonCopia = iterador.previous();
			}
			mostrarPokemon(pokemonCopia);
			comprobarBotones();
			pokemonActual = pokemonCopia;
			if (!iterador.hasPrevious()) {
				iterador.next();
			}
		}

	}

	/**
	 * Muestra el pokemon pasado por parametros
	 * 
	 * @param pokemon
	 */
	@SuppressWarnings("unchecked")
	private void mostrarPokemon(Pokemon pokemon) {
		textField.setText(pokemon.getNombre());
		try {
			lblIcono.setIcon(new ImageIcon(
					VentanaPadre.class.getResource("/resources/img/characters/" + textField.getText() + ".png")));
		} catch (Exception e) {
		}
		comboBox.addItem(pokemon.getClass().getSimpleName());
		comboBox.setSelectedItem(pokemon.getClass().getSimpleName());
	}

	/**
	 * Si no hay siguiente pokemon, se bloquea el boton de siguiente y viceversa
	 */
	private void comprobarBotones() {

		if (!iterador.hasNext()) {
			siguiente.setEnabled(false);
			anterior.grabFocus();
		} else {
			siguiente.setEnabled(true);
		}
		if (!iterador.hasPrevious()) {
			anterior.setEnabled(false);
		} else {
			anterior.setEnabled(true);
		}

	}

}
