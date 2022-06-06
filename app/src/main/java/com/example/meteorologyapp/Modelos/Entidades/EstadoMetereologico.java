package com.example.meteorologyapp.Modelos.Entidades;

public class EstadoMetereologico {
    private int id;
    private String fecha;
    private float estacion;
    private float temperatura;
    private float humedad;
    private float precipitacion;
    private String direccionViento;
    private float velocidadViento;

    public EstadoMetereologico() {
    }

    public EstadoMetereologico(int id, String fecha, float estacion, float temperatura,
                               float humedad, float precipitacion, String direccionViento,
                               float velocidadViento) {
        this.id = id;
        this.fecha = fecha;
        this.estacion = estacion;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.precipitacion = precipitacion;
        this.direccionViento = direccionViento;
        this.velocidadViento = velocidadViento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getEstacion() {
        return estacion;
    }

    public void setEstacion(float estacion) {
        this.estacion = estacion;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public float getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(float precipitacion) {
        this.precipitacion = precipitacion;
    }

    public String getDireccionViento() {
        return direccionViento;
    }

    public void setDireccionViento(String direccionViento) {
        this.direccionViento = direccionViento;
    }

    public float getVelocidadViento() {
        return velocidadViento;
    }

    public void setVelocidadViento(float velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    @Override
    public String toString() {
        return "EstadoMetereologico{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", estacion=" + estacion +
                ", temperatura=" + temperatura +
                ", humedad=" + humedad +
                ", precipitacion=" + precipitacion +
                ", direccionViento='" + direccionViento + '\'' +
                ", velocidadViento=" + velocidadViento +
                '}';
    }
}
