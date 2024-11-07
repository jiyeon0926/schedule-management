package schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.dto.ScheduleRequestDto;
import schedule.dto.ScheduleResponseDto;
import schedule.entity.Schedule;
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
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(service.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(@RequestParam String email) {
        return new ResponseEntity<>(service.findAllSchedules(email), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(@PathVariable Long id,
                                                                  @RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(service.updateSchedule(id, requestDto.getName(), requestDto.getContents(), requestDto.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id,
                                                   @RequestBody ScheduleRequestDto requestDto) {
        service.deleteSchedule(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
