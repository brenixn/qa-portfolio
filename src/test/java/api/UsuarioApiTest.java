package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsuarioApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void deveRetornarListaDePosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(100));
    }

    @Test
    public void deveRetornarPostPorId() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyString()));
    }

    @Test
    public void deveRetornar404ParaPostInexistente() {
        given()
                .when()
                .get("/posts/99999")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveCriarNovoPost() {
        String body = """
                {
                    "title": "Teste",
                    "body": "Criado via Rest Assured",
                    "userId": 1
                }
                """;

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Teste"))
                .body("id", not(emptyString()));
    }
}