package entities;

public class Producto {

    private Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;

    // Relacion 1:1 (un producto tiene un codigo de barras)
    private CodigoBarras codigoBarras;
    private boolean eliminado;

    public Producto() {}

    public Producto(Integer id, String nombre, Double precio, Integer stock, CodigoBarras codigoBarras, boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.codigoBarras = codigoBarras;
        this.eliminado = eliminado;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public CodigoBarras getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(CodigoBarras codigoBarras) { this.codigoBarras = codigoBarras; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", codigoBarras=" + (codigoBarras != null ? codigoBarras.getNumero() : "SIN CB") +
                ", eliminado=" + eliminado +
                '}';
    }
}
