package tr.com.obss.jip2022.bookportalproject.dto;

import lombok.*;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private String name;
    private List<Book> writtenBooks;
}
