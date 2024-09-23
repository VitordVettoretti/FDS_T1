public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private int ano;
    private boolean emprestado;
    private Usuario usuarioEmprestado;

    public Livro(int codigo, String titulo, String autor, int ano, boolean emprestado, Usuario usuarioEmprestado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.emprestado = false;
        this.usuarioEmprestado = null;
    }

    // Getters e setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public Usuario getUsuarioEmprestado() {
        return usuarioEmprestado;
    }

    public void setUsuarioEmprestado(Usuario usuarioEmprestado) {
        this.usuarioEmprestado = usuarioEmprestado;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ano=" + ano + 
               ", emprestado=" + emprestado + ", usuarioEmprestado=" + (usuarioEmprestado != null ? usuarioEmprestado.getNome() : "Nenhum") + "]";
    }
}