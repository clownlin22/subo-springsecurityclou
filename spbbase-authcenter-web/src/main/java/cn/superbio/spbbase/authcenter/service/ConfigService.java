package cn.superbio.spbbase.authcenter.service;

import cn.superbio.spbbase.authcenter.VO.LoginAppUserVO;
import cn.superbio.spbbase.authcenter.VO.LoginClientVO;
import cn.superbio.spbbase.authcenter.consumer.AuthCenterConsumer;
import cn.superbio.spbbase.authcenter.entity.LoginAppUserEntity;
import cn.superbio.spbbase.authcenter.entity.LoginClientEntity;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @Author: lxy
 * @Date: 2019/3/4 12:52
 */
@Service
public class ConfigService implements UserDetailsService,ClientDetailsService {

    @Autowired
    private AuthCenterConsumer authCenterConsumer;
    @Autowired
    private Mapper dozerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginAppUserEntity loginAppUser = authCenterConsumer.findByUsername(username);
        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        return  dozerMapper.map(loginAppUser, LoginAppUserVO.class);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        LoginClientEntity byClientId = authCenterConsumer.findByClientId(clientId);
        if(byClientId == null){
            throw new AuthenticationCredentialsNotFoundException("连接失败");
        }
        return dozerMapper.map(byClientId, LoginClientVO.class);
    }

}
