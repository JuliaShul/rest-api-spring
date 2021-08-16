package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.BookOutput;
import guru.qa.restbackend.domain.BookInfo;
import guru.qa.restbackend.exception.InvalidUsernameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController {

    Map<String, BookInfo> book = Map.of(
            "History", BookInfo.builder()
                    .id(123)
                    .nameBook("History")
                    .author("Nekrasov")
                    .build(),
            "Math", BookInfo.builder()
                    .id(124)
                    .nameBook("Math")
                    .author("Lobov")
                    .build(),
            "English", BookInfo.builder()
                    .id(125)
                    .nameBook("English")
                    .author("Razdrygin")
                    .build()
    );
    @PostMapping("book/add")
    @ApiOperation("Добавить книгу")
    public BookOutput addBooks( @RequestBody BookInfo bookInfo ) {
        if (book.get(bookInfo.getNameBook()) == null) {
            return BookOutput.builder()
                    .id(bookInfo.getId())
                    .nameBook(bookInfo.getNameBook())
                    .author(bookInfo.getAuthor())
                    .build();
        } else {
            throw new InvalidUsernameException();
        }
    }


    @GetMapping("book/getAll")
    @ApiOperation("Получение списка книг")
    public List<BookInfo> getAllBooks() {
        return book.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
