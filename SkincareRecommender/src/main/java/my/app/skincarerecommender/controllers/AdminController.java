/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import javax.validation.Valid;
import my.app.skincarerecommender.models.GuestModel;
import my.app.skincarerecommender.models.ProductModel;
import my.app.skincarerecommender.service.AdminService;
import my.app.skincarerecommender.service.MakeupService;
import my.app.skincarerecommender.service.SkincareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final AdminService adminService;
    private final SkincareService skincareService;
    private final MakeupService makeupService;
    
    @Autowired
    public AdminController(AdminService adminService, SkincareService skincareService,
            MakeupService makeupService){
        this.adminService = adminService;
        this.skincareService = skincareService;
        this.makeupService = makeupService;
    }
    
    @GetMapping("/admin")
    public String displayAdmin(Model model){
        return "admin-home";
    }
    
    @GetMapping("/admin/viewGuests")
    public String displayGuests(Model model){
        model.addAttribute("guests", adminService.findAllGuests());
        
        return "view-guestlist";
    }
    
    @GetMapping("/editGuest/{id}")
    public String updateAdminStatus(@PathVariable String id, Model model, GuestModel guest){       
        model.addAttribute("guest", adminService.findGuestById(id));
        return "edit-guest";
    }
    
    @PostMapping("/editGuest/{id}")
    public String updateAdminStatus(Model model, @PathVariable String id, boolean administrator){
//        model.addAttribute("guest", adminService.findGuestById(id));
//        if(br.hasErrors()){
//            return "edit-guest";
//        }
        
       adminService.updateAdminById(id, administrator);
        
        return "redirect:/";
    }
    
    @GetMapping("/addProduct")
    public String addProduct(Model model, ProductModel pm){
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("finishes", makeupService.getAllFinishes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("concerns", skincareService.getAllConcerns());
        model.addAttribute("categories", skincareService.getAllCategories());
        
        return "add-product";
    }
    
    @PostMapping("/addProduct")
    public String addProduct(@Valid ProductModel pm, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("finishes", makeupService.getAllFinishes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("concerns", skincareService.getAllConcerns());
        model.addAttribute("categories", skincareService.getAllCategories());
            return "add-product";
        }else {
            adminService.saveProduct(pm);
        }
        return "redirect:/";
    }
}
