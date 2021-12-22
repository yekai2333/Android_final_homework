package com.example.myapplication.ui.Weather.weather;

import java.util.List;
public class Weather{

    private DataBean data;
    private int status;
    private String desc;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DataBean {
        /**
         * yesterday : {"date":"20日星期一","high":"高温 18℃","fx":"北风","low":"低温 13℃","fl":"<![CDATA[<3级]]>","type":"中雨"}
         * city : 长沙
         * forecast : [{"date":"21日星期二","high":"高温 15℃","fengli":"<![CDATA[<3级]]>","low":"低温 13℃","fengxiang":"东北风","type":"阵雨"},{"date":"22日星期三","high":"高温 18℃","fengli":"<![CDATA[<3级]]>","low":"低温 14℃","fengxiang":"东北风","type":"阴"},{"date":"23日星期四","high":"高温 17℃","fengli":"<![CDATA[<3级]]>","low":"低温 13℃","fengxiang":"东北风","type":"阴"},{"date":"24日星期五","high":"高温 22℃","fengli":"<![CDATA[<3级]]>","low":"低温 11℃","fengxiang":"北风","type":"晴"},{"date":"25日星期六","high":"高温 25℃","fengli":"<![CDATA[<3级]]>","low":"低温 14℃","fengxiang":"南风","type":"晴"}]
         * ganmao : 天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。
         * wendu : 13
         */

        private YesterdayBean yesterday;
        private String city;
        private String ganmao;
        private String wendu;
        private List<ForecastBean> forecast;

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 20日星期一
             * high : 高温 18℃
             * fx : 北风
             * low : 低温 13℃
             * fl : <![CDATA[<3级]]>
             * type : 中雨
             */

            private String date;
            private String high;
            private String fx;
            private String low;
            private String fl;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ForecastBean {
            /**
             * date : 21日星期二
             * high : 高温 15℃
             * fengli : <![CDATA[<3级]]>
             * low : 低温 13℃
             * fengxiang : 东北风
             * type : 阵雨
             */

            private String date;
            private String high;
            private String fengli;
            private String low;
            private String fengxiang;
            private String type;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public String toString() {
            return "[ DataBean : " + "\n" +
                    "yesterday : {" + "\n" +
                    "date : " + yesterday.date + "\n" +
                    "high : " + yesterday.high + "\n" +
                    "fx : " + yesterday.fx + "\n" +
                    "low : " + yesterday.low + "\n" +
                    "fl : " + yesterday.fl + "\n" +
                    "};" + "\n" +
                    "city : " + city + "\n" +
                    "forecast : {" + "\n" +
                    "{" + "\n" +
                    "date : " + forecast.get(0).date + "\n" +
                    "high : " + forecast.get(0).high + "\n" +
                    "fengli : " + forecast.get(0).fengli + "\n" +
                    "low : " + forecast.get(0).low + "\n" +
                    "fengxiang : " + forecast.get(0).fengxiang + "\n" +
                    "type : " + forecast.get(0).type + "\n" +
                    "}" + "\n" +
                    "{" + "\n" +
                    "date : " + forecast.get(1).date + "\n" +
                    "high : " + forecast.get(1).high + "\n" +
                    "fengli : " + forecast.get(1).fengli + "\n" +
                    "low : " + forecast.get(1).low + "\n" +
                    "fengxiang : " + forecast.get(1).fengxiang + "\n" +
                    "type : " + forecast.get(1).type + "\n" +
                    "}" + "\n" +
                    "}" + "\n" +
                    "ganmao : " + ganmao + "\n" +
                    "wendu : " + wendu;
        }
    }
}