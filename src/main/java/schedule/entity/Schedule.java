package schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private Long id;

    private String name;
    private String contents;
    private String password;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;
    private String email; // 외래키
    private Writer writer;

    public Schedule(String name, String contents, String password, Writer writer) {
        this.name = name;
        this.contents = contents;
        this.password = password;
        this.dtcreate = LocalDateTime.now();
        this.dtmodify = LocalDateTime.now();
        this.email = writer.getEmail();
        this.writer = writer;
    }
}