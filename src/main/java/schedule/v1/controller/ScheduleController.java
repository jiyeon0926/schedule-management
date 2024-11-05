package schedule.v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.v1.dto.ScheduleRequestDto;
import schedule.v1.dto.ScheduleResponseDto;
import schedule.v1.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(service.saveSchedule(requestDto), HttpStatus.CREATED);
    }
}
