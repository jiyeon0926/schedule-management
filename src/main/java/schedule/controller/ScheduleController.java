package schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.dto.ScheduleRequestDto;
import schedule.dto.ScheduleResponseDto;
import schedule.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(service.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(service.findAllSchedules(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ScheduleResponseDto>> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findScheduleById(id), HttpStatus.OK);
    }
}