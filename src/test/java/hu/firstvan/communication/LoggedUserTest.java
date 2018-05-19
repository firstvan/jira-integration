package hu.firstvan.communication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoggedUserTest {
    private TokenHolder isPresent;

    @Before
    public void setUp() throws Exception {
        isPresent = new TokenHolder();
        isPresent.setToken("E01D59034823D119BA1A20C862BE72DE");
        isPresent.setUsername("firstvan");

        LoggedUser loggedUser = LoggedUser.getInstance();
        loggedUser.addToken(isPresent);
    }

    @Test
    public void objectEquals() {
        LoggedUser loggedUser = LoggedUser.getInstance();
        assertEquals(isPresent, loggedUser.getUserToken("firstvan"));
    }

    @Test
    public void tokenEquals() {
        LoggedUser loggedUser = LoggedUser.getInstance();
        assertEquals("E01D59034823D119BA1A20C862BE72DE", loggedUser.getUserToken("firstvan").getToken());
    }
}
