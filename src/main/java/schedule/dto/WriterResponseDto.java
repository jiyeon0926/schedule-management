package schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import schedule.entity.Writer;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WriterResponseDto {

    private String email;
    private String name;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;

    public WriterResponseDto(Writer writer) {
        this.email = writer.getEmail();
        this.name = writer.getName();
        this.dtcreate = writer.getDtcreate();
        this.dtmodify = writer.getDtcreate();
    }
}
