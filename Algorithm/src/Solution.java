public class Solution {
    public String numberToWords(int num) {
        if (num < 20) {
            return getAnswerBelow20(num);
        }
        if (num < 100) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getAnswerTens(num / 10));
            if (num % 10 > 0) {
                stringBuilder.append(" ").append(numberToWords(num % 10));
            }
            return stringBuilder.toString();
        }
        if (num < 1000) {
            return build(num, 100, "Hundred");
        }
        if (num < 1000 * 1000) {
            return build(num, 1000, "Thousand");
        }
        if (num < 1000 * 1000 * 1000) {
            return build(num, 1000 * 1000, "Million");
        }
        return build(num, 1000 * 1000 * 1000, "Billion");
    }

    private String build(int num, int subNum, String unit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(numberToWords(num / subNum));
        stringBuilder.append(" ");
        stringBuilder.append(unit);
        if (num % subNum > 0) {
            stringBuilder.append(" ");
            stringBuilder.append(numberToWords(num % subNum));
        }
        return stringBuilder.toString();
    }

    private String getAnswerBelow20(int num) {
        switch (num) {
            case 0:
                return "Zero";
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
            default:
                return "";
        }
    }

    private String getAnswerTens(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
            default:
                return null;
        }
    }
}