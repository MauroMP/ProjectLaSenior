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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.inject.Named;

import javax.ws.rs.core.MediaType;

import com.dominio.Almacenamiento;
import com.dominio.Movimiento;
import com.dominio.Producto;
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
	private String tipoM;
	private String producto;
	private String cantb;
	private String sp;
	private String optionS;
	private String optbuscar;
	private List<Movimiento> tablaList;
	private String url0= "http://dominio.ddns.net:8086/ProyectoRest/rest/mov/";
	private String url1 = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/";
	private String url2 = "http://dominio.ddns.net:8086/ProyectoRest/rest/alma/";
	private Date date;
	
    
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	
	public List<Movimiento> getTablaList() {
		return tablaList;
	}

	public void setTablaList(List<Movimiento> tablaList) {
		this.tablaList = tablaList;
	}

	public String getOptbuscar() {
		return optbuscar;
	}

	public void setOptbuscar(String optbuscar) {
		this.optbuscar = optbuscar;
	}

	public String getOptionS() {
		return optionS;
	}

	public void setOptionS(String optionS) {
		this.optionS = optionS;
	}

	public String getTipoM() {
		return tipoM;
	}

	public void setTipoM(String tipoM) {
		this.tipoM = tipoM;
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
	/*public String[] leerjson(String url){
	Gson gson = new Gson();
	String[] almace = gson.fromJson(readJsonFromUrl(url), String[].class);
	return almace;
	}
	*/
	
	public List<Movimiento> getMovs(){
		List<Movimiento> listmov = new ArrayList<Movimiento>();
		Gson gson = new Gson();
		Movimiento[] movs = gson.fromJson(readJsonFromUrl(url0+"movs"), Movimiento[].class);
		for (Movimiento m: movs) {
              listmov.add(m);
        }
		return listmov;
		}
	
	public String Search(){
		//List<Movimiento> listmovT = new ArrayList<Movimiento>();
		
       if(optionS.equals("P")||optionS.equals("D")||optionS.equals("A")||optionS.equals("F")) {
			
			if(optbuscar.equals(null)) {
				System.out.println("entro al if de options");
				tablaList=getMovs();
			}
			
			if(optionS.equals("P")) {
				System.out.println("P");
				tablaList=getBuscar("movsp/"+optbuscar);
				
			}
			if(optionS.equals("A")) {
				System.out.println("A");
				tablaList=getBuscar("movsa/"+optbuscar);
				
			}
			if(optionS.equals("D")) {
				System.out.println("D");
				tablaList=getBuscar("movd/"+optbuscar);
				
			}
			if(optionS.equals("F")) {
				System.out.println("D");
				tablaList=getBuscar("movsf/"+optbuscar);
				
			}
			
		}
		else {
			tablaList=getMovs();
		}
		
	  	System.out.println(optionS);
		System.out.println(optbuscar);
		optionS = "";
		optbuscar = "";
		
		return "bajaM.xhtml";
		}
	
	
	public List<Movimiento> getBuscar (String st) {
		List<Movimiento> listmov = new ArrayList<Movimiento>();
		Gson gson = new Gson();
		Movimiento[] movs = gson.fromJson(readJsonFromUrl(url0+st), Movimiento[].class);
		for (Movimiento m: movs) {
              listmov.add(m);
        }
		return listmov;
		
	}
	
	public List<Movimiento> getTabla(){
		List<Movimiento> listmovT = new ArrayList<Movimiento>();
		listmovT=getMovs();
		
		return listmovT;
		
		
	}
	
	public List<String> getAlmas (){
		
		List<String> listalmas = new ArrayList<String>();
		Gson gson = new Gson();
		String[] almas = gson.fromJson(readJsonFromUrl(url0+"obtnom"), String[].class);
		for (String m: almas) {
              listalmas.add(m);
        }
		return listalmas;
	}
	
   public List<String> getProds (){
		
		List<String> listprods= new ArrayList<String>();
		Gson gson = new Gson();
		String[] prods = gson.fromJson(readJsonFromUrl(url1+"obtnom"), String[].class);
		for (String m: prods) {
              listprods.add(m);
        }
		return listprods;
   }
	
	
	public String getStockprod() {
		
		Gson gson = new Gson();
		String[] prods = gson.fromJson(readJsonFromUrl(url1+"stockprod/"+producto), String[].class);
		String sp1 = prods[0];
		return sp1;
	}
	
	public String Comprobar () {
		
		sp=getStockprod();
		
		
		return "ConfirmarMov.xhtml";
	}
	
	public Almacenamiento almacenamientos(String nombre) {
		
		Gson gson = new Gson();
		Almacenamiento almacen = gson.fromJson(readJsonFromUrl(url2+nombre), Almacenamiento.class);
		return almacen;
	}
	
    public Producto productos (String nombre) {
		
		Gson gson = new Gson();
		Producto prod = gson.fromJson(readJsonFromUrl(url1+nombre), Producto.class);
		return prod;
	}
	

	public String crearmov () {
		Movimiento mov = new Movimiento();
		mov.setMovTipo(tipoM);
		mov.setAlmacenamiento(almacenamientos(almacenes));
		mov.setProducto(productos(producto));
		mov.setMovCantidad(Double.parseDouble(cantb));
		mov.setMovFecha(date);
		String movi = new Gson().toJson(mov);
		try {
			conectPost(movi, url0+"nvomov");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "altaM.xhtml";
	}
	
	
	public void conectPost (String dato, String ur) throws IOException {
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
			conn.setRequestMethod("POST");
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
				      }
				} else {
				    // ... do something with unsuccessful response
				  }
			}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			     
		
	}
	
			
	public String getFecha () {
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY");
		System.out.println(dateFormat.format(date));
		String fecha = dateFormat.format(date);
		return fecha;
	}
	
	
}
