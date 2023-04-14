package apirestful.iawebbackend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "QuestionaryGroup")
public class QuestionaryGroup {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "StartDate")
    private Date StartDate;

    @Column(name = "EndDate")
    private Date EndDate;

    @Column(name = "Active")
    private boolean Active;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Description_qg")
    private String Description;

    @Column(name = "PersonCategory")
    private int PersonCategory;


    @JsonIgnore
    @OneToMany(mappedBy = "questionaryGroup")
    private List<PollsAssignment> pollsAssignments;


    @JsonIgnore
    @OneToOne(mappedBy = "questionaryGroup", cascade = CascadeType.ALL)
    private TextRelation textRelation;

    public QuestionaryGroup() {
    }

    public QuestionaryGroup(Long id, Date startDate, Date endDate, boolean active, String name,TextRelation textRelation,
                            String description, int personCategory,
                            List<PollsAssignment> pollsAssignments) {
        Id = id;
        StartDate = startDate;
        EndDate = endDate;
        Active = active;
        Name = name;
        Description = description;
        this.textRelation=textRelation;
        PersonCategory = personCategory;
        this.pollsAssignments = pollsAssignments;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
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

    public int getPersonCategory() {
        return PersonCategory;
    }

    public void setPersonCategory(int personCategory) {
        PersonCategory = personCategory;
    }


    public List<PollsAssignment> getPollsAssignments() {
        return pollsAssignments;
    }

    public void setPollsAssignments(List<PollsAssignment> pollsAssignments) {
        this.pollsAssignments = pollsAssignments;
    }


    public TextRelation getTextRelation() {
        return textRelation;
    }

    public void setTextRelation(TextRelation textRelation) {
        this.textRelation = textRelation;
    }



    @Override
    public String toString() {
        return "QuestionaryGroup{" +
                "Id=" + Id +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                ", Active=" + Active +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", PersonCategory='" + PersonCategory + '\'' +
                ", pollsAssignments=" + pollsAssignments +
                ", textrelation=" + textRelation +
                '}';
    }
}
