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
        stateStack.lastElement().onEnter();
    }

    public static IState pop() {
        stateStack.lastElement().onExit();
        return stateStack.pop();
    }

    public static IState getCurrentState() {
        return stateStack.lastElement();
    }

}
