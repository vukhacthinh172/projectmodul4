package ra.demoprojectmodul4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.demoprojectmodul4.dto.CustomerDto;
import ra.demoprojectmodul4.dto.ProductDto;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.model.Product;
import ra.demoprojectmodul4.service.ICustomerService;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final ICustomerService customerService;

    @GetMapping()
    public String registerForm(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "/admin/register";
    }

    @PostMapping
    public String save(@ModelAttribute("customerDto") CustomerDto customerDto) {
        try {
            customerService.save(customerDto);
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:/register?email_invalid";
        }
        return "redirect:/login?success";
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String doRegister(@ModelAttribute @Valid CustomerDto customerDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//        customerService.save(customerDto);
//        return "redirect:/";
//    }
//@PostMapping
//public String save(@ModelAttribute @Valid CustomerDto customerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//    if (bindingResult.hasErrors()) {
//        return "/admin/register";
//    }
//
//    try {
//        customerService.save(customerDto);
//        redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please login.");
//        return "redirect:/login";
//    } catch (Exception e) {
//        redirectAttributes.addFlashAttribute("errorMessage", "Email is Already Registered!");
//        return "redirect:/register?email_invalid";
//    }
//}
}
