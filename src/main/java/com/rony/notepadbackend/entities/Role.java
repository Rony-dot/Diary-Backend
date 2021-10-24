package com.rony.notepadbackend.entities;

import com.rony.notepadbackend.dtos.request.RoleDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String role;

    public Role(RoleDto roleDto) {
        this.setId(roleDto.getId() == null ? UUID.randomUUID().toString() : roleDto.getId());
        this.setRole(roleDto.getRole());
    }
}
