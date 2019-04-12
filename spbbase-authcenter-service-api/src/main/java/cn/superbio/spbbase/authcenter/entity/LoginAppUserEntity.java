package cn.superbio.spbbase.authcenter.entity;
import java.util.List;

/**
 * spring security当前登录对象
 * @author lxy
 */

public class LoginAppUserEntity extends AppUserEntity  {

    private List<SysPermissionEntity> sysPermissions;
    private List<SysRoleEntity> sysRoles;

    public List<SysPermissionEntity> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermissionEntity> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public List<SysRoleEntity> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRoleEntity> sysRoles) {
        this.sysRoles = sysRoles;
    }
}
