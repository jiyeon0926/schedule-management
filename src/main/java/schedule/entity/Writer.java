package schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Writer {

    private String email; // 기본키
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;
}
