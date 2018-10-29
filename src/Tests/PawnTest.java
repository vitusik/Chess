import Pieces.Pawn;

public class PawnTest {
    public static void test1()
    {
        Pawn p1 = new Pawn(1,6, true);
        System.out.println(p1.move_check(1,5));
        Pawn p2 = new Pawn(2,1,false);
        System.out.println(p2.move_check(2,2));
    }
    public static void main(String[] args){
        test1();
    }
}
