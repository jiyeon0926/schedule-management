package schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Writer {

    private String email;
    private String name;
    private LocalDateTime dtcreate;
    private LocalDateTime dtmodify;

    public Writer(String email, String name) {
        this.email = email;
        this.name = name;
        this.dtcreate = LocalDateTime.now();
        this.dtmodify = LocalDateTime.now();
    }
}
