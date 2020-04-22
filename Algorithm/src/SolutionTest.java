import org.junit.Test;


public class SolutionTest {

    @Test
    public void testIntersection() throws Exception {
        Solution solution = new Solution();
        int[] start1 = new int[]{-10, 48};
        int[] end1 = new int[]{-43, 46};
        int[] start2 = new int[]{-16, 59};
        int[] end2 = new int[]{-1, 85};
        double[] result = solution.intersection(start1, end1, start2, end2);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
} 
