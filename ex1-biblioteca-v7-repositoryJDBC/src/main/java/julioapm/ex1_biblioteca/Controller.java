package julioapm.ex1_biblioteca;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/biblioteca")
public class Controller {
    private final AcervoRepository acervo;

    public Controller(AcervoRepository acervo) {
        this.acervo = acervo;
    }

    @GetMapping
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return acervo.getAll();
    }

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosDoAutor(@RequestParam String autor) {
        return acervo.getLivrosDoAutor(autor);
    }

    @GetMapping("/livrostitulo/{titulo}")
    public ResponseEntity<List<Livro>> getLivrosPorTitulo(@PathVariable String titulo) {
        var resultado = acervo.getLivrosTitulo(titulo);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping("/autores")
    public List<String> getAutores() {
        return acervo.getAutores();
    }

    @GetMapping("titulos")
    public List<String> getTitulos() {
        return acervo.getTitulos();
    }

    @PostMapping("/novolivro")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro) {
        return acervo.cadastraLivroNovo(livro);
    }

    @PostMapping("/emprestarlivro/{codigoLivro}")
    public ResponseEntity<String> emprestarLivro(@PathVariable int codigoLivro, @RequestBody Usuario usuario) {
        boolean sucesso = acervo.emprestarLivro(codigoLivro, usuario);
        if (sucesso) {
            return ResponseEntity.status(HttpStatus.OK).body("Livro emprestado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao emprestar o livro.");
    }

    @PostMapping("/devolverlivro/{codigoLivro}")
    public ResponseEntity<String> devolverLivro(@PathVariable int codigoLivro) {
        boolean sucesso = acervo.devolverLivro(codigoLivro);
        if (sucesso) {
            return ResponseEntity.status(HttpStatus.OK).body("Livro devolvido com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao devolver o livro.");
    }

    @GetMapping("/livrosdisponiveis")
    public List<Livro> getLivrosDisponiveis() {
        return acervo.getLivrosDisponiveis();
    }
    
    @GetMapping("/livrosemprestados/{usuarioCodigo}")
    public List<Livro> getLivrosEmprestadosAoUsuario(@PathVariable int usuarioCodigo) {
        return acervo.getLivrosEmprestadosAoUsuario(usuarioCodigo);
    }
}
