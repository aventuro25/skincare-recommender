/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductDaoDBTest {

    private final ProductRepository prepo;
    private final ProductDao pdao;
    private final SkintypeRepository skrepo;
    private final IngredientRepository irepo;
    private final AttributeRepository arepo;
    private final ConcernRepository crepo;
    private final BrandRepository brepo;
    private final BaseRepository baserepo;
    private final CategoryRepository caterepo;

    @Autowired
    public ProductDaoDBTest(ProductRepository prepo, SkintypeRepository skrepo,
            IngredientRepository irepo, AttributeRepository arepo,
            ConcernRepository crepo, ProductDao pdao, BrandRepository brepo,
            BaseRepository baserepo, CategoryRepository caterepo) {
        this.prepo = prepo;
        this.skrepo = skrepo;
        this.irepo = irepo;
        this.arepo = arepo;
        this.crepo = crepo;
        this.pdao = pdao;
        this.brepo = brepo;
        this.baserepo = baserepo;
        this.caterepo = caterepo;
    }

    @BeforeEach
    public void setUpClass() {
        List<Product> p = prepo.findAll();

        for (Product prod : p) {
            prepo.deleteById(prod.getItemnumber());
        }
        List<Brand> b = brepo.findAll();

        for (Brand br : b) {
            brepo.deleteById(br.getBrandid());
        }
    }

    /**
     * Test of findProductsBySkintype method, of class ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkintype() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();
        SkinType oily = new SkinType();
        SkinType combo = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            } else if (s.getSkintypename().equals("Oily")) {
                oily = s;
            } else if (s.getSkintypename().equals("Combo")) {
                combo = s;
            }
        }

        List<Base> bases = baserepo.findAll();

        Base oil = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;
            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setCategory(cleanser);
        dryProduct.setBase(oil);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setBrandname("TestBrand2");
        brepo.save(b2);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setBase(oil);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");

        prepo.save(dryProduct2);

        List<Product> dryDBProds = pdao.findProductsBySkintype(dry);

        assertEquals(2, dryDBProds.size());

        //test oily
        List<SkinType> oilyskintypes = new ArrayList<>();
        oilyskintypes.add(oily);
        Product oilyProduct = new Product();
        oilyProduct.setSkintypes(oilyskintypes);
        oilyProduct.setBrand(b);
        oilyProduct.setCategory(cleanser);
        oilyProduct.setBase(oil);
        oilyProduct.setItemnumber("TestNumber3");
        oilyProduct.setPrice(new BigDecimal("5"));
        oilyProduct.setProductname("Test Product3");

        prepo.save(oilyProduct);

        Product oilyProduct2 = new Product();
        oilyProduct2.setSkintypes(oilyskintypes);
        oilyProduct2.setBrand(b2);
        oilyProduct2.setCategory(cleanser);
        oilyProduct2.setBase(oil);
        oilyProduct2.setItemnumber("TestNumber4");
        oilyProduct2.setPrice(new BigDecimal("5"));
        oilyProduct2.setProductname("Test Product4");

        prepo.save(oilyProduct2);

        List<Product> oilyDBProds = pdao.findProductsBySkintype(oily);

        assertEquals(2, oilyDBProds.size());

        //test combo
        List<SkinType> comboskintypes = new ArrayList<>();
        comboskintypes.add(combo);
        Product comboProduct = new Product();
        comboProduct.setSkintypes(comboskintypes);
        comboProduct.setBrand(b);
        comboProduct.setCategory(cleanser);
        comboProduct.setBase(oil);
        comboProduct.setItemnumber("TestNumber5");
        comboProduct.setPrice(new BigDecimal("5"));
        comboProduct.setProductname("Test Product5");

        prepo.save(comboProduct);

        Product comboProduct2 = new Product();
        comboProduct2.setSkintypes(comboskintypes);
        comboProduct2.setBrand(b2);
        comboProduct2.setCategory(cleanser);
        comboProduct2.setBase(oil);
        comboProduct2.setItemnumber("TestNumber6");
        comboProduct2.setPrice(new BigDecimal("5"));
        comboProduct2.setProductname("Test Product6");

        prepo.save(comboProduct2);

        List<Product> comboDBProds = pdao.findProductsBySkintype(combo);

        assertEquals(2, comboDBProds.size());
    }

    /**
     * Test of findProductsBySkintypeAndCrueltyFreeTrue method, of class
     * ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkintypeAndCrueltyFreeTrue() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            }
        }

        List<Base> bases = baserepo.findAll();

        Base oil = new Base();
        Base silicone = new Base();
        Base water = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;
            } else if (b.getBasetype().equals("Water")) {
                water = b;
            } else if (b.getBasetype().equals("Silicone")) {
                silicone = b;
            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        b.setCrueltyfree(true);
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setBase(oil);
        dryProduct.setCategory(cleanser);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setBrandname("TestBrand2");
        brepo.save(b2);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setBase(oil);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");

        prepo.save(dryProduct2);

        List<Product> dryDBProds = pdao.findProductsBySkintypeAndCrueltyFreeTrue(dry);

        assertEquals(1, dryDBProds.size());
    }

    /**
     * Test of
     * findProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue
     * method, of class ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            }
        }

        List<Base> bases = baserepo.findAll();

        Base oil = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;

            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setCategory(cleanser);
        dryProduct.setBase(oil);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");
        Ingredient i = irepo.findByIngredientname("Triticum");

        List<Ingredient> ings = new ArrayList<>();

        ings.add(i);
        dryProduct.setIngredients(ings);

        Concern c = crepo.findByConcerntype("Acne");

        List<Concern> concerns = new ArrayList<>();
        concerns.add(c);
        dryProduct.setConcerns(concerns);

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setCrueltyfree(true);
        b2.setBrandname("TestBrand2");
        brepo.save(b2);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setBase(oil);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");
        Ingredient i2 = irepo.findByIngredientname("Retinol");

        List<Ingredient> ings2 = new ArrayList<>();

        ings2.add(i2);
        dryProduct2.setIngredients(ings2);
        dryProduct2.setConcerns(concerns);
        prepo.save(dryProduct2);

        Attribute a = arepo.findByAttributename("Gluten");

        List<Attribute> as = new ArrayList<>();
        as.add(a);

        List<Product> dryDBProds = pdao.findProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue(as, concerns, dry);

        assertEquals(1, dryDBProds.size());
    }

    /**
     * Test of findProductsBySkinTypeNotInAttributesAndInConcerns method, of
     * class ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkinTypeNotInAttributesAndInConcerns() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            }
        }
        List<Base> bases = baserepo.findAll();

        Base oil = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;

            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setCategory(cleanser);
        dryProduct.setBase(oil);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");
        Ingredient i = irepo.findByIngredientname("Triticum");

        List<Ingredient> ings = new ArrayList<>();

        ings.add(i);
        dryProduct.setIngredients(ings);

        Concern c = crepo.findByConcerntype("Acne");

        List<Concern> concerns = new ArrayList<>();
        concerns.add(c);
        dryProduct.setConcerns(concerns);

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setCrueltyfree(true);
        b2.setBrandname("TestBrand2");
        brepo.save(b2);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setBase(oil);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");
        Ingredient i2 = irepo.findByIngredientname("Retinol");

        List<Ingredient> ings2 = new ArrayList<>();

        ings2.add(i2);
        dryProduct2.setIngredients(ings2);
        dryProduct2.setConcerns(concerns);
        prepo.save(dryProduct2);

        Attribute a = arepo.findByAttributename("Gluten");

        List<Attribute> as = new ArrayList<>();
        as.add(a);

        List<Product> dryDBProds = pdao.findProductsBySkinTypeNotInAttributesAndInConcerns(as, concerns, dry);

        assertEquals(1, dryDBProds.size());
    }

    /**
     * Test of findProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue method,
     * of class ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            }
        }

        List<Base> bases = baserepo.findAll();

        Base oil = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;

            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setBase(oil);
        dryProduct.setCategory(cleanser);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");
        Ingredient i = irepo.findByIngredientname("Triticum");

        List<Ingredient> ings = new ArrayList<>();

        ings.add(i);
        dryProduct.setIngredients(ings);

        Concern c = crepo.findByConcerntype("Acne");

        List<Concern> concerns = new ArrayList<>();
        concerns.add(c);
        dryProduct.setConcerns(concerns);

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setCrueltyfree(true);
        b2.setBrandname("TestBrand2");
        brepo.save(b2);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setBase(oil);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");
        Ingredient i2 = irepo.findByIngredientname("Retinol");

        List<Ingredient> ings2 = new ArrayList<>();

        ings2.add(i2);
        dryProduct2.setIngredients(ings2);
        dryProduct2.setConcerns(concerns);
        prepo.save(dryProduct2);

        Attribute a = arepo.findByAttributename("Gluten");

        List<Attribute> as = new ArrayList<>();
        as.add(a);

        List<Product> dryDBProds = pdao.findProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue(as, dry);

        assertEquals(1, dryDBProds.size());
    }

    /**
     * Test of findProductsBySkinTypeNotInAttributes method, of class
     * ProductDaoDB.
     */
    @Test
    public void testFindProductsBySkinTypeNotInAttributes() {
        List<SkinType> skintypes = skrepo.findAll();
        SkinType dry = new SkinType();

        for (SkinType s : skintypes) {
            if (s.getSkintypename().equals("Dry")) {
                dry = s;
            }
        }

        List<Base> bases = baserepo.findAll();

        Base oil = new Base();

        for (Base b : bases) {
            if (b.getBasetype().equals("Oil")) {
                oil = b;

            }
        }

        Category cleanser = caterepo.findByCategorytype("Cleanser");

        //test dry
        List<SkinType> dryskintypes = new ArrayList<>();
        dryskintypes.add(dry);
        Product dryProduct = new Product();
        dryProduct.setSkintypes(dryskintypes);
        Brand b = new Brand();
        b.setBrandname("TestBrand");
        brepo.save(b);
        b = brepo.findByBrandname("TestBrand");
        dryProduct.setBrand(b);
        dryProduct.setBase(oil);
        dryProduct.setCategory(cleanser);
        dryProduct.setItemnumber("TestNumber");
        dryProduct.setPrice(new BigDecimal("5"));
        dryProduct.setProductname("Test Product");
        Ingredient i = irepo.findByIngredientname("Triticum");

        List<Ingredient> ings = new ArrayList<>();

        ings.add(i);
        dryProduct.setIngredients(ings);

        Concern c = crepo.findByConcerntype("Acne");

        List<Concern> concerns = new ArrayList<>();
        concerns.add(c);
        dryProduct.setConcerns(concerns);

        prepo.save(dryProduct);

        Product dryProduct2 = new Product();
        dryProduct2.setSkintypes(dryskintypes);
        Brand b2 = new Brand();
        b2.setCrueltyfree(true);
        b.setBrandname("TestBrand2");
        brepo.save(b);
        b2 = brepo.findByBrandname("TestBrand2");
        dryProduct2.setBrand(b2);
        dryProduct2.setBase(oil);
        dryProduct2.setCategory(cleanser);
        dryProduct2.setItemnumber("TestNumber2");
        dryProduct2.setPrice(new BigDecimal("5"));
        dryProduct2.setProductname("Test Product2");
        Ingredient i2 = irepo.findByIngredientname("Retinol");

        List<Ingredient> ings2 = new ArrayList<>();

        ings2.add(i2);
        dryProduct2.setIngredients(ings2);
        dryProduct2.setConcerns(concerns);
        prepo.save(dryProduct2);

        Attribute a = arepo.findByAttributename("Gluten");

        List<Attribute> as = new ArrayList<>();
        as.add(a);

        List<Product> dryDBProds = pdao.findProductsBySkinTypeNotInAttributes(as, dry);

        assertEquals(1, dryDBProds.size());
    }

    @Test
    public void findByConcern() {

    }

    @Test
    public void findByConcernAndCrueltyFreeTrue() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType() {

    }

    @Test
    public void findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType() {

    }

    @Test
    public void findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish() {

    }

    @Test
    public void findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish() {

    }

    @Test
    public void findProductsBasedOnAttributesAndCategoryAndFinish() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish() {

    }

    @Test
    public void findCrueltyFreeProductsBasedOnCategoryAndFinish() {

    }

    @Test
    public void findProductsBasedOnCategoryAndCoverageAndFinish() {

    }

    @Test
    public void findProductsBasedOnCategoryAndFinish() {

    }
}
