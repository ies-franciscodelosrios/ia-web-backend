package apirestful.iawebbackend.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User")
public class User {
    @Id
    @NotNull
    @Column(name = "Codigo",length = 50)
    private String Codigo;

    @Column(name = "Name",length = 50)
    private String Name;

    @Column(name = "Apellido1",length =30)
    private String Apellido1;

    @Column(name = "Apellido2",length = 30)
    private String Apellido2;

    @Column(name = "Email",length = 200)
    private String Email;

    @Column(name = "Profile_Picture")
    private String Profile_Picture;

    @NotNull
    @Column(name = "Login",length = 50,unique = true)
    private String Login;

    @Column(name = "Password",length = 60)
    private String Password;

    @Column(name = "Puesto", length = 60)
    private String Puesto;

    @Column(name = "Oficina", length = 60)
    private String Oficina;

    @Column(name = "Pais", length = 60)
    private String Pais;

    @Column(name = "Create_date",length = 50)
    private Timestamp Create_date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_rols",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> rols = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Turn> turns = new HashSet<>();

    public User(String codigo, String name, String apellido1, String apellido2, String email, String profile_Picture, String login, String password, String puesto, String oficina, String pais, Timestamp create_date, Set<Rol> rols, Set<Event> events, Set<Turn> turns) {
        Codigo = codigo;
        Name = name;
        Apellido1 = apellido1;
        Apellido2 = apellido2;
        Email = email;
        Profile_Picture = profile_Picture;
        Login = login;
        Password = password;
        Puesto = puesto;
        Oficina = oficina;
        Pais = pais;
        Create_date = create_date;
        this.rols = rols;
        this.events = events;
        this.turns = turns;
    }

    public User() {}

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String apellido1) {
        Apellido1 = apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String apellido2) {
        Apellido2 = apellido2;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfile_Picture() {
        return Profile_Picture;
    }

    public void setProfile_Picture(String profile_Picture) {
        Profile_Picture = profile_Picture;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public String getOficina() {
        return Oficina;
    }

    public void setOficina(String oficina) {
        Oficina = oficina;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public Timestamp getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        Create_date = create_date;
    }

    public Set<Rol> getRols() {
        return rols;
    }

    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Turn> getTurns() {
        return turns;
    }

    public void setTurns(Set<Turn> turns) {
        this.turns = turns;
    }

    @Override
    public String toString() {
        return "User{" +
                "Codigo='" + Codigo + '\'' +
                ", Name='" + Name + '\'' +
                ", Apellido1='" + Apellido1 + '\'' +
                ", Apellido2='" + Apellido2 + '\'' +
                ", Email='" + Email + '\'' +
                ", Profile_Picture='" + Profile_Picture + '\'' +
                ", Login='" + Login + '\'' +
                ", Password='" + Password + '\'' +
                ", Puesto='" + Puesto + '\'' +
                ", Oficina='" + Oficina + '\'' +
                ", Pais='" + Pais + '\'' +
                ", Create_date=" + Create_date +
                ", rols=" + rols +
                ", events=" + events +
                ", turns=" + turns +
                '}';
    }
}
