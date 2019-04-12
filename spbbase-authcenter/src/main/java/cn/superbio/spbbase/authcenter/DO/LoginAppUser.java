package cn.superbio.spbbase.authcenter.DO;

import java.util.List;

/**
 * spring security当前登录对象
 * @author lxy
 */

public class LoginAppUser extends AppUser {


    private List<SysPermission> sysPermissions;
    private List<SysRole> sysRoles;

    public List<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
