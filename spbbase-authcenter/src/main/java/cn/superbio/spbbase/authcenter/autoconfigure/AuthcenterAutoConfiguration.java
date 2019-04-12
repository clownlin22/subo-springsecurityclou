package cn.superbio.spbbase.authcenter.autoconfigure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan( "cn.superbio.spbbase.authcenter.mapper*" )
@ComponentScan(basePackages = {
        "cn.superbio.spbbase.authcenter.provider",
        "cn.superbio.spbbase.authcenter.service"})
public class AuthcenterAutoConfiguration {
}
