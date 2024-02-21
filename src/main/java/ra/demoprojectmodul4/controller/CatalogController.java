package ra.demoprojectmodul4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.demoprojectmodul4.model.Catalog;
import ra.demoprojectmodul4.service.ICatalogService;
import ra.demoprojectmodul4.serviceimpl.ICatalogServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final ICatalogService iCatalogService;
    private final ICatalogServiceImpl catalogService;

    @RequestMapping
    String customer(@RequestParam(required = false) String search, Model model) {
        if (search != null) {
            List<Catalog> list = iCatalogService.findByCatalogNameContains(search);
            model.addAttribute("catalog", list);
            model.addAttribute("search", search);
        } else {
            List<Catalog> activeCatalogs = iCatalogService.findAll();
            model.addAttribute("catalog", activeCatalogs);

        }
        return "/admin/catalog";
    }
    @RequestMapping("/hide")
    public String hideShowCatalog(@RequestParam Integer catalogId, Model model) {
        catalogService.hideShowCatalogByCatalogId(catalogId);
        model.addAttribute("successMessage", "Catalog hidden successfully!");
        return "redirect:/admin/catalog";
    }
    @RequestMapping("/form")
    public ModelAndView form(@RequestParam(name = "catalogId", required = false) Integer id) {
        Catalog catalog = new Catalog();

        if (id != null) {
            catalog = iCatalogService.findById(id);
            if (catalog == null) {
                throw new RuntimeException("Không có quyền truy cập ");
            }
        }
        ModelAndView modelAndView = new ModelAndView("/admin/catalogedit-add");
        modelAndView.addObject("catalog", catalog);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("catalog") Catalog catalog) {
        catalogService.save(catalog);
        return "redirect:/admin/catalog";
    }

}
