package apirestful.iawebbackend.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class UserRelationsPK implements Serializable {

    private static final long serialVersionUID = 4024864844238355109L;
    @NotNull
    @Column(name = "IdNavision",length = 50)
    private String IdNavision;

    @NotNull
    @Column(name = "IdNavision2",length = 50)
    private String IdNavision2;
}
