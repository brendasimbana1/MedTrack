package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;
import model.Registros;
import view.View_Home;
import view.View_Login;
import view.View_Patient;
import view.View_Register;
import view.View_Table;
import view.View_Table_Register;

public class Logic_View_Table_Register implements ActionListener {
	private View_Table_Register vtr;
	private View_Register vr = new View_Register();
	private View_Home vh;
	private View_Patient vp;
	private View_Table vt;
	private View_Login vl;
	private Logic_View_Table lvt;
	
	public Logic_View_Table_Register(View_Table_Register vtr)
	{
		this.vtr = vtr;
		this.vtr.btnListado.addActionListener(this);
		this.vtr.btnNuevoPaciente.addActionListener(this);
		this.vtr.btnNuevoRegistro.addActionListener(this);
		this.vtr.btnPrincipal.addActionListener(this);
		this.vtr.btnSalir.addActionListener(this);
		this.vtr.btnLista.addActionListener(this);
		this.lvt = new Logic_View_Table();
		actualizarTablaConCedula(this.vtr.cedulaPaciente);
	}
	
	private Object[][] obtenerDatos(String cedula)
	{
		Paciente p = this.vr.lvr.busquedaPaciente(cedula);
		System.out.println(p.getNombres());
	    List<Object[]> datos = new ArrayList<>();
		for(Registros r:Logic_View_Home.registros)
		{
			if(r.getCi().equals(p.getCi()))
			{
				 Object[] fila = {r.getCi(),r.getFechas_atencion(),r.getDiagnostico(),r.getPeso(),r.getAltura(),r.getAltura(),r.getPresion_arterial(),r.getEvolucion(),r.getIndicaciones(),r.getResponsable()};
		          datos.add(fila);
			}
		}
	    return datos.toArray(new Object[0][]);
	}
	
	private void actualizarTablaConCedula(String cedula) {
	    Object[][] nuevosDatos = obtenerDatos(cedula);
	    this.vtr.model.setRowCount(0);
	    for (Object[] fila : nuevosDatos) {
	    	this.vtr.model.addRow(fila);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vtr.btnPrincipal)
		{
			vh = new View_Home();
			vtr.dispose();
			vh.setVisible(true);
		}
		else if(e.getSource() == vtr.btnListado)
		{
			vt = new View_Table();
			vtr.dispose();
			vt.setVisible(true);
		}
		else if(e.getSource() == vtr.btnNuevoPaciente)
		{
			vp = new View_Patient();
			vtr.dispose();
			vp.setVisible(true);
		}
		else if(e.getSource() == vtr.btnNuevoRegistro)
		{
			vr = new View_Register();
			vtr.dispose();
			vr.setVisible(true);
		}
		else if(e.getSource() == vtr.btnSalir)
		{
			vl = new View_Login();
			vl.setVisible(true);
			vtr.dispose();
		}else if (e.getSource() == vtr.btnLista) {
			lvt.setPacientes(this.vtr);
		}
	}
}
