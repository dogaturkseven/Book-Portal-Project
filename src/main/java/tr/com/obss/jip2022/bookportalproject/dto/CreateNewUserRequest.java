package tr.com.obss.jip2022.bookportalproject.dto;

import lombok.*;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserRequest {
    @NotNull
    private String name;

    private String surname;

    @NotNull
    private String username;
    @NotNull
    private String password;

    private Integer age;

    private String city;

}
