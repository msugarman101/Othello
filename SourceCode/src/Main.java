public class Main {
    public static void main(String[] args) {
        Board board = new Board(); //create a board object
        board.setUp(); //set up the board for playing


        //the following code is all just testing existing aspects of Board.java (remove later)
        System.out.println("Printing the board!");
        board.printBoard();

        int add = board.add(3, 3, 1);
        System.out.println("first add attempt: " + add);

        add = board.add(1, 1, 2);
        System.out.println("second add attempt: " + add);

        add = board.add(3, 2, 2);
        System.out.println("third add attempt: " + add);

        System.out.println(" ");
        System.out.println("Updated board: ");
        board.printBoard();

        //xx

    }
}
