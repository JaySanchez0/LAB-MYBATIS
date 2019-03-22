/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author 2106913
 */
public class ItemRentado implements Serializable {

    private int id;

    private Item item;
    private Date fechainiciorenta;
    private Date fechafinrenta;

    public ItemRentado(int id, Item item, Date date, Date fechafinrenta) {
        this.id = id;
        this.item = item;
        this.fechainiciorenta = date;
        this.fechafinrenta = fechafinrenta;
    }

    public ItemRentado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getFechainiciorenta() {
        return fechainiciorenta;
    }

    public void setFechainiciorenta(Date fechainiciorenta) {
        this.fechainiciorenta = fechainiciorenta;
    }

    public Date getFechafinrenta() {
        return fechafinrenta;
    }

    public void setFechafinrenta(Date fechafinrenta) {
        this.fechafinrenta = fechafinrenta;
    }

    @Override
    public String toString() {
        return "ItemRentado{" + "id=" + id + ", item=" + item + ", fechainiciorenta=" + fechainiciorenta + ", fechafinrenta=" + fechafinrenta + '}';
    }
    public double getMulta() {
    	java.util.Date actual = Calendar.getInstance().getTime();
    	if(fechafinrenta.before(actual)) {
    		int daysYears = (actual.getYear()-fechafinrenta.getYear()-1)*365;
    		int daysMounth = Math.abs(actual.getMonth()-fechafinrenta.getMonth())*30;
    		int daysd = Math.abs(actual.getDay()-fechafinrenta.getDay());
    		return (daysYears+daysMounth+daysd)*1.1*item.getTarifaxDia();
    	}else {
    		return 0;
    	}
    }
    
    
}
