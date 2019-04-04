import java.util.Stack;

/**
 * className Main
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/2 20:53
 */
public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for(int i = 0; i < 5; i++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
