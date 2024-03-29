package pokemonGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.General;
import funcionalidad.excepciones.EnergiaNoValidaException;
import funcionalidad.excepciones.PokemonYaExisteException;
import funcionalidad.tipos.Animal;
import funcionalidad.tipos.Inteligencia;
import funcionalidad.tipos.Perturbador;
import funcionalidad.tipos.Guapo;
import funcionalidad.tipos.Peleador;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Gestiona las altas, bajas y modificaciones de los pokemons
 * 
 * @author Sanchez Cano Alejandro
 *
 */
public class GestionPokemon extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private musica cancion=new musica();
	/**
	 * Create the dialog.
	 */
	public GestionPokemon() {
		cancion.musicadecasa();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cancion.finmusicadecasa();
				volver();
			}

		});
		setTitle("Gestión de pokemons");
		setResizable(false);
		setBounds(100, 100, 654, 417);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 664, 21);
		contentPanel.add(menuBar);

		JMenu mnGestin = new JMenu("Gestión");
		mnGestin.setMnemonic('g');
		menuBar.add(mnGestin);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaPokemon alta = new AltaPokemon();
				alta.setVisible(true);
			}
		});
		mnGestin.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaPokemon baja = new BajaPokemon();
				baja.setVisible(true);
			}
		});
		mnGestin.add(mntmBaja);

		JMenuItem mntmModificacin = new JMenuItem("Modificación");
		mntmModificacin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmModificacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarPokemon modificar = new ModificarPokemon();
				modificar.setVisible(true);
			}
		});
		mnGestin.add(mntmModificacin);

		JSeparator separator = new JSeparator();
		mnGestin.add(separator);

		JMenuItem mntmMostrarTodosLos = new JMenuItem("Mostrar todos los pokemons");
		mntmMostrarTodosLos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmMostrarTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar mostrar = new Mostrar();
				mostrar.setVisible(true);
			}
		});
		mnGestin.add(mntmMostrarTodosLos);

		JSeparator separator_1 = new JSeparator();
		mnGestin.add(separator_1);

		JMenuItem mntmGenerarPokemons = new JMenuItem("Generar pokemons");
		mnGestin.add(mntmGenerarPokemons);
		mntmGenerarPokemons.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGenerarPokemons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new File("pokemons.obj").exists()) {
					JOptionPane.showMessageDialog(contentPanel, "Ya existen pokemons cargados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					generarPokemons();
					JOptionPane.showMessageDialog(contentPanel, "Pokemons generados correctamente");
				}
			}

		});

		JMenu mnBuscarPor = new JMenu("Buscar por...");
		mnBuscarPor.setMnemonic('b');
		menuBar.add(mnBuscarPor);

		JMenuItem mntmNombre = new JMenuItem("Nombre");
		mntmNombre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarNombre buscarNombre = new BuscarNombre();
				buscarNombre.setVisible(true);
			}
		});
		mnBuscarPor.add(mntmNombre);

		JMenuItem mntmTipo = new JMenuItem("Tipo");
		mntmTipo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mntmTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirTipo elegirTipo = new ElegirTipo();
				elegirTipo.setVisible(true);
			}
		});
		mnBuscarPor.add(mntmTipo);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GestionPokemon.class.getResource("")));
		label.setBounds(0, 0, 652, 352);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Jugar");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) {
						if (Principal.listaPokemon.isEmpty()) {
							JOptionPane.showMessageDialog(contentPanel, "¡No hay pokemons precargados!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							cancion.finmusicadecasa();
							dispose();
							EleccionPokemon eleccion = new EleccionPokemon();
							eleccion.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancion.finmusicadecasa();
						volver();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Vuelve a la pantalla anterior
	 */
	private void volver() {
		dispose();
		Principal principal = new Principal();
		principal.setVisible(true);
	}

	/**
	 * Genera pokemons predeterminados y los añade al archivo "pokemons.obj"
	 */
	private void generarPokemons() {

		try {
			
			//Animal
			
			Principal.listaPokemon.annadir(new Animal("MiniPekka", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Animal("Wolverine", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Animal("Pikachu", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Animal("Frankenstein", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Animal("Yoshi", General.generarAleatorio(300, 500)));

			// Tipo Inteligencia
			Principal.listaPokemon.annadir(new Inteligencia("Spiderman", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Inteligencia("drsimi", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Inteligencia("HarryPotter", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Inteligencia("MichaelJackson", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Inteligencia("SheldonCooper", General.generarAleatorio(300, 500)));

			// Tipo Perturbador
			Principal.listaPokemon.annadir(new Perturbador("MissingNo", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Perturbador("Pennywise", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Perturbador("RonaldMcDonald", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Perturbador("CoronelKFC", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Perturbador("CajeroOxxo", General.generarAleatorio(300, 500)));

			// Tipo Guapo
			Principal.listaPokemon.annadir(new Guapo("homero", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Guapo("PeterGrffin", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Guapo("Chayanne", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Guapo("Thor", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Guapo("Sans", General.generarAleatorio(300, 500)));

			// Tipo peleador
			Principal.listaPokemon.annadir(new Peleador("goku", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Peleador("shrerk", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Peleador("sonic", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Peleador("Terminator", General.generarAleatorio(300, 500)));
			Principal.listaPokemon.annadir(new Peleador("MarioBros", General.generarAleatorio(300, 500)));

			Principal.listaPokemon.escribir();
		} catch (PokemonYaExisteException | IOException | EnergiaNoValidaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
}
