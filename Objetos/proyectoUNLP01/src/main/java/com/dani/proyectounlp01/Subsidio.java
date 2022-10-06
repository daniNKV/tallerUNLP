/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.proyectounlp01;

/**
 *
 * @author dani
 */
public class Subsidio {
    private double monto;
    private String motivo;
    private boolean fueOtorgado;

    public Subsidio(double monto, String motivo) {
        this.monto = monto;
        this.motivo = motivo;
        this.fueOtorgado = false;
    }

    public double getMonto() {
        return this.monto;
    }
    public boolean getEstado(){
        return this.fueOtorgado;
    }
    public void otorgarSubsidio(){
        this.fueOtorgado = true;
    }
    public void denegarSubsidio(){
        this.fueOtorgado = false;
    }
    
    
}
