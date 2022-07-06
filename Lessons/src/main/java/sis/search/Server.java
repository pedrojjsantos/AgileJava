package sis.search;

import java.util.*;
import java.util.concurrent.*;


public class Server extends Thread {
    static final String START_MSG = "started";
    static final String END_MSG = "finished";
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<>();
    private ResultsListener listener;
    private ExecutorService threadPool;

    private static ThreadLocal<List<String>> threadLog =
            ThreadLocal.withInitial(ArrayList::new);

    private List<String> completeLog =
            Collections.synchronizedList(new ArrayList<>());

    public Server(ResultsListener listener, int maxThreads) {
        this.listener = listener;
        this.threadPool = Executors.newFixedThreadPool(maxThreads);
        start();
    }
    public void run() {
        while (true) {
            try{
                execute(queue.take());
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
    public void add(Search search) throws Exception {
        queue.put(search);
    }
    private void execute(final Search search) {
        threadPool.execute(() -> {
            try {
                log(START_MSG, search);
                search.execute();
                log(END_MSG, search);
                completeLog.addAll(threadLog.get());
                listener.executed(search);

            } catch (Exception e) {
                completeLog.add(search + " " + e.getMessage());
                listener.executed(search);
            }
            finally { threadLog.remove(); }
        });
    }

    public void shutDown() {
        this.interrupt();
        threadPool.shutdown();
    }

    public List<String> getLog() {
        return completeLog;
    }

    private void log(String message, Search search) {
        threadLog.get().add(
                search + " " + message + " at " + new Date());
    }

}
