package kmitl.sp.smp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jo on 4/4/2017.
 */
@Data
@Entity
@Table(name = "model")
public class Model {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "weight_profile")
    private String weightProfile;

    @Column(name = "timestamp")
    private Date timestamp;
}
