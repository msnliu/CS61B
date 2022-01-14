import edu.princeton.cs.algs4.Stack;

public class Queue<T> {
    private Stack<T> stack = new Stack<>();
    public void offer(T item) {
        stack.push(item);
    }

    public T poll() {
        Stack<T> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        T first = temp.pop();
        // put it back
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return first;
    }
}