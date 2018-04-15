package com.mt.demo.springsecurity.entity;

import lombok.Data;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * MenuView
 *
 * @author MT.LUO
 * 2018/1/23 14:57
 * @Description:
 */
@Data
@Entity
@Subselect("SELECT nb_memu.id,nb_memu.menu_component, nb_memu.menu_icon_cls, nb_memu.menu_keep_alive, nb_memu" +
        ".menu_level, nb_memu.menu_order, nb_memu.menu_parent_id, nb_memu.menu_path, nb_memu.menu_require_auth, " +
        "nb_memu.menu_title, nb_memu.menu_type, nb_memu.menu_value, nb_user.user_name FROM nb_user , nb_user_roles, " +
        "nb_memu_roles, nb_memu WHERE nb_user.id = nb_user_roles.user_entity_id AND nb_user_roles.roles_id = " +
        "nb_memu_roles.roles_id AND  nb_memu_roles.menu_entity_id = nb_memu.id AND nb_memu.deleted = 0")
@Synchronize({"nb_memu", "nb_user"})
public class MenuView {
    @Id
    private Long id;

    @Column(name = "user_name")
    protected String userName;

    @Column(name = "menu_title")
    protected String menuTitle;

    @Column(name = "menu_value")
    protected String menuValue;

    @Column(name = "menu_type")
    protected int menuType;

    @Column(name = "menu_level")
    protected int menuLevel;

    @Column(name = "menu_order")
    protected int menuOrder;

    @Column(name = "menu_parent_id")
    protected Long menuParentId;

    @Column(name = "menu_path")
    protected String menuPath;

    @Column(name = "menu_component")
    protected String menuComponent;

    @Column(name = "menu_icon_cls")
    protected String menuIconCls;
    @Column(name = "menu_keep_alive")
    protected boolean menuKeepAlive;
    @Column(name = "menu_require_auth")
    protected boolean menuRequireAuth;
}
