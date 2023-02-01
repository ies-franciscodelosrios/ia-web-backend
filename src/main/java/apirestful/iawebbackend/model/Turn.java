package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Turn")
public class Turn {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo",length = 10)
    private Long Codigo;

    @Column(name = "Lunes",length = 100)
    private Float Lunes;

    @Column(name = "LunesDescripcion")
    private String LunesDescripcion;

    @Column(name = "Martes",length = 100)
    private Float Martes;

    @Column(name = "MartesDescripcion")
    private String MartesDescripcion;

    @Column(name = "Miercoles",length = 100)
    private Float Miercoles;

    @Column(name = "MiercolesDescripcion")
    private String MiercolesDescripcion;


    @Column(name = "Jueves",length = 100)
    private Float Jueves;

    @Column(name = "JuevesDescripcion")
    private String JuevesDescripcion;

    @Column(name = "Viernes",length = 100)
    private Float Viernes;

    @Column(name = "ViernesDescripcion")
    private String ViernesDescripcion;

    @Column(name = "Sabado",length = 100)
    private Float Sabado;

    @Column(name = "SabadoDescripcion")
    private String SabadoDescripcion;

    @Column(name = "Domingo",length = 100)
    private Float Domingo;

    @Column(name = "DomingoDescripcion")
    private String DomingoDescripcion;

    @Column(name = "Total_Semana",length = 100)
    private Float Total_Semana;

    @Column(name = "Semana",length = 100)
    private Timestamp Semana;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    public Turn(Long codigo, Float lunes, String lunesDescripcion, Float martes, String martesDescripcion, Float miercoles, String miercolesDescripcion, Float jueves, String juevesDescripcion, Float viernes, String viernesDescripcion, Float sabado, String sabadoDescripcion, Float domingo, String domingoDescripcion, Float total_Semana, Timestamp semana, User user) {
        Codigo = codigo;
        Lunes = lunes;
        LunesDescripcion = lunesDescripcion;
        Martes = martes;
        MartesDescripcion = martesDescripcion;
        Miercoles = miercoles;
        MiercolesDescripcion = miercolesDescripcion;
        Jueves = jueves;
        JuevesDescripcion = juevesDescripcion;
        Viernes = viernes;
        ViernesDescripcion = viernesDescripcion;
        Sabado = sabado;
        SabadoDescripcion = sabadoDescripcion;
        Domingo = domingo;
        DomingoDescripcion = domingoDescripcion;
        Total_Semana = total_Semana;
        Semana = semana;
        this.user = user;
    }

    public Turn() {}

    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long codigo) {
        Codigo = codigo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getLunes() {
        return Lunes;
    }

    public void setLunes(Float lunes) {
        Lunes = lunes;
    }

    public String getLunesDescripcion() {
        return LunesDescripcion;
    }

    public void setLunesDescripcion(String lunesDescripcion) {
        LunesDescripcion = lunesDescripcion;
    }

    public Float getMartes() {
        return Martes;
    }

    public void setMartes(Float martes) {
        Martes = martes;
    }

    public String getMartesDescripcion() {
        return MartesDescripcion;
    }

    public void setMartesDescripcion(String martesDescripcion) {
        MartesDescripcion = martesDescripcion;
    }

    public Float getMiercoles() {
        return Miercoles;
    }

    public void setMiercoles(Float miercoles) {
        Miercoles = miercoles;
    }

    public String getMiercolesDescripcion() {
        return MiercolesDescripcion;
    }

    public void setMiercolesDescripcion(String miercolesDescripcion) {
        MiercolesDescripcion = miercolesDescripcion;
    }

    public Float getJueves() {
        return Jueves;
    }

    public void setJueves(Float jueves) {
        Jueves = jueves;
    }

    public String getJuevesDescripcion() {
        return JuevesDescripcion;
    }

    public void setJuevesDescripcion(String juevesDescripcion) {
        JuevesDescripcion = juevesDescripcion;
    }

    public Float getViernes() {
        return Viernes;
    }

    public void setViernes(Float viernes) {
        Viernes = viernes;
    }

    public String getViernesDescripcion() {
        return ViernesDescripcion;
    }

    public void setViernesDescripcion(String viernesDescripcion) {
        ViernesDescripcion = viernesDescripcion;
    }

    public Float getSabado() {
        return Sabado;
    }

    public void setSabado(Float sabado) {
        Sabado = sabado;
    }

    public String getSabadoDescripcion() {
        return SabadoDescripcion;
    }

    public void setSabadoDescripcion(String sabadoDescripcion) {
        SabadoDescripcion = sabadoDescripcion;
    }

    public Float getDomingo() {
        return Domingo;
    }

    public void setDomingo(Float domingo) {
        Domingo = domingo;
    }

    public String getDomingoDescripcion() {
        return DomingoDescripcion;
    }

    public void setDomingoDescripcion(String domingoDescripcion) {
        DomingoDescripcion = domingoDescripcion;
    }


    public Float getTotal_Semana() {
        return Total_Semana;
    }

    public void setTotal_Semana(Float total_Semana) {
        Total_Semana = total_Semana;
    }

    public Timestamp getSemana() {
        return Semana;
    }

    public void setSemana(Timestamp semana) {
        Semana = semana;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "Codigo=" + Codigo +
                ", Lunes=" + Lunes +
                ", LunesDescripcion='" + LunesDescripcion + '\'' +
                ", Martes=" + Martes +
                ", MartesDescripcion='" + MartesDescripcion + '\'' +
                ", Miercoles=" + Miercoles +
                ", MiercolesDescripcion='" + MiercolesDescripcion + '\'' +
                ", Jueves=" + Jueves +
                ", JuevesDescripcion='" + JuevesDescripcion + '\'' +
                ", Viernes=" + Viernes +
                ", ViernesDescripcion='" + ViernesDescripcion + '\'' +
                ", Sabado=" + Sabado +
                ", SabadoDescripcion='" + SabadoDescripcion + '\'' +
                ", Domingo=" + Domingo +
                ", DomingoDescripcion='" + DomingoDescripcion + '\'' +
                ", Total_Semana=" + Total_Semana +
                ", Semana=" + Semana +
                ", user=" + user +
                '}';
    }
}
