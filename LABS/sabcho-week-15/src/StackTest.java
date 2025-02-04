import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import java.util.EmptyStackException;
import java.util.stream.IntStream;



/**
 * Abstract test class for Stack implementations.
 *
 * Extending test classes must only implement the getIntegerStack
 * method. Be careful not to override ANY other methods!
 *
 * @author Simon Larsén
 * @version 2017-08-20
 */
public abstract class StackTest<T> {
    private Stack<Integer> stack;
    private int[] valuesInStack;
    private int initialStackSize;

    private Stack<Integer> emptyStack;

    @Before
    public void setUp() {
        valuesInStack = new int[]{3, 4, 1, -123, 4, 1};
        initialStackSize = valuesInStack.length;
        stack = getIntegerStack();
        pushArrayToStack(valuesInStack, stack);
        emptyStack = getIntegerStack();
    }

    /**
     * Push an array to the stack, in order.
     *
     * @param array An int array.getIntegerStack()
     * @param stack A Stack.
     */
    private void pushArrayToStack(int[] array, Stack<Integer> stack) {
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
    }

    /**
     * This is the only method that implementing classes need to override. It
     * should return an empty instance of a Stack implementation.
     *
     * @return An instance of a Stack implementation.
     */
    protected abstract Stack<Integer> getIntegerStack();



    @Test
    public void topIsLastPushedValue() {
        // Arrange
        int value = 1338;

        // Act
        emptyStack.push(value);
        stack.push(value);

        int emptyStackTop = emptyStack.top();
        int stackTop = stack.top();

        // Assert
        assertThat(emptyStackTop, equalTo(value));
        assertThat(stackTop, equalTo(value));
    }

    @Test(expected=EmptyStackException.class)
    public void topExceptionWhenStackIsEmpty() {

        // Act
        emptyStack.top();
    }


    @Test(expected=EmptyStackException.class)
    public void popExceptionWhenStackIsEmpty() {

        emptyStack.pop();
    }

    @Test
    public void popReturnsPushedValuesInLIFOOrder() {
        // Arrange
        int lastIndex = valuesInStack.length - 1;
        IntStream.range(0, initialStackSize)
            // Act
            .mapToObj(i ->
                new ResultPair<Integer>(stack.pop(), valuesInStack[lastIndex-i]))
            // Assert
            .forEach(pair ->
                assertThat(pair.actual, equalTo(pair.expected)));
    }

    @Test
    public void popFiveTimesDecreasesSizeByFive() {
        //Act

        int initialSize = initialStackSize;

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.size()==(initialSize-5));
    }


    @Test
    public void pushFiveTimesIncreasesSizeByFive() {

        //Act

        int initialSize = initialStackSize;

        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);
        stack.push(1);

        assertTrue(stack.size()==(initialSize+5));

    }

    @Test
    public void isEmptyIsFalseWhenStackIsNotEmpty() {
        // Act
        boolean stackIsEmpty = stack.isEmpty();
        // Assert
        assertFalse(stackIsEmpty);
    }

    @Test
    public void isEmptyIsTrueWhenStackIsEmpty() {
        // Act
        boolean emptyStackIsEmpty = emptyStack.isEmpty();
        // Assert
        assertTrue(emptyStackIsEmpty);
    }

    @Test
    public void isEmptyIsTrueWhenAllElementsHaveBeenPopped() {
        // Act
        popElements(stack, initialStackSize);
        boolean stackIsEmpty = stack.isEmpty();
        // Assert
        assertTrue(stackIsEmpty);
    }

    @Test
    public void sizeIs0WhenStackIsEmpty() {

        int stackSize = emptyStack.size();

        assertTrue(stackSize==0);
    }

    @Test
    public void sizeIs0WhenAllElementsHaveBeenPopped() {
        // Act
        popElements(stack, initialStackSize);
        int stackSize = stack.size();
        // Assert
        assertTrue(stackSize==0);
    }

    // HELPERS

    /**
     * Pops the desired amount of elements.
     *
     * @param stack A Stack.
     * @param amountOfElements The amount of elements to pop.
     */
    private void popElements(Stack<Integer> stack, int amountOfElements) {
        for (int i = 0; i < amountOfElements; i++) {
            stack.pop();
        }
    }

    /**
     * Class used for stream operations when both actual and expected values
     * need to be gather in conjunction.
     */
    private class ResultPair<T> {
        public final T actual;
        public final T expected;

        public ResultPair(T actual, T expected) {
            this.actual = actual;
            this.expected = expected;
        }
    }
}
