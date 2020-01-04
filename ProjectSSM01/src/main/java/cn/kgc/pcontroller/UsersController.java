package cn.kgc.pcontroller;

import cn.kgc.domain.Users;
import cn.kgc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller(value = "usersController2")
@RequestMapping(value = "/page/")  //指定请求前缀
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 注册查询用户名是否重名
     * @param uname
     * @return
     */
    @RequestMapping("checkUname")
    @ResponseBody
    public String checkUname(String uname){
        boolean res = usersService.findUname(uname);
        return "{\"result\":"+res+"}";
    }

    /**
     * 注册
     * @param users
     * @return
     */
    @RequestMapping("addUsers")
    public String addUsers(Users users){
        int i = usersService.addUsers(users);
        if (i > 0){
            return "redirect:login.jsp";
        }else{
            return "redirect:regs.jsp";
        }

    }


    /**
     * 登录
     * @param name,password
     * @return
     */
    @RequestMapping("login")
    public String login(String name, String password, HttpSession session){
        Users login = usersService.login(name, password);
        System.out.println(login);
        System.out.println(name + "====" + password);
        if (login == null){
            return "redirect:login.jsp";
        }else{
            //只要登录。就要用cookie或者session保存登录用户信息
            session.setAttribute("loginInfo",login);
            //设置有效期
            session.setMaxInactiveInterval(600);

            return "redirect:showHouse";
        }
    }
}
