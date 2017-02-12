package com.kuai.app.retrofit.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 服务器返回的结果
 */
public class JokeResult {
    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"我就想知道先是谁在这里炖屎的？！","hashId":"3526F88BB162C76B6D6D1006C7298E6A","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/3526F88BB162C76B6D6D1006C7298E6A.jpg"},{"content":"学习了 就差开宾馆了！","hashId":"A957BFBB50E164CDC6FF233632FF4819","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/A957BFBB50E164CDC6FF233632FF4819.jpg"},{"content":"就算再疼也不能让人看出来","hashId":"41A6AC77362E43448DBC33281B502E5F","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/41A6AC77362E43448DBC33281B502E5F.gif"},{"content":"好美的腿","hashId":"00D24DB56E836270A3222C0EE87362D9","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/00D24DB56E836270A3222C0EE87362D9.gif"},{"content":"开车太不稳了，后面女生晕车拼命吐","hashId":"8FE87F61794BB1B7C26122AFC9A0E587","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/8FE87F61794BB1B7C26122AFC9A0E587.gif"},{"content":"情人节最正确的送礼物方式，没有之一","hashId":"4F12B76CB22A1DA97727E7C4BCBE1241","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/4F12B76CB22A1DA97727E7C4BCBE1241.png"},{"content":"7成男生的前戏指法是错的","hashId":"9C435181D978A3C7C54BEB2CBB296AC5","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/9C435181D978A3C7C54BEB2CBB296AC5.jpg"},{"content":"最后再合个影吧","hashId":"6964F5CE93A7F8EB5380DF34179C42A7","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/6964F5CE93A7F8EB5380DF34179C42A7.jpg"},{"content":"来来来，就喝一杯！","hashId":"9C9C9C60C93B5AEA1F1730F1F9EB2F29","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/9C9C9C60C93B5AEA1F1730F1F9EB2F29.gif"},{"content":"大家一定注意防盗啊","hashId":"26AD77C2A85C84154CCB1F32D7D81474","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/26AD77C2A85C84154CCB1F32D7D81474.gif"}]}
     */

    @SerializedName("error_code")
    private int errorCode;
    private String reason;
    private ResultBean result;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<Joke> data;

        public List<Joke> getData() {
            return data;
        }

        public void setData(List<Joke> data) {
            this.data = data;
        }

        public static class Joke {
            @Override
            public String toString() {
                return "Joke{" +
                        "content='" + content + '\'' +
                        ", hashId='" + hashId + '\'' +
                        ", unixTime=" + unixTime +
                        ", updateTime='" + updateTime + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            /**
             * content : 我就想知道先是谁在这里炖屎的？！
             * hashId : 3526F88BB162C76B6D6D1006C7298E6A
             * unixtime : 1486785927
             * updatetime : 2017-02-11 12:05:27
             * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/3526F88BB162C76B6D6D1006C7298E6A.jpg
             */



            private String content;
            private String hashId;
            @SerializedName("unixtime")
            private int unixTime;
            @SerializedName("updatetime")
            private String updateTime;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixTime() {
                return unixTime;
            }

            public void setUnixTime(int unixTime) {
                this.unixTime = unixTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }


    /**
     * 返回结果事例
     * {"error_code": 0,"reason": "Success",
     * "result": {"data":[{"content":"我就想知道先是谁在这里炖屎的？！",
     * "hashId":"3526F88BB162C76B6D6D1006C7298E6A",
     * "unixtime":1486785927,
     * "updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/3526F88BB162C76B6D6D1006C7298E6A.jpg"},
     * {"content":"学习了 就差开宾馆了！","hashId":"A957BFBB50E164CDC6FF233632FF4819","unixtime":1486785927,"updatetime":
     * "2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/A957BFBB50E164CDC6FF233632FF4819.jpg"},
     * {"content":"就算再疼也不能让人看出来","hashId":"41A6AC77362E43448DBC33281B502E5F","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/41A6AC77362E43448DBC33281B502E5F.gif"},{"content":"好美的腿",
     * "hashId":"00D24DB56E836270A3222C0EE87362D9","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/00D24DB56E836270A3222C0EE87362D9.gif"},
     * {"content":"开车太不稳了，后面女生晕车拼命吐","hashId":"8FE87F61794BB1B7C26122AFC9A0E587","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/8FE87F61794BB1B7C26122AFC9A0E587.gif"},{"content":"情人节最正确的送礼物方式，没有之一",
     * "hashId":"4F12B76CB22A1DA97727E7C4BCBE1241","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/4F12B76CB22A1DA97727E7C4BCBE1241.png"},
     * {"content":"7成男生的前戏指法是错的","hashId":"9C435181D978A3C7C54BEB2CBB296AC5","unixtime":1486785927,
     * "updatetime":"2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/9C435181D978A3C7C54BEB2CBB296AC5.jpg"},
     * {"content":"最后再合个影吧","hashId":"6964F5CE93A7F8EB5380DF34179C42A7","unixtime":1486785927,"updatetime":
     * "2017-02-11 12:05:27","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/6964F5CE93A7F8EB5380DF34179C42A7.jpg"},
     * "content":"来来来，就喝一杯！","hashId":"9C9C9C60C93B5AEA1F1730F1F9EB2F29","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27"
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/9C9C9C60C93B5AEA1F1730F1F9EB2F29.gif"},{"content":"大家一定注意防盗啊",
     * "hashId":"26AD77C2A85C84154CCB1F32D7D81474","unixtime":1486785927,"updatetime":"2017-02-11 12:05:27",
     * "url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201702/11/26AD77C2A85C84154CCB1F32D7D81474.gif"}]}}
     */

}
