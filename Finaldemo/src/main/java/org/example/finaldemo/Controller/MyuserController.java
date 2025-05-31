package org.example.finaldemo.Controller;
import org.example.finaldemo.Entity.Myuser;
import org.example.finaldemo.Service.MyuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("myuser")
public class MyuserController {

    @Autowired
    private MyuserService myuserService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Myuser> userList = myuserService.getAllMyuser();
        model.addAttribute("MyuserList", userList);
        return "myuser_index";
    }

    @PostMapping("/saveuser")
    public String saveTableMyuser(@ModelAttribute("user") Myuser user) {


        this.myuserService.saveMyuser(user);

        return "redirect:/myuser/"; //注册后跳转到主页
    }

    @GetMapping("/showUserForUpdate/{id}")
    //@PathVariable用于获取路径参数
    public String showUserForUpdate(@PathVariable(value = "id") long id, Model model) {
        Myuser user = myuserService.getMyuserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        this.myuserService.deleteMyuserById(id);
        return "redirect:/myuser/";
    }

}