public class ObjVehiculo {
    private String Placa;
    private String Tipo;
    private int Categoria; // 1 = Flypass, 2 = Normal
    private double ValorTarifa;
    private int Consecutivo;
    private int Estado; // 1 = En espera, 2 = Despachado

    public ObjVehiculo() {
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public double getValorTarifa() {
        return ValorTarifa;
    }

    public void setValorTarifa(double valorTarifa) {
        ValorTarifa = valorTarifa;
    }

    public int getConsecutivo() {
        return Consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        Consecutivo = consecutivo;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

}