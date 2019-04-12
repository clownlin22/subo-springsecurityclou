package cn.superbio.spbbase.authcenter.VO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * spring security当前登录对象
 * @author lxy
 */

public class LoginAppUserVO extends AppUserVO implements UserDetails,Serializable {

    private static final long serialVersionUID = -5233090323123184241L;
    private List<SysPermissionVO> sysPermissions;
    private List<SysRoleVO> sysRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        if (!CollectionUtils.isEmpty(sysRoles)) {
            sysRoles.forEach(role -> {
                if (role.getCode().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role.getCode()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
                }
            });
        }

        if (!CollectionUtils.isEmpty(sysPermissions)) {
            sysPermissions.forEach(per -> {
                collection.add(new SimpleGrantedAuthority(per.getCode()));
            });
        }
        return collection;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<SysPermissionVO> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermissionVO> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public List<SysRoleVO> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRoleVO> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
