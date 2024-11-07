package schedule.service;

import schedule.dto.WriterRequestDto;
import schedule.dto.WriterResponseDto;

public interface WriterService {

    WriterResponseDto saveWriter(WriterRequestDto requestDto);
}
