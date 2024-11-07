package schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.dto.WriterRequestDto;
import schedule.dto.WriterResponseDto;
import schedule.service.WriterService;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final WriterService service;

    public WriterController(WriterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WriterResponseDto> createWriter(@RequestBody WriterRequestDto requestDto) {
        return new ResponseEntity<>(service.saveWriter(requestDto), HttpStatus.CREATED);
    }
}
