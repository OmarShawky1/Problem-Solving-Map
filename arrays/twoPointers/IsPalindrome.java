package arrays.twoPointers;

public class IsPalindrome {

    public boolean isPalindrome1(String s) {
        if (s.length() <= 1) return true;
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();

        while (left <= right) {
            if (!Character.isDigit(chars[left]) && !Character.isAlphabetic(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isDigit(chars[right]) && !Character.isAlphabetic(chars[right])) {
                right--;
                continue;
            }
            if (Character.toLowerCase(chars[left++]) != Character.toLowerCase(chars[right--])) return false;
        }
        return true;
    }

    // Fastest code. Original Solution from:
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            if (!isValid(l)) {
                left++;
                continue;
            }
            char r = s.charAt(right);
            if (!isValid(r)) {
                right--;
                continue;
            }
            if (convertToLowercase(l) != convertToLowercase(r)) return false;
            left++;
            right--;
        }
        return true;
    }

    private boolean isValid(char ch) {
        return 'a' <= ch && ch <= 'z' ||
                'A' <= ch && ch <= 'Z' ||
                '0' <= ch && ch <= '9';
    }

    private char convertToLowercase(char ch) {
        if ('A' <= ch && ch <= 'Z') {
            return (char) (ch + 'a' - 'A');
        }
        return ch;
    }

    public static void test() {
        IsPalindrome i = new IsPalindrome();
        assert i.isPalindrome(" ");
        assert !i.isPalindrome("race a car");
        assert i.isPalindrome("A man, a plan, a canal: Panama");
        assert i.isPalindrome("00");
        assert !i.isPalindrome("0P");
        assert !i.isPalindrome("ab2a");
        assert i.isPalindrome("1b1");
        assert i.isPalindrome("ab_a");
    }
}
