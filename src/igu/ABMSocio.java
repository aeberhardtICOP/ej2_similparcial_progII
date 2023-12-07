package igu;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.ClubService;
import service.SocioService;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;

import model.Club;
import model.Domicilio;
import model.Socio;
import model.TipoSocio;
import model.Vinculo;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ABMSocio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SocioService socser;
	private ClubService clser;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtEdad;
	private JTextField txtAñoNac;
	private JTextField txtNroTitular;
	private JTextField txtDomCalle;
	private JTextField txtDomNro;
	private JTextField txtDomPiso;
	private JTextField txtDomDepto;
	private JTextField txtDomLocalidad;
	private JComboBox cmbxClub;
	private JFormattedTextField ftxtFechaAfilicacion;
	private JComboBox cmbxVinculo;
	private JComboBox cmbxTipoTit;
	private JList jListSocios;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMSocio frame = new ABMSocio(new SocioService(), new ClubService());
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
	public ABMSocio(SocioService socser, ClubService clser) {
		this.socser=socser;
		this.clser=clser;
		socser.sociosAMemoria();
		clser.clubesAMemoria();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlAlta = new JPanel();
		pnlAlta.setBorder(new TitledBorder(null, "Carga socio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAlta.setBounds(10, 11, 671, 295);
		contentPane.add(pnlAlta);
		pnlAlta.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 42, 86, 20);
		pnlAlta.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(123, 42, 86, 20);
		pnlAlta.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 30, 86, 14);
		pnlAlta.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(123, 30, 86, 14);
		pnlAlta.add(lblApellido);
		
		JComboBox cmbxTipo = new JComboBox();
		cmbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Titular", "Adherente"}));
		cmbxTipo.setBounds(239, 42, 86, 20);
		pnlAlta.add(cmbxTipo);
		cmbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cmbxTipo.getSelectedItem().equals("Titular")) {
					ftxtFechaAfilicacion.setEnabled(true);
					cmbxTipoTit.setEnabled(true);
					txtDomCalle.setEnabled(true);
					txtDomNro.setEnabled(true);
					txtDomDepto.setEnabled(true);
					txtDomPiso.setEnabled(true);
					txtDomLocalidad.setEnabled(true);
					
					txtNroTitular.setEnabled(false);
					txtNroTitular.setText("");
					cmbxVinculo.setEnabled(false);
				}else {
					ftxtFechaAfilicacion.setEnabled(false);
					ftxtFechaAfilicacion.setText("");
					cmbxTipoTit.setEnabled(false);
					txtDomCalle.setEnabled(false);
					txtDomCalle.setText("");
					txtDomNro.setEnabled(false);
					txtDomNro.setText("");
					txtDomDepto.setEnabled(false);
					txtDomDepto.setText("");
					txtDomPiso.setEnabled(false);
					txtDomPiso.setText("");
					txtDomLocalidad.setEnabled(false);
					txtDomLocalidad.setText("");
					
					txtNroTitular.setEnabled(true);
					cmbxVinculo.setEnabled(true);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(239, 30, 46, 14);
		pnlAlta.add(lblNewLabel);
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 84, 86, 20);
		pnlAlta.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(10, 71, 46, 14);
		pnlAlta.add(lblDni);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(123, 84, 86, 20);
		pnlAlta.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(123, 71, 46, 14);
		pnlAlta.add(lblEdad);
		
		txtAñoNac = new JTextField();
		txtAñoNac.setBounds(239, 84, 86, 20);
		pnlAlta.add(txtAñoNac);
		txtAñoNac.setColumns(10);
		
		JLabel lblAñoNac = new JLabel("Año nacimiento:");
		lblAñoNac.setBounds(239, 71, 112, 14);
		pnlAlta.add(lblAñoNac);
		
		cmbxClub = new JComboBox();
		cmbxClub.setBounds(10, 132, 86, 20);
		pnlAlta.add(cmbxClub);
		{
			
			for (String nombre : listaClubes()) {
				cmbxClub.addItem(nombre);
			}
		}
		
		JLabel lblClub = new JLabel("Club:");
		lblClub.setBounds(10, 118, 46, 14);
		pnlAlta.add(lblClub);
		
		ftxtFechaAfilicacion = new JFormattedTextField();
		ftxtFechaAfilicacion.setBounds(10, 179, 86, 20);
		pnlAlta.add(ftxtFechaAfilicacion);
		try {
		    MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
		    dateFormatter.setPlaceholderCharacter('_'); // Carácter de marcador de posición
		    ftxtFechaAfilicacion.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		cmbxTipoTit = new JComboBox();
		cmbxTipoTit.setModel(new DefaultComboBoxModel(TipoSocio.values()));
		cmbxTipoTit.setBounds(123, 179, 86, 20);
		pnlAlta.add(cmbxTipoTit);
		
		JLabel lblFechaAfil = new JLabel("Fecha Afiliacion:");
		lblFechaAfil.setBounds(10, 165, 103, 14);
		pnlAlta.add(lblFechaAfil);
		
		JLabel lblTipoTit = new JLabel("Tipo titular:");
		lblTipoTit.setBounds(123, 165, 126, 14);
		pnlAlta.add(lblTipoTit);
		
		txtNroTitular = new JTextField();
		txtNroTitular.setBounds(10, 230, 86, 20);
		pnlAlta.add(txtNroTitular);
		txtNroTitular.setColumns(10);
		
		cmbxVinculo = new JComboBox();
		cmbxVinculo.setModel(new DefaultComboBoxModel(Vinculo.values()));
		cmbxVinculo.setBounds(123, 230, 86, 20);
		pnlAlta.add(cmbxVinculo);
		
		JLabel lblNroTit = new JLabel("Nro. Titular:");
		lblNroTit.setBounds(10, 218, 86, 14);
		pnlAlta.add(lblNroTit);
		
		JLabel lblVinculo = new JLabel("Vinculo:");
		lblVinculo.setBounds(123, 218, 65, 14);
		pnlAlta.add(lblVinculo);
		
		JPanel pnlDomicilio = new JPanel();
		pnlDomicilio.setBorder(new TitledBorder(null, "Domicilio:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDomicilio.setBounds(361, 34, 220, 179);
		pnlAlta.add(pnlDomicilio);
		pnlDomicilio.setLayout(null);
		
		txtDomCalle = new JTextField();
		txtDomCalle.setBounds(10, 35, 86, 20);
		pnlDomicilio.add(txtDomCalle);
		txtDomCalle.setColumns(10);
		
		JLabel lblDomCalle = new JLabel("Calle:");
		lblDomCalle.setBounds(10, 22, 46, 14);
		pnlDomicilio.add(lblDomCalle);
		
		txtDomNro = new JTextField();
		txtDomNro.setBounds(123, 35, 86, 20);
		pnlDomicilio.add(txtDomNro);
		txtDomNro.setColumns(10);
		
		JLabel lblDomNro = new JLabel("Nro:");
		lblDomNro.setBounds(123, 22, 46, 14);
		pnlDomicilio.add(lblDomNro);
		
		txtDomPiso = new JTextField();
		txtDomPiso.setBounds(10, 80, 86, 20);
		pnlDomicilio.add(txtDomPiso);
		txtDomPiso.setColumns(10);
		
		txtDomDepto = new JTextField();
		txtDomDepto.setBounds(123, 80, 86, 20);
		pnlDomicilio.add(txtDomDepto);
		txtDomDepto.setColumns(10);
		
		txtDomLocalidad = new JTextField();
		txtDomLocalidad.setBounds(10, 136, 199, 20);
		pnlDomicilio.add(txtDomLocalidad);
		txtDomLocalidad.setColumns(10);
		
		JLabel lblDomPiso = new JLabel("Piso:");
		lblDomPiso.setBounds(10, 66, 46, 14);
		pnlDomicilio.add(lblDomPiso);
		
		JLabel lblDomDepto = new JLabel("Depto:");
		lblDomDepto.setBounds(123, 66, 46, 14);
		pnlDomicilio.add(lblDomDepto);
		
		JLabel lblDomLocalidad = new JLabel("Localidad:");
		lblDomLocalidad.setBounds(10, 124, 112, 14);
		pnlDomicilio.add(lblDomLocalidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(554, 261, 89, 23);
		pnlAlta.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String dni = txtDni.getText();
				String añoNacimiento = txtAñoNac.getText();
				String edad = txtEdad.getText();
				Club cl = buscarPorNom(cmbxClub.getSelectedItem().toString());
				String fechaAfiliacion = ftxtFechaAfilicacion.getText();
				String tipoTitular = cmbxTipoTit.getSelectedItem().toString();
				String nroTitular = txtNroTitular.getText();
				String vinculo = cmbxVinculo.getSelectedItem().toString();
				
				String calle=txtDomCalle.getText();
				String nro=txtDomNro.getText();
				String piso=txtDomPiso.getText();
				String depto=txtDomDepto.getText();
				String localidad=txtDomLocalidad.getText();
				Domicilio dom = new Domicilio();
				
				dom.setCalle(calle);
				try {
				dom.setNroCalle(Integer.parseInt(nro));
				dom.setNroPiso(Integer.parseInt(piso));
				}catch(NumberFormatException ex) {
					dom.setNroCalle(1);
					dom.setNroPiso(1);
				}
				dom.setDepto(depto);
				dom.setLocalidad(localidad);
				
				socser.crearSocio(nombre, apellido, dni, añoNacimiento, edad, cl, cmbxTipo.getSelectedItem().toString(), dom, fechaAfiliacion, tipoTitular, nroTitular, vinculo);
				System.out.println("--GRAFICA---");
				System.out.println("--TIPO---"+cmbxTipo.getSelectedItem().toString());
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(455, 261, 89, 23);
		pnlAlta.add(btnCancelar);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBorder(new TitledBorder(null, "Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDatos.setBounds(10, 306, 671, 118);
		contentPane.add(pnlDatos);
		pnlDatos.setLayout(null);
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBorder(new TitledBorder(null, "Lista:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLista.setBounds(691, 11, 220, 413);
		contentPane.add(pnlLista);
		pnlLista.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 200, 391);
		pnlLista.add(scrollPane);
		
		jListSocios = new JList();
		scrollPane.setViewportView(jListSocios);
		// Crear el modelo de la lista
        DefaultListModel<Socio> listModel = new DefaultListModel<>();
        for (Socio socio : socser.getSocios().values()) {
            listModel.addElement(socio);
        }

        // Configurar la JList con el modelo y el ListCellRenderer personalizado
        jListSocios.setModel(listModel);
        jListSocios.setCellRenderer((ListCellRenderer) new ListCellRenderer<Socio>() {
            public Component getListCellRendererComponent(JList<? extends Socio> list, Socio value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                label.setOpaque(true);

                if (value != null) {
                    label.setText(value.getNombre() + " " + value.getApellido());
                }

                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }

                return label;
            }
        });
}
		
	
	public ArrayList<String> listaClubes(){
		HashMap<Long, Club>clubes=clser.getClubes();
		ArrayList<String>nombresOrganismos=new ArrayList<String>();
		for(Map.Entry<Long, Club> entry : clubes.entrySet()) {
			nombresOrganismos.add(entry.getValue().getNombre());
		}
		return nombresOrganismos;
	}
	
	public Club buscarPorNom(String nombre) {
		
		 for (Map.Entry<Long, Club> entry : clser.getClubes().entrySet()) {
			 if(entry.getValue().getNombre().equals(nombre)) {
				 return entry.getValue();			 }
		 }
		 return null;
	}
}
