package schedule.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final WriterRepository writerRepository;

    public ScheduleRepositoryImpl(DataSource dataSource, WriterRepository writerRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.writerRepository = writerRepository;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        // 일정 생성 요청을 할 때 받은 이메일을 파라미터로 받아서 writer 테이블에 존재하는 이메일인지 확인
        boolean email = writerRepository.isValidWriter(schedule.getEmail());

        if (!email) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not exist email.");
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withCatalogName("schedule").withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", schedule.getName());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());
        parameters.put("email", schedule.getEmail());
        parameters.put("dtcreate", schedule.getDtcreate());
        parameters.put("dtmodify", schedule.getDtmodify());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        schedule.setId(key.longValue());

        return schedule;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule order by dtmodify desc", scheduleRowMapper());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     * @param id 식별자
     * @param password 수정 권한을 확인하기 위해 비밀번호도 같이 요청해야 함
     */
    @Override
    public int updateSchedule(Long id, String name, String contents, String password) {
        // 쿼리를 통해 수정일도 수정된 시점에 현재시간으로 변경되도록 함
        return jdbcTemplate.update("update schedule set name = ?, contents = ?, dtmodify = now() where id = ? and password = ?", name, contents, id, password);
    }

    /**
     * @param id 식별자
     * @param password 삭제 권한을 확인하기 위해 비밀번호도 같이 요청해야 함
     */
    @Override
    public int deleteSchedule(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where id = ? and password = ?", id, password);
    }

    // mapRow(ResultSet rs, int rowNum) 메서드를 구현해 각 데이터 행을 매핑
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("dtcreate"),
                        rs.getDate("dtmodify")
                );
            }
        };
    }
}
