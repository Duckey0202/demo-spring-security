package com.mt.demo.springsecurity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * RoleToMenuEntity
 *
 * @author MT.LUO
 * 2018/3/29 0:20
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "nb_memu_roles")
public class RoleToMenuEntity  implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;
    @NotNull(message = "stream_id not null!!")
    @Column(name = "menu_entity_id")
    protected Long menuEntityId;

    @NotNull(message = "gstream_id not null!!")
    @Column(name = "roles_id")
    protected Long rolesId;
}
