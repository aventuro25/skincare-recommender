/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import my.app.skincarerecommender.entities.Brand;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDaoDB implements BrandDao {
    private final JdbcTemplate jdbc;
    
    public BrandDaoDB(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public List<Brand> findBrandsByCategory(int c) {
        final String sql = "SELECT b.crueltyfree, b.brandid, b.brandname, p.categoryid "
            + "FROM Brand b "
                + "INNER JOIN Product p "
            + "ON b.brandid = p.brandid "
            + "GROUP BY b.brandname, p.categoryid, b.brandid, b.crueltyfree "
            + "HAVING p.categoryid = ?;";
        
        return jdbc.query(sql, new BrandMapper(), c);
    }
    
    private final class BrandMapper implements RowMapper<Brand> {

        @Override
        public Brand mapRow(ResultSet rs, int index) throws SQLException {
            Brand b = new Brand();
            b.setBrandid(rs.getInt("brandid"));
            b.setBrandname(rs.getString("brandname"));
            b.setCrueltyfree(rs.getBoolean("crueltyfree"));
            return b;
        }
    }
}
