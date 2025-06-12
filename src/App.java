import models.Player;
import models.Room;

public class App {
    public static void main(String[] args) throws Exception {
        Room hub = new Room(0, null, null);
        Player player = new Player("Anderson", hub);

        player.getNameAndRoom();
    }
}
