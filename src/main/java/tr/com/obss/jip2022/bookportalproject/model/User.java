package tr.com.obss.jip2022.bookportalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseEntity{
    @NotNull
    @Size(min=3, max=10)
    private String name;

    private String surname;

    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min=3, max=100)
    private String password;

    private Integer age;

    private String city;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> readList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> favoriteList;


}
