package schedule.v1.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import schedule.v1.dto.ScheduleResponseDto;
import schedule.v1.entity.Schedule;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", schedule.getName());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());
        parameters.put("dtcreate", schedule.getDtcreate());
        parameters.put("dtmodify", schedule.getDtmodify());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getContents(), schedule.getPassword(), schedule.getDtcreate(), schedule.getDtmodify());
    }
}
