package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectorReporte extends JFrame{

	private static SelectorReporte INSTANCE;
	private JPanel contentPane;
	private JButton BtnReportePreferenciaContacto;
	private JButton BtnReporteUbicacion;
	
	public static SelectorReporte getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new SelectorReporte(); 	
			return new SelectorReporte();
		}
		else
			return new SelectorReporte();
	}
	
	private SelectorReporte() {
		
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 526, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		BtnReportePreferenciaContacto = new JButton("Reporte Preferencia contacto");
		BtnReportePreferenciaContacto.setBounds(130, 70, 220, 35);
		panel.add(BtnReportePreferenciaContacto);
		
		BtnReporteUbicacion = new JButton("Reporte Ubicacion");
		BtnReporteUbicacion.setBounds(130, 130, 220, 35);
		panel.add(BtnReporteUbicacion);
		
		this.setVisible(false);
	}

	public JButton getBtnReportePreferenciaContacto() {
		return BtnReportePreferenciaContacto;
	}

	public void setBtnReportePreferenciaContacto(JButton BtnReportePreferenciaContacto) {
		this.BtnReportePreferenciaContacto = BtnReportePreferenciaContacto;
	}

	public JButton getBtnReporteUbicacion() {
		return BtnReporteUbicacion;
	}

	public void setBtnReporteUbicacion(JButton BtnReporteUbicacion) {
		this.BtnReporteUbicacion = BtnReporteUbicacion;
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public void ocultarVentana()
	{
		this.setVisible(false);
	}
	
}
