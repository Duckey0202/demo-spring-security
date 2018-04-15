package com.mt.demo.springsecurity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * MenuTypeEntity
 *
 * @author MT.LUO
 * 2018/1/23 12:15
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "nb_memu_type")
public class MenuTypeEntity extends BaseEntity<MenuTypeEntity> {

    @NotNull(message = "menu_type_value not null")
    @Column(name = "menu_type_value")
    private int menuTypeValue;

    @NotNull(message = "menu_type_text not null")
    @Length(max = 64, message = "menu_type too long!!")
    @Column(name = "menu_type_text", length = 64)
    private String menuTypeText;
}
