package me.sukumdev.bookapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author {

    @Id
    @GeneratedValue
    private Long authorId;

    private String firstName;
    private String lastName;

    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnore
    private List<Book> books;

}
