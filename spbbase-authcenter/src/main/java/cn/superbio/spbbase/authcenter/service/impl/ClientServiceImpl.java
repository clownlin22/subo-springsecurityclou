package cn.superbio.spbbase.authcenter.service.impl;

import cn.superbio.spbbase.authcenter.DO.Client;
import cn.superbio.spbbase.authcenter.mapper.ClientMapper;
import cn.superbio.spbbase.authcenter.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: lxy
 * @Date: 2019/2/28 10:14
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
}
