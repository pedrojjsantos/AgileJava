package sis.search;

import java.util.*;


public class Server extends Thread {
    private List<Search> queue = Collections.synchronizedList(new LinkedList<>());
    private ResultsListener listener;
    public Server(ResultsListener listener) {
        this.listener = listener;
        start();
    }
    public void run() {
        while (true) {
            if (!queue.isEmpty())
                execute(queue.remove(0));
            Thread.yield();
        }
    }
    public void add(Search search) {
        queue.add(search);
    }
    private void execute(Search search) {
        search.execute();
        listener.executed(search);
    }
}
