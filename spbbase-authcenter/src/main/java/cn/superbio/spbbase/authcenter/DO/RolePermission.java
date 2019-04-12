package cn.superbio.spbbase.authcenter.DO;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Author: lxy
 * @Date: 2019/3/4 10:34
 */
@TableName("auth_role_permission")
public class RolePermission {

    private Long id;
    private Long roleId;
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
