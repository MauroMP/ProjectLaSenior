package com.services;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("calendar")
@SessionScoped
public class Calendar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getFecha () {
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/YYYY");
		System.out.println(dateFormat.format(date));
		String fecha = dateFormat.format(date);
		return fecha;
	}

}
