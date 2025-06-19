package models;

public class Player {
    public String name;
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

    public void getRoom() {
        System.out.println(room);
    }

    public void getNameAndRoom() {
        System.out.println(name);
        if (room != null) {
            System.out.println("Room: " + room.getRoomNumber());
            System.out.println("Description: " + room.getRoomDescription());
            System.out.println("Tipo: " + room.getRoomType());
        } else {
            System.out.println("Room is null");
        }
    }

    public void changingRoom(Room newRoom) {
        visitedrooms++;
        room = newRoom;
        score += 1;
        switch (newRoom.roomType) {
            case Normal:
                break;
            case Award:
                award();
                break;
            case Trap:
                trap();
                break;
            case Exit:
                win();
                break;
            default:
                break;
        }
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

    public void trap() {
        Double trap = Math.random() * 5;
        if (score > trap) {
            score -= trap;
            System.out.println("You fell into a Trap! Score: " + score);
        } else {
            score = 0.00;
            room = room.getHubRoom();
            System.out.println("You fell into a Trap! Returning to the room: " + room.roomDescription);
        }
    }

    public void award() {
        int luck = (int) (Math.random() * 10);
        if (luck < 5) {
            score += luck;
            System.out.println("Luck, receive " + luck + " points");
        } else {
            String[] awards = new String[] { "Axe", "Sword", "Shield", "Potion", "Helmet" };
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == null) {
                    addItem(awards[luck - 5]);
                    System.out
                            .println("Luck, receive item " + inventory[i] + " invetory storage:  " + (i + 1));
                    break;
                }
            }
        }
    }

    public void win() {
        System.out.println("Final Score: " + score);
        System.out.println("Inventory: ");
        for (int i = 0; i < inventory.length; i++) {
            int itemNumber = i+1;
            if (inventory[i] != null) {
                System.out.println("   Item: " + itemNumber + " - " + inventory[i]);
            }
        }
        System.out.println("Visited Vooms: " + visitedrooms);
    }
}