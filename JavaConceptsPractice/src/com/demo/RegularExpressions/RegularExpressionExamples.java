package com.demo.RegularExpressions;

import java.util.regex.*;

public class RegularExpressionExamples {
    public static void main(String[] args) {
        // Test simple character classes
        testPattern("[abc]", "a", true);
        testPattern("[abc]", "b", true);
        testPattern("[abc]", "c", true);
        testPattern("[abc]", "d", false);
        testPattern("[abc]", "ab", false); // Only single character

        // Test negation
        testPattern("[^abc]", "a", false);
        testPattern("[^abc]", "d", true);
        testPattern("[^abc]", "1", true);
        testPattern("[^abc]", "@", true);

        // Test ranges
        testPattern("[a-zA-Z]", "a", true);
        testPattern("[a-zA-Z]", "Z", true);
        testPattern("[a-zA-Z]", "1", false);
        testPattern("[a-zA-Z]", "m", true);

        // Test union
        testPattern("[a-d[m-p]]", "a", true);
        testPattern("[a-d[m-p]]", "e", false);
        testPattern("[a-d[m-p]]", "m", true);
        testPattern("[a-d[m-p]]", "o", true);

        // Test intersection
        testPattern("[a-z&&[def]]", "d", true);
        testPattern("[a-z&&[def]]", "e", true);
        testPattern("[a-z&&[def]]", "f", true);
        testPattern("[a-z&&[def]]", "a", false);
        testPattern("[a-z&&[def]]", "g", false);

        // Test subtraction
        testPattern("[a-z&&[^bc]]", "a", true);
        testPattern("[a-z&&[^bc]]", "b", false);
        testPattern("[a-z&&[^bc]]", "c", false);
        testPattern("[a-z&&[^bc]]", "d", true);

        testPattern("[a-z&&[^m-p]]", "l", true);
        testPattern("[a-z&&[^m-p]]", "m", false);
        testPattern("[a-z&&[^m-p]]", "o", false);
        testPattern("[a-z&&[^m-p]]", "q", true);

        // Test quantifiers
        testPattern("[amn]?", "", true);    // Zero occurrence
        testPattern("[amn]?", "a", true);   // One occurrence
        testPattern("[amn]?", "aa", false); // More than one
        
        testPattern("[amn]+", "a", true);
        testPattern("[amn]+", "aaa", true);
        testPattern("[amn]+", "ammmna", true);
        testPattern("[amn]+", "b", false);
        testPattern("[amn]+", "aMb", false); // M is not in pattern
        
        testPattern("[amn]*", "", true);     // Zero occurrence
        testPattern("[amn]*", "a", true);    // One occurrence
        testPattern("[amn]*", "ammmna", true); // Multiple
        
        testPattern("a{3}", "aaa", true);
        testPattern("a{3}", "aa", false);
        testPattern("a{3}", "aaaa", false);
        
        testPattern("a{2,}", "aa", true);
        testPattern("a{2,}", "aaa", true);
        testPattern("a{2,}", "a", false);
        
        testPattern("a{2,4}", "aa", true);
        testPattern("a{2,4}", "aaa", true);
        testPattern("a{2,4}", "aaaa", true);
        testPattern("a{2,4}", "a", false);
        testPattern("a{2,4}", "aaaaa", false);

        // Additional examples
        testPattern("\\d", "5", true);      // Digit
        testPattern("\\D", "5", false);     // Non-digit
        testPattern("\\s", " ", true);      // Whitespace
        testPattern("\\S", " ", false);     // Non-whitespace
        testPattern("\\w", "a", true);      // Word character
        testPattern("\\W", "@", true);      // Non-word character
    }

    private static void testPattern(String regex, String input, boolean expected) {
        boolean actual = Pattern.matches(regex, input);
        System.out.printf("Pattern: %-15s Input: %-8s Expected: %-5s Actual: %-5s %s%n",
                "\"" + regex + "\"", 
                "\"" + input + "\"", 
                expected, 
                actual,
                (expected == actual) ? "✓" : "✗");
    }
}