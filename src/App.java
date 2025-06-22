import java.util.Scanner;

import models.Labirinth;
import models.Player;
import models.Room;
import models.TypeRoom;

public class App {
    private static int numbersRooms = 1;

    public static void main(String[] args) {

        Labirinth labirinth = new Labirinth();

        Scanner scanner = new Scanner(System.in);

        boolean autoCreate = true;
        int salasCriadosByUser = 10;

        System.out.println("____Desafio do Labirinto____");
        System.out.println("1 - Iniciar");
        System.out.println("0 - Sair");
        int option = scanner.nextInt();

        boolean game = false;
        boolean formRoomGame = true;

        Room room0 = new Room(0, TypeRoom.Normal, "Sala central do labirinto");
        Room room1 = new Room(1, TypeRoom.Normal, "Entrada escura de pedra úmida.");
        Room room2 = new Room(2, TypeRoom.Trap, "Chão falso com espinhos escondidos.");
        Room room4 = new Room(3, TypeRoom.Normal, "Corredor com tochas apagadas.");
        Room room5 = new Room(4, TypeRoom.Award, "Sala com um baú de ouro reluzente.");
        Room room6 = new Room(5, TypeRoom.Trap, "Armadilha de flechas nas paredes.");
        Room room7 = new Room(6, TypeRoom.Normal, "Passagem estreita cheia de teias de aranha.");
        Room room8 = new Room(7, TypeRoom.Award, "Fonte mágica que restaura a vida.");
        Room room9 = new Room(8, TypeRoom.Trap, "Estátuas que disparam laser quando ativadas.");
        Room room10 = new Room(100, TypeRoom.Exit, "Porta rúnica que leva à saída da dungeon.");

        labirinth.createRoom(room0);
        labirinth.createRoom(room1);
        labirinth.createRoom(room2);
        labirinth.createRoom(room4);
        labirinth.createRoom(room5);
        labirinth.createRoom(room6);
        labirinth.createRoom(room7);
        labirinth.createRoom(room8);
        labirinth.createRoom(room9);
        labirinth.createRoom(room10);

        Player player = new Player(null, room0);

        if (option == 1) {
            player.changingRoom(labirinth, room0);
            game = true;
        } else {
            formRoomGame = false;
        }
        while (formRoomGame) {

            System.out.println("  _| Criar Salas |_");
            System.out.println("1 - Criar uma sala");
            if (autoCreate)
                System.out.println("2 - AutoCriar 9 Salas");
            System.out.println("9 - Começar o jogo");
            int optionRoom = scanner.nextInt();

            switch (optionRoom) {
                case 2:
                    if (autoCreate) {
                        System.out.println("Salas criadas com sucesso!");

                        clearConsole();
                        numbersRooms += 9;
                        autoCreate = false;
                    } else {
                        System.out.println("Você já usou o seu auto create");
                    }
                    break;

                case 1:
                    System.out.println("Digite nome da sala: ");
                    String roomName = scanner.next();
                    System.out.println();

                    System.out.println("Selecione o tipo da sala");
                    int i = 0;
                    for (TypeRoom type : TypeRoom.values()) {
                        i++;
                        System.out.println(i + " - para: " + type);
                    }
                    String selectType = "";
                    int typeRoomOption = scanner.nextInt();

                    switch (typeRoomOption) {
                        case 1:
                            selectType = TypeRoom.Normal.name();
                            break;
                        case 2:
                            selectType = TypeRoom.Trap.name();
                            break;

                        case 3:
                            selectType = TypeRoom.Award.name();
                            break;

                        case 4:
                            selectType = TypeRoom.Exit.name();
                            break;
                    }

                    salasCriadosByUser++;

                    Room newRoom = new Room(salasCriadosByUser, TypeRoom.valueOf(selectType), roomName);

                    labirinth.createRoom(newRoom);
                    System.out.println();
                    System.out.println("Sala criada: " + roomName);
                    clearConsole();
                    numbersRooms++;
                    break;

                case 9:
                    if (numbersRooms >= 10) {
                        formRoomGame = false;
                    } else {
                        System.out.println();
                        System.out
                                .println("Para iniciar o jogo, é necessário criar no minimo 10 salas, você tem apenas "
                                        + numbersRooms + " salas criadas");
                    }
                    break;
            }
            clearConsole();

        }
        if (autoCreate) {
            labirinth.removeRoom(1);
            labirinth.removeRoom(2);
            labirinth.removeRoom(3);
            labirinth.removeRoom(4);
            labirinth.removeRoom(5);
            labirinth.removeRoom(6);
            labirinth.removeRoom(7);
            labirinth.removeRoom(8);
            labirinth.removeRoom(9);
            numbersRooms -= 9;
        }
        while (game) {
            System.out.println("Start Game");
            String mensageName = (player.name == null) ? "1 - Inserir nome do jogador"
                    : "1 - Editar nome do jogador";
            System.out.println(mensageName);
            System.out.println("2 - Mudar de Sala");
            System.out.println("3 - Avançar de Sala");
            System.out.println("4 - Voltar uma Sala");
            System.out.println("5 - Sala atual do Jogador");
            System.out.println("6 - Listar todas as Salas");
            System.out.println("7 - Listar salas já visitadas");
            System.out.println("0 - Sair");
            int optionGame = scanner.nextInt();

            switch (optionGame) {
                case 1:
                    System.out.println("Insira o nome do seu jogador:");
                    String name = scanner.next();
                    player.name = name;
                    clearConsole();
                    break;

                case 2:
                    Room newRoom = null;
                    System.out.println("Escolha o número da sala que deseja se mover: ");
                    int numberRoomChosen = scanner.nextInt();
                    newRoom = labirinth.getRoomNumber(numberRoomChosen);
                    player.changingRoom(labirinth, newRoom);
                    player.getRoom();
                    clearConsole();
                    break;

                case 3:
                    Room nextRoom = labirinth.nextRoom(player.room);
                    player.changingRoom(labirinth, nextRoom);
                    player.getRoom();
                    game = player.win();
                    clearConsole();
                    break;

                case 4:
                    Room previusRoom = labirinth.previusRoom(player.room);
                    player.changingRoom(labirinth, previusRoom);
                    player.getRoom();
                    game = player.win();
                    break;

                case 5:
                    player.getRoom();
                    break;

                case 6:
                    labirinth.showRooms();
                    break;

                case 7:
                    player.salasVisitadas();
                    break;

                case 0:
                    game = false;
                    System.out.println("Obrigado por jogar!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    public final static void clearConsole() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public final static int getNumbersRoomsCreated(){
        return numbersRooms;
    }
}