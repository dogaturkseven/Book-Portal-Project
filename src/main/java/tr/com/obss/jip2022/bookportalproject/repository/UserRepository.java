package tr.com.obss.jip2022.bookportalproject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.bookportalproject.model.Book;
import tr.com.obss.jip2022.bookportalproject.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByName(String name);
    Optional<User> findUserByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.city =:city where u.username = :username")
    void updateUser(@Param("username") String username, @Param("city") String city);

    @Query("Select u from User u where u.username like :searchedWord%")
    List<User> customSearchUser(String searchedWord);


}
