package guru.qa.restbackend;

import guru.qa.restbackend.domain.BookInfo;
import org.junit.jupiter.api.Test;

import static guru.qa.restbackend.Specs.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest {

    @Test
    void getAllBooksTest() {
        given()
                .spec(request)
                .when()
                .get("/book/getAll")
                .then()
                .spec(responseSpec)
                .log().body();
    }

    @Test
    void getLengthBooks() {
        BookInfo[] book = given()
                .log().all()
                .spec(request)
                .when()
                .get("/book/getAll")
                .then()
                .spec(responseSpec)
                .extract().as(BookInfo[].class);

        assertEquals(3, book.length);
    }

}
