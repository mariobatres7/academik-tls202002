package edu.academik.telus.jmxexample;

/**
 *
 * @author Mario Batres
 */
public interface GameMBean {

    void playFootball(String clubName);

    String getPlayerName();

    void setPlayerName(String playerName);
}
