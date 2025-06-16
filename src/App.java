import models.Player;
import models.Room;
import models.TypeRoom;

public class App {
    public static void main(String[] args) {
        // Cria algumas salas
        Room sala0 = new Room(0, TypeRoom.Normal, "Sala central do labirinto");
        Room sala1 = new Room(1, TypeRoom.Normal, "Sala comum");
        Room sala2 = new Room(2, TypeRoom.Award, "Sala do tesouro");

        Player player = new Player("Anderson", sala0);

        // Exemplo de uso dos métodos
        player.getNameAndRoom();

        player.changingRoom(sala1);
        player.getNameAndRoom();

        player.changingRoom(sala2);
        player.win();
    }
}
