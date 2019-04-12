package cn.superbio.spbbase.authcenter.consumer;

import cn.superbio.spbbase.authcenter.AuthcenterServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: lxy
 * @Date: 2019/2/22 10:16
 */
@FeignClient("SPBBASE-AUTHCENTER")
public interface AuthCenterConsumer extends AuthcenterServiceApi {


}
