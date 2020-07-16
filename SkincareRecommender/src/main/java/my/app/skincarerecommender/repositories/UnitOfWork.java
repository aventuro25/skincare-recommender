/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Guest;
import my.app.skincarerecommender.entities.GuestMakeupSet;
import my.app.skincarerecommender.entities.GuestSkincareSet;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.IngredientCompatibility;
import my.app.skincarerecommender.entities.PreferenceEntity;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import my.app.skincarerecommender.models.GuestModel;
import my.app.skincarerecommender.models.MakeupPreference;
import my.app.skincarerecommender.models.Preference;
import my.app.skincarerecommender.models.ProductModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitOfWork {

    private final SkintypeRepository skinType;
    private final ConcernRepository concern;
    private final AttributeRepository attribute;
    private final ProductRepository product;
    private final ProductDao pdao;
    private final CoverageRepository covrepo;
    private final FinishRepository frepo;
    private final BrandRepository brepo;
    private final CategoryRepository crepo;
    private final PreferenceRepository prefrepo;
    private final GuestRepository grepo;
    private final IngredientRepository irepo;
    private final BaseRepository baserepo;
    private final IngredientCompatRepository icrepo;
    private final IngredientDao ingrepo;
    private final CoverageDao coverDao;
    private final ConcernDao concernDao;
    private final SkintypeDao sDao;
    private final BrandDao bdao;
    private final GuestSkincareSetRepository guestSkincareSet;
    private final GuestMakeupSetRepository guestMakeupSet;

    @Autowired
    public UnitOfWork(SkintypeRepository skinType, ConcernRepository concern,
            AttributeRepository attribute, ProductRepository product,
            ProductDao pdao, CoverageRepository covrepo, FinishRepository frepo,
            BrandRepository brepo, CategoryRepository crepo,
            PreferenceRepository prefrepo, GuestRepository grepo,
            IngredientRepository irepo, BaseRepository baserepo,
            IngredientCompatRepository icrepo, IngredientDao ingrepo,
            CoverageDao coverDao, ConcernDao concernDao, SkintypeDao sDao,
            BrandDao bdao, GuestSkincareSetRepository guestSkincareSet, GuestMakeupSetRepository guestMakeupSet) {
        this.skinType = skinType;
        this.concern = concern;
        this.attribute = attribute;
        this.product = product;
        this.pdao = pdao;
        this.covrepo = covrepo;
        this.frepo = frepo;
        this.brepo = brepo;
        this.crepo = crepo;
        this.prefrepo = prefrepo;
        this.grepo = grepo;
        this.irepo = irepo;
        this.baserepo = baserepo;
        this.icrepo = icrepo;
        this.ingrepo = ingrepo;
        this.coverDao = coverDao;
        this.concernDao = concernDao;
        this.sDao = sDao;
        this.bdao = bdao;
        this.guestSkincareSet = guestSkincareSet;
        this.guestMakeupSet = guestMakeupSet;
    }

    public List<SkinType> findAllSkinTypes() {
        return skinType.findAll();
    }

    public List<Concern> findAllConcerns() {
        return concern.findAll();
    }

    public List<Attribute> findAllAttributes() {
        return attribute.findAll();
    }

    public List<Product> findCrueltyFreeProductsBySkinType(SkinType s) {
        List<Product> recs = pdao.findProductsBySkintypeAndCrueltyFreeTrue(s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsBySkintype(SkinType skintype) {
        List<Product> recs = pdao.findProductsBySkintype(skintype);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndConcerns(List<Attribute> a, List<Concern> c, SkinType s) {
        List<Product> recs = pdao.findProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue(a, c, s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsBasedOnAttributesAndConcerns(List<Attribute> a, List<Concern> c, SkinType s) {
        List<Product> recs = pdao.findProductsBySkinTypeNotInAttributesAndInConcerns(a, c, s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findCrueltyFreeProductsBasedOnAttributes(List<Attribute> a, SkinType s) {
        List<Product> recs = pdao.findProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue(a, s);
        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsNotInAttributes(List<Attribute> a, SkinType s) {
        List<Product> recs = pdao.findProductsBySkinTypeNotInAttributes(a, s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsByConcern(List<Concern> c, SkinType s) {
        List<Product> recs = pdao.findByConcern(c, s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findCrueltyFreeProductsByConcern(List<Concern> c, SkinType s) {
        List<Product> recs = pdao.findByConcernAndCrueltyFreeTrue(c, s);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Brand> findAllBrands() {
        return brepo.findAll();
    }

    public List<Product> findProductsByBrandidAndCategorytype(int id, String type) {
        Category c = crepo.findByCategorytype(type);
        Brand b = brepo.findById(id).orElse(null);

        return product.findByBrandAndCategory(b, c);
    }

    public List<Coverage> getAllCoverages() {
        return covrepo.findAll();
    }

    public List<Finish> getAllFinishes() {
        return frepo.findAll();
    }

    public Product findProductById(String id) {
        return product.findById(id).orElse(null);
    }

    public List<Product>
            findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<Product> recs = pdao.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(a, category, coverage, finish, base, skintype);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<Product> recs = pdao.findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(a, category, coverage, finish, base, skintype);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<Product> recs = pdao.findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(category, coverage, finish, base, skintype);

        recs = hydrateProductList(recs);

        return recs;
    }

    public List<Product> findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<Product> recs = pdao.findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(category, coverage, finish, base, skintype);

        recs = hydrateProductList(recs);

        return recs;
    }

    public Category findCategoryByType(String type) {
        return crepo.findByCategorytype(type);
    }

    public PreferenceEntity savePreference(MakeupPreference e) {
        PreferenceEntity pe = new PreferenceEntity();

        int[] att = e.getA();
        List<Attribute> atts = new ArrayList<>();
        if (att.length != 0) {
            for (int i = 0; i < att.length; i++) {
                Attribute a = attribute.findById(att[i]).orElse(null);
                atts.add(a);
            }
        }
        Coverage c = covrepo.findById(e.getCoverage().getCoverageid()).orElse(null);
        Product p = product.findById(e.getProduct().getItemnumber()).orElse(null);
        Finish f = frepo.findById(e.getFinish().getFinishid()).orElse(null);

        pe.setAttributes(atts);
        pe.setCoverage(c);
        pe.setCrueltyfree(e.isCrueltyfree());
        pe.setProduct(p);
        pe.setFinish(f);
        pe.setSkintype(e.getSkintype());
        PreferenceEntity pref = prefrepo.save(pe);

        return pref;
    }

    public PreferenceEntity findPreferenceById(int id) {
        PreferenceEntity pe = prefrepo.findById(id).orElse(null);
        return pe;
    }

    public Guest findGuestById(String id) {
        return grepo.findById(id).orElse(null);
    }

    public void saveGuest(GuestModel g) {
        Guest guest = new Guest();
        PreferenceEntity pe = new PreferenceEntity();
        List<Attribute> atts = new ArrayList<>();

        int[] a = g.getA();
        if (a != null) {
            for (int i = 0; i < a.length; i++) {
                Attribute attr = attribute.findById(a[i]).orElse(null);
                atts.add(attr);
            }
        }
        List<Concern> concerns = new ArrayList<>();
        int[] c = g.getC();
        if (c != null) {
            for (int i = 0; i < c.length; i++) {
                Concern con = concern.findById(c[i]).orElse(null);
                concerns.add(con);
            }
        }
        pe.setFinish(g.getFinish());
        pe.setCrueltyfree(g.isCrueltyfree());
        pe.setAttributes(atts);
        pe.setConcerns(concerns);
        if (g.getSkintype() != null) {
            SkinType s = skinType.findById(g.getSkintype().getSkintypeid()).orElse(null);
            pe.setSkintype(s);
        }
        if (g.getCoverage() != null) {
            pe.setCoverage(g.getCoverage());
        }
        PreferenceEntity pr = prefrepo.save(pe);
//        List<PreferenceEntity> pref = new ArrayList<>();
//        pref.add(pr);
        guest.setPreference(pr);
        guest.setGuestid(g.getGuestid());
        guest.setGuestname(g.getGuestname());
        guest.setPassword(g.getPassword());
//        guest.getPreference().setConcerns(concerns);
//        
//        guest.getPreference().setSkintype(s);
        grepo.save(guest);
    }

    public List<Guest> findAllGuests() {
        return grepo.findAll();
    }

    public void setNewAdminById(String id, boolean admin) {
        Guest g = grepo.findById(id).orElse(null);

        if (g != null) {
            g.setAdministrator(admin);
            grepo.save(g);
        }
    }

    public List<Category> findAllCategories() {
        return crepo.findAll();
    }

    public void saveProduct(ProductModel pm) {

//        Product p = new Product();
        String chromeDriverPath = "/usr/local/Caskroom/chromedriver/76.0.3809.68/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);

//        String[] urls = {"https://www.ulta.com/cc-cream-with-spf-50?productId=xlsImpprod5770257"};
//        
//        for(int i = 0; i<urls.length; i++){
        driver.get(pm.getUrl());

        String output = driver.getPageSource();

        Document doc = Jsoup.parse(output);

        parseProduct(doc, pm);

        driver.close();

    }

    private void parseProduct(Document doc, ProductModel pm) {
        Product p = new Product();
        int[] concerns = pm.getConcerns();
        List<Concern> cons = new ArrayList<>();
        if (concerns != null) {
            for (int i = 0; i < concerns.length; i++) {
                Concern c = concern.findById(concerns[i]).orElse(null);
                cons.add(c);
            }
        }

        int[] skintypes = pm.getSkintypes();
        List<SkinType> skins = new ArrayList<>();
        if (skintypes != null) {
            for (int j = 0; j < skintypes.length; j++) {
                SkinType s = skinType.findById(skintypes[j]).orElse(null);
                skins.add(s);
            }
        }

        int[] coverages = pm.getCoverages();
        List<Coverage> covers = new ArrayList<>();
        if (coverages != null) {
            for (int k = 0; k < coverages.length; k++) {
                Coverage cov = covrepo.findById(coverages[k]).orElse(null);
                covers.add(cov);
            }
        }
        //set the item number
        Element productId = doc.select("div.ProductMainSection__itemNumber").first();

        String id = productId.text();
        String itemNumber = "";
        String[] idTokens = id.split(" ");
        if (idTokens.length == 5) {
            itemNumber = idTokens[4];
        } else if (idTokens.length == 2) {
            itemNumber = idTokens[1];
        }

        Product productDB = product.findById(itemNumber).orElse(null);
        if (productDB == null) {
            p.setItemnumber(itemNumber);

        } else {
            p = productDB;
            List<Concern> dbConcerns = p.getConcerns();
            HashMap<String, Concern> chash = new HashMap<>();
            for (Concern con : dbConcerns) {
                chash.put(con.getConcerntype(), con);
            }
            for (Concern cern : cons) {
                if (!chash.containsKey(cern.getConcerntype())) {
                    dbConcerns.add(cern);
                }
            }
            List<SkinType> dbSkintypes = p.getSkintypes();
            HashMap<String, SkinType> sthash = new HashMap<>();
            for (SkinType sk : dbSkintypes) {
                sthash.put(sk.getSkintypename(), sk);
            }
            for (SkinType skii : skins) {
                if (!sthash.containsKey(skii.getSkintypename())) {
                    dbSkintypes.add(skii);
                }
            }
            List<Coverage> dbCoverages = p.getCoveragetypes();
            HashMap<String, Coverage> covhash = new HashMap<>();
            for (Coverage cove : dbCoverages) {
                covhash.put(cove.getCoveragetype(), cove);
            }
            for (Coverage coveii : covers) {
                if (!covhash.containsKey(coveii.getCoveragetype())) {
                    dbCoverages.add(coveii);
                }
            }
            p.setConcerns(dbConcerns);
            p.setSkintypes(dbSkintypes);
            p.setCoveragetypes(dbCoverages);
            product.save(p);
            return;
        }

        //set the name
        Element productName = doc.select("div.ProductMainSection__productName").first();

        String name = productName.text();
        p.setProductname(name);

        p.setConcerns(cons);
        p.setSkintypes(skins);
        p.setCoveragetypes(covers);
        p.setUrl(pm.getUrl());
        p.setFinish(pm.getFinish());
        p.setCategory(pm.getCategory());

//            List<Concern> dbconcerns = productDB.getConcerns();
//            List<SkinType> dbst = productDB.getSkintypes();
//            for (Concern con : dbconcerns) {
//                if (!con.getConcerntype().equalsIgnoreCase(concern.getConcerntype())) {
//                    dbconcerns.add(concern);
//                    productDB.setConcerns(dbconcerns);
//                }
//                
//            }
//            for(SkinType st : dbst){
//                if (!st.getSkintypename().equalsIgnoreCase(skin.getSkintypename())) {
//                    dbst.add(skin);
//                    productDB.setSkintypes(dbst);
//                }
//                
//            }
//            saveProduct(productDB);
//            return;
//        }
//        p.setCategory(c);
        //set the price
        Element price = doc.select("div.ProductPricingPanel").first();

        String text = price.child(0).textNodes().get(0).text();
        while (!Character.isDigit(text.charAt(0))) {
            text = text.substring(1);
        }
        p.setPrice(new BigDecimal(text));

        //set details
        Elements detailsboi = doc.select("div.ProductDetail__productContent");

        p.setDetails(detailsboi.text());

        //set brand
        Element brandname = doc.select("div.ProductMainSection__brandName").first();

        String brand = brandname.text();

        Brand b = new Brand();

        Brand existingBrand = brepo.findByBrandname(brand);

        if (existingBrand == null) {
            b.setBrandname(brand);
            b = brepo.save(b);
        } else {
            b = existingBrand;
        }

        p.setBrand(b);

        //set ingredients
        List<Ingredient> dbIngredients = irepo.findAll();

        HashMap<String, Ingredient> map = new HashMap<>();
        for (Ingredient i : dbIngredients) {
            String n = i.getIngredientname().toLowerCase();
            map.put(n, i);
        }

        Element ingredients = doc.select("div.ProductDetail__ingredients").first();
        Element items = ingredients.getElementsByClass("ProductDetail__productContent").first();

        String[] ingredientlist = items.text().split(", ");

        List<Ingredient> tmpIngredients = new ArrayList<>();

        for (int s = 0; s < ingredientlist.length - 1; s++) {
            Ingredient i = new Ingredient();
            i.setIngredientname(ingredientlist[s]);
            tmpIngredients.add(i);
        }

        List<Ingredient> productIngredients = new ArrayList<>();

        for (Ingredient i : tmpIngredients) {
            String n2 = i.getIngredientname().toLowerCase();
            if (map.containsKey(n2)) {
                productIngredients.add(map.get(n2));
            } else {
                Ingredient i2 = irepo.save(i);
                productIngredients.add(i2);
                map.put(n2, i2);
            }
        }

        p.setIngredients(productIngredients);
        List<Ingredient> oilIngredients = irepo.findByBasetype("Oil");
        List<Ingredient> waterIngredients = irepo.findByBasetype("Water");
        List<Ingredient> siliconeIngredients = irepo.findByBasetype("Silicone");

        int water = 0;
        int oil = 0;
        int silicone = 0;
        for (Ingredient dient : productIngredients) {
            for (Ingredient o : oilIngredients) {
                if (o.getIngredientname().equalsIgnoreCase(dient.getIngredientname())) {
                    oil += 1;
                }
            }
            for (Ingredient w : waterIngredients) {
                if (w.getIngredientname().equalsIgnoreCase(dient.getIngredientname())) {
                    water += 1;
                }
            }
            for (Ingredient s : siliconeIngredients) {
                if (s.getIngredientname().equalsIgnoreCase(dient.getIngredientname())) {
                    silicone += 1;
                }
            }
        }
        Base base = new Base();
        if (water > oil || water > silicone) {
            base = baserepo.findByBasetype("Water");

        } else if (oil > silicone || oil > water) {
            base = baserepo.findByBasetype("Oil");

        } else if (silicone > oil || silicone > water) {
            base = baserepo.findByBasetype("Silicone");

        } else {
            base = baserepo.findByBasetype("Water");
        }
        p.setBase(base);
        //list.stream.flatmap(x->list2.stream.filter(y->x.getName().equals(y.getNAme).foreach(q -> productingredients.addq).

        //save product
        product.save(p);
    }

    public List<IngredientCompatibility> findCompatibilityList(int id) {
        return icrepo.findCompatibilityList(id);
    }

    public IngredientCompatibility findCompatibility(Ingredient i, Ingredient i2) {
        return icrepo.findCompatibility(i.getIngredientid(), i2.getIngredientid());
    }

    public Concern findConcernById(int id) {
        return concern.findById(id).orElse(null);
    }

    public Coverage findCoverageByType(String type) {
        return covrepo.findByCoveragetype(type);
    }

    public Finish findFinishByType(String type) {
        return frepo.findByFinishtype(type);
    }

    public Brand findBrandByName(String name) {
        return brepo.findByBrandname(name);
    }

    public Base findBaseByType(String type) {
        return baserepo.findByBasetype(type);
    }

    public SkinType findSkinTypeByType(String type) {
        return skinType.findBySkintypename(type);
    }

    public void deleteGuestById(String id) {
        grepo.deleteById(id);
    }

    public List<Product> findAllProducts() {
        return product.findAll();
    }

    public void deleteProductById(String id) {
        product.deleteById(id);
    }

    public Product saveSimpleProduct(Product p) {
        return product.save(p);
    }

    public List<PreferenceEntity> findAllPreferences() {
        return prefrepo.findAll();
    }

    public void deletePreferenceEntityById(int id) {
        prefrepo.deleteById(id);
    }

    public Attribute findAttributeByName(String name) {
        return attribute.findByAttributename(name);
    }

    public Concern findConcernByType(String type) {
        return concern.findByConcerntype(type);
    }

    public Ingredient findIngredientByName(String name) {
        return irepo.findByIngredientname(name);
    }

    public List<IngredientCompatibility> findAllIngredientCompat() {
        return icrepo.findAll();
    }

    public void saveGuestProducts(String[] itemnumbers, String username) {
        Guest g = grepo.findById(username).orElse(null);
        List<Product> prods = new ArrayList<>();
        if (g != null) {
            for (int i = 0; i < itemnumbers.length; i++) {
                Product prod = product.findById(itemnumbers[i]).orElse(null);
                if (prod != null) {
                    prods.add(prod);
                }
            }
            if (!prods.isEmpty()) {
                g.setProducts(prods);
                grepo.save(g);
            }
        }

    }

    public Guest findGuestByGuestId(String id) {
        return grepo.findById(id).orElse(null);
    }

    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(int[] a, Category foundation, Coverage coverage, Finish finish, SkinType skintype) {
        List<Attribute> att = hydrateAttributes(a);

        List<Product> p = pdao.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(att, foundation, coverage, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(int[] a, Category primer, Finish finish, SkinType skintype) {
        List<Attribute> att = hydrateAttributes(a);
        List<Product> p = pdao.findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(att, primer, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(int[] a, Category foundation, Coverage coverage, Finish finish, SkinType skintype) {
        List<Attribute> att = hydrateAttributes(a);
        List<Product> p = pdao.findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(att, foundation, coverage, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findProductsBasedOnAttributesAndCategoryAndFinish(int[] a, Category primer, Finish finish, SkinType skintype) {
        List<Attribute> att = hydrateAttributes(a);
        List<Product> p = pdao.findProductsBasedOnAttributesAndCategoryAndFinish(att, primer, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(Category foundation, Coverage coverage, Finish finish, SkinType skintype) {
        List<Product> p = pdao.findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(foundation, coverage, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndFinish(Category primer, Finish finish, SkinType skintype) {
        List<Product> p = pdao.findCrueltyFreeProductsBasedOnCategoryAndFinish(primer, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findProductsBasedOnCategoryAndCoverageAndFinish(Category foundation, Coverage coverage, Finish finish, SkinType skintype) {
        List<Product> p = pdao.findProductsBasedOnCategoryAndCoverageAndFinish(foundation, coverage, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    public List<Product> findProductsBasedOnCategoryAndFinish(Category primer, Finish finish, SkinType skintype) {
        List<Product> p = pdao.findProductsBasedOnCategoryAndFinish(primer, finish, skintype);
        p = hydrateProductList(p);
        return p;
    }

    private List<Product> hydrateProductList(List<Product> recs) {

        for (Product p : recs) {
            p.setBase(baserepo.findById(p.getBase().getBaseid()).orElse(null));
            p.setBrand(brepo.findById(p.getBrand().getBrandid()).orElse(null));
            p.setCategory(crepo.findById(p.getCategory().getCategoryid()).orElse(null));
            p.setFinish(frepo.findById(p.getFinish().getFinishid()).orElse(null));
            p.setIngredients(ingrepo.findByProductId(p.getItemnumber()));
            p.setCoveragetypes(coverDao.findByProductId(p.getItemnumber()));

            p.setConcerns(concernDao.findByProductId(p.getItemnumber()));
            p.setSkintypes(sDao.findByProductId(p.getItemnumber()));
        }
        return recs;
    }

    private List<Attribute> hydrateAttributes(int[] a) {
        List<Attribute> att = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            Attribute at = attribute.findById(a[i]).orElse(null);
            if (at != null) {
                att.add(at);
            }

        }
        return att;
    }

    public List<Brand> findBrandsByCategory(Category c) {
        return bdao.findBrandsByCategory(c.getCategoryid());
    }

    public Preference convertGuestPreferenceToModel(PreferenceEntity pe) {
        Preference p = new Preference();

        p.setCrueltyfree(pe.isCrueltyfree());
        p.setSkintype(pe.getSkintype());

        List<Attribute> att = pe.getAttributes();

        if (!att.isEmpty()) {
            int[] a = new int[att.size()];
            for (int i = 0; i < att.size(); i++) {
                a[i] = att.get(i).getAttributeid();
            }
            p.setA(a);
        }

        List<Concern> con = pe.getConcerns();

        if (!con.isEmpty()) {
            int[] c = new int[con.size()];
            for (int i = 0; i < con.size(); i++) {
                c[i] = con.get(i).getConcernid();
            }
            p.setC(c);
        }
        return p;
    }

    public MakeupPreference convertGuestPreferenceToMakeupModel(PreferenceEntity pe) {
        MakeupPreference mp = new MakeupPreference();
        List<Attribute> att = pe.getAttributes();

        if (!att.isEmpty()) {
            int[] a = new int[att.size()];
            for (int i = 0; i < att.size(); i++) {
                a[i] = att.get(i).getAttributeid();
            }
            mp.setA(a);
        }

        mp.setCoverage(pe.getCoverage());
        mp.setCrueltyfree(pe.isCrueltyfree());
        mp.setFinish(pe.getFinish());
        mp.setSkintype(pe.getSkintype());

        return mp;
    }

    public void saveSimpleRecs(String[] savedRecs, String username) {
        if (savedRecs != null) {
            List<GuestSkincareSet> gss = new ArrayList<>();
            for (int i = 0; i < savedRecs.length; i++) {
                GuestSkincareSet set = new GuestSkincareSet();
                String[] itemnumbers = savedRecs[i].split("_");
                List<Product> prods = new ArrayList<>();
                for (int j = 0; j < itemnumbers.length; j++) {
                    Product p = product.findById(itemnumbers[j]).orElse(null);
                    prods.add(p);
                }
                int v = 1;
                for (Product p : prods) {
                    if (v == 1) {
                        set.setProduct1(p);
                    } else if (v == 2) {
                        set.setProduct2(p);
                    } else if (v == 3) {
                        set.setProduct3(p);
                    }
                    v++;
                }
                gss.add(set);
            }
            for (GuestSkincareSet guestSet : gss) {
                guestSet.setGuest(grepo.findById(username).orElse(null));
                guestSkincareSet.save(guestSet);
            }
        }

    }

    public List<GuestSkincareSet> findSetsByUsername(Guest guest) {
        return guestSkincareSet.findByGuest(guest);
    }

    //this is not "dry" because the final save method within the loop requires different daos and i don't have time to think about how to dry this up lol im sorry
    public void saveFullMakeupRecs(String[] savedRecs, String username) {
        if (savedRecs != null) {
            List<GuestMakeupSet> gss = new ArrayList<>();
            for (int i = 0; i < savedRecs.length; i++) {
                GuestMakeupSet set = new GuestMakeupSet();
                String[] itemnumbers = savedRecs[i].split("_");
                List<Product> prods = new ArrayList<>();
                for (int j = 0; j < itemnumbers.length; j++) {
                    Product p = product.findById(itemnumbers[j]).orElse(null);
                    prods.add(p);
                }
                int v = 1;
                for (Product p : prods) {
                    if (v == 1) {
                        set.setProduct1(p);
                    } else if (v == 2) {
                        set.setProduct2(p);
                    }
                    v++;
                }
                gss.add(set);
            }
            for (GuestMakeupSet guestSet : gss) {
                guestSet.setGuest(grepo.findById(username).orElse(null));
                guestMakeupSet.save(guestSet);
            }
        }
    }

    public List<GuestMakeupSet> findMakeupSetsByGuest(Guest g) {
        return guestMakeupSet.findByGuest(g);
    }
}
