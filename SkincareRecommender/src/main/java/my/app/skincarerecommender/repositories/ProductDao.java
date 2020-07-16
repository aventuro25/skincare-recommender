/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.util.List;
import my.app.skincarerecommender.entities.Attribute;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Concern;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Product;
import my.app.skincarerecommender.entities.SkinType;

public interface ProductDao {

    List<Product> findProductsBySkintype(SkinType s);

    List<Product> findProductsBySkintypeAndCrueltyFreeTrue(SkinType s);

    List<Product>
            findProductsBySkinTypeNotInAttributesAndInConcernAndCrueltyFreeTrue(List<Attribute> a, List<Concern> c, SkinType s);

    List<Product> findProductsBySkinTypeNotInAttributesAndInConcerns(List<Attribute> a, List<Concern> c, SkinType s);

    List<Product> findProductsBySkinTypeNotInAttributesAndCrueltyFreeTrue(List<Attribute> a, SkinType s);

    List<Product> findProductsBySkinTypeNotInAttributes(List<Attribute> a, SkinType s);

    List<Product> findByConcern(List<Concern> c, SkinType s);

    List<Product> findByConcernAndCrueltyFreeTrue(List<Concern> c, SkinType s);

    List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category categorytype, Coverage coverage, Finish finish, Base base, SkinType skintype);

    List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinishAndBaseType(List<Attribute> a, Category categorytype, Coverage coverage, Finish finish, Base base, SkinType skintype);

    List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category categorytype, Coverage coverage, Finish finish, Base base, SkinType skintype);

    List<Product> findProductsBasedOnCategoryAndCoverageAndFinishAndBaseType(Category categorytype, Coverage coverage, Finish finish, Base base, SkinType skintype);

    List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(List<Attribute> a, Category cat, Coverage cov, Finish f, SkinType st);

    List<Product> findCrueltyFreeProductsBasedOnAttributesAndCategoryAndFinish(List<Attribute> a, Category cat, Finish f, SkinType st);

    List<Product> findProductsBasedOnAttributesAndCategoryAndCoverageAndFinish(List<Attribute> a, Category cat, Coverage cov, Finish f, SkinType st);

    List<Product> findProductsBasedOnAttributesAndCategoryAndFinish(List<Attribute> a, Category cat, Finish f, SkinType st);

    List<Product> findCrueltyFreeProductsBasedOnCategoryAndCoverageAndFinish(Category cat, Coverage cov, Finish f, SkinType st);

    List<Product> findCrueltyFreeProductsBasedOnCategoryAndFinish(Category cat, Finish f, SkinType st);

    List<Product> findProductsBasedOnCategoryAndCoverageAndFinish(Category cat, Coverage cov, Finish f, SkinType st);

    List<Product> findProductsBasedOnCategoryAndFinish(Category cat, Finish f, SkinType st);
}
