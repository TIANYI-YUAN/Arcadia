import java.io.PrintWriter;
import java.util.*;

import java.io.File;
import java.lang.Math;

public class texasholdem{

    public static void main(String[] args){
        int chips;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Texas-Holdem!");
        System.out.println("Please enter the chips");
      
 

        texasholdem player1 = new texasholdem();
        chips = in.nextInt();
        System.out.println("Your current chips are "+chips);
        do{
            player1.gameMain();
        }while(1==1);
    }
    
    // texasholdem(int args){
        
    //     System.out.println("Your current chips are " + chips);
        
    // }

    
    public static void gameMain(){
        
        //heart club diamond spade 
        List<String> cardOnBoard = new ArrayList<String>();
        int[] playerHand = new int[2];
        int[] dealerHand = new int[2];
        int[] board = new int[5];

        Scanner in = new Scanner(System.in);
        



        System.out.println("-------------------------------------------");
        System.out.println("Please enter the Trips and Ante/Blind, separated by a space.");

        String startbet = in.nextLine();
        String[] betArray = startbet.split(" ");
        int trips = Integer.parseInt(betArray[0]);
        int anteANDblind = Integer.parseInt(betArray[1]);
        String[] cardPool = {"Heart A","Heart 2","Heart 3","Heart 4","Heart 5","Heart 6","Heart 7","Heart 8","Heart 9","Heart 10","Heart Jack","Heart Queen","Heart King"
        ,"Club A","Club 2","Club 3","Club 4","Club 5","Club 6","Club 7","Club 8","Club 9","Club 10","Club Jack","Club Queen","Club King"
        ,"Diamond A","Diamond 2","Diamond 3","Diamond 4","Diamond 5","Diamond 6","Diamond 7","Diamond 8","Diamond 9","Diamond 10","Diamond Jack","Diamond Queen","Diamond King"
        ,"Spade A","Spade 2","Spade 3","Spade 4","Spade 5","Spade 6","Spade 7","Spade 8","Spade 9","Spade 10","Spade Jack","Spade Queen","Spade King"};

        System.out.println("Your trip is "+trips+"| Your Ante/Blind is "+anteANDblind);
        System.out.println("Game Start,Good Luck!");

        playerHand[0] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(playerHand[0]));
        System.out.println("Your first card is " + cardPool[playerHand[0]]);
        dealerHand[0] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(dealerHand[0]));
        // System.out.println("Dealer's first card is " + cardPool[dealerHand[0]]);
        playerHand[1] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(playerHand[1]));
        System.out.println("Your second card is " + cardPool[playerHand[1]]);
        dealerHand[1] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(dealerHand[1]));
        // System.out.println("Dealer's second card is " + cardPool[dealerHand[1]]);


        System.out.println("-------------------------------------------");
        System.out.println("Check or Bet?");
        String com = in.nextLine();
        int playBet = 0;
        int betFlag = 0;
        if(com.equals("b")){
            System.out.println("-------------------------------------------");
            System.out.println("3 or 4 times?");
            int betTimes = in.nextInt();
            
            if(betTimes == 3){
                playBet = 3 * anteANDblind; 
            }else if(betTimes == 4){
                playBet = 4 * anteANDblind;
            }
            betFlag = 1;

        }
            board[0]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[0]));
            board[1]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[1]));
            board[2]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[2]));
            System.out.println("Flop Cards are /" + cardPool[board[0]]+"/"+cardPool[board[1]]+"/"+cardPool[board[2]]);
        

        
        System.out.println("-------------------------------------------");
        if(betFlag ==0){
            System.out.println("Check or Bet?");
            com = in.nextLine();
        }else{
            com = "null";
        }

        
        
        if(com.equals("b")){
            System.out.println("-------------------------------------------");
            System.out.println("2 times betted.");
            playBet = 2 * anteANDblind;
            betFlag = 1;
            
        }
            board[3]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[3]));
            board[4]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[4]));
            
            System.out.println("River Turn Cards are /" + cardPool[board[3]]+"/"+cardPool[board[4]]);
        

        System.out.println("-------------------------------------------");
        if(betFlag==0){
            System.out.println("Bet or Fold?");
            com = in.nextLine();
        }else{
            com = "null";
        }
        
        if(com.equals("b")){
            System.out.println("-------------------------------------------");
            System.out.println("1 times betted.");
            playBet = anteANDblind;
            betFlag = 1;
            
            
        }
            System.out.println("-------------------------------------------");
            System.out.println("Dealer's Cards are /" + cardPool[dealerHand[0]]+"/"+cardPool[dealerHand[1]]);
        


    }


    public static int dealer(List exceptCards){
        Random random = new Random();
        int card;
        int size = exceptCards.size();
        // System.out.println("There are " + size +" cards on board.");
        if(size>0){
            
            do{
                card = random.nextInt(52);
            }while(checkIfRepeat(card,exceptCards)==false);

            return card;
          
        }else{
            card = random.nextInt(52);
            return card;
        }
    }

    public static boolean checkIfRepeat(int args,List exceptCards){
        int size = exceptCards.size();
            for(int i = 0 ; i< size;i++){
                if(args == Integer.parseInt(exceptCards.get(i).toString())){
                    return false;
                };
            }
        return true;

    }

    // public void add(int chips){
    //     int total;
    //     total = 10 + chips;
    //     System.out.println("Your after chips are "+total);
    // }


}