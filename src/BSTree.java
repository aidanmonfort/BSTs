import java.util.ArrayList;

public class BSTree <T extends Comparable<T>>{
    private BinaryNode<T> root;

    public BSTree(){
        root = null;
    }

    public void add(T data){
        if(root == null){
            root = new BinaryNode<>(data);
        }
        else {
            addRecur(root, data);
        }
    }

    public boolean exists(T data){
        BinaryNode<T> node = root;
        while(node != null){
            if(node.getData() == data){
                return true;
            }
            node = (data.compareTo(node.getData()) < 0) ? node.getLeft() : node.getRight();
        }
        return false;
    }

    private void addRecur(BinaryNode<T> node, T data){
        ArrayList<T> list = new ArrayList<>();
        if(data.compareTo(node.getData()) < 0){
            if (node.getLeft() == null){
                node.setLeft(new BinaryNode<>(data));
                flatten(root, list);
                root = balance(list, 0, list.size() - 1);
            }
            else{
                addRecur(node.getLeft(), data);
            }
        }
        else{
            if (node.getRight() == null){
                node.setRight(new BinaryNode<>(data));
                flatten(root, list);
                root = balance(list, 0, list.size() - 1);
            }
            else{
                addRecur(node.getRight(), data);
            }
        }
    }

    public void delete(T data){
        del(root, data);
    }

    private BinaryNode<T> del(BinaryNode<T> node, T key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.getData()) < 0){
            return del(node.getLeft(), key);
        } else if (key.compareTo(node.getData()) > 0) {
            return del(node.getRight(), key);
        }
        else{
            BinaryNode<T> nNode = new BinaryNode<>(node.getData());
            if(node.getLeft() == null && node.getRight() == null){
                BinaryNode<T> del = root;
                while(del.getRight() != node && del.getLeft() != node){
                    del = (node.getData().compareTo(del.getData()) < 0) ? del.getLeft() : del.getRight();
                }
                if(node == del.getRight()){
                    del.setRight(null);
                }
                else{
                    del.setLeft(null);
                }
            } else if(node.getLeft() == null){
                node.setData(node.getRight().getData());
                del(node.getRight(), node.getRight().getData());
            } else if (node.getRight() == null) {
                node.setData(node.getLeft().getData());
                del(node.getLeft(), node.getLeft().getData());
            }
            else{
                BinaryNode<T> leftL = node.getLeft();
                while(leftL.getRight() != null){
                    leftL = leftL.getRight();
                }
                node.setData(leftL.getData());
                del(leftL, leftL.getData());
            }
            ArrayList<T> data = new ArrayList<>();
            flatten(root, data);
            root = balance(data, 0, data.size()- 1);
            return nNode;
        }
    }

    private void flatten(BinaryNode<T> node, ArrayList<T> list){
        if(node == null){
            return;
        }
        flatten(node.getLeft(),list);
        list.add(node.getData());
        flatten(node.getRight(),list);
    }

    private BinaryNode<T> balance(ArrayList<T> list, int start,int end){
        if(start > end) return null;
        int mid = (start + end)/2;
        BinaryNode<T> node = new BinaryNode<>(list.get(mid));
        node.setLeft(balance(list, start, mid - 1));
        node.setRight(balance(list, mid + 1, end));
        return node;
    }

    public void print(){
        printRecur(root);
    }

    private void printRecur(BinaryNode<T> node){
        if(node == null) return;

        printRecur(node.getLeft());
        System.out.print(node + ", ");
        printRecur(node.getRight());
    }
}
