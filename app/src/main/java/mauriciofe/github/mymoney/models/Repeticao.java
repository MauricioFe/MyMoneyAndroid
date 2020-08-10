package mauriciofe.github.mymoney.models;

public class Repeticao {
    private int id;
    private String descricao;
    private String periodo;
    private int numParcelas;
    private int numOcorrencias;

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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }

    public int getNumOcorrencias() {
        return numOcorrencias;
    }

    public void setNumOcorrencias(int numOcorrencias) {
        this.numOcorrencias = numOcorrencias;
    }
}
