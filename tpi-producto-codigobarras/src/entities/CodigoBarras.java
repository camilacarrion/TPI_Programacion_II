package entities;

public class CodigoBarras {

    private Integer id;
    private String numero;
    private boolean eliminado;

    public CodigoBarras() {
    }

    public CodigoBarras(Integer id, String numero, boolean eliminado) {
        this.id = id;
        this.numero = numero;
        this.eliminado = eliminado;
    }

    public CodigoBarras(String numero) {
        this.numero = numero;
        this.eliminado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public String toString() {
        return "CodigoBarras{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", eliminado=" + eliminado +
                '}';
    }
}
