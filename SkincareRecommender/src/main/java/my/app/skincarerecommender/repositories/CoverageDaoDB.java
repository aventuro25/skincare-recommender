/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import my.app.skincarerecommender.entities.Coverage;
import my.app.skincarerecommender.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CoverageDaoDB implements CoverageDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public CoverageDaoDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Coverage> findByProductId(String id) {
        final String sql = "SELECT * FROM coverage c "
                + "INNER JOIN productcoverage pc ON c.coverageid = pc.coverageid "
                + "WHERE pc.itemnumber = ?";

        return jdbc.query(sql, new CoverageMapper(), id);
    }
    
    private final class CoverageMapper implements RowMapper<Coverage> {

        @Override
        public Coverage mapRow(ResultSet rs, int index) throws SQLException {
            Coverage c = new Coverage();
            c.setCoverageid(rs.getInt("coverageid"));
            c.setCoveragetype(rs.getString("coveragetype"));
            return c;
        }
    }

}
