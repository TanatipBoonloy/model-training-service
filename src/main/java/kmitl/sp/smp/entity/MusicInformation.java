package kmitl.sp.smp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jo on 3/26/2017.
 */
@Data
@Entity
@Table(name = "music_information")
public class MusicInformation {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist")
    private String artist;

    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "danceability")
    private double danceability;

    @Column(name = "energy")
    private double energy;

    @Column(name = "key")
    private int key;

    @Column(name = "loudness")
    private double loudness;

    @Column(name = "mode")
    private int mode;

    @Column(name = "speechiness")
    private double speechiness;

    @Column(name = "acousticness")
    private double acousticness;

    @Column(name = "instrumentalness")
    private double instrumentalness;

    @Column(name = "liveness")
    private double liveness;

    @Column(name = "valence")
    private double valence;

    @Column(name = "tempo")
    private double tempo;

    @Column(name = "duration_ms")
    private int durationMs;

    @Column(name = "time_signature")
    private int timeSignature;

    @Column(name = "url")
    private String url;
}
