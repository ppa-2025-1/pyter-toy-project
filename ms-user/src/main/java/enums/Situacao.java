package enums;

public enum Situacao {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em Andamento"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
