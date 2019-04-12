package cn.superbio.spbbase.authcenter.controller;


import cn.superbio.spbbase.authcenter.consumer.AuthCenterConsumer;
import cn.superbio.spbbase.authcenter.consumer.AuthCenterWebConsumer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxy
 * @Date: 2019/2/22 10:19
 */
@Api(tags = "登录接口")
@RequestMapping("/api/authcenter")
@RestController
public class AuthCenterController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private AuthCenterConsumer authCenterConsumer;
    @Autowired
    private AuthCenterWebConsumer authCenterWebConsumer;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private TokenStore tokenStore;

    @ApiOperation(value = "根据用户查询权限角色", notes = "根据用户查询权限角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", dataType = "string", value = "用户名", required = true),
    })
    @GetMapping(value = "/findAuthoritiesByUsername", params = "username")
    public List<String> findAuthoritiesByUsername(String username) {
        List<String> authorities = authCenterConsumer.findAuthoritiesByUsername(username);
        if (authorities.size() == 0) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        return authorities;
    }

    //@ApiOperation(value = "注销登录", notes = "注销登录")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "token", dataType = "string", value = "token令牌", required = true),
    //})
    //@GetMapping(value = "/reToken", params = "token")
    //public boolean reToken(String token) {
    //    OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
    //    if (accessToken == null) {
    //        return false;
    //    } else {
    //        this.tokenStore.removeAccessToken(accessToken);
    //        return true;
    //    }
    //}

    @Autowired
    private ConsumerTokenServices tokenServices;
    @ApiOperation(value = "注销登录", notes = "注销登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", dataType = "string", value = "token令牌", required = true),
            @ApiImplicitParam(name = "access_token", dataType = "string", value = "token令牌", required = true),
    })
    @GetMapping("/removetoken")
    public void logout(String access_token, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(access_token)) {
            if (StringUtils.isNoneBlank(token)) {
                access_token = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
        tokenServices.revokeToken(access_token);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", dataType = "string", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", dataType = "string", value = "密码", required = true),
    })
    @GetMapping("/sys/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, "system");
        parameters.put("client_secret", "system");
        parameters.put(OAuth2Utils.SCOPE, "app");
        parameters.put("username", username);
        parameters.put("password", password);

        Map<String, Object> tokenInfo = authCenterWebConsumer.postAccessToken(parameters);

        return tokenInfo;
    }




}
