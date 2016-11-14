package main.framework.state;

import java.util.HashMap;
import java.util.Stack;


public class StateStack {

    private static HashMap<String, IState> stateMap = new HashMap<>();
    private static Stack<IState> stateStack = new Stack<>();

    public static void addState(String key ,IState state) {
        stateMap.put(key, state);
    }

    public static void push(String key) {
        stateStack.push(stateMap.get(key));
        stateStack.lastElement().init();
    }

    public static IState pop() {
        stateStack.lastElement().onExit();
        return stateStack.pop();
    }

    public static void popToMainMenu() {
        System.out.println("initializing popper");
        int counter = 0;
        for (IState state : stateStack) {
            counter++;
        }
        System.out.println("Counter = " + counter);
        for(int i = 1; i < counter; i++) {
            pop().onClose();
            System.out.println("popping");
        }
        getCurrentState().onEnter();

    }

    public static IState getCurrentState() {
        return stateStack.lastElement();
    }

}
