package kmitl.sp.smp.util;

import kmitl.sp.smp.entity.MusicInformation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jo on 4/4/2017.
 */
public class ConvertClassUtils {
    public static Object[] convertMusicInformationToAttributes(MusicInformation musicInformation) {
        Double[] obj = new Double[13];
        obj[0] = musicInformation.getDanceability();
        obj[1] = musicInformation.getEnergy();
        obj[2] = ((double) musicInformation.getKey()) / 11;
        obj[3] = musicInformation.getLoudness();
        obj[3] = (((obj[3] > -1) ? -1 : (obj[3] < -14) ? -14 : obj[3]) + 14) / 13;
        obj[4] = (double) musicInformation.getMode();
        obj[5] = musicInformation.getSpeechiness();
        obj[6] = musicInformation.getAcousticness();
        obj[7] = musicInformation.getInstrumentalness();
        obj[8] = musicInformation.getLiveness();
        obj[9] = musicInformation.getValence();
        obj[10] = musicInformation.getTempo();
        obj[10] = (((obj[10] < 74) ? 74 : (obj[10] > 194) ? 194 : obj[10]) - 74) / 120;
        obj[11] = (double) musicInformation.getTimeSignature();
        obj[11] = (((obj[11] < 3) ? 3 : (obj[11] > 5) ? 5 : obj[11]) - 3) / 2;
        obj[12] = ((double)musicInformation.getGenreId())/6;
        return obj;
    }

    public static List<Object[]> converMusicInformationListToAttributesList(List<MusicInformation> musicInformationList) {
        return musicInformationList.stream()
                .map(ConvertClassUtils::convertMusicInformationToAttributes)
                .collect(Collectors.toList());
    }
}
