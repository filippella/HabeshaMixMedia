package org.dalol.habeshamixmedia.data.transformers.videos;

import com.google.gson.Gson;

import org.dalol.habeshamixmedia.data.model.vo.VideoInfoVO;
import org.dalol.habeshamixmedia.data.transformers.Transformer;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 15:11.
 */
public class StringToMapTransformer implements Transformer<String, Map<String, String>> {

    @Override
    public Map<String, String> transform(String input) throws Exception {

        Map<String, String> videosInfo = refine(input);

        String tmp = videosInfo.get("url_encoded_fmt_stream_map");
        if (tmp != null) {
            String[] split = tmp.split(",");
            List<VideoInfoVO> availableVideos = new ArrayList<>();
            for (String value : split) {
                Map<String, String> videoInfo = refine(value);
                availableVideos.add(new VideoInfoVO(videoInfo.get("url"), videoInfo.get("itag"), videoInfo.get("type"), videoInfo.get("quality")));
            }
            String videoJson = new Gson().toJson(availableVideos);
            videosInfo.put("url_encoded_fmt_stream_map", videoJson);
        }
        return videosInfo;
    }

    private Map<String, String> refine(String value) throws IOException {
        String parameters[] = value.split("&");
        Map<String, String> map = new Hashtable<>();
        for (String par : parameters) {
            String[] keyValue = par.split("=");
            if(keyValue.length == 1) keyValue = new String[] {keyValue[0], ""};
            map.put(keyValue[0], URLDecoder.decode(keyValue[1], "UTF-8"));
        }
        return map;
    }
}
