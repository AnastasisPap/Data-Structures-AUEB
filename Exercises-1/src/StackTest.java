import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackTest {

    // Check if it pushes correctly
    @Test
    public void pushOnTop() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        stack.push(1);
        stack.push(2);

        Assert.assertTrue(2 == stack.peek());
    }

    // Checks if it checks correctly if Empty
    @Test
    public void checkIfEmpty() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        Assert.assertTrue(stack.isEmpty());
    }

    // Checks if pop method works correctly
    @Test
    public void removeFromTop() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();

        boolean outcome_1 = stack.peek() == 2;
        stack.pop();
        stack.pop();
        boolean outcome_2 = stack.isEmpty();
        Assert.assertTrue(outcome_2 && outcome_1);
    }

    // Checks if can remove from empty stack (must throw No such element exception)
    @Test(expected = NoSuchElementException.class)
    public void removeFromEmpty() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        stack.pop();
    }

    // Checks if getting from top of empty stack
    @Test(expected = NoSuchElementException.class)
    public void getFromEmpty() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        stack.peek();
    }

    // Checks if correctly stores the items
    @Test
    public void testStore() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        String storedItems = "";

        while (!stack.isEmpty()) {
            int num = stack.pop();
            storedItems += num;
        }

        Assert.assertEquals(storedItems, "321");

    }

    // Checks if correctly stores the length
    @Test
    public void testLength() {
        StringStackImpl<Integer> stack = new StringStackImpl<>();

        Assert.assertEquals(stack.size(), 0);

        stack.push(1);
        stack.push(2);

        Assert.assertEquals(stack.size(), 2);

        stack.pop();

        Assert.assertEquals(stack.size(), 1);
    }
}
