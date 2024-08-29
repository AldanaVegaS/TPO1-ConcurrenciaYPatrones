package Builder;


public interface Builder {
    void setTamaño(int filas, int columnas);
    void setVillano();
    void setZonaContaminada(int cant);
    void setPozos(int cant);
    void setTesoro(int cant);
    void setObstaculos(int cant);
}
