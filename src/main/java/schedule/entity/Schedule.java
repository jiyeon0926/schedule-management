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
    private String email;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;

    public Schedule(String name, String contents, String password, String email) {
        this.name = name;
        this.contents = contents;
        this.password = password;
        this.dtcreate = LocalDateTime.now();
        this.dtmodify = LocalDateTime.now();
        this.email = email;
    }
}