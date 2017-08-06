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
        player1.gameMain();
        
    }
    
    // texasholdem(int args){
        
    //     System.out.println("Your current chips are " + chips);
        
    // }

    
    public static void gameMain(){
        System.out.println("Game Start,Good Luck!");
        //heart club diamond spade 
        List<String> cardOnBoard = new ArrayList<String>();
        int[] playerHand = new int[2];
        int[] dealerHand = new int[2];
        int[] board = new int[5];

        playerHand[0] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(playerHand[0]));
        System.out.println("Your first card is " + playerHand[0]);
        dealerHand[0] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(dealerHand[0]));
        System.out.println("Dealer's first card is " + dealerHand[0]);
        playerHand[1] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(playerHand[1]));
        System.out.println("Your second card is " + playerHand[1]);
        dealerHand[1] = dealer(cardOnBoard);
        cardOnBoard.add(Integer.toString(dealerHand[1]));
        System.out.println("Dealer's second card is " + dealerHand[1]);


        System.out.println("-------------------------------------------");
        System.out.println("check or bet?");

        Scanner in = new Scanner(System.in);
        String com = in.nextLine();
        
        if(com.equals("b")){
            System.out.println("-------------------------------------------");
            System.out.println("please enter how much you want to bet ANTE/BLIND.");
            String bet = in.nextLine();
            
        }else{
            board[0]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[0]));
            board[1]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[1]));
            board[2]=dealer(cardOnBoard);
            cardOnBoard.add(Integer.toString(board[2]));
            System.out.println("Flop Cards are /" + board[0]+"/"+board[1]+"/"+board[2]);
        }
    }


    public static int dealer(List exceptCards){
        Random random = new Random();
        int card;
        int size = exceptCards.size();
        System.out.println("There are " + size +" cards on board.");
        if(size>0){
            
            do{
                card = random.nextInt(52)+1;
            }while(checkIfRepeat(card,exceptCards)==false);

            return card;
          
        }else{
            card = random.nextInt(52)+1;
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