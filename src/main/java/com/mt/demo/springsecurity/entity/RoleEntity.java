package com.mt.demo.springsecurity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "nb_role")
public class RoleEntity extends BaseEntity<RoleEntity> {

    @NotNull(message = "role name not null")
    @Length(min = 1, max = 16, message = "role name too long!!!")
    @Column(name = "role_name", length = 16)
    protected String roleName;

    @Transient
    protected List<MenuEntity> menuEntities;

}
