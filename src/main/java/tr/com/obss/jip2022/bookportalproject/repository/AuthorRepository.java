package tr.com.obss.jip2022.bookportalproject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.bookportalproject.model.Author;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findAuthorByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Author a set a.name =:updatedName where a.name = :name")
    void updateAuthorName(@Param("name") String name, @Param("updatedName") String updatedName);


}
