/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import my.app.skincarerecommender.entities.Base;
import my.app.skincarerecommender.entities.Brand;
import my.app.skincarerecommender.entities.Category;
import my.app.skincarerecommender.entities.Finish;
import my.app.skincarerecommender.entities.Ingredient;
import my.app.skincarerecommender.entities.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDaoDB implements IngredientDao {
    private final JdbcTemplate jdbc;
    
    public IngredientDaoDB(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public List<Ingredient> findByProductId(String id) {
        
        final String sql = "SELECT * FROM ingredient i "
                + "INNER JOIN productingredient pi ON i.ingredientid = pi.ingredientid "
                + "WHERE pi.itemnumber = ?;";
        
        return jdbc.query(sql, new IngredientMapper(), id);
        
   }
    
    private final class IngredientMapper implements RowMapper<Ingredient> {

        @Override
        public Ingredient mapRow(ResultSet rs, int index) throws SQLException {
            Ingredient i = new Ingredient();
            i.setIngredientid(rs.getInt("ingredientid"));
            i.setIngredientname(rs.getString("ingredientname"));
            return i;
        }
    }
}
