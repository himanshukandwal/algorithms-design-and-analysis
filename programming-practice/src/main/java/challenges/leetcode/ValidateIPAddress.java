package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 468. Validate IP Address
 *
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither. IPv4 addresses are canonically represented in dot-decimal notation,
 * which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 *
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid. IPv6 addresses are represented as eight groups of four hexadecimal digits,
 * each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit
 * some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6
 * address(Omit leading zeros and using upper cases).
 *
 * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
 * For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 *
 * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 * Note: You may assume there is no extra space or special characters in the input string.
 *
 * Example 1:
 *              Input: "172.16.254.1"
 *              Output: "IPv4"
 *              Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 *              Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *              Output: "IPv6"
 *              Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 *              Input: "256.256.256.256"
 *              Output: "Neither"
 *              Explanation: This is neither a IPv4 address nor a IPv6 address.
 *
 * @author Hxkandwal
 */
public class ValidateIPAddress extends AbstractCustomTestRunner {

    private static ValidateIPAddress _instance = new ValidateIPAddress();

    public String _validIPAddress(String IP) {
        boolean fail = false;
        if (IP.indexOf(".") > 0 && !IP.startsWith(".") && !IP.endsWith(".")) {
            String[] parts = IP.split("\\.");
            if (parts.length == 4) {
                for (String p : parts) {
                    for (char c : p.toCharArray()) if (!Character.isDigit(c))
                        fail = true;

                    if (fail || p.length () == 0 || p.length () > 3 || (p.charAt(0) == '0' && p.length() > 1) || Integer.valueOf(p) >= 256)
                        fail = true;
                }
                if (!fail) return "IPv4";
            }
        } else if (IP.indexOf(":") > 0 && !IP.startsWith(":") && !IP.endsWith(":")) {
            String[] parts = IP.split(":");
            if (parts.length == 8) {
                for (String p : parts) {
                    for (char c : p.toCharArray())
                        if ((!Character.isDigit(c) && !Character.isLetter(c)) || (c >= 'G' && c <= 'Z') || (c >= 'g' && c <= 'z')) fail = true;

                    if (fail || p.length() == 0 || p.length() > 4) fail = true;
                }
                if (!fail) return "IPv6";
            }
        }
        return "Neither";
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("2001:0db8:85a3:0:0:8A2E:0370:7334:", "Neither");
        _instance.runTest("2001:0db8:85a3:0:0:8A2E:0370:7334", "IPv6");
        _instance.runTest("172.16.254.1", "IPv4");
    }

    public void runTest(final String IP, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { IP });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
