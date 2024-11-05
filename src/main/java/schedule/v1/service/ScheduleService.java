package schedule.v1.service;

import schedule.v1.dto.ScheduleRequestDto;
import schedule.v1.dto.ScheduleResponseDto;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);
}
