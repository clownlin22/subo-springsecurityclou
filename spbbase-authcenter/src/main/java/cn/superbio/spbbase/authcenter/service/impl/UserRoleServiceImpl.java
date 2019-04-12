package cn.superbio.spbbase.authcenter.service.impl;

import cn.superbio.spbbase.authcenter.DO.UserRole;
import cn.superbio.spbbase.authcenter.mapper.UserRoleMapper;
import cn.superbio.spbbase.authcenter.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: lxy
 * @Date: 2019/3/4 10:40
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
