package agricolapilon.pilonsa.model;

/**
 * Created by BYTE on 27/11/2018.
 */

public class Pilon {
    private  String uid;
    private  String linea;
    private  String proyecto;
    private  String fechaSiembra;
    private  String granosSembrados;
    private  String fechaTransplante;
    private  String plantasTransplantadas;
    private  String ubicacion;
    private  String fechaPolinizado;
    private  String plantasPolinizadas;
    private  String fechaCosecha;
    private  String mazorcasCosechadas;
    private  String comentario;

    public Pilon() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public String getGranosSembrados() {
        return granosSembrados;
    }

    public void setGranosSembrados(String granosSembrados) {
        this.granosSembrados = granosSembrados;
    }

    public String getFechaTransplante() {
        return fechaTransplante;
    }

    public void setFechaTransplante(String fechaTransplante) {
        this.fechaTransplante = fechaTransplante;
    }

    public String getPlantasTransplantadas() {
        return plantasTransplantadas;
    }

    public void setPlantasTransplantadas(String plantasTransplantadas) {
        this.plantasTransplantadas = plantasTransplantadas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaPolinizado() {
        return fechaPolinizado;
    }

    public void setFechaPolinizado(String fechaPolinizado) {
        this.fechaPolinizado = fechaPolinizado;
    }

    public String getPlantasPolinizadas() {
        return plantasPolinizadas;
    }

    public void setPlantasPolinizadas(String plantasPolinizadas) {
        this.plantasPolinizadas = plantasPolinizadas;
    }

    public String getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public String getMazorcasCosechadas() {
        return mazorcasCosechadas;
    }

    public void setMazorcasCosechadas(String mazorcasCosechadas) {
        this.mazorcasCosechadas = mazorcasCosechadas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return linea;
    }
}
