package org.example.finaldemo.Controller;

import org.example.finaldemo.Entity.Manage;
import org.example.finaldemo.Service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageController {
    @Autowired
    private ManageService manageService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/showStuForm")
    public String showStuForm(Model model) {
        Manage stu = new Manage();
        model.addAttribute("stu", stu);
        return "new_stu";
    }

    @PostMapping("/saveStu")
    public String saveStu(@ModelAttribute("stu") Manage stu) {
        manageService.saveStu(stu);
        //重定向回到根路径即主页index.html
        return "redirect:/";
    }

    @GetMapping("/showStuForUpdate/{id}")
    //@PathVariable用于获取路径参数
    public String showStuForUpdate(@PathVariable(value = "id") long id, Model model) {
        Manage stu = manageService.getStuById(id);
        model.addAttribute("stu", stu);
        return "update_stu";
    }

    @GetMapping("/deleteStu/{id}")
    public String deleteStu(@PathVariable(value = "id") long id) {
        this.manageService.deleteStuById(id);
        return "redirect:/";
    }

    @GetMapping("/query{newName}")
    public String query(@PathVariable(value = "newName") String newName, Model model) {

        List<Manage> listStuNumber = manageService.findByName(newName);
        model.addAttribute("listStu", listStuNumber);
        return "index";
    }

    //获取分页数据
    @GetMapping("/page/{pageNo}")
    //pageNo页面编号 sortField排序字段 sortDir排序方向
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 3;

        Page<Manage> page = manageService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Manage> listStu = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listStu", listStu);
        return "index";
    }

    @RequestMapping("HomeStuSearch")
    public String StuSearch() {
        return "HomeSearch";
    }

    @GetMapping("/Home/query{newName}")
    public String Stuquery(@PathVariable(value = "newName") String newName, Model model) {

        List<Manage> listStuNumber = manageService.findByName(newName);
        model.addAttribute("listStu", listStuNumber);
        return "HomeSearch";
    }
}

