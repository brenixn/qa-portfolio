package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    //GET

    @Test
    @Order(1)
    public void deveRetornarListaDePosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(100));
    }

    @Test
    @Order(2)
    public void deveRetornarPostPorId() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(emptyString()))
                .body("body", not(emptyString()));
    }

    @Test
    @Order(3)
    public void deveRetornar404ParaPostInexistente() {
        given()
                .when()
                .get("/posts/99999")
                .then()
                .statusCode(404);
    }

    //POST

    @Test
    @Order(4)
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
                .body("body", equalTo("Criado via Rest Assured"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    //PUT

    @Test
    @Order(5)
    public void deveAtualizarPostExistente(){
        String body = """
                {
                "id": 1,
                "title": "Título atualizado",
                "body": "Conteúdo atualizado via Rest Assured",
                "userId": 1
                }
                """;

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Título atualizado"))
                .body("body", equalTo("Conteúdo atualizado via Rest Assured"))
                .body("id", equalTo(1));
    }

    //DELETE

    @Test
    @Order(6)
    public void deveDeletarPostExistente(){
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(7)
    public void deveRetornarPostsPorUsuario(){
        given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("userId", everyItem(equalTo(1)));
    }
}