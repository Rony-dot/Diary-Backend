package com.rony.notepadbackend.entities;

import com.rony.notepadbackend.dtos.request.RoleDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {

//    @Id
////    private String id = UUID.randomUUID().toString();
//    private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Column(unique = true, nullable = false)
    private String name;

//    public Role(RoleDto roleDto) {
//        this.setId(roleDto.getId() == null ? UUID.randomUUID().toString() : roleDto.getId());
//        this.setRole(roleDto.getRole());
//    }
}
