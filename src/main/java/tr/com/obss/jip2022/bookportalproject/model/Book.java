package tr.com.obss.jip2022.bookportalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity{
    private String title;

    private String authorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Author author;

    private String genre;
}
