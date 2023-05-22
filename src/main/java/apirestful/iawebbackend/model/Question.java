package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.soap.Text;
import java.util.List;

@Entity
@Table(name = "Question")
public class Question {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "text")
    private String text;


    @Column(name = "type",length = 5)
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<TextRelation> textRelation;

    public Question() {
    }

    public Question(Long id, String text, String type, List<TextRelation> textRelation) {
        Id = id;
        this.text = text;
        this.type = type;
        this.textRelation = textRelation;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TextRelation> getTextRelation() {
        return textRelation;
    }

    public void setTextRelation(List<TextRelation> textRelation) {
        this.textRelation = textRelation;
    }

    @Override
    public String toString() {
        return "Question{" +
                "Id=" + Id +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
