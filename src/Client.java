import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        tree.add(100);
        tree.add(125);
        tree.add(150);
        tree.add(110);
        tree.add(50);
        tree.add(25);
        tree.add(10);

        tree.delete(10);
        tree.print();
        System.out.println();
    }
}
