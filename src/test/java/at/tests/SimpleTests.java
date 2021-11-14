package at.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTests {

    @Test
    public void tetsOk(){

    }

    @Test
    public void testFalse(){
        Assert.assertTrue(false);
    }

    @Test
    public void testException(){
        throw new IllegalArgumentException("Exception sample");
    }
}
