package models;

public class Player {
    private String name;
    private String[] inventory;
    private Double score;
    public Room room;

    public Player(String name, Room room) {
        this.name = name;
        this.room = room;
        this.inventory = new String[10]; // exemplo de tamanho fixo
        this.score = 0.0;
    }
}
