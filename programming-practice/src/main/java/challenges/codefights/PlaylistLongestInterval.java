package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Playlist Longest Interval
 *
 * You like to spend time during winter holidays in Starbucks. One of the reasons is that nice winter songs are being played there.
 * But there is one thing you don't like - they use to play the same songs from time to time. You decided to find out how long you
 * can sit there and drink your coffee without listening any song twice.
 *
 * Given a playlist of holiday music for today in array songs, find the longest interval of time (in seconds) when you won't hear
 * any song twice. Each song is given in format "<songName> (m:ss)".
 *
 * Example:
 *      For songs = ["A (1:30)", "B (2:00)", "A (1:30)", "C (1:00)"], the output should be playlistLongestInterval(songs) = 270.
 *      The longest interval of not repeating songs is ["B (2:00)", "A (1:30)", "C (1:00)"], so the output should be 120 + 90 + 60 = 270 seconds.
 *
 * link: https://app.codesignal.com/challenge/WrsStwaupXqwbgneR
 *
 * @author Hxkandwal
 */
public class PlaylistLongestInterval extends AbstractCustomTestRunner {

    int playlistLongestInterval(String[] songs) {
        int max = 0;
        if (songs == null) return max;
        Map<String, Integer> map = new HashMap<>();

        for (int idx = 0, start = 0, lmax = 0; idx < songs.length; idx ++) {
            String d = songs [idx].split("\\ ")[1];
            int v = Integer.valueOf(d.substring(1, d.length() - 1).split(":")[0]) * 60 +
                    Integer.valueOf(d.substring(1, d.length() - 1).split(":")[1]);

            lmax += v;
            while (map.containsKey(songs [idx])) lmax -= map.remove(songs [start ++]);

            max = Math.max(max, lmax);
            map.put (songs [idx], v);
        }
        return max;
    }

}
