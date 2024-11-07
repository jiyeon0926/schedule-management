package schedule.repository;

import schedule.dto.WriterResponseDto;
import schedule.entity.Writer;

public interface WriterRepository {

    WriterResponseDto saveWriter(Writer writer);
}
