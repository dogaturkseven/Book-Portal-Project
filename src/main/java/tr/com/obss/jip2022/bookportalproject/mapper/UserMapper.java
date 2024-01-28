package tr.com.obss.jip2022.bookportalproject.mapper;


import org.mapstruct.Mapper;
import tr.com.obss.jip2022.bookportalproject.dto.UserDto;
import tr.com.obss.jip2022.bookportalproject.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapTo(User user);
}
