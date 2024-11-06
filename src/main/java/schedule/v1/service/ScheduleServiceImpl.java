package schedule.v1.service;

import org.springframework.stereotype.Service;
import schedule.v1.dto.ScheduleRequestDto;
import schedule.v1.dto.ScheduleResponseDto;
import schedule.v1.entity.Schedule;
import schedule.v1.repository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getName(), requestDto.getContents(), requestDto.getPassword());

        return repository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return repository.findAllSchedules();
    }

    @Override
    public List<ScheduleResponseDto> findScheduleById(Long id) {
        return repository.findScheduleById(id);
    }
}
