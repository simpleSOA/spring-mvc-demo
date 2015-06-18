package com.hengtu.weixin.controller;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.sts.model.v20150401.GetFederationTokenResponse;
import com.hengtu.weixin.service.AliyunStsService;
import com.hengtu.weixin.vo.AliyunStsCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AliyunStsController {


    @RequestMapping(value = "/sts/getcredential")
    @ResponseBody
    public AliyunStsCredentials getCredentials() throws ClientException {
        String name = "hellosts";
        long duration = 3600;
        ProtocolType protocolType = ProtocolType.HTTPS;
        GetFederationTokenResponse response = AliyunStsService.getFederationToken(name, duration, protocolType);
        AliyunStsCredentials credentials = new AliyunStsCredentials();
        credentials.setAccessKeyId(response.getCredentials().getAccessKeySecret());
        credentials.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
        credentials.setExpiration(response.getCredentials().getExpiration());
        credentials.setSecurityToken(response.getCredentials().getSecurityToken());
        return credentials;
    }
}
