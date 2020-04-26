import org.junit.Test;


public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void test() {
        String result = solution.getTwoCharString("22");
        assert result.equals("Twenty Two");

        result = solution.getTwoCharString("10");
        assert result.equals("Ten");

        result = solution.getTwoCharString("15");
        assert result.equals("Fifteen");

        result = solution.getTwoCharString("99");
        assert result.equals("Ninety Nine");

        result = solution.getTwoCharString("02");
        assert result.equals("Two");

        result = solution.getThreeCharString("100");
        assert result.equals("One Hundred");

        result = solution.getThreeCharString("101");
        assert result.equals("One Hundred One");

        result = solution.getThreeCharString("202");
        assert result.equals("Two Hundred Two");

        result = solution.getThreeCharString("222");
        assert result.equals("Two Hundred Twenty Two");

        result = solution.numberToWords(1001);
        System.out.println(result);
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
        System.out.println(result);
        assert result.equals("One Hundred Million Thirty Thousand");
    }
} 
