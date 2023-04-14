package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Poll")
public class Poll {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "Active")
    private boolean Active;


    @Column(name = "Completed")
    private boolean Completed;

    @Column(name = "Signed")
    private boolean Signed;

    @Column(name = "OnLoad")
    private boolean OnLoad;

    @Column(name = "LastModified")
    private Date LastModified;

    @Column(name = "Create_Date")
    private Date Create_Date;





    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "polls_assignment_id")
    private PollsAssignment pollsAssignment;


    public Poll(Long id, boolean active, boolean completed, boolean signed, boolean onLoad, Date lastModified, Date create_Date, PollsAssignment pollsAssignment) {
        Id = id;
        Active = active;
        Completed = completed;
        Signed = signed;
        OnLoad = onLoad;
        LastModified = lastModified;
        Create_Date = create_Date;
        this.pollsAssignment = pollsAssignment;
    }

    public Poll() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public boolean isSigned() {
        return Signed;
    }

    public void setSigned(boolean signed) {
        Signed = signed;
    }

    public boolean isOnLoad() {
        return OnLoad;
    }

    public void setOnLoad(boolean onLoad) {
        OnLoad = onLoad;
    }

    public Date getLastModified() {
        return LastModified;
    }

    public void setLastModified(Date lastModified) {
        LastModified = lastModified;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        Create_Date = create_Date;
    }



    public PollsAssignment getPollsAssignment() {
        return pollsAssignment;
    }

    public void setPollsAssignment(PollsAssignment pollsAssignment) {
        this.pollsAssignment = pollsAssignment;
    }


    @Override
    public String toString() {
        return "Poll{" +
                "Id=" + Id +
                ", Active=" + Active +
                ", Completed=" + Completed +
                ", Signed=" + Signed +
                ", OnLoad=" + OnLoad +
                ", LastModified=" + LastModified +
                ", Create_Date=" + Create_Date +
                '}';
    }
}
