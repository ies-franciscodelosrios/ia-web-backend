package apirestful.iawebbackend.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "Name")
    private String Name;

    @Column(name = "Description")
    private String Description;

    @NotNull
    @Column(name = "Date_Start_Event")
    private Timestamp Date_Start_Event;

    @NotNull
    @Column(name = "Create_date")
    private Timestamp Create_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Event(Long id, String name, String description, Timestamp date_Start_Event, Timestamp create_date) {
        super();
        this.id = id;
        Name = name;
        Description = description;
        Date_Start_Event = date_Start_Event;
        Create_date = create_date;
    }

    public Event() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Timestamp getDate_Start_Event() {
        return Date_Start_Event;
    }

    public void setDate_Start_Event(Timestamp date_Start_Event) {
        Date_Start_Event = date_Start_Event;
    }

    public Timestamp getCreate_date() {
        return Create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        Create_date = create_date;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", Name=" + Name + ", Description=" + Description + ", Date_Start_Event="
                + Date_Start_Event + ", Create_date=" + Create_date + "]";
    }
}
