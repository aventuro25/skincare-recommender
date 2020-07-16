/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.security.Principal;
import java.util.List;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.Preference;
import my.app.skincarerecommender.service.GuestService;
import my.app.skincarerecommender.service.MakeupService;
import my.app.skincarerecommender.service.SkincareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MakeupController {

    private final MakeupService makeupService;
    private final SkincareService skincareService;
    private final GuestService guestService;

    @Autowired
    public MakeupController(MakeupService makeupService, SkincareService skincareService,GuestService guestService) {
        this.makeupService = makeupService;
        this.skincareService = skincareService;
        this.guestService = guestService;
    }

    @GetMapping("/foundation-preferences")
    public String displayBrands(Model model) {
        Category c = makeupService.findCategoryByType("Foundation");
        
        model.addAttribute("brands", makeupService.findBrandsByCategory(c));
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("attributes", skincareService.getAllAttributes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("finishes", makeupService.getAllFinishes());
        return "foundation-preferences";
    }

    @GetMapping("/foundation-preferences/{id}")
    public String displayPreferences(Model model, @PathVariable int id) {
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("attributes", skincareService.getAllAttributes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("finishes", makeupService.getAllFinishes());

        PreferenceEntity p = makeupService.getPreferenceById(id);
        

        List<Product> foundationrecs = makeupService.calculateFoundationMatch(p);

        model.addAttribute("recs", foundationrecs);
        return "easy";
    }
    
    @PostMapping("/foundation-preferences/{id}")
    public String saveProducts(@PathVariable int id, String[] itemnumbers, Principal principal){
        guestService.saveProducts(itemnumbers, principal);
        return "redirect:/";
    }

    @GetMapping("/preferences/makeup/full")
    public String fullMakeupRec(Model model, Principal p) {
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("finishes", makeupService.getAllFinishes());
        model.addAttribute("attributes", skincareService.getAllAttributes());
        
        if(p != null){
            Guest g = guestService.findByUsername(p.getName());
            PreferenceEntity pe = g.getPreference();
            if(pe != null){
            MakeupPreference mp = guestService.convertGuestPreferenceToMakeupModel(pe);
            model.addAttribute("guestPreference", mp);
            return "custom-makeup-preference";
            } 
        }
        
        return "makeup-preferences";
    }

    @PostMapping("/preferences/makeup/full")
    public String fullMakeupRec(MakeupPreference mp, Model model) {
        model.addAttribute("recs", makeupService.calculateFullMatch(mp));
        return "full-makeup";
    }
    
    @PostMapping("/preferences/makeup/full/save")
    public String saveFullMakeupRec(String[] savedRecs, Principal principal){
        if(principal != null){
            guestService.saveFullMakeupRecs(savedRecs, principal);
        }
        
        return "redirect:/";
    }
}
