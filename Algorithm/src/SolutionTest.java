import org.junit.Test;


public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void test() {
        String result = solution.numberToWords(22);
        assert result.equals("Twenty Two");

        result = solution.numberToWords(10);
        assert result.equals("Ten");

        result = solution.numberToWords(15);
        assert result.equals("Fifteen");

        result = solution.numberToWords(99);
        assert result.equals("Ninety Nine");

        result = solution.numberToWords(2);
        assert result.equals("Two");

        result = solution.numberToWords(100);
        assert result.equals("One Hundred");

        result = solution.numberToWords(101);
        assert result.equals("One Hundred One");

        result = solution.numberToWords(202);
        assert result.equals("Two Hundred Two");

        result = solution.numberToWords(222);
        assert result.equals("Two Hundred Twenty Two");

        result = solution.numberToWords(1001);
        assert result.equals("One Thousand One");

        result = solution.numberToWords(400);
        assert result.equals("Four Hundred");

        result = solution.numberToWords(12345);
        assert result.equals("Twelve Thousand Three Hundred Forty Five");

        result = solution.numberToWords(400);
        assert result.equals("Four Hundred");

        result = solution.numberToWords(1234567);
        assert result.equals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");

        result = solution.numberToWords(1234567891);
        assert result.equals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");

        result = solution.numberToWords(100030000);
        assert result.equals("One Hundred Million Thirty Thousand");
    }
} 
