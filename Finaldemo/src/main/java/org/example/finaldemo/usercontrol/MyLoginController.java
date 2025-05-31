package org.example.finaldemo.usercontrol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyLoginController {

    @RequestMapping("login_page")  //显示给用户看到的的登录地址
    public String toLogin(){
        return "config_login";
    }  //跳转到实际的网页地址。
}