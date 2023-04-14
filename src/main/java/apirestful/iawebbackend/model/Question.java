package apirestful.iawebbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.xml.soap.Text;

@Entity
@Table(name = "Question")
public class Question {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private boolean type;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private TextRelation textRelation;

    public Question() {
    }

    public Question(int id, String text, boolean type, TextRelation textRelation) {
        Id = id;
        this.text = text;
        this.type = type;
        this.textRelation = textRelation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public TextRelation getTextRelation() {
        return textRelation;
    }

    public void setTextRelation(TextRelation textRelation) {
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
