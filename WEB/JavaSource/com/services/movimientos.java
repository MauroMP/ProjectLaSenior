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
import java.util.Date;



import javax.enterprise.context.SessionScoped;
import javax.inject.Named;




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
	private String url = "http://dominio.ddns.net:8086/ProyectoRest/rest/mov/obtnom";
	private Date selectDate;
	private boolean popup = true;
 
	
	
	public String getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(String almacenes) {
		this.almacenes = almacenes;
	}

	public Date getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(Date selectDate) {
		this.selectDate = selectDate;
	}

	public boolean isPopup() {
		return popup;
	}

	public void setPopup(boolean popup) {
		this.popup = popup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String[] getAlmas (){
		
		//List<String> almasL = new ArrayList<String>();
		
			almas = leerjson(url);
		
		return almas;
	}
	
	public void Imprimir () {
		
			
			System.out.println(url);
	
		System.out.println(almacenes);
	
	}

}
