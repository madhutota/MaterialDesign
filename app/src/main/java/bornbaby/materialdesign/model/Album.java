package bornbaby.materialdesign.model;

/**
 * Created by madhu on 13-Nov-17.
 */

public class Album {

    private String name;
    private int numberOfSongs;
    private int thumbnail;

    public Album(String name ,int numberOfSongs,int thumbnail) {
        this.name = name;
        this.numberOfSongs = numberOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

