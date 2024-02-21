package ra.demoprojectmodul4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.demoprojectmodul4.dto.ProductDto;
import ra.demoprojectmodul4.model.Product;
import ra.demoprojectmodul4.service.ICatalogService;
import ra.demoprojectmodul4.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;
    private  final ICatalogService iCatalogService;
  @RequestMapping()
  String product(@RequestParam(required = false) String search, Model model) {
      if (search != null) {
          List<Product> list = iProductService.findProductByProductNameContaining(search);
          model.addAttribute("products", list);
          model.addAttribute("search", search);
      } else {

          List<Product> activeProduct = iProductService.findAllOrderBy();
          model.addAttribute("products", activeProduct);

      }
      return "/admin/product";
  }
  @RequestMapping("/hide")
    public String hideShowProduct(@RequestParam Integer id , Model model){
      iProductService.hideShowProduct(id);
      model.addAttribute("successMessage", "Sản phẩm đã được ẩn thành công!");
      return "redirect:/admin/product";
  }
    @RequestMapping("/form")
    public ModelAndView form(@RequestParam(name = "productId", required = false) Integer id) {
        Product product = new Product();

        if (id != null) {
            product = iProductService.findById(id);
            if (product == null) {
                throw new RuntimeException("Không có quyền truy cập");
            }
        }

        ModelAndView modelAndView = new ModelAndView("/admin/productedit-add");
        modelAndView.addObject("product", product);
        modelAndView.addObject("catalog",iCatalogService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) {
        ProductDto dto = new ProductDto(product,file);
        iProductService.save(dto);
        return "redirect:/admin/product";
    }

}
