package acc.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SystemController {

    @GetMapping("/system")
    public String system(HttpServletRequest request){
        return "/system";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "/accessDenied";
    }
}
