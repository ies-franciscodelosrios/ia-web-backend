package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UsersRelations")
public class UsersRelations  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @EmbeddedId
    private UserRelationsPK userRelationsPK;

    @NotNull
    @Column(name = "IdNavisionIsPT")
    private Boolean IdNavisionIsPT;

    @NotNull
    @Column(name = "PersonCategory",length = 10)
    private String PersonCategory;

    @NotNull
    @Column(name = "IdNavision2Name",length = 100)
    private String IdNavision2Name;

    @NotNull
    @Column(name = "IdNavisionName",length = 100)
    private String IdNavisionName;

    @NotNull
    @Column(name = "IdNavision2Mail",length = 100)
    private String IdNavision2Mail;

    @NotNull
    @Column(name = "IdNavisionMail",length = 100)
    private String IdNavisionMail;

    @NotNull
    @Column(name = "PersonCategory2",length = 10)
    private String PersonCategory2;

    @NotNull
    @Column(name = "IdNavision2IsPT",length = 100)
    private Boolean IdNavision2IsPT;

    @NotNull
    @Column(name = "RelationCreateDate")
    private Timestamp RelationCreateDate;

    @NotNull
    @Column(name = "Active")
    private Boolean Active;

    public UsersRelations(UserRelationsPK userRelationsPK, Boolean idNavisionIsPT, String personCategory, String idNavisionName, String idNavisionMail, String idNavision2Name, String idNavision2Mail, String personCategory2, Boolean idNavision2IsPT, Timestamp relationCreateDate, Boolean active) {
        super();
        this.userRelationsPK = userRelationsPK;
        IdNavisionIsPT = idNavisionIsPT;
        PersonCategory = personCategory;
        IdNavisionName = idNavisionName;
        IdNavisionMail = idNavisionMail;
        IdNavision2Name = idNavision2Name;
        IdNavision2Mail = idNavision2Mail;
        PersonCategory2 = personCategory2;
        IdNavision2IsPT = idNavision2IsPT;
        RelationCreateDate = relationCreateDate;
        Active = active;
    }

    public UsersRelations() {}

    public UserRelationsPK getUserRelationsPK() {
        return userRelationsPK;
    }

    public Boolean getIdNavisionIsPT() {
        return IdNavisionIsPT;
    }

    public void setIdNavisionIsPT(Boolean idNavisionIsPT) {
        IdNavisionIsPT = idNavisionIsPT;
    }

    public String getPersonCategory() {
        return PersonCategory;
    }

    public void setPersonCategory(String personCategory) {
        PersonCategory = personCategory;
    }

    public String getIdNavision2Name() {
        return IdNavision2Name;
    }

    public void setIdNavision2Name(String idNavision2Name) {
        IdNavision2Name = idNavision2Name;
    }

    public String getIdNavision2Mail() {
        return IdNavision2Mail;
    }

    public void setIdNavision2Mail(String idNavision2Mail) {
        IdNavision2Mail = idNavision2Mail;
    }

    public String getPersonCategory2() {
        return PersonCategory2;
    }

    public void setPersonCategory2(String personCategory2) {
        PersonCategory2 = personCategory2;
    }

    public Boolean getIdNavision2IsPT() {
        return IdNavision2IsPT;
    }

    public void setIdNavision2IsPT(Boolean idNavision2IsPT) {
        IdNavision2IsPT = idNavision2IsPT;
    }

    public Timestamp getRelationCreateDate() {
        return RelationCreateDate;
    }

    public void setRelationCreateDate(Timestamp relationCreateDate) {
        RelationCreateDate = relationCreateDate;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public void setUserRelationsPK(UserRelationsPK userRelationsPK) {
        this.userRelationsPK = userRelationsPK;
    }

    public String getIdNavisionName() {
        return IdNavisionName;
    }

    public void setIdNavisionName(String idNavisionName) {
        IdNavisionName = idNavisionName;
    }

    public String getIdNavisionMail() {
        return IdNavisionMail;
    }

    public void setIdNavisionMail(String idNavisionMail) {
        IdNavisionMail = idNavisionMail;
    }

    @Override
    public String toString() {
        return "UsersRelations{" +
                "userRelationsPK=" + userRelationsPK +
                ", IdNavisionIsPT=" + IdNavisionIsPT +
                ", PersonCategory='" + PersonCategory + '\'' +
                ", IdNavision2Name='" + IdNavision2Name + '\'' +
                ", IdNavisionName='" + IdNavisionName + '\'' +
                ", IdNavision2Mail='" + IdNavision2Mail + '\'' +
                ", IdNavisionMail='" + IdNavisionMail + '\'' +
                ", PersonCategory2='" + PersonCategory2 + '\'' +
                ", IdNavision2IsPT=" + IdNavision2IsPT +
                ", RelationCreateDate=" + RelationCreateDate +
                ", Active=" + Active +
                '}';
    }
}
