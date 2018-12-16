package sk.stopangin.auditing;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by martin.cuchran on 12/11/2018.
 */

@Service
public class AuditDaoJdbcImpl implements AuditDao {
    public static final String INSERT_INTO_AUDIT = "INSERT INTO audit VALUES(?,?,?)";
    private JdbcTemplate jdbcTemplate;

    public AuditDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Audit create(Audit audit) {
        jdbcTemplate.update(INSERT_INTO_AUDIT, audit.getId(), audit.getAuditedEntityClassname(), null);
        return audit;
    }
}
