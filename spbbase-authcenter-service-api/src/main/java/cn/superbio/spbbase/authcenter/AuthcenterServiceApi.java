package cn.superbio.spbbase.authcenter;

import cn.superbio.spbbase.authcenter.entity.LoginAppUserEntity;
import cn.superbio.spbbase.authcenter.entity.LoginClientEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lxy
 * @Date: 2019/2/22 10:09
 */
@Api(tags = "登录接口")
@RequestMapping("/service/api/authcenter")
public interface AuthcenterServiceApi {

    @ApiOperation(value = "登录接口", notes = "登录接口")
    @GetMapping(value = "findByUsername")
    LoginAppUserEntity findByUsername(@RequestParam("username") String username);

    @ApiOperation(value = "登录接口", notes = "登录接口")
    @GetMapping(value = "findAuthoritiesByUsername")
    List<String> findAuthoritiesByUsername(@RequestParam("username") String username);

    @ApiOperation(value = "client接口", notes = "client接口")
    @GetMapping(value = "findByClientId")
    LoginClientEntity findByClientId(@RequestParam("clientId") String clientId);


}
