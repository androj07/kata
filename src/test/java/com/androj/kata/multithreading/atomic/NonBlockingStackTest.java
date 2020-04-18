package com.androj.kata.multithreading.atomic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NonBlockingStackTest {
    private NonBlockingStack<Integer> stack;

    @Before
    public void beforeEach() {
        stack = new NonBlockingStack<>();
    }


    @Test
    public void shouldReturnNullFromEmptyStack() {
        Integer pop = stack.pop();
        assertThat(pop, equalTo(null));
    }

    @Test
    public void shouldPushAndPopTheSameValue() {
        Integer value = 1024;
        stack.push(value);
        Integer popped = stack.pop();

        assertThat(popped, equalTo(value));
    }

    @Test
    public void shouldPushManyAndReceiveInReversedOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertThat(stack.pop(), equalTo(4));
        assertThat(stack.pop(), equalTo(3));
        assertThat(stack.pop(), equalTo(2));
        assertThat(stack.pop(), equalTo(1));
        assertThat(stack.pop(), equalTo(null));
    }

    @Test
    public void shouldCountEveryOperation() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.pop();
        stack.pop();
        stack.pop();

        assertThat(stack.getOperationsCount(), equalTo(7));
    }
}