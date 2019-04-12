package cn.superbio.spbbase.authcenter.service.impl;


import cn.superbio.spbbase.authcenter.DO.AppUser;
import cn.superbio.spbbase.authcenter.mapper.AppUserMapper;
import cn.superbio.spbbase.authcenter.service.AppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {


}
