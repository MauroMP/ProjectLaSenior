package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.inject.Named;

import com.dominio.Movimiento;
import com.google.gson.Gson;



@Named("mov")
@SessionScoped
public class movimientos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String almacenes;
	private String[] almas;
	private String tipo;
	private String producto;
	private String cantb;
	private String sp;
	private String optionS;
	private String buscar;
	private String url = "http://dominio.ddns.net:8086/ProyectoRest/rest/mov/";
	private String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
    private HtmlOutputText sp1 = new HtmlOutputText();
	
    
    
    
	

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public String getOptionS() {
		return optionS;
	}

	public void setOptionS(String optionS) {
		this.optionS = optionS;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	public String getCantb() {
		return cantb;
	}

	public void setCantb(String cantb) {
		this.cantb = cantb;
	}

	public String getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(String almacenes) {
		this.almacenes = almacenes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public HtmlOutputText getSp1() {
		return sp1;
	}

	public void setSp1(HtmlOutputText sp1) {
		this.sp1 = sp1;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	      
	    }
	    return sb.toString();
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
	public String[] leerjson(String url){
	Gson gson = new Gson();
	String[] almace = gson.fromJson(readJsonFromUrl(url), String[].class);
	return almace;
	}
	
	public List<Movimiento> getMovs(){
		List<Movimiento> listmov = new ArrayList<Movimiento>();
		Gson gson = new Gson();
		Movimiento[] movs = gson.fromJson(readJsonFromUrl(url+"movs"), Movimiento[].class);
		for (Movimiento m: movs) {
              listmov.add(m);
        }
		return listmov;
		}
	
	public List<Movimiento> getBuscar(String opt){
		List<Movimiento> listmov = new ArrayList<Movimiento>();
		Gson gson = new Gson();
		Movimiento[] movs = gson.fromJson(readJsonFromUrl(url+opt), Movimiento[].class);
		for (Movimiento m: movs) {
              listmov.add(m);
        }
		return listmov;
		}
	
	public List<Movimiento> getTabla(){
		List<Movimiento> listmovT = new ArrayList<Movimiento>();
		listmovT=getMovs();
		System.out.println(optionS);
		System.out.println(buscar);
		optionS = "";
		buscar = "";
		return listmovT;
		
		
	}
	
	/*public List<Movimiento> getTabla(){
		
	
	   List<Movimiento> listmovT = new ArrayList<Movimiento>();
		
		if(optionS.equals("Producto")||optionS.equals("Descripcion")||optionS.equals("Almacenamiento")||optionS.equals("Fecha")) {
			
			if(buscar.equals(null)) {
				System.out.println("entro al if de options");
				listmovT=getMovs();
			}
			
			if(optionS.equals("Producto")) {
				listmovT=getBuscar("movsp/"+buscar);
			}
			if(optionS.equals("Almacenamiento")) {
				listmovT=getBuscar("movsa/"+buscar);
			}
			if(optionS.equals("Descripcion")) {
				listmovT=getBuscar("movd/"+buscar);
			}
			if(optionS.equals("Fecha")) {
				listmovT=getBuscar("movsf/"+buscar);
			}
			
		}
		if(optionS!=null && buscar.equals("") ){
			System.out.println("entra primer if");
		}
		if(optionS.equals("") && buscar.equals("") ) {
			System.out.println("entra segundo if");
		}
		else {
			listmovT=getMovs();
		}
		
	   return listmovT;
		
		
	}
	*/	
	public String[] getAlmas (){
		
		almas = leerjson(url+"obtnom");
		
		return almas;
	}
	
	public String[] getProds () {
		String [] prods = leerjson(url1+"obtnom");
		return prods;
	}
	
	public String getStockprod() {
		
		String [] prodstock = leerjson(url1+"stockprod/"+producto);
		 String sp1 = prodstock[0];
		return sp1;
	}
	
	public String Comprobar () {
		
		sp=getStockprod();
		
		
		return "ConfirmarMov.xhtml";
	}
	
	
	

}
