package Builder;


public interface Builder {
    public void setTamaño(int filas, int columnas);
    public void setVillano();
    public void setZonaContaminada(int cant);
    public void setPozos(int cant);
    public void setTesoro(int cant);
    public void setObstaculos(int cant);
    public Mapa build();
}
