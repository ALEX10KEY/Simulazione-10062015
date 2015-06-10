package it.polito.ferrovie;

import it.polito.ferrovie.bean.Connessione;
import it.polito.ferrovie.bean.Fermata;
import it.polito.ferrovie.bean.Linea;

import java.util.List;

public class Model {
	
	private List<Linea> linee ;
	private List<Fermata> fermate ;
	private List<Connessione> connessioni ;
	
	public List<Linea> getLinee() {
		return linee;
	}
	
	public void setLinee(List<Linea> linee) {
		this.linee = linee;
	}
	
	public List<Fermata> getFermate() {
		return fermate;
	}
	
	public void setFermate(List<Fermata> fermate) {
		this.fermate = fermate;
	}
	
	public List<Connessione> getConnessioni() {
		return connessioni;
	}
	
	public void setConnessioni(List<Connessione> connessioni) {
		this.connessioni = connessioni;
	}
}
