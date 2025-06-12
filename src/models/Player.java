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

    public void getRoom(){
        System.out.println(room);
    }
    public void getNameAndRoom(){
        System.out.println(name);
        if (room != null) {
            System.out.println("Room: " + room.getRoomNumber());
            System.out.println("Description: " + room.getRoomDescription());
            System.out.println("Tipo: " + room.getRoomType());
        } else {
            System.out.println("Room is null");
        }
    }
}
