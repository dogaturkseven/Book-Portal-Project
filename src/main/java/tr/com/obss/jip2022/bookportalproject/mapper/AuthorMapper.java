package tr.com.obss.jip2022.bookportalproject.mapper;

import org.mapstruct.Mapper;
import tr.com.obss.jip2022.bookportalproject.dto.AuthorDto;
import tr.com.obss.jip2022.bookportalproject.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto mapTo(Author author);
}
