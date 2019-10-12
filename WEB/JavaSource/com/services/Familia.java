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


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



import com.google.gson.Gson;




@Named("fami")
@SessionScoped

public class Familia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String familias;
	private String[] famnom;
	private String fam;
	private String url = "http://dominio.ddns.net:8086/ProyectoRest/rest/prod/obtnom";

	public String getFamilia() {
		return familias;
	}
	
	public void setFamilia(String familias) {
		this.familias = familias;
	}
	
	
	
	public String getFam() {
		return fam;
	}

	public void setFam(String fam) {
		this.fam = fam;
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

	public static String readJsonFromUrl(String url)  {
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
		String[] fano = gson.fromJson(readJsonFromUrl(url), String[].class);
		return fano;

	}
	
	public String[] getFamn(){
		
		famnom = leerjson(url);
		return famnom;
	}
		
	
	

}
