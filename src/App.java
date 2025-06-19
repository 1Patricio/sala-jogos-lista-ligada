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
        labirinth.createRoom(room6);
        labirinth.createRoom(room7);
        labirinth.createRoom(room8);
        labirinth.createRoom(room9);

        // player.changingRoom(room1);
        // player.getNameAndRoom();

        // player.changingRoom(room2);
        // player.win();

        Scanner scanner = new Scanner(System.in);
        Player player = new Player(null, room2);

        System.out.println("____Desafio do Labirinto____");
        System.out.println("1 - Iniciar");
        System.out.println("2 - Sair");
        int option = scanner.nextInt();
        boolean game = (option == 1) ? true : false;

        while (game) {
            System.out.println("Start Game");
            String mensageName = (player.name == null) ? "1 - Inserir nome do jogador" : "1 - Editar nome do jogador";
            System.out.println(mensageName);
            System.out.println("2 - Mudar de Sala");
            System.out.println("3 - Avançar de Sala");
            System.out.println("4 - Sala atual do player1");
            System.out.println("9 - Sair");
            int optionGame = scanner.nextInt();

            switch (optionGame) {
                case 1:
                    System.out.println("Insira o nome do seu jogador:");
                    String name = scanner.next();
                    player.name = name;
                    break;
                case 2:
                    player.changingRoom(labirinth, room9);
                    break;

                case 3:
                    Room nextRoom = labirinth.nextRoom(player.room);
                    player.changingRoom(labirinth, nextRoom);
                    player.getRoom();
                    break;

                case 4:
                    player.getRoom();
                    break;
                case 9:
                    game = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();

    }
}
