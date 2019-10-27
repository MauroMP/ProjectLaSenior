package com.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.dominio.Almacenamiento;
import com.dominio.Familia;
import com.dominio.Movimiento;
import com.dominio.Producto;
import com.google.gson.Gson;


@Named("prod")
@SessionScoped

public class productos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Nombre;
	private String prodstkMin;
	private String prodstkTotal;
	private String prodSegmentacion;
	private String prodEstiba;
	private String prodPeso;
	private String prodVol;
	private String prodLote;
	private String prodPrecio;
	private String[] almas;
	private String fam;
	private String sp;
	private Date fecVen;
	private Date fecela;
	private String[] famnom;
	private String almacenes;
	private String nomProd;
	private String nomfam;
	private String pro;
	private Producto selectedRow;
	private String mensajeConect;
	private String producto;
	private String nomfamilia;
	

	
	public String getNomfamilia() {
		return nomfamilia;
	}
	public void setNomfamilia(String nomfamilia) {
		this.nomfamilia = nomfamilia;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getMensajeConect() {
		return mensajeConect;
	}
	public void setMensajeConect(String mensajeConect) {
		this.mensajeConect = mensajeConect;
	}
	public Producto getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(Producto selectedRow) {
		this.selectedRow = selectedRow;
	}
	
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public String getNomfam() {
		return nomfam;
	}
	public void setNomfam(String nomfam) {
		this.nomfam = nomfam;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public String getAlmacenes() {
		return almacenes;
	}
	public void setAlmacenes(String almacenes) {
		this.almacenes = almacenes;
	}
	
	public String[] getFamnom() {
		return famnom;
	}
	public void setFamnom(String[] famnom) {
		this.famnom = famnom;
	}
	public Date getFecVen() {
		return fecVen;
	}
	public void setFecVen(Date fecVen) {
		this.fecVen = fecVen;
	}
	public Date getFecela() {
		return fecela;
	}
	public void setFecela(Date fecela) {
		this.fecela = fecela;
	}

	private String linea ; 
	
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	public String getProdstkMin() {
		return prodstkMin;
	}
	public void setProdstkMin(String prodstkMin) {
		this.prodstkMin = prodstkMin;
	}
	public String getProdstkTotal() {
		return prodstkTotal;
	}
	public void setProdstkTotal(String prodstkTotal) {
		this.prodstkTotal = prodstkTotal;
	}
	public String getProdSegmentacion() {
		return prodSegmentacion;
	}
	public void setProdSegmentacion(String prodSegmentacion) {
		this.prodSegmentacion = prodSegmentacion;
	}
	public String getProdEstiba() {
		return prodEstiba;
	}
	public void setProdEstiba(String prodEstiba) {
		this.prodEstiba = prodEstiba;
	}
	public String getProdPeso() {
		return prodPeso;
	}
	public void setProdPeso(String prodPeso) {
		this.prodPeso = prodPeso;
	}
	public String getProdVol() {
		return prodVol;
	}
	public void setProdVol(String prodVol) {
		this.prodVol = prodVol;
	}
	public String getProdLote() {
		return prodLote;
	}
	public void setProdLote(String prodLote) {
		this.prodLote = prodLote;
	}
	public String getProdPrecio() {
		return prodPrecio;
	}
	public void setProdPrecio(String prodPrecio) {
		this.prodPrecio = prodPrecio;
	}
	
	public String tomarURL() {
		String valor = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/nvoprod/";
		return valor;
	}
	
	/// Convertir JSOn a string Familia
	public Familia famiCompleta(String nombre) {
		String url2 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/fam/";
		Gson gson = new Gson();
		Familia fami = gson.fromJson(readJsonFromUrl(url2+nombre), Familia.class);
		return fami;
	}
	
	/// Convertir de string JSon a Almacenamiento
	public Almacenamiento almacenamientos(String nombre) {
		String urlalm = "http://dominio.ddns.net:8086/ProyectoRest/rest/alma/";
		Gson gson = new Gson();
		Almacenamiento almacen = gson.fromJson(readJsonFromUrl(urlalm+nombre), Almacenamiento.class);
		return almacen;
	}
	
	/// se hace el procedimineto onRowEdit de la Tabla
	public void onRowEdit(RowEditEvent event) {
		System.out.println("entre al row");
		Long produ = ((Producto) event.getObject()).getProdId();
		Producto pro = new Producto();
		pro.setProdId(produ);
		System.out.println(produ.toString());
		if(!Nombre.equals("")) {
			pro.setProdNombre(Nombre);
		}else {
			pro.setProdNombre(((Producto) event.getObject()).getProdNombre());
		}
		System.out.println("entre al row1");
		if(!prodPrecio.equals("")) {
			pro.setProdPrecio(Double.parseDouble(prodPrecio));
		}else {
			pro.setProdPrecio(((Producto) event.getObject()).getProdPrecio());
		}
		if(!almacenes.equals("")) {
			pro.setAlmacenamiento(almacenamientos(almacenes));
		}else {
			pro.setAlmacenamiento(((Producto) event.getObject()).getAlmacenamiento());
		}
		System.out.println("entre al row2");
		if(!nomfamilia.equals("")) {
			pro.setFamilia(famiCompleta(nomfamilia));
		}else {
			pro.setFamilia(((Producto) event.getObject()).getFamilia());
		}
		if(fecVen!=null) {
			pro.setProdFven(fecVen);
		}else {
			pro.setProdFven(((Producto) event.getObject()).getProdFven());
		}
		if(fecela!=null) {
			pro.setProdFelab(fecela);
		}else {
			pro.setProdFelab(((Producto) event.getObject()).getProdFelab());
		}
		if(!prodLote.equals("")) {
			pro.setProdLote(prodLote);
		}else {
			pro.setProdLote(((Producto) event.getObject()).getProdLote());
		}
		
		String prod1 = new Gson().toJson(pro);
		try {
			String url0= "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
			conectPut(prod1,url0+"update");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        FacesMessage msg = new FacesMessage(mensajeConect,((Producto) event.getObject()).getProdId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Nombre ="";
        nomfamilia ="";
        almacenes ="";
    }
	
	public void conectPut (String dato, String ur) throws IOException {
		URL url = null;
			try {
				url = new URL(ur);
			} catch (MalformedURLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("PUT");
				conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
				String input = dato;
				DataOutputStream os = new DataOutputStream(conn.getOutputStream());
				os.write(input.getBytes());
				os.flush();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}		
			try {
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				    BufferedReader bufferedReader;
					bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						System.out.println(line);
					    mensajeConect = line;
					}
				}else{
					    // ... do something with unsuccessful response
					  }
			}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			} 			
		}
	
	/// tabla row cancel
    public void onRowCancel(RowEditEvent event) {

        FacesMessage msg = new FacesMessage("Cancelado", ((Producto) event.getObject()).getProdId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    
    // tabla seleccionar una linea tabla
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Mov. Seleccionado", ((Producto) event.getObject()).getProdId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
       
    }
    
	public String agregarProducto() {
		Familia famil = new Familia();
		famil = obtenerFamilia(fam);
		System.out.println("Entre a agregar producto");
		Producto produ = new Producto();
		produ.setProdNombre(Nombre);
		Almacenamiento almacen = new Almacenamiento();
		almacen = obteneralmacen(almacenes);
		produ.setFamilia(famil);
		produ.setAlmacenamiento(almacen);
		produ.setProdStkmin(Long.parseLong(prodstkMin));
		produ.setProdStktotal(Double.parseDouble(prodstkTotal));
		produ.setProdSegmetac(prodSegmentacion);
		produ.setProdEstiba(prodEstiba);
		produ.setProdPeso(Double.parseDouble(prodPeso));
		produ.setProdVol(Double.parseDouble(prodVol));
		produ.setProdLote(prodLote);
		produ.setProdPrecio(Double.parseDouble(prodPrecio));
		produ.setProdFelab(fecela);
		produ.setProdFven(fecVen);
		String nvoprod = new Gson().toJson(produ);
		String url1 = tomarURL();
		try {
			ConexionPost(nvoprod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ABMProducto.xhtml";			

	}
	
	public String getFam() {
		return fam;
	}
	public void setFam(String fam) {
		this.fam = fam;
	}
	
	// Salir de la pantalla
	public String getSalir() {
		return "menuprincipal.xhtml";
	}
		
	public String[] leerjson(String url){
		Gson gson = new Gson();
		String[] almace = gson.fromJson(readJsonFromUrl(url), String[].class);
		return almace;
	}
	
	public static String readJsonFromUrl(String url){
		
	    InputStream is = null;
		try {
			is = new URL(url).openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = null;
		try {
			jsonText = readAll(rd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	     
	      return jsonText;
	    } finally {
	      try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  }
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	      
	    }
	    return sb.toString();
	 }
	
	
	// Crear la conexion con el post

	public void ConexionPost (String dato) throws IOException {
		URL url = null;
		try {
			String url1 = tomarURL();
			url = new URL(url1);
		} catch (MalformedURLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod( "POST" );
			//conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
			conn.setRequestProperty("Content-Type", "application/json");
			String input = dato;
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			
			os.write(input.getBytes());
			os.flush();
			System.out.println("Final try");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			System.out.println("Final2");
		}
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = bufferedReader.readLine()) != null) {
	//	    	  JOptionPane.showMessageDialog(null, "Producto Guardado Correctamente!!!");
		      }
		    }
		   else {
		//	  JOptionPane.showMessageDialog(null, "No se ha podido guardar el Producto");
		  }
	}
	
	
	/// Realizar la conexion para Borrar
	public void ConexionBorrar (String dato) throws IOException {
		URL url = null;
		try {
			String url1= "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/borrar";
			url = new URL(url1);
		} catch (MalformedURLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod( "DELETE" );
			//conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
			conn.setRequestProperty("Content-Type", "application/json");
			String input = dato;
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			
			os.write(input.getBytes());
			os.flush();
			System.out.println("Final try");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			System.out.println("Final2");
		}
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = bufferedReader.readLine()) != null) {
		      }
		    }
		   else {
		  }
		
	}
	/// Obtener un almacen segun el nombre
	public Almacenamiento obteneralmacen(String nomalm) {
		String urlalma = "http://dominio.ddns.net:8086/ProyectoRest/rest/alma/";
		Gson gson = new Gson();
		Almacenamiento almac = gson.fromJson(readJsonFromUrl(urlalma+nomalm), Almacenamiento.class);
		return almac;
	}
	
	/// Obtener la Familia segun el nombre
	public Familia obtenerFamilia(String nomfam) {
		String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/fam/";
		Gson gson = new Gson();
		Familia fam = gson.fromJson(readJsonFromUrl(url1+nomfam), Familia.class);
		return fam;
	}
	
	/// cargo lista de nombre de Familias en combo
	public String[] getFamn(){
		String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/familianom";
		famnom = leerjson(url1);
		return famnom;
	}
	
	// Cargo la lista de movimientos en combobox
	public String[] getAlmas (){
		String url = "http://dominio.ddns.net:8086/ProyectoRest/rest/mov/obtnom";

		almas = leerjson(url);	
		return almas;
	}
	
	
	/// Visualizar los datos en el formulario para guardar
	public String Comprobar () {
		
		return "ABMProducto.xhtml";
	}
	
	/// Para eliminar o Modificar obtiene todos los nombre de los productos
	public List<Producto> getProds () {
		List<Producto> listpro = new ArrayList<Producto>();
		Gson gson = new Gson();
		String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
		Producto [] prods = gson.fromJson(readJsonFromUrl(url1+"prods"), Producto[].class);
		for(Producto p: prods){
			listpro.add(p);
		}
		return listpro;
	}
	
	
	/// Obtengo los datos del producto y cargo datos para eliminar
	public void buscarprod(){
		Producto prod1 = new Producto();
		prod1 = obtenerprod(pro);
		System.out.println("llegue");
		

     }
		
	public Producto obtenerprod(String produ) {
		
		String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
		System.out.println(produ);
		Gson gson = new Gson();
		Producto prod = gson.fromJson(readJsonFromUrl(url1+produ), Producto.class);
		return prod;
	}
			
	// configurar la fecha de la tabla
	public String Fecha (Date fecVen) {
		String fecha;
		if(fecVen!=null) {
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY");
			fecha = dateFormat.format(fecVen);
		}else {
			fecha = "Sin fecha";
		}
		return fecha;
	}
		
	public String Fecha2 (Date fecela) {
		String fecha;
		if(fecela!=null) {
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY");
			fecha = dateFormat.format(fecela);
		}else {
			fecha = "Sin fecha";
		}
		return fecha;
	}	
	
	/// eliminar Producto
	public void eliminarPro() {
		System.out.println("Elimine");
		System.out.println(selectedRow.getProdId().toString());
		Producto pro = new Producto();
		pro = getProducto (selectedRow.getProdNombre().toString());
		String prod = new Gson().toJson(pro);
		try {
			ConexionBorrar(prod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FacesMessage msg = new FacesMessage("Prod. Eliminado", selectedRow.getProdId().toString());
		FacesContext.getCurrentInstance().addMessage(null,msg);
		
	}
	
	/// Trae el producto por Nombre
	public Producto getProducto (String nom) {
		String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
		Gson gson = new Gson();
		Producto prod = gson.fromJson(readJsonFromUrl(url1+nom), Producto.class);
		return prod;
	}
}
