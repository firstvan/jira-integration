package hu.firstvan.communication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenHolderTest {

    private TokenHolder isPresent;
    private TokenHolder isNotPresent;

    @Before
    public void setUp() throws Exception {
        isNotPresent = new TokenHolder();
        isPresent = new TokenHolder();

        isPresent.setToken("E01D59034823D119BA1A20C862BE72DE");
        isPresent.setUsername("firstvan");
    }

    @Test
    public void checkUserIsEqual() {
        assertEquals("firstvan", isPresent.getUsername());
    }

    @Test
    public void checkTokenIsEqual() {
        assertEquals("E01D59034823D119BA1A20C862BE72DE", isPresent.getToken());
    }

    @Test
    public void isPresent() {
        assertTrue(isPresent.isTokenAvailable());
    }

    @Test
    public void isNotPresent() {
        assertFalse(isNotPresent.isTokenAvailable());
    }
}
