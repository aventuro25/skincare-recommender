/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoDB implements ProductDao {

    private JdbcTemplate jdbc;

    @Autowired
    public ProductDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Product> findProductsBySkintype(SkinType s) {
        final String sql = "SELECT * FROM product p "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE pst.skintypeid =?;";
        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBySkintypeAndCrueltyFreeTrue(SkinType s) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b ON p.brandid = b.brandid "
                + "INNER JOIN ProductSkinType pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (b.crueltyfree = true AND pst.skintypeid = ?);";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product>
            findProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue(List<Attribute> a, List<Concern> c, SkinType s) {

        String attributeIDs = "";

        boolean isFirst = true;

        for (Attribute att : a) {
            if (!isFirst) {
                attributeIDs += ",";
            }
            attributeIDs += att.getAttributeid();
            isFirst = false;
        }

        List<String> strValues = new ArrayList<>();
        for (Concern i : c) {
            strValues.add(Integer.toString(i.getConcernid()));
        }

        String result = String.join(",", strValues);

        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b ON p.brandid = b.brandid "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (pst.skintypeid = ? AND b.crueltyfree = true AND p.itemnumber NOT IN (SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i "
                + "ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia "
                + "ON i.ingredientid = ia.ingredientid WHERE ia.attributeid IN (" + attributeIDs + ")) "
                + "AND p.itemnumber IN (SELECT pc.itemnumber FROM "
                + "ProductConcern pc WHERE pc.concernid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBySkinTypeNotInAttributesAndInConcerns(List<Attribute> a, List<Concern> c, SkinType s) {
        String attributeIDs = "";

        boolean isFirst = true;

        for (Attribute att : a) {
            if (!isFirst) {
                attributeIDs += ",";
            }
            attributeIDs += att.getAttributeid();
            isFirst = false;
        }

        List<String> strValues = new ArrayList<>();
        for (Concern i : c) {
            strValues.add(Integer.toString(i.getConcernid()));
        }

        String result = String.join(",", strValues);

        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (pst.skintypeid = ? AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + attributeIDs + ")) AND p.itemnumber IN "
                + "(SELECT pc.itemnumber FROM ProductConcern pc WHERE pc.concernid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue(List<Attribute> a, SkinType s) {
        String attributeIDs = "";

        boolean isFirst = true;

        for (Attribute att : a) {
            if (!isFirst) {
                attributeIDs += ",";
            }
            attributeIDs += att.getAttributeid();
            isFirst = false;
        }
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b ON p.brandid = b.brandid "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (b.crueltyfree = true AND pst.skintypeid = ? AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + attributeIDs + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBySkinTypeNotInAttributes(List<Attribute> a, SkinType s) {
        String attributeIDs = "";

        boolean isFirst = true;

        for (Attribute att : a) {
            if (!isFirst) {
                attributeIDs += ",";
            }
            attributeIDs += att.getAttributeid();
            isFirst = false;
        }
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (pst.skintypeid = ? AND p.itemnumber NOT IN (SELECT pi.itemnumber FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + attributeIDs + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());

    }

    @Override
    public List<Product> findByConcern(List<Concern> c, SkinType s) {
        List<String> strValues = new ArrayList<>();
        for (Concern i : c) {
            strValues.add(Integer.toString(i.getConcernid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (pst.skintypeid = ? AND p.itemnumber IN "
                + "(SELECT pc.itemnumber "
                + "FROM ProductConcern pc "
                + "WHERE pc.concernid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findByConcernAndCrueltyFreeTrue(List<Concern> c, SkinType s) {
        List<String> strValues = new ArrayList<>();
        for (Concern i : c) {
            strValues.add(Integer.toString(i.getConcernid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b ON p.brandid = b.brandid "
                + "INNER JOIN productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (pst.skintypeid = ? AND b.crueltyfree = true AND p.itemnumber IN "
                + "(SELECT pc.itemnumber FROM ProductConcern pc WHERE pc.concernid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), s.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND p.baseid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), category.getCategoryid(),
                coverage.getCoverageid(), finish.getFinishid(), base.getBaseid(),
                skintype.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND p.baseid = ? AND "
                + "pst.skintypeid = ? AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), category.getCategoryid(), coverage.getCoverageid(),
                finish.getFinishid(), base.getBaseid(),
                skintype.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? "
                + "AND p.finishid = ? "
                + "AND p.baseid = ? AND pst.skintypeid = ? AND b.crueltyfree = true);";

        return jdbc.query(sql, new ProductMapper(), category.getCategoryid(),
                coverage.getCoverageid(), finish.getFinishid(), base.getBaseid(),
                skintype.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category category, Coverage coverage, Finish finish, Base base, SkinType skintype) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? "
                + "AND p.finishid = ? "
                + "AND p.baseid = ? AND pst.skintypeid = ?);";

        return jdbc.query(sql, new ProductMapper(), category.getCategoryid(),
                coverage.getCoverageid(), finish.getFinishid(), base.getBaseid(),
                skintype.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(List<Attribute> a, Category cat, Coverage cov, Finish f, SkinType st) {
    List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);

        
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                cov.getCoverageid(), f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(List<Attribute> a, Category cat, Finish f, SkinType st) {
        List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(List<Attribute> a, Category cat, Coverage cov, Finish f, SkinType st) {
        List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                cov.getCoverageid(), f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnAttributesAndCategoryAndFinish(List<Attribute> a, Category cat, Finish f, SkinType st) {
        List<String> strValues = new ArrayList<>();
        for (Attribute i : a) {
            strValues.add(Integer.toString(i.getAttributeid()));
        }

        String result = String.join(",", strValues);
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "               
                + "WHERE (p.categoryid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND p.itemnumber NOT IN "
                + "(SELECT pi.itemnumber "
                + "FROM ProductIngredient pi "
                + "INNER JOIN Ingredient i ON pi.ingredientid = i.ingredientid "
                + "INNER JOIN IngredientAttribute ia ON i.ingredientid = ia.ingredientid "
                + "WHERE ia.attributeid IN (" + result + ")));";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(Category cat, Coverage cov, Finish f, SkinType st) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true);";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                cov.getCoverageid(), f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findCrueltyFreeProductsBasedOnCategoryAndFinish(Category cat, Finish f, SkinType st) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Brand b on p.brandid = b.brandid "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "                
                + "WHERE (p.categoryid = ? AND AND p.finishid = ? AND "
                + "pst.skintypeid = ? AND b.crueltyfree = true);";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnCategoryAndCoverageAndFinish(Category cat, Coverage cov, Finish f, SkinType st) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "INNER JOIN ProductCoverage pc ON p.itemnumber = pc.itemnumber "
                + "WHERE (p.categoryid = ? AND pc.coverageid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ?);";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                cov.getCoverageid(), f.getFinishid(),
                st.getSkintypeid());
    }

    @Override
    public List<Product> findProductsBasedOnCategoryAndFinish(Category cat, Finish f, SkinType st) {
        final String sql = "SELECT * FROM Product p "
                + "INNER JOIN Productskintype pst ON p.itemnumber = pst.itemnumber "
                + "WHERE (p.categoryid = ? AND p.finishid = ? AND "
                + "pst.skintypeid = ?);";

        return jdbc.query(sql, new ProductMapper(), cat.getCategoryid(),
                f.getFinishid(),
                st.getSkintypeid());
    }

    private final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int index) throws SQLException {
            Product p = new Product();
            p.setItemnumber(rs.getString("itemnumber"));
            p.setProductname(rs.getString("productname"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setUrl(rs.getString("url"));
            Finish f = new Finish();
            f.setFinishid(rs.getInt("finishid"));
            p.setFinish(f);
            Brand brand = new Brand();
            brand.setBrandid(rs.getInt("brandid"));
            p.setBrand(brand);
            Base b = new Base();
            b.setBaseid(rs.getInt("baseid"));
            p.setBase(b);
            p.setDetails(rs.getString("details"));
            Category c = new Category();
            c.setCategoryid(rs.getInt("categoryid"));
            p.setCategory(c);
            return p;
        }
    }
}
