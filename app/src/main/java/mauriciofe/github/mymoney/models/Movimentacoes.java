package mauriciofe.github.mymoney.models;

import java.util.Date;

public class Movimentacoes {
    private int id;
    private String descricao;
    private double valor;
    private Date data;
    private String observacoes;
    private int categoria_id;
    private int tipoMovimentacao_id;
    private int repeticao_id;
    private int usuario_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getTipoMovimentacao_id() {
        return tipoMovimentacao_id;
    }

    public void setTipoMovimentacao_id(int tipoMovimentacao_id) {
        this.tipoMovimentacao_id = tipoMovimentacao_id;
    }

    public int getRepeticao_id() {
        return repeticao_id;
    }

    public void setRepeticao_id(int repeticao_id) {
        this.repeticao_id = repeticao_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
