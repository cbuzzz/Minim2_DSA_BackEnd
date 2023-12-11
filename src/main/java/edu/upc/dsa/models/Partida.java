package edu.upc.dsa.models;

import edu.upc.dsa.CRUD.util.RandomUtils;

public class Partida {
    String partidaId;
    String username;
    String itemId;
    int mapa;
    int level;
    int points;
    Boolean finished;

    public Partida() {
    }

    public Partida(String username, String itemId, int mapa, int level, int points) {
        this.partidaId = RandomUtils.getId();
        this.username = username;
        this.itemId = itemId;
        this.mapa = mapa;
        this.level = level;
        this.points = points;
        this.finished = false;
    }

    public String getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(String partidaId) {
        this.partidaId = partidaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getMapa() {
        return mapa;
    }

    public void setMapa(int mapa) {
        this.mapa = mapa;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
