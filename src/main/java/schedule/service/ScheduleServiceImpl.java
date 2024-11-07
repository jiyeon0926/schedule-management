package schedule.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import schedule.dto.ScheduleRequestDto;
import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;
import schedule.repository.ScheduleRepository;
import schedule.repository.WriterRepository;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getName(), requestDto.getContents(), requestDto.getPassword(), requestDto.getEmail());

        if (requestDto.getName() == null || requestDto.getContents() == null || requestDto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and contents and password are required values.");
        }

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String name, String contents, String password) {
        if (name == null || contents == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and contents and password are required values.");
        }

        // 저장된 비밀번호를 가져와서 입력한 비밀번호와 일치한지 비교
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is different.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, name, contents, password);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 수정 후, 조회한 결과를 응답
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        if (!password.equals(schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is different.");
        }

        int deletedRow = scheduleRepository.deleteSchedule(id, password);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}