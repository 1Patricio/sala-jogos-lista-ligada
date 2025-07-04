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
        this.visitedrooms = 0;
        this.descriptionVisitedRooms = new String[100];
    }

    public void getRoom() {
        System.out.println();
        System.out.println("====| Sala |====");
        System.out.println(room.roomDescription);
        System.out.println("Tipo: " + room.roomType);
    }

    public void getNameAndRoom() {
        System.out.println(name);
        if (room != null) {
            System.out.println("Sala: " + room.getRoomNumber());
            System.out.println("Informações da sala: " + room.getRoomDescription());
            System.out.println("Tipo: " + room.getRoomType());
        } else {
            System.out.println("Erro de execução: Esta sala não existe");
        }
    }

    public void changingRoom(Labirinth laririnth, Room newRoom) {
        if (newRoom == null) {
            System.out.println("Erro de execução: Esta sala não existe (New Room is Nul)");
            return;
        }
        room = newRoom;
        score += 1;
        switch (newRoom.roomType) {
            case Normal:
                break;

            case Award:
                for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                    if (descriptionVisitedRooms[newRoom.roomNumber] != newRoom.roomDescription) {
                        descriptionVisitedRooms[newRoom.roomNumber] = newRoom.roomDescription;
                        visitedrooms++;
                        award();
                        break;
                    }
                }
                break;

            case Trap:
                for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                    if (descriptionVisitedRooms[newRoom.roomNumber] != newRoom.roomDescription) {
                        descriptionVisitedRooms[newRoom.roomNumber] = newRoom.roomDescription;
                        visitedrooms++;
                        trap(laririnth);
                        break;
                    }
                }
                break;

            case Exit:
                int numbersVisitedRooms = 0;
                descriptionVisitedRooms[newRoom.roomNumber] = newRoom.roomDescription;
                visitedrooms++;
                for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                    if (descriptionVisitedRooms[i] != null) {
                        numbersVisitedRooms++;
                    }
                }
                if (numbersVisitedRooms <= 9) {
                    System.out.println("Você chegou a saída, mas ainda não visitou todas as salas");
                    goToHub(laririnth);
                } else {
                    win();
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
        System.out.println("Inventário cheio. Não foi possível colocar o item: " + item);
    }

    public void trap(Labirinth labirinth) {
        Double trap = Math.random() * 5;
        if (score > trap) {
            score -= trap;
            System.out.println("Você caiu em uma armadilha! Pontuação: " + score);
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
            System.out.println("Que sorte! Você recebeu " + luck + " pontos");
        } else {
            String[] awards = new String[] { "Machado", "Espada", "Escudo", "Poção", "Capacete" };
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == null) {
                    addItem(awards[luck - 5]);
                    System.out
                            .println("Que sorte! Você recebeu um item " + inventory[i] + " Inventário atual:  " + (i + 1));
                    break;
                }
            }
        }
    }

    public boolean win() {
        boolean game = true;
        if (room.roomType.name() == "Saída") {
            game = false;

            System.out.println();
            System.out.println("===| Final |===");
            System.out.println("Pontuação Final: " + score);
            System.out.println("Inventário: ");
            for (int i = 0; i < inventory.length; i++) {
                int itemNumber = i + 1;
                if (inventory[i] != null) {
                    System.out.println("   Item: " + itemNumber + " - " + inventory[i]);
                }
            }
            System.out.println("Quantidade de Salas Visitadas: " + visitedrooms);

            System.out.println("Salas Visitadas:");
            for (int i = 0; i < descriptionVisitedRooms.length; i++) {
                if (descriptionVisitedRooms[i] != null) {
                    System.out.println("Sala: " + descriptionVisitedRooms[i]);
                }
            }
            return game;
        }
        return game;
    }

    public void salasVisitadas() {
        System.out.println();
        System.out.println("Quantidade de Salas Visitadas: " + visitedrooms);

        System.out.println("Salas Visitadas:");
        for (int i = 0; i < descriptionVisitedRooms.length; i++) {
            if (descriptionVisitedRooms[i] != null) {
                System.out.println("Sala: " + descriptionVisitedRooms[i]);
            }
        }
        System.out.println();
    }

    public void goToHub(Labirinth labirinth) {
        Room hub = labirinth.getHubRoom();
        if (hub != null) {
            this.room = hub;
            System.out.println("Retornando à Sala Inicial: " + hub.getRoomDescription());
        } else {
            System.out.println("Sala Inicial não encontrada!");
        }
    }
}