package cn.superbio.spbbase.authcenter.provider;


import cn.superbio.spbbase.authcenter.AuthcenterServiceApi;
import cn.superbio.spbbase.authcenter.DO.*;
import cn.superbio.spbbase.authcenter.entity.LoginAppUserEntity;
import cn.superbio.spbbase.authcenter.entity.LoginClientEntity;
import cn.superbio.spbbase.authcenter.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author lxy
 */
@RestController
public class AppUserController implements AuthcenterServiceApi {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private Mapper dozerMapper;

    @Override
    public LoginAppUserEntity findByUsername(String username) {
        QueryWrapper<AppUser> userQueryWrapperr = new QueryWrapper<>();
        userQueryWrapperr.eq("name", username);
        AppUser appUser = appUserService.getOne(userQueryWrapperr);

        if (appUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            //设置用户
            loginAppUser.setId(appUser.getId());
            loginAppUser.setName(appUser.getName());
            loginAppUser.setPassword(appUser.getPassword());

            //// 设置角色
            //根据id获取多个角色id
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", appUser.getId());
            List<UserRole> userRoles = userRoleService.list(userRoleQueryWrapper);
            if (userRoles.size() != 0) {
                //设置角色id
                List<Long> roleIds = new ArrayList<>();
                userRoles.forEach(userRole -> roleIds.add(userRole.getRoleId()));
                //获取角色
                List<SysRole> sysRoles = (List<SysRole>) sysRoleService.listByIds(roleIds);
                loginAppUser.setSysRoles(sysRoles);

                //// 设置权限
                //根据角色id获取多个权限id
                QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
                rolePermissionQueryWrapper.in("role_id", roleIds);
                List<RolePermission> rolePermissions = rolePermissionService.list(rolePermissionQueryWrapper);
                if (rolePermissions.size() != 0) {
                    //设置权限id
                    List<Long> permissionsIds = new ArrayList<>();
                    rolePermissions.forEach(rolePermission -> permissionsIds.add(rolePermission.getPermissionId()));
                    //获取权限
                    List<SysPermission> sysPermissions = (List<SysPermission>) sysPermissionService.listByIds(permissionsIds);
                    loginAppUser.setSysPermissions(sysPermissions);
                }
            }
            LoginAppUserEntity map = dozerMapper.map(loginAppUser, LoginAppUserEntity.class);
            return map;
        }
        return null;
    }

    @Override
    public List<String> findAuthoritiesByUsername(String username) {

        List<String> authorities = new ArrayList<>();
        //根据用户名查询id
        QueryWrapper<AppUser> userQueryWrapperr = new QueryWrapper<>();
        userQueryWrapperr.eq("name", username);
        AppUser appUser = appUserService.getOne(userQueryWrapperr);
        if (appUser != null) {
            //// 设置角色
            //根据id获取多个角色id
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("id", appUser.getId());
            List<UserRole> userRoles = userRoleService.list(userRoleQueryWrapper);
            if (userRoles.size() != 0) {
                //设置角色id
                List<Long> roleIds = new ArrayList<>();
                userRoles.forEach(userRole -> roleIds.add(userRole.getRoleId()));
                //获取角色
                List<SysRole> sysRoles = (List<SysRole>) sysRoleService.listByIds(roleIds);

                //// 设置权限
                //根据角色id获取多个权限id
                QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
                rolePermissionQueryWrapper.in("role_id", roleIds);
                List<RolePermission> rolePermissions = rolePermissionService.list(rolePermissionQueryWrapper);
                if (rolePermissions.size() != 0) {
                    //设置权限id
                    List<Long> permissionsIds = new ArrayList<>();
                    rolePermissions.forEach(rolePermission -> permissionsIds.add(rolePermission.getPermissionId()));
                    //获取权限
                    List<SysPermission> sysPermissions = (List<SysPermission>) sysPermissionService.listByIds(permissionsIds);
                    sysPermissions.forEach(ss -> authorities.add(ss.getCode()));
                }
            }
        }
        return authorities;
    }

    @Override
    public LoginClientEntity findByClientId(String clientId) {
        QueryWrapper<Client> clientQueryWrapper = new QueryWrapper<>();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        clientQueryWrapper.eq("client_id", clientId);
        Client client = clientService.getOne(clientQueryWrapper);

        //获取client
        LoginClient loginClient = new LoginClient();
        loginClient.setClientId(client.getClientId());
        loginClient.setResourceIds(stringSet(client.getResourceIds()));
        loginClient.setClientSecret(client.getClientSecret());
        loginClient.setScope(stringSet(client.getScope()));
        loginClient.setAuthorizedGrantTypes(stringSet(client.getAuthorizedGrantTypes()));
        loginClient.setRegisteredRedirectUri(stringSet(client.getWebServerRedirectUri()));
        loginClient.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        loginClient.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        loginClient.setAdditionalInformation(stringObjectHashMap);
        loginClient.setAutoapprove(client.getAutoapprove());

        return dozerMapper.map(loginClient, LoginClientEntity.class);
    }

    public Set<String> stringSet(String object){
        HashSet<String> strings = new HashSet<>();
        if(object!=null && !"".equals(object)){
            List<String> lists =  Arrays.asList(object.split(","));
            lists.forEach(list -> strings.add(list));
        }
        return strings;
    }


}
