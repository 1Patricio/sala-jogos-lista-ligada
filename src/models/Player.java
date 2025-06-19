package models;

public class Player {
    public String name;
    private String[] inventory;
    private Double score;
    public Room room;
    private int visitedrooms;
    private String[] descriptionVisitedRooms;

    public Player(String name, Room room) {
        this.name = name;
        this.room = room;
        this.inventory = new String[10];
        this.score = 0.0;
        this.visitedrooms = 1;
        this.descriptionVisitedRooms = new String[10];
    }

    public void getRoom() {
        System.out.println(room.roomDescription);
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

    public void changingRoom(Labirinth laririnth, Room newRoom) {
        if (newRoom == null) {
            System.out.println("Error: newRoom is null");
            return;
        }
        room = newRoom;
        score += 1;
        switch (newRoom.roomType) {
            case Normal:
                break;
            case Award:
                award();
                break;
            case Trap:
                trap(laririnth);
                break;
            case Exit:
                int numbersVisitedRooms = 0;
                for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                    if (descriptionVisitedRooms[i] != null) {
                        numbersVisitedRooms++;
                    }
                }
                if (numbersVisitedRooms >= 10) {
                    win();
                } else {
                    System.out.println("Você chegou a saída, mas ainda não visitou todas as salas");
                    System.out.println("Voltando para o hub");
                    goToHub(laririnth);
                }
                break;
            default:
                break;
        }

        boolean visited = false;
        for (int i = 0; i < descriptionVisitedRooms.length; i++) {
            if (newRoom.getRoomDescription().equals(descriptionVisitedRooms[i])) {
                visited = true;
                break;
            }
        }
        if (!visited) {
            for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                if (descriptionVisitedRooms[i] == null || descriptionVisitedRooms[i].isEmpty()) {
                    descriptionVisitedRooms[i] = newRoom.getRoomDescription();
                    visitedrooms++;
                    break;
                }
            }
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

    public void trap(Labirinth labirinth) {
        Double trap = Math.random() * 5;
        if (score > trap) {
            score -= trap;
            System.out.println("You fell into a Trap! Score: " + score);
        } else {
            score = 0.00;
            System.out.println("Você caiu em uma armadilha, mas não tem pontos o suficientes!");
            goToHub(labirinth);
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
        if (room.roomDescription == "Exit") {

            System.out.println("Final Score: " + score);
            System.out.println("Inventory: ");
            for (int i = 0; i < inventory.length; i++) {
                int itemNumber = i + 1;
                if (inventory[i] != null) {
                    System.out.println("   Item: " + itemNumber + " - " + inventory[i]);
                }
            }
            System.out.println("Quantity Visited Rooms: " + visitedrooms);

            System.out.println("Visited Rooms:");
            for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                System.out.println("Room: " + descriptionVisitedRooms[i]);
            }
        }
    }

    public void goToHub(Labirinth labirinth) {
        Room hub = labirinth.getHubRoom();
        if (hub != null) {
            this.room = hub;
            System.out.println("Returning to the hub room:: " + hub.getRoomDescription());
        } else {
            System.out.println("Hub not found!");
        }
    }
}