package com.mt.demo.springsecurity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "nb_user")
public class UserEntity extends BaseEntity<UserEntity> {

    @NotNull(message = "username not null")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "用户名为中文，英文，数字")
    @Length(min = 3, max = 32, message = "username size between (3, 32)")
    @Column(name = "user_name", length = 32)
    protected String userName;

    @Length(max = 60, message = "password max 60")
    @Column(name = "user_password", length = 60)
    protected String userPassword;

    @Column(name = "user_phone", length = 16)
    @Length(max = 16, message = "phone max 16")
    protected String userPhone;
    @Column(name = "user_remark", length = 128)
    @Length(max = 128, message = "remark max 128")
    protected String userRemark;

    @ManyToMany(fetch = FetchType.EAGER)
    protected List<RoleEntity> roles;

}
