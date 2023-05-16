package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "PollsAssignment")
public class PollsAssignment {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "IdNavision")
    private String IdNavision;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Email")
    private String Email;

    @Column(name = "IdNavision2",columnDefinition = "VARCHAR(50) DEFAULT 'DEFAULT'")
    private String IdNavision2;

    @Column(name = "PersonCategory")
    private int PersonCategory;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "questionary_group_id")
    private QuestionaryGroup questionaryGroup;


    @OneToOne(mappedBy = "pollsAssignment", cascade = CascadeType.ALL)
    private Poll poll;

    @OneToOne(mappedBy = "pollsAssignment", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Response response;


    @Column(name = "Active")
    private boolean Active;

    public PollsAssignment() {
    }

    public PollsAssignment(Long id, String idNavision, String name, String email, String idNavision2, int personCategory, QuestionaryGroup questionaryGroup, Response response, boolean active) {
        Id = id;
        IdNavision = idNavision;
        Name = name;
        Email = email;
        IdNavision2 = idNavision2;
        PersonCategory = personCategory;
        this.questionaryGroup = questionaryGroup;
        Active = active;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getIdNavision() {
        return IdNavision;
    }

    public void setIdNavision(String idNavision) {
        IdNavision = idNavision;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdNavision2() {
        return IdNavision2;
    }

    public void setIdNavision2(String idNavision2) {
        IdNavision2 = idNavision2;
    }

    public int getPersonCategory() {
        return PersonCategory;
    }

    public void setPersonCategory(int personCategory) {
        PersonCategory = personCategory;
    }

    public QuestionaryGroup getQuestionaryGroup() {
        return questionaryGroup;
    }

    public void setQuestionaryGroup(QuestionaryGroup questionaryGroup) {
        this.questionaryGroup = questionaryGroup;
    }



    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }


    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    @Override
    public String toString() {
        return "PollsAssignment{" +
                "Id=" + Id +
                ", IdNavision='" + IdNavision + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", IdNavision2='" + IdNavision2 + '\'' +
                ", PersonCategory=" + PersonCategory +
                ", Active=" + Active +
                '}';
    }
}
