package test.com.company;

import com.company.Solution;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MainTest {
    private int param;
    private int result;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {4, 4},
                {0, 0},
                {9, 9},
        });
    }

    public MainTest(int param, int result) {
        this.param = param;
        this.result = result;
    }

    @Test
    public void testWatch() throws Exception {

    }

} 
