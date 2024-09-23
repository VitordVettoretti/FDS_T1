package julioapm.ex1_biblioteca;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcervoImplJdbc implements AcervoRepository {
    private final JdbcTemplate jdbcTemplate;

    public AcervoImplJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Livro> getAll() {
        return this.jdbcTemplate.query("SELECT codigo, titulo, autor, ano FROM livros",
        (resultSet, rowNum) -> new Livro(
            resultSet.getInt("codigo"),
            resultSet.getString("titulo"),
            resultSet.getString("autor"),
            resultSet.getInt("ano")
        ));
    }

    public List<Livro> getLivrosDoAutor(String autor) {
        return null;
    }

    public List<String> getAutores() {
        return null;
    }

    public List<String> getTitulos() {
        return null;
    }

    public List<Livro> getLivrosTitulo(String titulo) {
        return null;
    }

    public boolean cadastraLivroNovo(Livro livro) {
        this.jdbcTemplate.update(
            "INSERT INTO livros(codigo,titulo,autor,ano) VALUES (?,?,?,?)",
            livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno());
        return true;
    }

    @Override
    public boolean removeLivro(int codigo) {
        this.jdbcTemplate.update("DELETE FROM livros WHERE codigo = ?", codigo);
        return true;
    }

    @Override
    public boolean emprestarLivro(int codigoLivro, Usuario usuario) {
        this.jdbcTemplate.update(
            "UPDATE livros SET emprestado = ?, usuario_codigo = ? WHERE codigo = ?",
            true, usuario.getCodigo(), codigoLivro
        );
        return true;
    }

    @Override
    public boolean devolverLivro(int codigoLivro) {
        this.jdbcTemplate.update(
            "UPDATE livros SET emprestado = ?, usuario_codigo = NULL WHERE codigo = ?",
            false, codigoLivro
        );
        return true;
    }
}
