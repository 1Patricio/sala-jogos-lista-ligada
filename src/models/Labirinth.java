package models;

public class Labirinth<T> {

    public class RoomKnot<T> {
        public T room;
        public RoomKnot<T> previous;
        public RoomKnot<T> next;

        public RoomKnot(T room) {
            this.room = room;
            this.previous = null;
            this.next = null;
        }
    }

    private RoomKnot<Room> startLabirinth;
    private RoomKnot<Room> endLabirinth;

    public Labirinth() {
        this.startLabirinth = null;
        this.endLabirinth = null;
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

    // ou fazer uma função para validar o "tamanho" do labirinto à parte

    public void removeRoom(int roomNumber) {
        if (startLabirinth == null)
            return;

        RoomKnot<Room> current = startLabirinth;
        while (current != null) {
            if (current.room.roomNumber == roomNumber) {
                if (current == startLabirinth) {
                    startLabirinth = current.next;
                    if (startLabirinth != null)
                        startLabirinth.previous = null;
                } else if (current == endLabirinth) {
                    endLabirinth = current.previous;
                    if (endLabirinth != null)
                        endLabirinth.next = null;
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
        RoomKnot<Room> current = startLabirinth;
        while (current != null) {
            if (current.room.getRoomNumber() == 0) {
                return current.room;
            }
            current = current.next;
        }
        return null;
    }

    public Room getRoomNumber(int number) {
        RoomKnot<Room> current = startLabirinth;
        while (current != null) {
            if (current.room.getRoomNumber() == number) {
                return current.room;
            }
            current = current.next;
        }
        return null;
    }

    public Room nextRoom(Room currentRoom) {
        RoomKnot<Room> atual = startLabirinth;
        while (atual != null) {
            if (atual.room.roomNumber == currentRoom.roomNumber) {
                return (atual.next != null) ? atual.next.room : null;
            }
            atual = atual.next;
        }
        if (currentRoom == null)
            return null;
        return currentRoom.next;
    }

    public Room previusRoom (Room currentRoom){
        RoomKnot<Room> atual = endLabirinth;
        while (atual != null) {
            if (atual.room.roomNumber == currentRoom.roomNumber) {
                return (atual.previous != null) ? atual.previous.room : null;
            }
            atual = atual.previous;
        }
        if (currentRoom == null)
            return null;
        return currentRoom.previous;
    }
}