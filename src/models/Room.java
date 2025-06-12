package models;

public class Room {
    private int roomNumber;
    private String roomType;
    private String roomDescription;
    public Room next;
    public Room previous;

    public Room(int roomNumber, String roomType, String roomDescription) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.next = null;
        this.previous = null;
    }
//teste de novo
};