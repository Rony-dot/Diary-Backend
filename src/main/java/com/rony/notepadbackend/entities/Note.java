package com.rony.notepadbackend.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tbl_DiaryBook")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseModel implements Serializable {

/*
   @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    */


    @NotBlank(message = "title cannot be null")
    @Size(min = 3, max = 35, message
            = "Title must be between 3 and 30 characters")
    @Column
    private String title;

    @NotBlank(message = "body cannot be null")
    @Size(min = 3, message
            = "body at least 3 characters")
    @Column
    private String body;

    @Column
    private String imagePath;
}
