package gimnasios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gimnasios.controller.ControladorAsistente;
import gimnasios.controller.ControladorGimnasio;
import gimnasios.controller.ControladorLocalidad;
import gimnasios.model.*;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField jtfId;
	private JTextField jtfDni;
	private JTextField jtfNombre;
	private JTextField jtfApellidos;
	private JTextField jtfCuotaMensual;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	JCheckBox checkActivo;
	private JTextField jtfFiltroApellidos;
	private JTextField jtfFechaNacimiento;
	JComboBox<Gimnasio> jcbGimnasio = null;
	JComboBox<Localidad> jcbLocalidad = null;
	JComboBox<Asistente> jcbAsistentesFiltrados = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0};
//		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
//		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0};
//		gbl_contentPane.columnWidths = new int[]{0};
//		gbl_contentPane.rowHeights = new int[]{0};
//		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Gestión de asistentes a gimnasio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gimnasio:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jcbGimnasio = new JComboBox<Gimnasio>();
		GridBagConstraints gbc_jcbGimnasio = new GridBagConstraints();
		gbc_jcbGimnasio.gridwidth = 2;
		gbc_jcbGimnasio.insets = new Insets(0, 0, 5, 0);
		gbc_jcbGimnasio.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbGimnasio.gridx = 1;
		gbc_jcbGimnasio.gridy = 1;
		contentPane.add(jcbGimnasio, gbc_jcbGimnasio);
		
		JLabel lblNewLabel_2 = new JLabel("Filtro apellidos asistente:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
				
		jtfFiltroApellidos = new JTextField();
		GridBagConstraints gbc_jtfFiltroApellidos = new GridBagConstraints();
		gbc_jtfFiltroApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltroApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltroApellidos.gridx = 1;
		gbc_jtfFiltroApellidos.gridy = 2;
		contentPane.add(jtfFiltroApellidos, gbc_jtfFiltroApellidos);
		jtfFiltroApellidos.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar asistentes");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarAsistentes();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFiltrar.gridx = 2;
		gbc_btnFiltrar.gridy = 2;
		contentPane.add(btnFiltrar, gbc_btnFiltrar);
		
		JLabel lblNewLabel_3 = new JLabel("Asistentes filtrados:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		jcbAsistentesFiltrados = new JComboBox<Asistente>();
		jcbAsistentesFiltrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatosAsistenteSeleccionado();
			}
		});
		GridBagConstraints gbc_jcbAsistentesFiltrados = new GridBagConstraints();
		gbc_jcbAsistentesFiltrados.gridwidth = 2;
		gbc_jcbAsistentesFiltrados.insets = new Insets(0, 0, 5, 0);
		gbc_jcbAsistentesFiltrados.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbAsistentesFiltrados.gridx = 1;
		gbc_jcbAsistentesFiltrados.gridy = 3;
		contentPane.add(jcbAsistentesFiltrados, gbc_jcbAsistentesFiltrados);
		
		JLabel lblNewLabel_4 = new JLabel("Datos del asistente");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 3;
		gbc_lblNewLabel_4.insets = new Insets(10, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Id:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		jtfId = new JTextField();
		jtfId.setEnabled(false);
		GridBagConstraints gbc_jtfId = new GridBagConstraints();
		gbc_jtfId.insets = new Insets(0, 0, 5, 0);
		gbc_jtfId.gridwidth = 2;
		gbc_jtfId.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfId.gridx = 1;
		gbc_jtfId.gridy = 5;
		contentPane.add(jtfId, gbc_jtfId);
		jtfId.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("DNI/NIE/Pasaporte:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		jtfDni = new JTextField();
		GridBagConstraints gbc_jtfDni = new GridBagConstraints();
		gbc_jtfDni.insets = new Insets(0, 0, 5, 0);
		gbc_jtfDni.gridwidth = 2;
		gbc_jtfDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDni.gridx = 1;
		gbc_jtfDni.gridy = 6;
		contentPane.add(jtfDni, gbc_jtfDni);
		jtfDni.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Localidad:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 7;
		contentPane.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		jcbLocalidad = new JComboBox<Localidad>();
		GridBagConstraints gbc_jcbLocalidad = new GridBagConstraints();
		gbc_jcbLocalidad.gridwidth = 2;
		gbc_jcbLocalidad.insets = new Insets(0, 0, 5, 0);
		gbc_jcbLocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbLocalidad.gridx = 1;
		gbc_jcbLocalidad.gridy = 7;
		contentPane.add(jcbLocalidad, gbc_jcbLocalidad);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.gridwidth = 2;
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 8;
		contentPane.add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Apellidos:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 9;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		jtfApellidos = new JTextField();
		GridBagConstraints gbc_jtfApellidos = new GridBagConstraints();
		gbc_jtfApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_jtfApellidos.gridwidth = 2;
		gbc_jtfApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfApellidos.gridx = 1;
		gbc_jtfApellidos.gridy = 9;
		contentPane.add(jtfApellidos, gbc_jtfApellidos);
		jtfApellidos.setColumns(10);
		
		checkActivo = new JCheckBox("");
		GridBagConstraints gbc_checkActivo = new GridBagConstraints();
		gbc_checkActivo.anchor = GridBagConstraints.EAST;
		gbc_checkActivo.insets = new Insets(0, 0, 5, 5);
		gbc_checkActivo.gridx = 0;
		gbc_checkActivo.gridy = 10;
		contentPane.add(checkActivo, gbc_checkActivo);
		
		JLabel lbl1 = new JLabel("Activo");
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.anchor = GridBagConstraints.WEST;
		gbc_lbl1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl1.gridx = 1;
		gbc_lbl1.gridy = 10;
		contentPane.add(lbl1, gbc_lbl1);
		
		JLabel lblNewLabel_11 = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 11;
		contentPane.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		jtfFechaNacimiento = new JTextField();
		GridBagConstraints gbc_jtfFechaNacimiento = new GridBagConstraints();
		gbc_jtfFechaNacimiento.gridwidth = 2;
		gbc_jtfFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFechaNacimiento.gridx = 1;
		gbc_jtfFechaNacimiento.gridy = 11;
		contentPane.add(jtfFechaNacimiento, gbc_jtfFechaNacimiento);
		jtfFechaNacimiento.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Cuota mensual (€):");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 12;
		contentPane.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		jtfCuotaMensual = new JTextField();
		GridBagConstraints gbc_jtfCuotaMensual = new GridBagConstraints();
		gbc_jtfCuotaMensual.insets = new Insets(0, 0, 5, 0);
		gbc_jtfCuotaMensual.gridwidth = 2;
		gbc_jtfCuotaMensual.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCuotaMensual.gridx = 1;
		gbc_jtfCuotaMensual.gridy = 12;
		contentPane.add(jtfCuotaMensual, gbc_jtfCuotaMensual);
		jtfCuotaMensual.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarAsistente ();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 0);
		gbc_btnGuardar.gridwidth = 3;
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 14;
		contentPane.add(btnGuardar, gbc_btnGuardar);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.weighty = 1.0;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 15;
		contentPane.add(panel, gbc_panel);

		
		// Cargo en pantalla todos los gimnasios y las localidades
		cargarTodosGimnasios();
		cargarTodasLocalidades();
	}
	
	
	
	/**
	 * 
	 */
	private void cargarTodosGimnasios() {
		List<Gimnasio> l = ControladorGimnasio.findAll();
		
		this.jcbGimnasio.removeAllItems();
		for (Gimnasio g : l) {
			this.jcbGimnasio.addItem(g);
		}
	}
	
	
	/**
	 * 
	 */
	private void cargarTodasLocalidades() {
		List<Localidad> l = ControladorLocalidad.findAll();
		
		this.jcbLocalidad.removeAllItems();
		for (Localidad loc : l) {
			this.jcbLocalidad.addItem(loc);
		}
	}
	
	
	/**
	 * 
	 */
	private void filtrarAsistentes() {
		int idGimnasioSeleccionado = ((Gimnasio) this.jcbGimnasio.getSelectedItem()).getId();
		List<Asistente> l = ControladorAsistente.filterByApellidosAndIdGimnasio(
				idGimnasioSeleccionado, this.jtfFiltroApellidos.getText());
		
		this.jcbAsistentesFiltrados.removeAllItems();
		for (Asistente a : l) {
			this.jcbAsistentesFiltrados.addItem(a);
		}
	}
	
	
	/**
	 * 
	 */
	private void cargarDatosAsistenteSeleccionado() {
		if (this.jcbAsistentesFiltrados.getSelectedItem() != null) {
			Asistente a = (Asistente) this.jcbAsistentesFiltrados.getSelectedItem();
			
			this.jtfId.setText("" + a.getId());
			this.jtfDni.setText(a.getDniNiePasaporte());
			
			for (int i = 0; i < this.jcbAsistentesFiltrados.getItemCount(); i++) {
				if (this.jcbAsistentesFiltrados.getItemAt(i).getId() == a.getId()) {
					this.jcbAsistentesFiltrados.setSelectedIndex(i);
				}
			}
			
			this.jtfNombre.setText(a.getNombre());
			this.jtfApellidos.setText(a.getApellidos());
			this.checkActivo.setSelected(a.isActivo());
			this.jtfCuotaMensual.setText("" + a.getCuotaMensual());
			
			String strFechaNac = (a.getFechaNacimiento() != null)? sdf.format(a.getFechaNacimiento()) : "";
			this.jtfFechaNacimiento.setText(strFechaNac);
		}
	}
	
	
	/**
	 * 
	 */
	private void guardarAsistente() {
		try {
			if (!isDniValido()) {
				JOptionPane.showMessageDialog(null, "El dni/nie/pasaporte no es válido. Debe contente 8 dígitos contiguos");
				return;
			}
			
			if (!isFechaNacimientoValida()) {
				JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida, debe ser anterior a hoy (dd/MM/yyyy)");
				return;
			}
			
			if (!isCuotaMensualValida()) {
				JOptionPane.showMessageDialog(null, "La cuota mensual debe ser numérica");
				return;
			}
			
			
			// Guardado del asistente
			Asistente a  = new Asistente();
			
			a.setId(Integer.parseInt(this.jtfId.getText()));
			a.setDniNiePasaporte(this.jtfDni.getText());
			a.setIdLocalidad( ((Localidad) this.jcbLocalidad.getSelectedItem()).getId() );
			a.setNombre(this.jtfNombre.getText());
			a.setApellidos(this.jtfApellidos.getText());
			a.setActivo(this.checkActivo.isSelected());		
			
			Date nuevaFecha = (!this.jtfFechaNacimiento.getText().trim().equals(""))?
					sdf.parse(this.jtfFechaNacimiento.getText()) : null;
			a.setFechaNacimiento(nuevaFecha);
			a.setCuotaMensual(Float.parseFloat(this.jtfCuotaMensual.getText()));
			
			// Envío el producto a guardado
			if (ControladorAsistente.update(a) == 1) {
				JOptionPane.showMessageDialog(null, "Guardado correctamente");
			}
			else {
				JOptionPane.showMessageDialog(null, "NO se ha podido guardar");
			}
	
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}		

		
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isDniValido() {
		return this.jtfDni.getText().matches(".*[0-9]{8}.*");
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isFechaNacimientoValida() {
		String strFechaNac = this.jtfFechaNacimiento.getText();
		
		if (strFechaNac.trim().equals("")) {
			return true;
		}
		
		Date fechaNac = null;
		try {
			fechaNac = sdf.parse(strFechaNac);
		}
		catch (Exception ex) {
			return false;
		}
		
		if ((new Date()).before(fechaNac)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isCuotaMensualValida () {
		try {
			Float.parseFloat(this.jtfCuotaMensual.getText());
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}


}















