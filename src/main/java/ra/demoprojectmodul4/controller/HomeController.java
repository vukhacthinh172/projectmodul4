package ra.demoprojectmodul4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.demoprojectmodul4.model.Customer;
import ra.demoprojectmodul4.model.Product;
import ra.demoprojectmodul4.service.ICustomerService;
import ra.demoprojectmodul4.service.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/index")
@RequiredArgsConstructor
public class HomeController {
    private final IProductService iProductService;

    private final ICustomerService iCustomerService;

    @RequestMapping()
    String product(@RequestParam(required = false) String search, Model model) {
        if (search != null) {
            List<Product> list = iProductService.findProductByProductNameContaining(search);
            model.addAttribute("products", list);
            model.addAttribute("search", search);
        } else {

                List<Product> activeProducts = iProductService.findAllOrderBy().stream()
                        .filter(Product::isStatus)  // Lọc các sản phẩm có trạng thái là true
                        .collect(Collectors.toList());

                model.addAttribute("products", activeProducts);
            }

            return "/user/index";
        }

//    @GetMapping()
//    public String showIndex(Model model , Integer id) {
//        if (id != null) {
//            // Thực hiện logic để lấy thông tin khách hàng
//            Customer customer = iCustomerService.findById(id).orElse(null);
//            // Thêm thông tin khách hàng vào mô hình
//            model.addAttribute("customer", customer);
//        }
//        // Chuyển hướng đến trang index
//        return "/user/index";
//    }
    }
