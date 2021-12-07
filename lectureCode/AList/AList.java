/** Array based list.
 *  @author Josh Hug
 */


/** Invariants:
    addLast: The next item we want to add, will go into position size
    getLast: The item we want to return is in position size - 1
    size: The number of items in the list should be size.
*/

public class AList<Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            /** rather than adding a number of memory boxes equal to some resizing factor RFACTOR
            We instead resize by multiplying the number of boxes by RFACTOR 
            resize(size * RFACTOR) */
            resize(size + 1);
        }

        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item last = getLast();
        items[size - 1] = null;
        size -= 1;
        return last;
    }
} 