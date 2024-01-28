package tr.com.obss.jip2022.bookportalproject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.obss.jip2022.bookportalproject.model.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    Optional<Book> findBookByTitle(String title);
    Optional<Book> findBookById(long id);

    @Query("select b from Book b where b.authorName=:name")
    List<Book> findBookByAuthor(@Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.title =:updatedTitle where b.title = :title")
    void updateBookTitle(@Param("title") String title, @Param("updatedTitle") String updatedTitle);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.authorName =:updatedName where b.authorName = :name")
    void updateBookAuthor(@Param("name") String name, @Param("updatedName") String updatedName);

    @Query("Select b from Book b where b.title like :searchedWord%")
    List<Book> CustomSearchBooks(String searchedWord);

    List<Book> searchBookByGenre(String genre);

}
