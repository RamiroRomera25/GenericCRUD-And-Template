package rami.generic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import rami.generic.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class TaskEntity {

    private UUID id;

    private String toDo;

    private TaskStatus status;

    private LocalDateTime createDate;

    private LocalDate estimatedDueDate;

    private Boolean isActive;
}
