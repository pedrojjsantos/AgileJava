package sis.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ServerTest {
    private AtomicInteger numberOfResults = new AtomicInteger();
    private Server server;
    private static final long TIMEOUT = 3000L;
    private final int numberOfSearches = 100;

    @Before
    public void setUp() throws Exception {
        ResultsListener listener = (search -> numberOfResults.incrementAndGet());
        server = new Server(listener, 4);
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(server.isAlive());
        server.shutDown();
        server.join(3000);
        assertFalse(server.isAlive());
    }

    @Test
    public void testSearch() throws Exception {
        long start = System.currentTimeMillis();
        executeSearches();
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed < 20);
        waitForResults();
    }

    @Test
    public void testLogs() throws Exception {
        executeSearches();
        waitForResults();
        verifyLogs();
    }

    @Test
    public void testException() throws Exception {
        final String errorMessage = "problem";
        Search faultySearch = new Search(SearchTest.URL, "") {
            public void execute() {
                throw new RuntimeException(errorMessage);
            }
        };
        server.add(faultySearch);
        waitForResults(1);
        List<String> log = server.getLog();
        assertTrue(log.get(0).contains(errorMessage));
    }
    private void waitForResults() {
        waitForResults(numberOfSearches);
    }

    private void waitForResults(int count) {
        long start = System.currentTimeMillis();

        while (numberOfResults.get() < count) {
            try { Thread.sleep(1); }
            catch (InterruptedException e) {}

            if (System.currentTimeMillis() - start > TIMEOUT)
                fail("Timeout");
        }
    }

    private void executeSearches() throws Exception {
        for (int i = 0; i < numberOfSearches; i++)
            server.add(new Search(SearchTest.URL, "" + i));
    }

    private void verifyLogs() {
        List<String> list = server.getLog();
        assertEquals(numberOfSearches * 2, list.size());
        for (int i = 0; i < numberOfSearches; i += 2)
            verifySameSearch(list.get(i), list.get(i + 1));
    }

    private void verifySameSearch(String startSearchMsg, String endSearchMsg) {
        String startSearch = substring(startSearchMsg, Server.START_MSG);
        String endSearch = substring(endSearchMsg, Server.END_MSG);
        assertEquals(startSearch, endSearch);
    }

    private String substring(String string, String upTo) {
        int endIndex = string.indexOf(upTo);
        assertTrue("didn't find " + upTo + " in " + string,
                endIndex != -1);
        return string.substring(0, endIndex);
    }
}
