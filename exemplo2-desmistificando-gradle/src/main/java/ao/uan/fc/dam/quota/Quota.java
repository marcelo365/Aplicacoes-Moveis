package ao.uan.fc.dam.quota;

public class Quota {

    private Long id;
    private String autor;
    private String texto;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Quota [id=" + id + ", autor=" + autor + ", texto=" + texto + "]";
    }

    public static void main(String[] args) {
        System.out.println("Aplicação Quota em execução");
        Quota q = new Quota();
        q.setId(1L); // Usando Long.valueOf(1) é outra forma válida.
        q.setAutor("Joao Costa");
        q.setTexto("Deus é vida.");
        System.out.println(q);
    }
}