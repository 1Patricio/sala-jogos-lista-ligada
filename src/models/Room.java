package models;

public class Room {
    public int roomNumber;
    public TypeRoom roomType;
    public String roomDescription;
    public Room next;
    public Room previous;

    
    public Room(int roomNumber, TypeRoom roomType, String roomDescription) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.next = null;
        this.previous = null;
    }

    public Room getHubRoom(){
        Room current = this;
        while (current.previous != null) {
            current = current.previous;
        }
        while (current != null) {
            if (current.roomNumber == 1) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public TypeRoom getRoomType() {
        return roomType;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    
};