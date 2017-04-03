package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.SuggestedMusic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Singleton
@Transactional
public interface SuggestedMusicRepository extends CrudRepository<SuggestedMusic, Integer> {
    List<SuggestedMusic> findByUserId(int userId);

    @Query(value = "SELECT * FROM suggested_music WHERE user_id = ?1 ORDER BY `timestamp` DESC LIMIT ?2 ",
            nativeQuery = true)
    List<SuggestedMusic> getLatestSuggestedMusic(int userId, int qty);

//    @Query(value = "select music_info.id as music_id," +
//            "music_info.danceability as danceability," +
//            "music_info.energy as energy," +
//            "(music_info.`key`)/11 as `key`," +
//            "((if(music_info.loudness>-1,-1,if(music_info.loudness<-14,-14,music_info.loudness)))+14)/13 as loudness," +
//            "music_info.`mode` as `mode`," +
//            "music_info.speechiness as speechiness," +
//            "music_info.acousticness as acousticness," +
//            "music_info.instrumentalness as instrumentalness," +
//            "music_info.liveness as liveness," +
//            "music_info.valence as valence," +
//            "(if(music_info.tempo<74,74,if(music_info.tempo>194,194,music_info.tempo))-74)/120 as tempo," +
//            "((if(music_info.time_signature<3,3,if(music_info.time_signature>5,5,music_info.time_signature)))-3)/2 as time_signature," +
//            "(music_info.genre_id / 6) as genre," +
//            "If( (listen_info.total_time / ( listen_info.listen_times * music_info.duration_ms ) > 0.25 ),1,0) as liked," +
//            "If( (listen_info.total_time / ( listen_info.listen_times * music_info.duration_ms ) > 0.25 ),0,1) as disliked" +
//            "from ( select `user-music`.`music_id`, " +
//            " COUNT ( * ) as listen_times, " +
//            " SUM ( `user-music`.`listen_time` ) as total_time " +
//            "from `user-music`" +
//            "where `user-music`.user_id = ?1 " +
//            "group by `user-music`.music_id ) as listen_info" +
//            "join music_information as music_info" +
//            "on music_info.id = listen_info.music_id" +
//            "order by music_id", nativeQuery = true)
    @Query(value = "select music_info.danceability as danceability,\n" +
            "music_info.energy as energy,\n" +
            "(music_info.`key`)/11 as `key`,\n" +
            "((if(music_info.loudness>-1,-1,if(music_info.loudness<-14,-14,music_info.loudness)))+14)/13 as loudness,\n" +
            "music_info.`mode` as `mode`,\n" +
            "music_info.speechiness as speechiness,\n" +
            "music_info.acousticness as acousticness,\n" +
            "music_info.instrumentalness as instrumentalness,\n" +
            "music_info.liveness as liveness,\n" +
            "music_info.valence as valence,\n" +
            "(if(music_info.tempo<74,74,if(music_info.tempo>194,194,music_info.tempo))-74)/120 as tempo,\n" +
            "((if(music_info.time_signature<3,3,if(music_info.time_signature>5,5,music_info.time_signature)))-3)/2 as time_signature,\n" +
            "(music_info.genre_id / 6) as genre,\n" +
            "If( (listen_info.total_time / ( listen_info.listen_times * music_info.duration_ms ) > 0.25 ),1,0) as liked,\n" +
            "If( (listen_info.total_time / ( listen_info.listen_times * music_info.duration_ms ) > 0.25 ),0,1) as disliked\n" +
            "from (select `user-music`.music_id,\n" +
            "count(*) as listen_times,\n" +
            "sum(`user-music`.listen_time) as total_time \n" +
            "from `user-music`\n" +
            "where `user-music`.user_id = ?1 \n" +
            "group by `user-music`.music_id ) as listen_info\n" +
            "join music_information as music_info\n" +
            "on music_info.id = listen_info.music_id\n" +
            "order by music_id", nativeQuery = true)
    List<Object[]> getAttributesObjects(int userId);
}
