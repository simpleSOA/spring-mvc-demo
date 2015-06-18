package com.hengtu.weixin.controller;

import com.hengtu.weixin.service.WeixinSignatureService;
import com.hengtu.weixin.util.EncryptUtil;
import com.hengtu.weixin.vo.WeixinTicket;
import com.hengtu.weixin.vo.WeixinToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeixinSignatureController {

    @Autowired
    private WeixinSignatureService service;


    @RequestMapping(value = "/qianming")
    public void getSignature(){
        WeixinToken token = service.getSignatrue();
        System.out.println(token);
        WeixinTicket weixinTicket = service.getByWeixinToken(token);
        System.out.println(weixinTicket);
        String str = "jspai_ticket="+weixinTicket.getTicket()+"&noncestr=1234567890abc&timestamp="+System.currentTimeMillis()/1000+
                "&url=http://mp.weixin.qq.com?param=fotor.img.com";
        System.out.println(EncryptUtil.SHA1(str));

    }
}
