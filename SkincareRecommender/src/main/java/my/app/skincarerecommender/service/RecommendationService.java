/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.IngredientCompatibility;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.repositories.UnitOfWork;
import my.app.skincarerecommender.models.Preference;
import my.app.skincarerecommender.models.SimpleRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final UnitOfWork unit;

    @Autowired
    public RecommendationService(UnitOfWork unit) {
        this.unit = unit;
    }

    public List<SimpleRecommendation> calculateGuestSimpleRecommendations(Preference p) {

//        Guest g = setGuestPreferences(p);
        List<Attribute> attributes = unit.findAllAttributes();
        List<Attribute> chosenAttributes = new ArrayList<>();
        int[] guestAttributes = p.getA();
        if (guestAttributes != null) {
            for (int i = 0; i < guestAttributes.length; i++) {
                for (Attribute a : attributes) {
                    if (a.getAttributeid() == guestAttributes[i]) {
                        chosenAttributes.add(a);
                    }
                }
            }
        }

        List<Concern> concerns = new ArrayList<>();
        int[] guestConcerns = p.getC();
        if (guestConcerns != null) {
            for (int z = 0; z < guestConcerns.length; z++) {
                concerns.add(unit.findConcernById(guestConcerns[z]));
            }
        }

        //is it possible to dry this up??
        List<Product> productsRec = new ArrayList<>();

        if (!chosenAttributes.isEmpty()) {
            if (!concerns.isEmpty()) {
                if (p.isCrueltyfree()) {
                    productsRec = unit.findCrueltyFreeProductsBasedOnAttributesAndConcerns(chosenAttributes, concerns, p.getSkintype());

                } else {
                    productsRec = unit.findProductsBasedOnAttributesAndConcerns(chosenAttributes, concerns, p.getSkintype());
                }
            } else {
                if (p.isCrueltyfree()) {
                    productsRec = unit.findCrueltyFreeProductsBasedOnAttributes(chosenAttributes, p.getSkintype());
                } else {
                    productsRec = unit.findProductsNotInAttributes(chosenAttributes, p.getSkintype());
                }
            }
        } else {
            if (!concerns.isEmpty()) {
                if (p.isCrueltyfree()) {
                    unit.findCrueltyFreeProductsByConcern(concerns, p.getSkintype());
                } else {
                    unit.findProductsByConcern(concerns, p.getSkintype());
                }
            }
            if (p.isCrueltyfree()) {
                unit.findCrueltyFreeProductsBySkinType(p.getSkintype());
            } else {
                productsRec = unit.findProductsBySkintype(p.getSkintype());
            }
        }

        List<SimpleRecommendation> recs = calculateSimpleMatches(productsRec);

        return recs;
    }

    private List<SimpleRecommendation> calculateSimpleMatches(List<Product> prods) {
        //or three separate lists of recommendations??
        List<Product> cleansers = new ArrayList<>();
        List<Product> moisturizers = new ArrayList<>();
        List<Product> eyecreams = new ArrayList<>();

        for (Product p : prods) {
            if (p.getCategory().getCategorytype().equals("Cleanser")) {
                cleansers.add(p);
            } else if (p.getCategory().getCategorytype().equals("Moisturizer")) {
                moisturizers.add(p);
            } else if (p.getCategory().getCategorytype().equals("Eye Cream")) {
                eyecreams.add(p);
            }
        }

        for (Product h : cleansers) {
            List<Ingredient> ii = new ArrayList<>();
            for (Ingredient i : h.getIngredients()) {

                List<IngredientCompatibility> o = unit.findCompatibilityList(i.getIngredientid());
                if (!o.isEmpty()) {
                    ii.add(i);

                }
            }
            h.setIngredients(ii);

        }

//
        for (Product h : moisturizers) {
            List<Ingredient> ii = new ArrayList<>();
            for (Ingredient i : h.getIngredients()) {

                List<IngredientCompatibility> o = unit.findCompatibilityList(i.getIngredientid());
                if (!o.isEmpty()) {
                    ii.add(i);

                }
            }
            h.setIngredients(ii);

        }
        for (Product h : eyecreams) {
            List<Ingredient> ii = new ArrayList<>();
            for (Ingredient i : h.getIngredients()) {

                List<IngredientCompatibility> o = unit.findCompatibilityList(i.getIngredientid());
                if (!o.isEmpty()) {
                    ii.add(i);

                }
            }
            h.setIngredients(ii);

        }

        HashMap<String, Integer> ratings = new HashMap<>();

        List<IngredientCompatibility> compats = unit.findAllIngredientCompat();

        for (IngredientCompatibility ic : compats) {
            String id = ic.getIngredient1().getIngredientid() + "-" + ic.getIngredient2().getIngredientid();
            ratings.put(id, ic.getRating());
        }

        HashMap<String, SimpleRecommendation> tmprecs = new HashMap<>();
        int w = 0;
        for (Product b : cleansers) {
            for (Product d : moisturizers) {
                for (Product e : eyecreams) {
                    for (Ingredient i : b.getIngredients()) {
                        for (Ingredient v : d.getIngredients()) {
                            for (Ingredient l : e.getIngredients()) {
                                String iv = i.getIngredientid() + "-" + v.getIngredientid();
                                String il = i.getIngredientid() + "-" + l.getIngredientid();
                                String lv = l.getIngredientid() + "-" + v.getIngredientid();
//                                IngredientCompatibility q = unit.findCompatibility(i, v);
//
//                                IngredientCompatibility t = unit.findCompatibility(i, l);
//                                IngredientCompatibility y = unit.findCompatibility(v, l);
//                                int rating = 0;
                                Integer q = ratings.get(iv);
                                Integer t = ratings.get(il);
                                Integer y = ratings.get(lv);
                                int qr = 0;
                                int tr = 0;
                                int yr = 0;
                                if (q != null) {
                                    qr = q;
                                }
                                if (t != null) {
                                    tr = t;
                                }
                                if (y != null) {
                                    yr = y;
                                }
                                w += (tr + yr + qr);

                            }

                        }
                    }
                    SimpleRecommendation sr = new SimpleRecommendation();
                    sr.setCleanser(b);
                    sr.setMoisturizer(d);
                    sr.setEyecream(e);
                    sr.setCompatibility(w);
                    tmprecs.put(sr.getCleanser().getItemnumber() + "_" + sr.getMoisturizer().getItemnumber() + "_" + sr.getEyecream().getItemnumber(), sr);
                    w = 0;
                }
            }
        }
        List<SimpleRecommendation> recs = new ArrayList<>();
        for (HashMap.Entry<String, SimpleRecommendation> entry : tmprecs.entrySet()) {
            recs.add(entry.getValue());
        }

        Collections.sort(recs, (sr2, sr1) -> sr1.getCompatibility() - sr2.getCompatibility());
        List<SimpleRecommendation> finalrecs = new ArrayList<>();
        int q = 0;
        if (recs.size() < 50) {
            q = recs.size();
        } else {
            q = 50;
        }
        for (int i = 0; i < q; i++) {
            finalrecs.add(recs.get(i));
        }
//        List<SimpleRecommendation> finalRecs = new ArrayList<>();
//
//        List<Product> oilbasedcleansers = new ArrayList<>();
//        List<Product> waterbasedcleansers = new ArrayList<>();
//        List<Product> siliconebasedcleansers = new ArrayList<>();
//
//        for (Product c : cleansers) {
//            if (c.getBase().getBasetype().equals("Oil")) {
//                oilbasedcleansers.add(c);
//            } else if (c.getBase().getBasetype().equals("Water")) {
//                waterbasedcleansers.add(c);
//            }
//            if (c.getBase().getBasetype().equals("Silicone")) {
//                siliconebasedcleansers.add(c);
//            }
//        }
//
//        List<Product> oilbasedmoisturizers = new ArrayList<>();
//        List<Product> waterbasedmoisturizers = new ArrayList<>();
//        List<Product> siliconebasedmoisturizers = new ArrayList<>();
//
//        for (Product m : moisturizers) {
//            if (m.getBase().getBasetype().equals("Oil")) {
//                oilbasedmoisturizers.add(m);
//            } else if (m.getBase().getBasetype().equals("Water")) {
//                waterbasedmoisturizers.add(m);
//            }
//            if (m.getBase().getBasetype().equals("Silicone")) {
//                siliconebasedmoisturizers.add(m);
//            }
//        }
//
//        List<Product> oilbasedeyecreams = new ArrayList<>();
//        List<Product> waterbasedeyecreams = new ArrayList<>();
//        List<Product> siliconebasedeyecreams = new ArrayList<>();
//
//        for (Product e : eyecreams) {
//            if (e.getBase().getBasetype().equals("Oil")) {
//                oilbasedeyecreams.add(e);
//            } else if (e.getBase().getBasetype().equals("Water")) {
//                waterbasedeyecreams.add(e);
//            }
//            if (e.getBase().getBasetype().equals("Silicone")) {
//                siliconebasedeyecreams.add(e);
        return finalrecs;
    }
}

//        List<SimpleRecommendation> oilrecs = calculateProductCompatibility(oilbasedcleansers, oilbasedmoisturizers, oilbasedeyecreams);
//        List<SimpleRecommendation> waterrecs = calculateProductCompatibility(waterbasedcleansers, waterbasedmoisturizers, waterbasedeyecreams);
//        List<SimpleRecommendation> siliconerecs = calculateProductCompatibility(siliconebasedcleansers, siliconebasedmoisturizers, siliconebasedeyecreams);
//        List<SimpleRecommendation> recs = findBestRecs(oilrecs, waterrecs, siliconerecs);
//    private List<SimpleRecommendation> calculateProductCompatibility(List<Product> c, List<Product> m, List<Product> e) {
//calculate based on ingredient compatibility and set compatibility score for each recommendation.
//        List<SimpleRecommendation> recs = new ArrayList<>();
//
//        for (Product moist : m) {
//            SimpleRecommendation simple = new SimpleRecommendation();
//            simple.setMoisturizer(moist);
//            recs.add(simple);
//        }
//        List<Product> bhaahacleanse = new ArrayList<>();
////      List<Product> ahacleanse = new ArrayList<>();
////        List<Product> bpcleanse = new ArrayList<>();
//        List<Product> retinolcleanse = new ArrayList<>();
//        List<Product> vitccleanse = new ArrayList<>();
//
//        for (Product cleanse : c) {
//            for (Ingredient i : cleanse.getIngredients()) {
//                if (i.getIngredientname().equalsIgnoreCase("Salicylic acid")
//                        || i.getIngredientname().equalsIgnoreCase("BHA")
//                        || i.getIngredientname().contains("Salicylate")
//                        || i.getIngredientname().contains("salicylate")
//                        || i.getIngredientname().equalsIgnoreCase("AHA")
//                        || i.getIngredientname().equalsIgnoreCase("Glycolic acid")
//                        || i.getIngredientname().equalsIgnoreCase("Lactic Acid")
//                        || i.getIngredientname().equalsIgnoreCase("Willow bark extract")
//                        || i.getIngredientname().equalsIgnoreCase("beta hydroxylbutanoic acid")
//                        || i.getIngredientname().equalsIgnoreCase("tropic acid")
//                        || i.getIngredientname().equalsIgnoreCase("trethocanic acid")) {
//                    bhaahacleanse.add(cleanse);
////                }else if(i.getIngredientname().equalsIgnoreCase("AHA")
////                        || i.getIngredientname().equalsIgnoreCase("Glycolic acid")
////                        || i.getIngredientname().equalsIgnoreCase("Lactic Acid")){
////                    bhaahacleanse.add(cleanse);
////                } else if (i.getIngredientname().equalsIgnoreCase("Benzoyl peroxide")) {
////                    bpcleanse.add(cleanse);
//                } else if (i.getIngredientname().equalsIgnoreCase("Retinol") || i.getIngredientname().equalsIgnoreCase("retinyl acetate")
//                        || i.getIngredientname().equalsIgnoreCase("retinyl palmitate") || i.getIngredientname().equalsIgnoreCase("retinaldehyde")
//                        || i.getIngredientname().equalsIgnoreCase("propionic acid") || i.getIngredientname().equalsIgnoreCase("retinoid")) {
//                    retinolcleanse.add(cleanse);
//                } else if (i.getIngredientname().equalsIgnoreCase("Vitamin C") || i.getIngredientname().equalsIgnoreCase("Sodium Acorbyl Phosphate")
//                        || i.getIngredientname().equalsIgnoreCase("Ascorbyl palmitate") || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl palmitate")
//                        || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl phosphate") || i.getIngredientname().equalsIgnoreCase("Calium ascorat")
//                        || i.getIngredientname().equalsIgnoreCase("Sodium ascorbate")) {
//                    vitccleanse.add(cleanse);
//                }
//            }
//        }
//
//        List<Product> bhaahacompatmoist = new ArrayList<>();
////      List<Product> ahacompatmoist = new ArrayList<>();
////        List<Product> bpcompatmoist = new ArrayList<>();
//        List<Product> retinolcompatmoist = new ArrayList<>();
//        List<Product> vitccompatmoist = new ArrayList<>();
//
//        for (Product moist : m) {
//            for (Ingredient i : moist.getIngredients()) {
//                if (i.getIngredientname().contains("hyaluronic acid")
//                        || i.getIngredientname().equalsIgnoreCase("sodium hyaluronate")) {
//                    bhaahacompatmoist.add(moist);
//                    retinolcompatmoist.add(moist);
////                    nest another if statement that looks for bad matches to calculate compatibility
//                }
//                if (i.getIngredientname().equalsIgnoreCase("licorice")
//                        || i.getIngredientname().equalsIgnoreCase("liquorice")
//                        || i.getIngredientname().equalsIgnoreCase("sweet root")
//                        || i.getIngredientname().equalsIgnoreCase("glycyrrhiza glabra")
//                        || i.getIngredientname().equalsIgnoreCase("turmuric")
//                        || i.getIngredientname().equalsIgnoreCase("maracuja oil")
//                        || i.getIngredientname().equalsIgnoreCase("calendula extract")
//                        || i.getIngredientname().equalsIgnoreCase("senna seed extract")
//                        || i.getIngredientname().equalsIgnoreCase("blue tansy")
//                        || i.getIngredientname().equalsIgnoreCase("white willow bark")
//                        || i.getIngredientname().equalsIgnoreCase("chamomile")
//                        || i.getIngredientname().equalsIgnoreCase("tamanu oil")
//                        || i.getIngredientname().equalsIgnoreCase("prickly pear seed oil")
//                        || i.getIngredientname().contains("aloe")
//                        || i.getIngredientname().equalsIgnoreCase("witch hazel")
//                        || i.getIngredientname().contains("Green tea")
//                        || i.getIngredientname().equalsIgnoreCase("Camellia sinesis")
//                        || i.getIngredientname().equalsIgnoreCase("resveratrol")) {
//                    if (!bhaahacompatmoist.contains(moist)) {
//                        bhaahacompatmoist.add(moist);
//                    }
//                }
//                if (i.getIngredientname().equalsIgnoreCase("glycerin")) {
//                    if (!retinolcompatmoist.contains(moist)) {
//                        retinolcompatmoist.add(moist);
//                    }
//                }
//                if (i.getIngredientname().equalsIgnoreCase("glutathione")
//                        || i.getIngredientname().equalsIgnoreCase("ferulic acid")
//                        || i.getIngredientname().contains("copper")
//                        || i.getIngredientname().equalsIgnoreCase("palmitoyl pentapeptide")
//                        || i.getIngredientname().contains("rice")
//                        || i.getIngredientname().contains("soy")
//                        || i.getIngredientname().equalsIgnoreCase("phytopeptide")
//                        || i.getIngredientname().equalsIgnoreCase("soy amino acids")
//                        || i.getIngredientname().equalsIgnoreCase("glycine soja")) {
//                    vitccompatmoist.add(moist);
//                }
//            }
//        }
//
//        //vit c products
//        List<SimpleRecommendation> vitcrecs = setProductsIntoRecommendation(vitccleanse, vitccompatmoist, e);
//
//        //retinol products
//        List<SimpleRecommendation> retinolrecs = setProductsIntoRecommendation(retinolcleanse, retinolcompatmoist, e);
//
//        //bhaaha products
//        List<SimpleRecommendation> bhaaharecs = setProductsIntoRecommendation(retinolcleanse, bhaahacompatmoist, e);
//
//        List<SimpleRecommendation> recs = setRecommendations(vitcrecs, bhaaharecs, retinolrecs);
//        List<Product> eyevitc = new ArrayList<>();
//        List<Product> 
//
//        for (Product eye : e) {
//            for (Ingredient i : eye.getIngredients()) {
//                if (i.getIngredientname().equalsIgnoreCase("Vitamin C") || i.getIngredientname().equalsIgnoreCase("Sodium Acorbyl Phosphate")
//                        || i.getIngredientname().equalsIgnoreCase("Ascorbyl palmitate") || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl palmitate")
//                        || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl phosphate") || i.getIngredientname().equalsIgnoreCase("Calium ascorat")
//                        || i.getIngredientname().equalsIgnoreCase("Sodium ascorbate")) {
//                    if (!eyevitc.contains(eye)) {
//
//                        eyevitc.add(eye);
//                    }
//                }
//            }
//        }
//        for(SimpleRecommendation sr : recs){
//            for(Ingredient i : sr.getMoisturizer().getIngredients()){
//               for(Product eye : e){
//                   if(i.getIngredientname().equalsIgnoreCase("Vitamin C") || i.getIngredientname().equalsIgnoreCase("Sodium Acorbyl Phosphate")
//                           || i.getIngredientname().equalsIgnoreCase("Ascorbyl palmitate") || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl palmitate")
//                           || i.getIngredientname().equalsIgnoreCase("Magnesium ascorbyl phosphate") || i.getIngredientname().equalsIgnoreCase("Calium ascorat")
//                           || i.getIngredientname().equalsIgnoreCase("Sodium ascorbate")){
//                       if(eye.getIngredients().contains(glutathione) || eye.getIngredients().contains(palmitoyl) ){
//                           sr.setEyecream(eye);
//                   }
//                   
//               }
//            }
//        }
//
//        
//    
//        }
//        return recs;
//    }
//
//    private List<SimpleRecommendation> setProductsIntoRecommendation(List<Product> cleanse, List<Product> moist, List<Product> eye) {
//        List<SimpleRecommendation> subrecs = new ArrayList<>();
//        int b = 0;
//        int l = 0;
//        int q = 0;
//        while (b < cleanse.size() - 1) {
//            SimpleRecommendation s = new SimpleRecommendation();
//
//            for (; b < cleanse.size() - 1; b++) {
//                s.setCleanser(cleanse.get(b));
//                break;
//            }
//            for (; l < moist.size() - 1; l++) {
//                s.setMoisturizer(moist.get(l));
//                break;
//            }
//            for (; q < eye.size() - 1; q++) {
//                s.setEyecream(eye.get(q));
//                break;
//            }
//            subrecs.add(s);
//
//        }
//        return subrecs;
//    }
//    private List<SimpleRecommendation> setRecommendations(List<SimpleRecommendation> vitcrecs, List<SimpleRecommendation> bhaaharecs, List<SimpleRecommendation> retinolrecs) {
////        List<SimpleRecommendation> recs = new ArrayList<>();
////        int w = 0;
////        int t = 0;
////        int u = 0;
////        while (w < vitcrecs.size() - 1) {
////            SimpleRecommendation s = new SimpleRecommendation();
////
////            for (; w < vitcrecs.size() - 1; w++) {
////                recs.add(vitcrecs.get(w));
////                break;
////            }
////            for (; t < bhaaharecs.size() - 1; t++) {
////                recs.add(bhaaharecs.get(t));
////                break;
////            }
////            for (; u < retinolrecs.size() - 1; u++) {
////                recs.add(retinolrecs.get(u));
////                break;
////            }
////            bhaaharecs.add(s);
////
////        }
////        return recs;
//    }
//    private List<SimpleRecommendation> findBestRecs(List<SimpleRecommendation> o, List<SimpleRecommendation> w, List<SimpleRecommendation> s) {
//        //calculate based on each simplerecommendation compatibility score.
////        List<SimpleRecommendation> bestrecs = new ArrayList<>();
////        //check each set for bad combo and add to compatibility score. the lower the score the better the compatibility.
//////        List<SimpleRecommendation> os = calculateCompatibility(o);
//////        List<SimpleRecommendation> ws = calculateCompatibility(w);
//////        List<SimpleRecommendation> ss = calculateCompatibility(s);
////        return bestrecs;
//    }
//    private List<SimpleRecommendation> calculateCompatibility(List<SimpleRecommendation> r){
////        List<SimpleRecommendation> calculated = new ArrayList<>();
////        return calculated;
//    }
//    private Guest setGuestPreferences(Preference p) {
//        Guest g = new Guest();
//        g.getPreference().setSkintype(p.getSkintype());
//
//        List<Concern> concernList = new ArrayList<>();
//
//        List<Concern> concerns = unit.findAllConcerns();
//        int[] guestConcerns = p.getC();
//        for (int i = 0; i < guestConcerns.length - 1; i++) {
//            for (Concern c : concerns) {
//                if (c.getConcernid() == guestConcerns[i]) {
//                    concernList.add(c);
//                }
//            }
//        }
//
//        if (!concernList.isEmpty()) {
//            g.getPreference().setConcerns(concernList);
//        }
//
//        return g;
//    }
//}
