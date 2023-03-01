package kk.soft.co.jp.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kk.soft.co.jp.sys.model.MessageInfoMstModel;
import kk.soft.co.jp.sys.service.MessageInfoMstService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/messageInfoMst")
@Slf4j
public class MainController {

    @Resource
    private MessageInfoMstService messageInfoMstService;

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @PostMapping("/signin")
    public String signin(MessageInfoMstModel messageInfoMstModel, Model model){

        messageInfoMstService.insert(messageInfoMstModel);

        model.addAttribute("msg","Home");

       return "home";
    }
    @PostMapping("/signup")
    public String signup(MessageInfoMstModel messageInfoMstModel, Model model){

        messageInfoMstService.insert(messageInfoMstModel);

        model.addAttribute("msg","登録完了");

       return "signin";
    }

}
