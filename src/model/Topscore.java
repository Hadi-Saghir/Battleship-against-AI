/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package model;

import java.time.ZonedDateTime;

public class Topscore {
    private String username;
    private int shots;
    private String date;

    //contructed with params username and shot. date is auto-generated
    public Topscore(String username, int shots) {
        this.username = username;
        this.shots = shots;
        String date = ZonedDateTime.now().toString(); //isn't working :( properly
    }

    //getter of shots
    public int getShots() {
        return shots;
    }
    //setter of date
    public void setDate(String date) {
        this.date = date;
    }

    //toString
    @Override
    public String toString() {
       String str = "Name:" + username + "    Shots: " + shots + "    Date: " +date ;
       return str;
    }
}
