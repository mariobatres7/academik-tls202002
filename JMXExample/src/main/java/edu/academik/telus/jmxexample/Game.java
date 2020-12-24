package edu.academik.telus.jmxexample;

/**
 *
 * @author Mario Batres
 */
public class Game implements GameMBean {

    private String playerName;

    @Override
    public void playFootball(String clubName) {
        System.out.println(this.playerName + "  playing for " + clubName);
    }

    @Override
    public String getPlayerName() {
        System.out.println("Get PlayerName:  " + this.playerName);
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        System.out.println("Set PlayerName = " + this.playerName);
        this.playerName = playerName;
    }

}
