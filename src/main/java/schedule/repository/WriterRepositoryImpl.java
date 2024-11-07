package schedule.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import schedule.dto.WriterResponseDto;
import schedule.entity.Writer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WriterRepositoryImpl implements WriterRepository {

    private final JdbcTemplate jdbcTemplate;

    public WriterRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public WriterResponseDto saveWriter(Writer writer) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withCatalogName("schedule").withTableName("writer").usingColumns("email", "name", "dtcreate", "dtmodify");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("email", writer.getEmail());
        parameters.put("name", writer.getName());
        parameters.put("dtcreate", writer.getDtcreate());
        parameters.put("dtmodify", writer.getDtmodify());

        simpleJdbcInsert.execute(parameters);

        return new WriterResponseDto(writer.getEmail(), writer.getName(), writer.getDtcreate(), writer.getDtmodify());
    }

    /**
     * @param email 요청 받을 이메일
     * @param name 요청 받을 작성자명
     * @return 조회한 결과가 있을 경우, true 반환
     */
    @Override
    public boolean isValidWriter(String email, String name) {
        String sql = "select count(*) from writer where email = ? and name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email, name}, Integer.class);

        return count != null && count != 0;
    }
}