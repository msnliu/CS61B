import java.util.Stack;

public class callstack<T> {
    private Stack<T> stack = new Stack<>();
    public void offer(T item) {
        stack.push(item);
    }
    public T poll() {
        return poll(stack.pop());
    }
    private T poll(T previous) {
        if (stack.isEmpty()) {
            return previous;
        }
        T current = stack.pop();
        T toreturn = poll(current);
        offer(previous);
        return toreturn;
    }
}
