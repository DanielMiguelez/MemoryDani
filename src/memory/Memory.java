package memory;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

public class Memory extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     
    public static void main(String[] args) {
      
       launch(args);
       
       String playerName ="";
       String passPlayer1 ="";
       String playerName2 ="";
       String passPlayer2 ="";
       
       //Player register
       
       boolean player1Registered = false;
       boolean player2Registered = false;
       
       while(!player1Registered){ 
           
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese nombre jugador 1: ");
        playerName = scanner.nextLine();

        System.out.println("Ingrese contraseña jugador 1: ");
        passPlayer1 = scanner.nextLine();
          
        player1Registered = true;
        
       }
       
       Player player1 = new Player(playerName, passPlayer1);
       
       
       while(!player2Registered && player1Registered ){ 
           
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese nombre jugador 2: ");
        playerName2 = scanner.nextLine();

        System.out.println("Ingrese contraseña jugador 2: ");
        passPlayer2 = scanner.nextLine();
        
        player2Registered = true;
       }
       
       Player player2 = new Player(playerName2, passPlayer2);
       
       //Deck init
       
        Deck deck = new Deck();

        Card Mario = new Card("Mario", 1);
        Card Luigi = new Card("Luigi", 2);
        Card Wario = new Card("Wario", 3);
        Card Toad = new Card("Toad", 4);
        Card Yoshi = new Card("Yoshi", 5);
        Card Peach = new Card("Peach", 6);
        Card Bowser = new Card("Bowser", 7);
        Card Koopa = new Card("Koopa", 8);

        // AGREGAR CARTAS AL MAZO 2 VECES, Y TENEMOS LAS 16.
        
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
        // Barajar el mazo
        deck.shuffle();
        
        // Declarar matriz de cartas
        
     String[][] cards = new String[4][4];
        
     ArrayList<Card> Deck = deck.getCards();
     
     System.out.println();
     
     
     
     // LLENAMOS LA MATRIZ CON EL FOR ANIDADO, CON TODAS LAS CARTAS CREADAS.
     
      if (Deck.size() >= 16) {
            // Llenar la matriz de cartas
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Card card = Deck.get(index++);
                    cards[i][j] = card.getName(); // Asignar el nombre de la carta a la posición correspondiente en la matriz
                    System.out.print(card + "\t"); // Imprimir la carta y agregar un tabulador para mantener las columnas alineadas
                }
                System.out.println(); // Imprimir una nueva línea después de imprimir todas las cartas de una fila
            }
        } else {
            System.out.println("El mazo no tiene suficientes cartas para llenar la matriz.");
        }
        
          //REGISTRO DE LOS JUGADORES CON DOS BUCLES WHILE
          
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;
        boolean player2Turn = false;
        boolean gameFinished = false;
        boolean player1Wins = false;
        boolean player2Wins = false;
        int row1 = 0;
        int col1 = 0;
        int row2 = 0;
        int col2 = 0;
        int card1 = 0;
        int card2 = 0;
        
        
        
       // FLUJO DEL JUEGO Y TURNOS
        
        while(!gameFinished){
            
            if(player1.getPoints()+ player2.getPoints()== 8 && player1.getPoints() > player2.getPoints() ){
                player1.sumVictories(1);
                player1Wins = true;
                gameFinished = true;
                System.out.println("PLAYER 1 WINS!!!!");
                
                
            }else if(player1.getPoints()+ player2.getPoints()== 8 && player1.getPoints() < player2.getPoints() ){
                player2.sumVictories(1);
                player2Wins = true;
                gameFinished = true;
                System.out.println("PLAYER 2 WINS!!!!");
            }else {
                gameFinished = true;
                System.out.println("Match tied!!!!");
            }
             
            while(player1Turn){

                System.out.println("TURNO DEL JUGADOR 1!");

                System.out.println("Ingrese la posición de la fila (0-3) para la primera carta:");
                row1 = scanner.nextInt();

                System.out.println("Ingrese la posición de la columna (0-3) para la primera carta:");
                col1 = scanner.nextInt();

                System.out.println("Ingrese la posición de la fila (0-3) para la segunda carta:");
                row2 = scanner.nextInt();

                System.out.println("Ingrese la posición de la columna (0-3) para la segunda carta:");
                col2 = scanner.nextInt();

                player1Turn = false;

                card1 = row1*4+col1;
                card2 = row2*4+col2;

                if( Deck.get(card1).isTurned()== true && Deck.get(card2).isTurned()== true){
                    System.out.println("NOT POSSIBLE!!!");
                }else{
                   if(Deck.get(card1).equals(Deck.get(card2))){
                    System.out.println("Match!");
                    player1Turn = true;
                    Deck.get(card1).setTurned();
                    Deck.get(card2).setTurned();
                    player1.sumPoints(1);
                    System.out.println("PUNTOS JUGADOR 1! : " + player1.getPoints());
                    System.out.println("PUNTOS JUGADOR 2! : " + player2.getPoints());
                    
                    deck.printCardsMatrix(Deck, cards);

                 }else{
                     System.out.println("NOT MATCH");
                     player1Turn = false;
                     player2Turn = true;
                     
                     deck.printCardsMatrix(Deck, cards);
                 } 
                }
            }
            
            while(player2Turn){

                    System.out.println("TURNO DEL JUGADOR 2!");

                    System.out.println("Ingrese la posición de la fila (0-3) para la primera carta:");
                    row1 = scanner.nextInt();

                    System.out.println("Ingrese la posición de la columna (0-3) para la primera carta:");
                    col1 = scanner.nextInt();

                    System.out.println("Ingrese la posición de la fila (0-3) para la segunda carta:");
                    row2 = scanner.nextInt();

                    System.out.println("Ingrese la posición de la columna (0-3) para la segunda carta:");
                    col2 = scanner.nextInt();

                    player2Turn = false;

                    card1 = row1*4+col1;
                    card2 = row2*4+col2;

                    if( Deck.get(card1).isTurned()== true && Deck.get(card2).isTurned()== true){
                        System.out.println("NOT POSSIBLE!!!");
                        
                        deck.printCardsMatrix(Deck, cards);
                    }else{
                       if(Deck.get(card1).equals(Deck.get(card2))){
                        System.out.println("Match!");
                        player2Turn = true;
                        Deck.get(card1).setTurned();
                        Deck.get(card2).setTurned();
                        player2.sumPoints(1);
                        System.out.println("PUNTOS JUGADOR 1! : " + player1.getPoints());
                        System.out.println("PUNTOS JUGADOR 2! : " + player2.getPoints());
                        
                        deck.printCardsMatrix(Deck, cards);

                        }else{
                             System.out.println("NOT MATCH");
                             player1Turn = true;
                             player2Turn = false;
                             System.out.println("PUNTOS JUGADOR 1! : " + player1.getPoints());
                             System.out.println("PUNTOS JUGADOR 2! : " + player2.getPoints());
                             
                             deck.printCardsMatrix(Deck, cards);
                        } 
                    }  
            }
        } 
    }
}
