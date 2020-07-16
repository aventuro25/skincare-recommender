/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.app.skincarerecommender.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import my.app.skincarerecommender.entities.Concern;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ConcernDaoDB implements ConcernDao{

    private final JdbcTemplate jdbc;
    
    public ConcernDaoDB(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    @Override
    public List<Concern> findByProductId(String id) {
        final String sql = "SELECT * FROM concern c "
                + "INNER JOIN productconcern pc ON c.concernid = pc.concernid "
                + "WHERE pc.itemnumber = ?";
        
        return jdbc.query(sql, new ConcernMapper(), id);
    }
    private final class ConcernMapper implements RowMapper<Concern> {

        @Override
        public Concern mapRow(ResultSet rs, int index) throws SQLException {
            Concern c = new Concern();
            c.setConcernid(rs.getInt("concernid"));
            c.setConcerntype(rs.getString("concerntype"));
            return c;
        }
    }
}
