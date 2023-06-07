package apirestful.iawebbackend.model;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class EventDTO {
        private Long id;
        private String name;
        private String description;
        private Timestamp dateStartEvent;
        private Timestamp createDate;
        private String assignByUserId;
        private String userId;
}
