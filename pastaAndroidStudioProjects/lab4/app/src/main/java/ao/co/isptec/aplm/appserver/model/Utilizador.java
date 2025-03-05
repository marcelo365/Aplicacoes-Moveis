package ao.co.isptec.aplm.appserver.model;

public class Utilizador {

    private int id;
    private String nomecompleto;
    private String username;
    private String senha;

    public Utilizador() {

    }

    public Utilizador(String nomecompleto, String username, String senha) {
        this.nomecompleto = nomecompleto;
        this.username = username;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Utilizador [id=" + id + ", nomecompleto=" + nomecompleto + ", username=" + username + ", senha=" + senha
                + "]";
    }

}
