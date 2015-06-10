package it.polito.ferrovie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.ferrovie.bean.Connessione;
import it.polito.ferrovie.bean.Fermata;
import it.polito.ferrovie.bean.Linea;
import it.polito.ferrovie.db.DBConnect;

public class FerrovieDAO {

	/**
	 * Fills the info about the Fermata with the specified id
	 * 
	 * @param f
	 * @return {@code true} iff the operation was successful
	 */
	public boolean getFermata(Fermata f) {
		final String sql = "SELECT nome, coordx, coordy FROM fermata WHERE id_fermata = ?";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, f.getIdFermata()) ;
			
			ResultSet rs = st.executeQuery() ;
			
			boolean ok = false ;
			
			if(rs.next()) {
				f.setNome( rs.getString("nome") ) ;
				f.setCoords(new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")) ) ;
				ok = true ;
			}
			
			st.close() ;
			conn.close();
			
			return ok ;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
	}
	
	/**
	 * Return a {@link Fermata} object for the given {@code idFermata}
	 *
	 * @param idFermata
	 * @return the new object, or {@code null} if not found
	 */
	public Fermata getFermata(int idFermata) {
		Fermata f = new Fermata(idFermata, null, null) ;
		if( getFermata(f) )
			return f ;
		else
			return null ;
	}
	
	/**
	 * Get a list of all {@link Fermata} defined in the database.
	 * The list is provided in no alphabetical order of the <b>name</b>.
	 * 
	 * @return the new list
	 */
	public List<Fermata> getAllFermata() {
		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC" ;
		
		List<Fermata> l = new ArrayList<Fermata>() ;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			while( rs.next() ) {
				Fermata f = new Fermata(
						rs.getInt("id_Fermata"),
						rs.getString("nome"),
						new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")) ) ;
				l.add(f) ;
			}
						
			st.close() ;
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		
		return l ;
	}
	
	/**
	 * Get a list of all {@link Linea} defined in the database.
	 * The list is provided in no alphabetical order of the <b>name</b>.
	 * 
	 * @return the new list
	 */
	public List<Linea> getAllLinea() {
		final String sql = "SELECT id_linea, nome, velocita, intervallo FROM linea ORDER BY nome ASC" ;
		
		List<Linea> l = new ArrayList<Linea>() ;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			while( rs.next() ) {
				Linea f = new Linea(
						rs.getInt("id_linea"),
						rs.getString("nome"),
						rs.getDouble("velocita"),
						rs.getDouble("intervallo")) ;
				l.add(f) ;
			}
						
			st.close() ;
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		
		return l ;
	}
	
	public List<Connessione> getAllConnessione(List <Linea> linee, List <Fermata> fermate) {
		
		final String sql = "select id_connessione, id_linea, id_stazP, id_stazA from connessione" ;
		
		List<Connessione> l = new ArrayList<Connessione>() ;

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			while( rs.next() ) {
				
				int idLinea = rs.getInt("id_linea") ;
				int idStazP = rs.getInt("id_stazP") ;
				int idStazA = rs.getInt("id_stazA") ;
				
				Linea myLinea = linee.get(linee.indexOf(new Linea(idLinea, null, 0, 0))) ;
				Fermata myStazP = fermate.get(fermate.indexOf(new Fermata(idStazP, null, null))) ;
				Fermata myStazA = fermate.get(fermate.indexOf(new Fermata(idStazA, null, null))) ;
				
				
				Connessione cnx = new Connessione(
						rs.getInt("id_connessione"),
						myLinea,
						myStazP,
						myStazA ) ;
				
				l.add(cnx) ;
				
			}
						
			st.close() ;
			conn.close();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e) ;
		}
		return l ;
	}

	
}
