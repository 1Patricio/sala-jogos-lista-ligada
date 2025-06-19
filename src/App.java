import java.util.Scanner;

import models.Labirinth;
import models.Player;
import models.Room;
import models.TypeRoom;

public class App {
    public static void main(String[] args) {

        Labirinth labirinth = new Labirinth();
        // // Lista de salas
        Room room0 = new Room(0, TypeRoom.Normal, "Sala central do labirinto");
        Room room1 = new Room(1, TypeRoom.Normal, "Entrada escura de pedra úmida.");
        Room room2 = new Room(2, TypeRoom.Trap, "Chão falso com espinhos escondidos.");
        Room room3 = new Room(3, TypeRoom.Normal, "Corredor com tochas apagadas.");
        Room room4 = new Room(4, TypeRoom.Award, "Sala com um baú de ouro reluzente.");
        Room room5 = new Room(5, TypeRoom.Trap, "Armadilha de flechas nas paredes.");
        Room room6 = new Room(6, TypeRoom.Normal, "Passagem estreita cheia de teias de aranha.");
        Room room7 = new Room(7, TypeRoom.Award, "Fonte mágica que restaura a vida.");
        Room room8 = new Room(8, TypeRoom.Trap, "Estátuas que disparam laser quando ativadas.");
        Room room9 = new Room(9, TypeRoom.Exit, "Porta rúnica que leva à saída da dungeon.");

        labirinth.createRoom(room0);
        labirinth.createRoom(room1);
        labirinth.createRoom(room2);
        labirinth.createRoom(room3);
        labirinth.createRoom(room4);
        labirinth.createRoom(room5);
        
        // player.changingRoom(room1);
        // player.getNameAndRoom();

        // player.changingRoom(room2);
        // player.win();

        Scanner scanner = new Scanner(System.in);
        int option;
        String name;

        System.out.println("____Desafio do Labirinto____");
        System.out.println("1 - Iniciar");
        System.out.println("2 - Sair");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Insira o nome do seu jogador:");
                name = scanner.nextLine();
                Player player = new Player(name, room0);
                labirinth.showRooms();
                break;
        
            default:
                System.out.println("Obrigado por jogar!");
                break;
        }
    }
}
