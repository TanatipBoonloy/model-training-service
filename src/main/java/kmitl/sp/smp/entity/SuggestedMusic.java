package kmitl.sp.smp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jo on 3/26/2017.
 */
@Data
@Entity
@Table(name = "suggested_music")
public class SuggestedMusic {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "music_id")
    private String musicId;

    @Column(name = "timestamp")
    private Date timestamp;
}
