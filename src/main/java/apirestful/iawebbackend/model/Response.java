package apirestful.iawebbackend.model;


import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "Response")
public class Response {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "text_value")
    private String text_Value;

    @Column(name = "integer_value",nullable = true)
    private int integer_Value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_relation_id")
    private TextRelation textRelation;

    public Response() {
    }

    public Response(int id, String text_Value, int integer_Value, PollsAssignment pollassignment, TextRelation textRelation) {
        Id = id;
        this.text_Value = text_Value;
        this.integer_Value = integer_Value;
        this.textRelation = textRelation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getText_Value() {
        return text_Value;
    }

    public void setText_Value(String text_Value) {
        this.text_Value = text_Value;
    }

    public int getInteger_Value() {
        return integer_Value;
    }

    public void setInteger_Value(int integer_Value) {
        this.integer_Value = integer_Value;
    }



    public TextRelation getTextRelation() {
        return textRelation;
    }

    public void setTextRelation(TextRelation textRelation) {
        this.textRelation = textRelation;
    }

    @Override
    public String toString() {
        return "Response{" +
                "Id=" + Id +
                ", text_Value='" + text_Value + '\'' +
                ", integer_Value=" + integer_Value +
                '}';
    }
}
