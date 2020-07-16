/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.IngredientCompatibility;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.MakeupRecommendation;
import my.app.skincarerecommender.repositories.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeupService {

    private final UnitOfWork unit;

    @Autowired
    public MakeupService(UnitOfWork unit) {
        this.unit = unit;

    }

    public List<Brand> findAllBrands() {
        return unit.findAllBrands();
    }

    public List<Product> findProductsByBrandidAndCategory(int id, String type) {
        return unit.findProductsByBrandidAndCategorytype(id, type);
    }

    public List<Coverage> getAllCoverages() {
        return unit.getAllCoverages();
    }

    public List<Finish> getAllFinishes() {
        return unit.getAllFinishes();
    }

    public List<Product> calculateFoundationMatch(PreferenceEntity mp) {
        Category c = unit.findCategoryByType("Primer");
//        c.setCategorytype("Primer");
        Product product = unit.findProductById(mp.getProduct().getItemnumber());
        Base base = product.getBase();
        mp.setCoverage(unit.findCoverageByType("n/a"));
        List<Product> primerMatches;

//        List<Attribute> attributes = unit.findAllAttributes();
//        List<Attribute> chosenAttributes = new ArrayList<>();
//        int[] guestAttributes = mp.getAttributes();
//
//        for (int i = 0; i < guestAttributes.length - 1; i++) {
//            for (Attribute a : attributes) {
//                if (a.getAttributeid() == guestAttributes[i]) {
//                    chosenAttributes.add(a);
//                }
//            }
//        }
//
//        Category cat = unit.findCategoryByType(c.getCategorytype());
        if (!mp.getAttributes().isEmpty()) {
            if (mp.isCrueltyfree()) {
                primerMatches = unit.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(mp.getAttributes(), c, mp.getCoverage(), mp.getFinish(), base, mp.getSkintype());

            } else {
                primerMatches = unit.findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(mp.getAttributes(), c, mp.getCoverage(), mp.getFinish(), base, mp.getSkintype());
            }
        } else {
            if (mp.isCrueltyfree()) {
                primerMatches = unit.findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(c, mp.getCoverage(), mp.getFinish(), base, mp.getSkintype());
            } else {
                primerMatches = unit.findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(c, mp.getCoverage(), mp.getFinish(), base, mp.getSkintype());
            }

        }

        return primerMatches;
    }

    public PreferenceEntity savePreference(MakeupPreference pe) {
        return unit.savePreference(pe);
    }

    public PreferenceEntity getPreferenceById(int id) {
        return unit.findPreferenceById(id);
    }

    public List<MakeupRecommendation> calculateFullMatch(MakeupPreference mp) {

        PreferenceEntity pe = new PreferenceEntity();
        List<Product> foundations = new ArrayList<>();
        List<Product> primers = new ArrayList<>();
        Category primer = unit.findCategoryByType("Primer");
        Category foundation = unit.findCategoryByType("Foundation");

        if (mp.getA().length != 0) {
            if (mp.isCrueltyfree()) {
                foundations = unit.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(mp.getA(), foundation, mp.getCoverage(), mp.getFinish(), mp.getSkintype());
                primers = unit.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(mp.getA(), primer, mp.getFinish(), mp.getSkintype());

            } else {
                foundations = unit.findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(mp.getA(), foundation, mp.getCoverage(), mp.getFinish(), mp.getSkintype());
                primers = unit.findProductsBasedOnAttributesAndCategoryAndFinish(mp.getA(), primer, mp.getFinish(), mp.getSkintype());

            }
        } else {
            if (mp.isCrueltyfree()) {
                foundations = unit.findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(foundation, mp.getCoverage(), mp.getFinish(), mp.getSkintype());
                primers = unit.findCrueltyFreeProductsBasedOnCategoryAndFinish(primer, mp.getFinish(), mp.getSkintype());

            } else {
                foundations = unit.findProductsBasedOnCategoryAndCoverageAndFinish(foundation, mp.getCoverage(), mp.getFinish(), mp.getSkintype());
                primers = unit.findProductsBasedOnCategoryAndFinish(primer, mp.getFinish(), mp.getSkintype());

            }

        }

//        List<Product> foundationOil = new ArrayList<>();
//        List<Product> foundationWater = new ArrayList<>();
//        List<Product> foundationSilicone = new ArrayList<>();
//        List<Product> primerOil = new ArrayList<>();
//        List<Product> primerWater = new ArrayList<>();
//        List<Product> primerSilicone = new ArrayList<>();
//
//        for (Product f : foundations) {
//            if (f.getBase().getBasetype().equals("Oil")) {
//                foundationOil.add(f);
//            } else if (f.getBase().getBasetype().equals("Water")) {
//                foundationWater.add(f);
//            } else if (f.getBase().getBasetype().equals("Silicone")) {
//                foundationSilicone.add(f);
//            }
//        }
//
//        for (Product p : primers) {
//            if (p.getBase().getBasetype().equals("Oil")) {
//                primerOil.add(p);
//            } else if (p.getBase().getBasetype().equals("Water")) {
//                primerWater.add(p);
//            } else if (p.getBase().getBasetype().equals("Silicone")) {
//                primerSilicone.add(p);
//            }
//        }
        for (Product f : foundations) {
            List<Ingredient> ii = new ArrayList<>();
            for (Ingredient i : f.getIngredients()) {

                List<IngredientCompatibility> o = unit.findCompatibilityList(i.getIngredientid());
                if (!o.isEmpty()) {
                    ii.add(i);

                }
            }
            f.setIngredients(ii);

        }
        for (Product p : primers) {
            List<Ingredient> ii = new ArrayList<>();
            for (Ingredient i : p.getIngredients()) {

                List<IngredientCompatibility> o = unit.findCompatibilityList(i.getIngredientid());
                if (!o.isEmpty()) {
                    ii.add(i);

                }
            }
            p.setIngredients(ii);

        }

        HashMap<String, Integer> ratings = new HashMap<>();

        List<IngredientCompatibility> compats = unit.findAllIngredientCompat();

        for (IngredientCompatibility ic : compats) {
            String id = ic.getIngredient1().getIngredientid() + "-" + ic.getIngredient2().getIngredientid();
            ratings.put(id, ic.getRating());
        }

        HashMap<String, MakeupRecommendation> recs = new HashMap<>();
        int w = 0;

        for (Product f : foundations) {
            for (Product p : primers) {
                for (Ingredient i : f.getIngredients()) {
                    for (Ingredient l : p.getIngredients()) {
                        String iv = i.getIngredientid() + "-" + l.getIngredientid();
//                                String il = i.getIngredientid() + "-" + l.getIngredientid();
//                                String lv = l.getIngredientid() + "-" + v.getIngredientid();
//                                IngredientCompatibility q = unit.findCompatibility(i, v);
//
//                                IngredientCompatibility t = unit.findCompatibility(i, l);
//                                IngredientCompatibility y = unit.findCompatibility(v, l);
//                                int rating = 0;
                        Integer q = ratings.get(iv);
//                                Integer t = ratings.get(il);
//                                Integer y = ratings.get(lv);
                        int qr = 0;
//                                int tr = 0;
//                                int yr = 0;
                        if (q != null) {
                            qr = q;
                        }
                        w = qr;
//                                if (t != null) {
//                                    tr = t;
//                                }
//                                if (y != null) {
//                                    yr = y;
//                                }
//                                w += (tr + yr + qr);
                    }
                }
                if (f.getBase().getBaseid() == p.getBase().getBaseid()) {
                    MakeupRecommendation mr = new MakeupRecommendation();
                    mr.setFoundation(f);
                    mr.setPrimer(p);
                    mr.setCompatibility(w);
                    recs.put(mr.getFoundation().getItemnumber() + "_" + mr.getPrimer().getItemnumber() ,mr);
                }
                w = 0;
            }
        }
        List<MakeupRecommendation> finalrecs = new ArrayList<>();
        for(HashMap.Entry<String, MakeupRecommendation> entry : recs.entrySet()){
            finalrecs.add(entry.getValue());
        }
        Random r = new Random();
        finalrecs = finalrecs.stream()
                .sorted((a, b) -> r.nextInt(11) - 5).limit(50).collect(Collectors.toList());

//        Collections.sort(recs, (sr2, sr1) -> sr1.getCompatibility() - sr2.getCompatibility());
//        List<MakeupRecommendation> finalrecs = new ArrayList<>();
//        int q = 0;
//        if (recs.size() < 50) {
//            q = recs.size();
//        } else {
//            q = 50;
//        }
//        for (int i = 0; i < q; i++) {
//            finalrecs.add(recs.get(i));
//        }
        return finalrecs;
    }

    public Brand findBrandByName(String name) {
        return unit.findBrandByName(name);
    }

    public SkinType findSkinTypeByType(String type) {
        return unit.findSkinTypeByType(type);
    }

    public Coverage findCoverageByType(String type) {
        return unit.findCoverageByType(type);
    }

    public Finish findFinishByType(String type) {
        return unit.findFinishByType(type);
    }

    public Attribute findAttributeByName(String name) {
        return unit.findAttributeByName(name);
    }

    public Concern findConcernByType(String type) {
        return unit.findConcernByType(type);
    }

    public Base findBaseByType(String type) {
        return unit.findBaseByType(type);
    }

    public Category findCategoryByType(String type) {
        return unit.findCategoryByType(type);
    }

    public List<Brand> findBrandsByCategory(Category c) {
        return unit.findBrandsByCategory(c);
    }
}
