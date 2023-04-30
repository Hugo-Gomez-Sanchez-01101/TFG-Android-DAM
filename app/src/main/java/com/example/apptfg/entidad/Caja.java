package com.example.apptfg.entidad;

public class Caja {
    private String color;
    private String bahiasExternas;
    private String factorForma;
    private String bahiasInternas;
    private String name;
    private String fuenteAlmintación;
    private double precio;
    private String panelLateral;
    private String tipo;

    public Caja(String color, String bahiasExternas, String factorForma, String bahiasInternas, String name, String fuenteAlmintación, double precio, String panelLateral, String tipo) {
        this.color = color;
        this.bahiasExternas = bahiasExternas;
        this.factorForma = factorForma;
        this.bahiasInternas = bahiasInternas;
        this.name = name;
        this.fuenteAlmintación = fuenteAlmintación;
        this.precio = precio;
        this.panelLateral = panelLateral;
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBahiasExternas() {
        return bahiasExternas;
    }

    public void setBahiasExternas(String bahiasExternas) {
        this.bahiasExternas = bahiasExternas;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    public String getBahiasInternas() {
        return bahiasInternas;
    }

    public void setBahiasInternas(String bahiasInternas) {
        this.bahiasInternas = bahiasInternas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuenteAlmintación() {
        return fuenteAlmintación;
    }

    public void setFuenteAlmintación(String fuenteAlmintación) {
        this.fuenteAlmintación = fuenteAlmintación;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPanelLateral() {
        return panelLateral;
    }

    public void setPanelLateral(String panelLateral) {
        this.panelLateral = panelLateral;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
