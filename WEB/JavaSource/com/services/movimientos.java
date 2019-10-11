package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.json.JSONException;
import org.json.JSONObject;

@Named("mov")
@SessionScoped
public class movimientos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String almacenes;
	String almas;
	String url = "http://dominio.ddns.net:8086/ProyectoRest/rest/mov/obtnom";

	public String getAlmacenes() {
		return almacenes;
	}




	public void setAlmacenes(String almacenes) {
		this.almacenes = almacenes;
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
	
	public static String readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      String envio = json.toString();
	      System.out.println(envio);
	      return envio;
	    } finally {
	      is.close();
	    }
	  }


	public String getAlmas (){
		try {
			almas = readJsonFromUrl(url);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return almas;
	}
	

}
