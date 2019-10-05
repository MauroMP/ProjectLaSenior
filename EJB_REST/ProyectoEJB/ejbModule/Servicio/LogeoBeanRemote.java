package Servicio;

import javax.ejb.Remote;

import dominio.Usuario;

@Remote
public interface LogeoBeanRemote {
	public Usuario controlUsuario(String usuario);

}
