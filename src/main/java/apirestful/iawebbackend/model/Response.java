package apirestful.iawebbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "Response")
public class Response {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "text_value")
    private String text_Value;

    @Column(name = "integer_value",nullable = true)
    private int integer_Value;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "text_relation_id")
    private TextRelation textRelation;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pollsAssignment_id")
    private PollsAssignment pollsAssignment;

    public Response() {
    }

    public Response(Long id, String text_Value, int integer_Value) {
        Id = id;
        this.text_Value = text_Value;
        this.integer_Value = integer_Value;
    }

    public Response(Long id, String text_Value, int integer_Value, Long id_text) {
        Id = id;
        this.text_Value = text_Value;
        this.integer_Value = integer_Value;
        id_text=this.textRelation.getRelationId();
    }
    public Response(int integer_Value,String text_Value, String tr_id, String pa_id ) {
        this.text_Value = text_Value;
        this.integer_Value = integer_Value;
        tr_id= String.valueOf(this.textRelation.getRelationId());
        pa_id=String.valueOf(this.pollsAssignment.getId());
    }
    public Response(Long id, String text_Value, int integer_Value,TextRelation textRelation, PollsAssignment pollsAssignment) {
        Id = id;
        this.text_Value = text_Value;
        this.integer_Value = integer_Value;
        this.textRelation = textRelation;
        this.pollsAssignment=pollsAssignment;
    }



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public PollsAssignment getPollsAssignment() {
        return pollsAssignment;
    }

    public void setTextRelation(TextRelation textRelation) {
        this.textRelation = textRelation;
    }

    public void setPollsAssignment(PollsAssignment pollsAssignment) {
        this.pollsAssignment = pollsAssignment;
    }



    @Override
    public String toString() {
        return "Response{" +
                "Id=" + Id +
                ", text_Value='" + text_Value + '\'' +
                ", integer_Value=" + integer_Value +
                ", text_Relation=" + textRelation +
                '}';
    }
}
