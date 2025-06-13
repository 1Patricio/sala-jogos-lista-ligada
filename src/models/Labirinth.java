package models;

public class Labirinth {

    private Room[] room;



    public class RoomPointer{
        public Room room;
        public RoomPointer next;

        public RoomPointer(Room room){
            this.room = room;
            this.next = null;

        }
    }

    private RoomPointer startLabirinth;

    public Labirinth(){
        this.startLabirinth = null;
    }

    public void createRooms(Room room){

    }
}
