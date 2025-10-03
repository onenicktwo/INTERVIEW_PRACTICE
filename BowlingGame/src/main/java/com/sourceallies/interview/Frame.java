package com.sourceallies.interview;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    protected final List<Integer> rolls = new ArrayList<>();

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public int getFirstRoll() {
        return rolls.isEmpty() ? 0 : rolls.get(0);
    }

    public boolean isStrike() {
        return !rolls.isEmpty() && rolls.get(0) == 10;
    }

    public boolean isSpare() {
        return rolls.size() == 2 && getBaseScore() == 10;
    }

    public int getBaseScore() {
        int sum = 0;
        for (int i = 0; i < rolls.size(); i++) {
            sum += rolls.get(i);
        }
        return sum;
    }
}
