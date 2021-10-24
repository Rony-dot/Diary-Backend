package com.rony.notepadbackend.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

}
