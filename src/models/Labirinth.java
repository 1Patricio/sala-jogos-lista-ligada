package models;

public class Labirinth {

    public class RoomKnot<T>{
        public Room room;
        public RoomKnot previous;
        public RoomKnot next;

        public RoomKnot(Room room){
            this.room = room;
            this.previous = null;
            this.next = null;
        }
    }

    private RoomKnot startLabirinth;
    private RoomKnot endLabirinth;

    public Labirinth(){
        this.startLabirinth = null;
        this.endLabirinth = null;
        //implementar validação para que o labirinto não tenha menos que 10 salas, creio que seja necessário criar aqui
    }

    public void createRoom(Room room) {
        RoomKnot<Room> newKnot = new RoomKnot<>(room);
        if (startLabirinth == null) {
            startLabirinth = newKnot;
            endLabirinth = newKnot;
        } else {
            endLabirinth.next = newKnot;
            newKnot.previous = endLabirinth;
            endLabirinth = newKnot;
        }
    }

    public void showRooms() {
        if (startLabirinth == null) {
            System.out.println("Não há salas para visualização");
            return;
        }
    
        RoomKnot current = startLabirinth;
        while (current != null) {
            System.out.print(current.room + " ");
            current = current.next;
        }
        System.out.println();
    }

    //ou fazer uma função para validar o "tamanho" do labirinto à parte

    public void removeRoom(int roomNumber) {
        if (startLabirinth == null) return;

        RoomKnot<Room> current = startLabirinth;
        while (current != null) {
            if (current.room.roomNumber == roomNumber) {
                if (current == startLabirinth) {
                    startLabirinth = current.next;
                    if (startLabirinth != null) startLabirinth.previous = null;
                } 
				else if (current == endLabirinth) {
                    endLabirinth = current.previous;
                    if (endLabirinth != null) endLabirinth.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                return;
            }
            current = current.next;
        }
    }

    public Room getHubRoom() {
        RoomKnot current = startLabirinth;
        while (current != null) {
            if (current.room.roomNumber == 0) {
                return current.room;
            }
            current = current.next;
        }
        return null;
    }

    public Room getRoomNumber(int number){
        RoomKnot current = startLabirinth;
        while (current != null) {
            if (current.room.roomNumber == number) {
                return current.room;
            }
            current = current.next;
        }
        return null;
    }
}
