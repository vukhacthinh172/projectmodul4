package ra.demoprojectmodul4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.service.ICustomerService;
import ra.demoprojectmodul4.serviceimpl.CustomerServiceImpl;

import java.util.List;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private CustomerServiceImpl customerService;
    @RequestMapping
    String customer(@RequestParam(required = false) String search , Model model){
        if (search!=null){
            List<Customer> list = iCustomerService.findByKeyword(search);
            model.addAttribute("customers",list);
            model.addAttribute("search",search);
        }else {
            model.addAttribute("customers",iCustomerService.findAll());
        }
        return "/admin/customer";
    }
    @RequestMapping("/blockUnblock")
    public String blockUnblock(@RequestParam Integer customerId){
        if (customerId!=null) {
            customerService.blockUnblockCustomer(customerId);
        }
       return "redirect:/admin/customer";
    }

}
