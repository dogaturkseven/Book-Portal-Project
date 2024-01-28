package tr.com.obss.jip2022.bookportalproject.model;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id //primary key'i belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generated value autoincrement sekilde oluşmasını sağlar
    private long id;
}
