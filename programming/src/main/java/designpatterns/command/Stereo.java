package designpatterns.command;

public class Stereo {
    public void on() {
        System.out.println("Stereo is On...");
    }

    public void off() {
        System.out.println("Stereo is Off...");
    }

    public void setCd() {
        System.out.println("Stereo is set for CD input...");
    }

    public void setDvd() {

    }

    public void setRadio() {

    }

    public void setVolumn(int level) {
        System.out.println("Stereo volumn set to " + level);
    }
}
