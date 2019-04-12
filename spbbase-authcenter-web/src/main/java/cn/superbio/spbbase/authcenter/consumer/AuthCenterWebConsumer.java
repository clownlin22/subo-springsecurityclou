package cn.superbio.spbbase.authcenter.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: lxy
 * @Date: 2019/3/12 13:36
 */
@FeignClient("SPBBASE-AUTHCENTER-WEB")
public interface AuthCenterWebConsumer {

    @PostMapping(path = "/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);


}
