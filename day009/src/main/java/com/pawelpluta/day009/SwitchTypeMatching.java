package com.pawelpluta.day009;

class SwitchTypeMatching {
    MatchResults match(Object value) {

        return switch (value) {
            case Integer i -> MatchResults.forInteger();
            case String s -> MatchResults.forString();
            case Long l -> MatchResults.forLong();
            case CharSequence c -> MatchResults.forCharSequence();
            case Character cr -> MatchResults.forChar();
            case null -> MatchResults.forNull();
            default -> null;
        };
    }
}
