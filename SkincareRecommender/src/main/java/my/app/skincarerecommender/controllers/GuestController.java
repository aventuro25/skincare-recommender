/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestMakeupSet;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.models.GuestModel;
import my.app.skincarerecommender.service.GuestService;
import my.app.skincarerecommender.service.MakeupService;
import my.app.skincarerecommender.service.SkincareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {

    private final GuestService guestService;
    private final SkincareService skincareService;
    private final MakeupService makeupService;

    public GuestController(GuestService guestService, SkincareService skincareService,
            MakeupService makeupService) {
        this.guestService = guestService;
        this.skincareService = skincareService;
        this.makeupService = makeupService;
    }

    @GetMapping("/signup")
    public String displaySignup(GuestModel g, Model model) {
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("concerns", skincareService.getAllConcerns());
        model.addAttribute("attributes", skincareService.getAllAttributes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("finishes", makeupService.getAllFinishes());
        return "sign-up";
    }

    @PostMapping("/signup")
    public String signup(@Valid GuestModel g, BindingResult br, Model model) {
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());
        model.addAttribute("concerns", skincareService.getAllConcerns());
        model.addAttribute("attributes", skincareService.getAllAttributes());
        model.addAttribute("coverages", makeupService.getAllCoverages());
        model.addAttribute("finishes", makeupService.getAllFinishes());

        if (br.hasErrors()) {
            return "sign-up";
        }

        List<FieldError> errors = guestService.addNewGuest(g);

        if (!errors.isEmpty()) {
            for (FieldError o : errors) {
                br.addError(o);
            }
            return "sign-up";
        }

//        guestService.addNewGuest(g);
        return "redirect:/";
    }

    @GetMapping("/login")
    private String displayLogin(GuestModel guest) {
        return "login";
    }

//    @PostMapping("/login")
//    private String submitLogin(){
//        return "redirect:/";
//    }
    @GetMapping("/pastRecommendations")
    public String displayPastRecs(Principal principal, Model model) {

        if (principal != null) {
            Guest g = guestService.findByUsername(principal.getName());

            if (g != null) {
                List<Product> recs = g.getProducts();
                List<GuestSkincareSet> sets = guestService.findSetsByUsername(g);
                List<GuestMakeupSet> makeup = guestService.findMakeupSetsByGuest(g);
                if (!recs.isEmpty() || !sets.isEmpty() || !makeup.isEmpty()) {
                    if (!recs.isEmpty()) {
                        model.addAttribute("guestProducts", recs);
                    }
                    if (!sets.isEmpty()) {
                        model.addAttribute("sets", sets);
                    }
                    if (!makeup.isEmpty()) {
                        model.addAttribute("makeup", makeup);
                    }

                    return "saved-recommendations";
                }
                if (recs.isEmpty() && sets.isEmpty() && makeup.isEmpty()) {
                    return "error/404";
                }

            } else {
                return "error/403";
            }
        }
            return "error/403";
        
    }

}
