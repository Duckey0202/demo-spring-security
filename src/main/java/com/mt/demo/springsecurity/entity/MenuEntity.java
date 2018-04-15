package com.mt.demo.springsecurity.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "nb_memu")
@JsonIgnoreProperties(value = {"roles", "hibernateLazyInitializer"})
public class MenuEntity extends BaseEntity<MenuEntity> {

    @NotNull(message = "menu_title not null")
    @Length(min = 1, max = 32, message = "menu title size between (1, 32)!!")
    @Column(name = "menu_title", length = 32)
    protected String menuTitle;

    @Length(max = 32, message = "menu_value max 32!!")
    @Column(name = "menu_value", length = 32)
    protected String menuValue;

    @Column(name = "menu_type")
    protected int menuType;

    @Column(name = "menu_level")
    protected int menuLevel;

    @Column(name = "menu_order")
    protected int menuOrder;

    @Column(name = "menu_parent_id")
    protected Long menuParentId;

    @NotNull(message = "menu_path not null")
    @Length(min = 1, max = 255, message = "menu_path size between (1, 254)!!")
    @Column(name = "menu_path", length = 255)
    protected String menuPath;

    @Length(max = 64, message = "menu_component max 64!!")
    @Column(name = "menu_component", length = 64)
    protected String menuComponent;

    @Length(max = 64, message = "menu_icon_cls  max 64!!")
    @Column(name = "menu_icon_cls", length = 64)
    protected String menuIconCls;
    @Column(name = "menu_keep_alive", length = 1)
    protected boolean menuKeepAlive;
    @Column(name = "menu_require_auth", length = 1)
    protected boolean menuRequireAuth;

    @JSONField(serialize = false)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "nb_memu_roles", joinColumns = {@JoinColumn(name = "menu_entity_id")}, inverseJoinColumns =
            {@JoinColumn(name = "roles_id")})
    protected List<RoleEntity> roles;
}
