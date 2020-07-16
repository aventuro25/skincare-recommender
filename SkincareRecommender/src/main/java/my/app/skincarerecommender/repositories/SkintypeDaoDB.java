/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import my.app.skincarerecommender.entities.SkinType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SkintypeDaoDB implements SkintypeDao {

    private final JdbcTemplate jdbc;

    public SkintypeDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<SkinType> findByProductId(String id) {
        final String sql = "SELECT * FROM skintype st "
                + "INNER JOIN productskintype pst ON st.skintypeid = pst.skintypeid "
                + "WHERE pst.itemnumber = ?";

        return jdbc.query(sql, new SkinTypeMapper(), id);

    }

    private final class SkinTypeMapper implements RowMapper<SkinType> {

        @Override
        public SkinType mapRow(ResultSet rs, int index) throws SQLException {
            SkinType s = new SkinType();
            s.setSkintypeid(rs.getInt("skintypeid"));
            s.setSkintypename(rs.getString("skintypename"));
            return s;
        }
    }
}
