package ar.edu.unlam.pb2.eva03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ar.edu.unlam.pb2.eva03.enumeradores.TipoDeEvento;
import ar.edu.unlam.pb2.eva03.interfaces.ICorredor;
import ar.edu.unlam.pb2.eva03.interfaces.INadador;


public class Club {
	private String nombre;
	private Set<Deportista> socios;
	private Map<String, Evento> competencias;
	
	public Club(String nombre) {
		this.setNombre(nombre);
		socios= new HashSet<>();
		competencias= new HashMap<String, Evento>();
		
	}

	public void agregarDeportista(Deportista nuevoDeportista) {
		socios.add(nuevoDeportista);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadSocios() {
		// TODO Auto-generated method stub
		return this.socios.size();
	}

	public void crearEvento(TipoDeEvento tipoEvento, String nombre) {
		Evento nuevoEvento= new Evento(tipoEvento, nombre);
		competencias.put(nombre, nuevoEvento);
	}

	private Deportista getDeportista(Deportista nombreDeportista) {
		// TODO Auto-generated method stub
		for (Deportista actual : socios) {
			if(actual.equals(nombreDeportista))
				return actual;
		}
		return null;
	}

	private Evento getEvento(String nombreEvento) {
		return competencias.get(nombreEvento);
	}

	public Boolean inscribirEnEvento(String nombreEvento, Deportista nombre) throws NoEstaPreparado {
		Evento nuevoEvento = getEvento(nombreEvento);
		Deportista nuevoDeportista = getDeportista(nombre);

		if (!(nuevoDeportista instanceof INadador)
				&& nuevoEvento.getTipoEvento() == TipoDeEvento.CARRERA_NATACION_EN_AGUAS_ABIERTAS) {
			throw new NoEstaPreparado();
		}

		if (nuevoDeportista instanceof ICorredor && nuevoEvento.getTipoEvento() == TipoDeEvento.TRIATLON_IRONMAN) {
			throw new NoEstaPreparado();
		}

		if (nuevoDeportista != null && nuevoEvento != null) {
			return nuevoEvento.agregarDeportistaAlEvento(nuevoDeportista);
		}
		return false;
	}
	
	
	
}
