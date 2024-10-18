package ao.co.isptec.aplm.listnotesactivity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Nota implements Serializable {
    private String titulo;
    private String descricao;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {
        return this.titulo;
    }

}
