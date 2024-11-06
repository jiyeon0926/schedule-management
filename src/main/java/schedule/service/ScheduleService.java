package schedule.service;

import schedule.dto.ScheduleRequestDto;
import schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedules();

    List<ScheduleResponseDto> findScheduleById(Long id);
}
