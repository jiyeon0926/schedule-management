package schedule.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String name;
    private String contents;
    private String password;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;

    public Schedule(String name, String contents,
                    String password, LocalDateTime dtcreate, LocalDateTime dtmodify) {
        this.name = name;
        this.contents = contents;
        this.password = password;
        this.dtcreate = dtcreate;
        this.dtmodify = dtmodify;
    }
}