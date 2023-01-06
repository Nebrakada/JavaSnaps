package com.pawelpluta.day004;

record WrappedString(String value) {
    boolean isPalindrome() {
        String caseInsensitiveNoWhiteSpaceValue = value.toLowerCase().replaceAll("\s", "");

        for (int i = 0; i < caseInsensitiveNoWhiteSpaceValue.length(); i++) {
            if (caseInsensitiveNoWhiteSpaceValue.charAt(i) != caseInsensitiveNoWhiteSpaceValue.charAt(caseInsensitiveNoWhiteSpaceValue.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
