package com.sourceallies.interview;

import lombok.Data;

@Data
class Bowling {
    private Frame[] frames = new Frame[10];
    private int frameIndex;

    public Bowling() {
        frameIndex = 0;
        for (int i = 0; i < 10; i++) {
            frames[i] = new Frame();
        }
    }

    public boolean isEmpty() {
        return frameIndex == 0 && frames[0].getRolls().isEmpty();
    }

    public boolean isDone() {
        // The game is done when the 10th frame (index 9) is complete.
        Frame tenthFrame = frames[9];
        int rollCount = tenthFrame.getRolls().size();
        boolean hasMark = tenthFrame.isStrike() || tenthFrame.isSpare();

        // Done if 3 rolls were made, or 2 rolls without a mark.
        return (rollCount == 3) || (rollCount == 2 && !hasMark);
    }

    public void knockPins(int pins) {
        if (isDone()) {
            return;
        }

        Frame currentFrame = frames[frameIndex];
        currentFrame.addRoll(pins);

        // Advance frame if it's a standard frame (1-9) and now complete. We don't
        // advance past the 10th frame.
        boolean isFrameComplete = currentFrame.isStrike() || currentFrame.getRolls().size() == 2;
        if (frameIndex < 9 && isFrameComplete) {
            frameIndex++;
        }
    }

    public int getScoreAtIndex(int i) {
        Frame currentFrame = frames[i];
        int frameScore = currentFrame.getBaseScore();

        // Bonus logic only applies to frames 1-9
        if (i < 9) {
            if (currentFrame.isStrike()) {
                frameScore += getStrikeBonus(i);
            } else if (currentFrame.isSpare()) {
                frameScore += getSpareBonus(i);
            }
        }

        return frameScore;
    }

    private int getStrikeBonus(int frameIndex) {
        Frame nextFrame = frames[frameIndex + 1];

        if (nextFrame.isStrike() && frameIndex < 8) {
            return 10 + frames[frameIndex + 2].getFirstRoll();
        }

        return nextFrame.getBaseScore();
    }

    private int getSpareBonus(int frameIndex) {
        return frames[frameIndex + 1].getFirstRoll();
    }
}