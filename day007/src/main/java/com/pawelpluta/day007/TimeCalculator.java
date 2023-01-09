package com.pawelpluta.day007;

class TimeCalculator {

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int DAYS_IN_WEEK = 7;
    private static final int HOURS_IN_DAY = 24;

    Long minutesToSeconds(Long minutes) {
        return minutes * SECONDS_IN_MINUTE;
    }

    Long secondsToHours(Long seconds) {
        return seconds / SECONDS_IN_HOUR;
    }

    Long weeksToDays(Long weeks) {
        return weeks * DAYS_IN_WEEK;
    }

    Long daysToWeeks(Long days) {
        return days / 7;
    }

    Long weeksToSeconds(Long weeks) {
        return weeks * DAYS_IN_WEEK * HOURS_IN_DAY * SECONDS_IN_HOUR;
    }
}
