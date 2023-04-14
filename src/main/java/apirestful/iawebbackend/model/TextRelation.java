package apirestful.iawebbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TextRelation")
public class TextRelation {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RelationId")
    private Long RelationId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionary_group_id")
    private QuestionaryGroup questionaryGroup;

    @OneToOne(mappedBy = "textRelation", cascade = CascadeType.ALL)
    private Response response;

    public TextRelation() {
    }

    public TextRelation(Long relationId,Question question, QuestionaryGroup questionaryGroup, Response response) {
        RelationId = relationId;
        this.question = question;
        this.questionaryGroup = questionaryGroup;
        this.response = response;
    }

    public Long getRelationId() {
        return RelationId;
    }

    public void setRelationId(Long relationId) {
        RelationId = relationId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionaryGroup getQuestionaryGroup() {
        return questionaryGroup;
    }

    public void setQuestionaryGroup(QuestionaryGroup questionaryGroup) {
        this.questionaryGroup = questionaryGroup;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "TextRelation{" +
                "RelationId=" + RelationId +
                ", question=" + question +
                ", response=" + response +
                '}';
    }
}

