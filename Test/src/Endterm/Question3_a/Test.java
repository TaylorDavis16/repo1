package Endterm.Question3_a;

public class Test {
    public Item doItem() {
        Item i = new Item(); /* Line 5 */
        return i; /* Line 6 */
    }
    public static void main (String[] args) {
        Test t = new Test(); /* Line 8 */
        Item newItem = t.doItem(); /* Line 9 */
        newItem = new Item(); /* Line 10 */
        System.out.println("completed"); /* Line 11 */
    }
}
