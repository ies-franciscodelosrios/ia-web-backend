package apirestful.iawebbackend.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "Rolname")
    private String Rolname;

    @Column(name = "Description")
    private String Description;

    @NotNull
    @Column(name = "Create_date")
    private Timestamp Create_date;

    public Rol(Long id, String rolname, String description, Timestamp create_date) {
        super();
        this.id = id;
        Rolname = rolname;
        Description = description;
        Create_date = create_date;
    }

    public Rol() {}

    public Long getId() {
        return id;
    }

    public String getRolname() {
        return Rolname;
    }

    public void setRolname(String rolname) {
        Rolname = rolname;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Timestamp getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        Create_date = create_date;
    }

    @Override
    public String toString() {
        return "Rol [id=" + id + ", Rolname=" + Rolname + ", Description=" + Description + ", Create_date="
                + Create_date + "]";
    }
}
