package com.myblog.web.admin;

import com.myblog.field.User;
import com.myblog.service.UserService;
import com.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminControl {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        User user = userService.userCheck(username, MD5Utils.code(password));
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

//    @RequestMapping("/login")
//    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("message",msg);
//        // 此方法不处理登录成功,由shiro进行处理
//        return "/admin/login";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.users();
    }

}
