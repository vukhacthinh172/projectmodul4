package ra.demoprojectmodul4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ra.demoprojectmodul4.service.ICustomerService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final ICustomerService iCustomerService;
    @GetMapping("/login")
    public  String login(){
        return "admin/login";
    }
}
