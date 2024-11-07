package schedule.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import schedule.dto.WriterRequestDto;
import schedule.dto.WriterResponseDto;
import schedule.entity.Writer;
import schedule.repository.WriterRepository;

@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository repository;

    public WriterServiceImpl(WriterRepository repository) {
        this.repository = repository;
    }

    @Override
    public WriterResponseDto saveWriter(WriterRequestDto requestDto) {
        Writer writer= new Writer(requestDto.getEmail(), requestDto.getName());

        if (requestDto.getEmail() == null || requestDto.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email and name are required values.");
        }

        return repository.saveWriter(writer);
    }
}