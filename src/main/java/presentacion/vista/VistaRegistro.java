package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaRegistro extends JFrame{
	
	private JFrame frame;
	private JTextField user;
	private JPasswordField password;
	private JButton btnIniciar;
	

	public VistaRegistro () {
		super();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1064, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(45, 47, 66, 14);
        frame.getContentPane().add(lblUser);
        panel.add(lblUser);

        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setBounds(45, 111, 82, 14);
        frame.getContentPane().add(lblPassword);
        panel.add(lblPassword);
        
        user = new JTextField();
        user.setBounds(144, 47, 229, 27);
		panel.add(user);
		user.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(144, 111, 229, 27);
		panel.add(password);
		password.setColumns(10);
		
		btnIniciar= new JButton("INICIAR");
        btnIniciar.setForeground(Color.BLACK);
        btnIniciar.setBackground(Color.WHITE);
        btnIniciar.setBounds(167, 203, 111, 27);
        frame.getContentPane().add(btnIniciar);
        panel.add(btnIniciar);
        
        this.frame.setVisible(true);
	}
	
	public JButton getBtnIniciar() {
		return btnIniciar;
	}
	
	public String getUser() {
		return this.user.getText();
	}
	
	public String getPassword() {
		return new String(this.password.getPassword());
	}
	
	public void ocultarVista() {
		this.frame.setVisible(false);
	}
	
	public void showError() {
		JOptionPane.showMessageDialog(this.frame, "Usuario o contraseña incorrecto");
	}
	
	public void limpiarCampos() {
		this.user.setText("");
		this.password.setText("");
	}

}

