package models;

public class Player {
    private String name;
    private String[] inventory;
    private Double score;
    public Room room;
    private int visitedrooms;

    public Player(String name, Room room) {
        this.name = name;
        this.room = room;
        this.inventory = new String[10]; 
        this.score = 0.0;
        this.visitedrooms = 1;
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

    public void changingRoom(Room newRoom){
        visitedrooms++;
        room = newRoom;
    }

    public void addItem(String item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                return;
            }
        }
        System.out.println("Inventory is full. Cannot add item: " + item);
    }

    public void trapPoints(){
        Double trap = Math.random() * 10;
        if (trap > score){
            score -= trap;
        } else {
            score = 0.00;
        }
    }

    public void win(){
        System.out.println("Final Score: " + score);
        System.out.println("Inventory: ");
        for (int i = 0; i < inventory.length; i++){
            int itemNumber = i++;
            while (inventory[i] != null) {
                System.out.println("   Item: " + itemNumber + " - " + inventory[i]);
            }
        }
        System.out.println("Visited Vooms: " + visitedrooms);
    }
}