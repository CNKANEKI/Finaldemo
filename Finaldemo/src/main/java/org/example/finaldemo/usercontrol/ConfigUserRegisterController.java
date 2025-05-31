package org.example.finaldemo.usercontrol;
import org.example.finaldemo.Entity.Myuser;
import org.example.finaldemo.Service.MyuserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class ConfigUserRegisterController {
    public int pageNo;
    public String sortField;
    public String sortDir;
    /**
     * 服务对象
     */
    @Resource
    private MyuserService tablemyuserService;

    @Resource
    private MyuserService tableruseroleservice;

    //注册页面
    @GetMapping("/add")
    public String showTableMyuserForm(Model model) {

        Myuser user = new Myuser();
        model.addAttribute("user", user);
        return "config_register";
    }

    //这个会在提交保存按钮的时候执行。
    @PostMapping("/adduser")
    public String saveTableMyuser(@ModelAttribute("user") Myuser user) {


        this.tablemyuserService.saveMyuser(user);

        return "redirect:/"; //注册后跳转到主页
    }
}