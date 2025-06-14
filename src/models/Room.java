package models;

public class Room {
    public int roomNumber;
    public String roomType;
    public String roomDescription;
    public Room next;
    public Room previous;

    
    public Room(int roomNumber, String roomType, String roomDescription) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.next = null;
        this.previous = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    
};