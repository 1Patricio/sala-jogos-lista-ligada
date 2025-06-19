import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import models.Labirinth;
import models.Player;
import models.Room;
import models.TypeRoom;

public class App {
    public static void main(String[] args) {

        Labirinth labirinth = new Labirinth();
        // // Lista de salas
        Room room1 = new Room(0, TypeRoom.Normal, "Sala central do labirinto");
        Room room2 = new Room(1, TypeRoom.Normal, "Entrada escura de pedra úmida.");
        Room room3 = new Room(2, TypeRoom.Trap, "Chão falso com espinhos escondidos.");
        Room room4 = new Room(3, TypeRoom.Normal, "Corredor com tochas apagadas.");
        Room room5 = new Room(4, TypeRoom.Award, "Sala com um baú de ouro reluzente.");
        Room room6 = new Room(5, TypeRoom.Trap, "Armadilha de flechas nas paredes.");
        Room room7 = new Room(6, TypeRoom.Normal, "Passagem estreita cheia de teias de aranha.");
        Room room8 = new Room(7, TypeRoom.Award, "Fonte mágica que restaura a vida.");
        Room room9 = new Room(8, TypeRoom.Trap, "Estátuas que disparam laser quando ativadas.");
        Room room10 = new Room(9, TypeRoom.Exit, "Porta rúnica que leva à saída da dungeon.");

        List<Room> listRooms = new ArrayList<>();

        labirinth.createRoom(room1);
        labirinth.createRoom(room2);
        labirinth.createRoom(room3);
        labirinth.createRoom(room4);
        labirinth.createRoom(room5);
        labirinth.createRoom(room6);
        labirinth.createRoom(room7);
        labirinth.createRoom(room8);
        labirinth.createRoom(room9);
        labirinth.createRoom(room10);

        listRooms.add(room1);
        listRooms.add(room2);
        listRooms.add(room3);
        listRooms.add(room4);
        listRooms.add(room5);
        listRooms.add(room6);
        listRooms.add(room7);
        listRooms.add(room8);
        listRooms.add(room9);
        listRooms.add(room10);

        Scanner scanner = new Scanner(System.in);
        Player player = new Player(null, null);


        System.out.println("____Desafio do Labirinto____");
        System.out.println("1 - Iniciar");
        System.out.println("0 - Sair");
        int option = scanner.nextInt();
        boolean game = (option == 1) ? true : false;

        while (game) {
            System.out.println("Start Game");
            String mensageName = (player.name == null) ? "1 - Inserir nome do jogador" : "1 - Editar nome do jogador";
            System.out.println(mensageName);
            String enterLabirinth = (player.room == null) ? "2 - Entrar no labirinto" : "2 - Encontrar sala";
            System.out.println(enterLabirinth);
            System.out.println("0 - Sair");
            int optionGame = scanner.nextInt();

            switch (optionGame) {
                case 1:
                    System.out.println("Insira o nome do seu jogador:");
                    String name = scanner.next();
                    player.name = name;
                    break;
                case 2:
                    if(player.room == null){
                        System.out.println(player.name + " está entrando no labirinto . . .");
                        System.out.println(player.name + " encontra a primeira sala do labirinto, e tem que tomar uma decisão:");
                        System.out.println("____________________________");
                        System.out.println("E - Entrar na sala");
                        System.out.println("A - Avançar para próxima sala");
                        System.out.println("____________________________");
                        String choose = scanner.next();

                        switch (choose.toUpperCase()) {
                            case "E":
                                System.out.println(player.name + " entrou na sala " + room1.roomDescription + " por agora, está seguro, mas ainda há mais para progredir...");
                                player.room = room1;
                                
                                break;
                            case "A":
                                System.out.println(player.name + " avança para a próxima sala, adentrando mais o labirinto");
                                player.changingRoom(labirinth, chooserRooms(listRooms));
                                break;
                            default:
                                break;
                        }

                    }
                    System.out.println("Ao adentrar mais a sala " + player.name + "encontra uma nova porta, e tem que tomar uma decisão:");


                    
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

    public static Room chooserRooms(List<Room> listRooms){

        Random random = new Random();

        int chooser = random.nextInt(listRooms.size());
        
        return listRooms.get(chooser);
    }
}
