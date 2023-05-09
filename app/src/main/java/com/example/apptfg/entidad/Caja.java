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

    public String getBahiasExternas() {
        return bahiasExternas;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public String getBahiasInternas() {
        return bahiasInternas;
    }

    public String getName() {
        return name;
    }

    public String getFuenteAlmintación() {
        return fuenteAlmintación;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPanelLateral() {
        return panelLateral;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Caja{" +
                "color='" + color + '\'' +
                ", bahiasExternas='" + bahiasExternas + '\'' +
                ", factorForma='" + factorForma + '\'' +
                ", bahiasInternas='" + bahiasInternas + '\'' +
                ", name='" + name + '\'' +
                ", fuenteAlmintación='" + fuenteAlmintación + '\'' +
                ", precio=" + precio +
                ", panelLateral='" + panelLateral + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
