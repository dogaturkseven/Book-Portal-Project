package tr.com.obss.jip2022.bookportalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String username;

    private Integer age;

    private String city;

    private List<Book> readList;

    private List<Role> roles;

    private List<Book> favoriteList;
}
