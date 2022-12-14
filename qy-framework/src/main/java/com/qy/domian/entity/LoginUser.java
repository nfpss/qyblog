package com.qy.domian.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/14 17:20
 **/
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -7627553945731641123L;

    private UserDO userDO;

    private List<String> permList;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(UserDO userDO, List<String> permList) {
        this.userDO = userDO;
        this.permList = permList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = permList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
