package Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dominio.Usuario;

/**
 * Session Bean implementation class LogeoBean
 */
@Stateless
public class LogeoBean implements LogeoBeanRemote {

    /**
     * Default constructor. 
     */
    public LogeoBean() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
    private EntityManager em;

    public Usuario controlUsuario(String usuario){
    	List <Usuario> usu = new ArrayList<>();
    	Usuario user = null;
    	TypedQuery<Usuario> query=em.createNamedQuery("Usuario.findAll",Usuario.class);
    	usu = query.getResultList();
    	for(Usuario u : usu ) {
    		if (u.getUsuNombre().equals(usuario)) {
    			user = u;
    		}
    }
    	return user;
    	}
       
    
}
