public class doublelink<Item> {
    private Node<Item> first;
    private Node<Item> last;

    private class Node<Item>  {
        Item item;
        Node<Item> prev;
        Node<Item> next;
    }

    public doublelink() {
        first = null;
    }

    public void insert_at_beginning(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.prev = last;
        first.next = oldfirst;
        oldfirst.prev = first;
        last.next = first;
    }

    public void insert_at_end(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        oldlast.next = last;
        last.prev = oldlast;
        last.next = first;
        first.prev = last;
    }
}
