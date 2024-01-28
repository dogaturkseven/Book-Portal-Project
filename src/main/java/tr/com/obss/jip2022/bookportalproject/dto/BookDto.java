package tr.com.obss.jip2022.bookportalproject.dto;

import lombok.*;
import tr.com.obss.jip2022.bookportalproject.model.Author;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private String authorName;
    private String genre;
}
