package pokemonGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import funcionalidad.ContraseniaNoValidaException;
import funcionalidad.CorreoNoValidoException;
import funcionalidad.EnvoltorioPokemons;
import funcionalidad.ManejoUsuarios;
import funcionalidad.Usuario;
import funcionalidad.excepciones.ElementoNoExisteException;
import funcionalidad.excepciones.UsuarioNoValidoException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;

/**
 * Login del juego.
 * 
 * @author Sanchez Cano Alejandro
 */
public class Principal extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Campo nombre del usuario.
	 */
	private JTextField fieldNombre;

	/**
	 * Etique usuario.
	 */
	private JLabel labelUsuario;

	/**
	 * Constructor para enviar los datos entre ventanas.
	 */
	private static Principal frame = new Principal();
	//private JLabel gifLabel;

	static EnvoltorioPokemons listaPokemon = new EnvoltorioPokemons();

	static Usuario jugador1;
	private JPasswordField passwordField;

	/**
	 * Inicia la aplicaciï¿½n
	 * 
	 * @param args
	 *            argumentos del main.
	 * @throws JavaLayerException
	 * @throws FileNotFoundException
	 */
	
	private musica cancion= new musica();
	public static void main(String[] args) throws FileNotFoundException {
		cargarPokemons();

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Principal() {
		cancion.musicadetension();
		setResizable(false);
		setTitle("Multi Crash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 25, 1163, 682);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			ManejoUsuarios.cargarLista();
		} catch (ClassNotFoundException | IOException e1) {
		}

		JButton botonCrearCuenta = new JButton("Crear Cuenta");
		botonCrearCuenta.setMnemonic('c');
		botonCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroUsuario registroUsuario = new RegistroUsuario();
				registroUsuario.setVisible(true);
			}
		});
		botonCrearCuenta.setBounds(287, 456, 186, 47);
		contentPane.add(botonCrearCuenta);

		JButton botonLogin = new JButton("Entrar");
		botonLogin.setMnemonic('e');

		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		
		botonLogin.setBounds(89, 456, 186, 47);
		contentPane.add(botonLogin);

		fieldNombre = new JTextField();
		fieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
		fieldNombre.setBounds(287, 373, 186, 30);
		contentPane.add(fieldNombre);
		fieldNombre.setColumns(10);

		labelUsuario = new JLabel("Nombre de usuario:");
		labelUsuario.setForeground(Color.ORANGE);
		labelUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelUsuario.setBounds(89, 372, 162, 30);
		contentPane.add(labelUsuario);

		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(Color.ORANGE);
		lblContrasea.setFont(new Font("Dialog", Font.BOLD, 15));
		lblContrasea.setBounds(89, 414, 162, 30);
		contentPane.add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrar();
				}
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					abrirAyuda();
				}
			}
		});
		passwordField.setBounds(287, 415, 186, 30);
		contentPane.add(passwordField);

		
/*
		JLabel tituloLabel = new JLabel("");
		tituloLabel.setIcon(new ImageIcon(Principal.class.getResource("/resources/img/titulo.png")));
		tituloLabel.setBounds(-81, 12, 1045, 465);
		contentPane.add(tituloLabel);

		gifLabel = new JLabel("");
		gifLabel.setIcon(new ImageIcon(Principal.class.getResource("/resources/img/oh-oh.gif")));
		gifLabel.setBounds(503, 325, 209, 146);
		contentPane.add(gifLabel);*/

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Principal.class.getResource("/resources/img/fondo.png")));
		label.setBounds(0, 0, 1161, 652);
		contentPane.add(label);

	}

	private void abrirAyuda() {
		try {
			Desktop.getDesktop().browse(new URI("http://rincondelprogramador.esy.es/PokemonBattles/index.html"));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "No se ha podido abrir la ayuda", "Mensaje de error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Pasa a la siguiente pantalla
	 */
	private void entrar() {
		try {
			jugador1 = ManejoUsuarios.getUsuario((fieldNombre.getText()), passwordField.getPassword().toString());

			char[] c = passwordField.getPassword();
			String s1 = new String(c);
			String s2 = jugador1.getContrasenia();

			if (!s1.equals(s2)) {
				JOptionPane.showMessageDialog(frame, "El usuario o la contraseña no son correctos", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "Logeado correctamente", "Informaci�n",
						JOptionPane.INFORMATION_MESSAGE);
				cancion.finmusicadetension();
				dispose();

				GestionPokemon eleccion = new GestionPokemon();
				eleccion.setVisible(true);
			}

		} catch (ElementoNoExisteException | UsuarioNoValidoException | ContraseniaNoValidaException | CorreoNoValidoException e1) {
			JOptionPane.showMessageDialog(frame, "El usuario o la contraseña no son correctos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			fieldNombre.setText("");
			passwordField.setText("");
		}

	}

	private static void cargarPokemons() {
		try {
			listaPokemon.leer();
		} catch (ClassNotFoundException | IOException e) {

		}

	}

	

}
