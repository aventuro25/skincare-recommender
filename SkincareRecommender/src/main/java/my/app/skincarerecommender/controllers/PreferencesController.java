/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.controllers;

import java.security.Principal;
import java.util.List;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.models.Preference;
import my.app.skincarerecommender.models.SimpleRecommendation;
import my.app.skincarerecommender.service.GuestService;
import my.app.skincarerecommender.service.RecommendationService;
import my.app.skincarerecommender.service.SkincareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreferencesController {

    private final SkincareService skincareService;
    private final RecommendationService recService;
    private final GuestService guestService;

    @Autowired
    public PreferencesController(SkincareService skincareService,
            RecommendationService recService, GuestService guestService) {
        this.skincareService = skincareService;
        this.recService = recService;
        this.guestService = guestService;
    }

    @GetMapping("/")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/preferences/simple")
    public String displayPreferences(Model model, Principal p) {
        model.addAttribute("skintypes", skincareService.getAllSkinTypes());

        model.addAttribute("concerns", skincareService.getAllConcerns());
        model.addAttribute("attributes", skincareService.getAllAttributes());

        if (p != null) {
            Guest g = guestService.findByUsername(p.getName());
            PreferenceEntity pe = g.getPreference();
            if (pe != null) {
                Preference pr = guestService.convertGuestPreferenceToModel(pe);
                model.addAttribute("guestPreference", pr);
                return "custom-preference";
            }
        }

        return "preferences";
    }

    @PostMapping("/preferences/simple")
    public String displayResults(Preference p, Model model) {

        List<SimpleRecommendation> products = recService.calculateGuestSimpleRecommendations(p);

        model.addAttribute("recs", products);

        return "simple-results";
    }

    @PostMapping("/preferences/simple/save")
    public String saveResults(String[] savedRecs, Principal p) {
        if (p != null) {
            guestService.saveSimpleRecs(savedRecs, p);
        }
        return "redirect:/";
    }
}
