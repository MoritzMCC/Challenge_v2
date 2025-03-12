package de.MoritzMCC.challengApi;

import de.MoritzMCC.challengApi.challenges.NoSneakChallenge;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {

    private static List<AbstractChallenge> challenges = new ArrayList<>();
    private static List<AbstractChallenge>aktiveChallenges = new ArrayList<>();

    public static List<AbstractChallenge> getChallengesAsList() {
        return challenges;
    }

    public static List<AbstractChallenge> getAktiveChallenges() {
        aktiveChallenges.clear();
        for (AbstractChallenge challenge : challenges) {
            if (challenge.isActivated())
                aktiveChallenges.add(challenge);
        }
        return aktiveChallenges;
    }

    public static void addChallenge(AbstractChallenge challenge) {
        challenges.add(challenge);
    }
    public static void removeChallenge(AbstractChallenge challenge) {
        challenges.remove(challenge);
    }
    public static List<AbstractChallenge> getChallengesAsRows(int rowNumber) {
        List<AbstractChallenge> row = new ArrayList<>();
        for(int i = 9*rowNumber; i < 9*rowNumber+9; i++) {
            row.add(challenges.get(i));
      }
      return row;
    }


}
