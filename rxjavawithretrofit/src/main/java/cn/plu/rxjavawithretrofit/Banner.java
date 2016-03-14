package cn.plu.rxjavawithretrofit;

import java.util.List;

/**
 * Created by lily on 16-3-14.
 */
public class Banner {


    /**
     * apiVersion : 1.0
     * data : [{"hrefTarget":"haiwang","title":"海王 日常连胜ING!!","tag":"","_index":"2","image":"http://img.plures.net/2016/01/05/15b9/c26b/c015/4b2b/509b/86b6/7bd9/24fc.jpg","type":"","tag_color":"","hrefType":"2"},{"hrefTarget":"daidai","title":"野生天然呆","tag":"","_index":"2","image":"http://img.plures.net/2016/01/06/432c/161e/ff61/1429/831b/dfb2/10ba/58fb.jpg","type":"","tag_color":"","hrefType":"2"},{"hrefTarget":"135514","title":"Star★中国娃","tag":"","_index":"2","image":"http://img.plures.net/2016/03/14/99a2/5628/05fc/02ad/2d56/027e/d6fd/bdb1.png","type":"","tag_color":"","hrefType":"2"},{"hrefTarget":"104248","title":"太子：越来越菜了，刷会记录，练练吧！","tag":"","_index":"0","image":"http://img.plures.net/2015/11/20/d1bc/c402/8385/750f/e532/4292/025c/5a40.jpg","type":"","tag_color":"","hrefType":"2"},{"hrefTarget":"104396","title":"若尘：早上好，199上号，一组199玫瑰晚上全程跑边境","tag":"","_index":"0","image":"http://img.plures.net/2015/11/23/5f62/9e79/29a0/a0c5/4859/a574/a58b/1842.jpg","type":"","tag_color":"","hrefType":"2"}]
     */
    private String apiVersion;
    private List<DataEntity> data;

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * hrefTarget : haiwang
         * title : 海王 日常连胜ING!!
         * tag :
         * _index : 2
         * image : http://img.plures.net/2016/01/05/15b9/c26b/c015/4b2b/509b/86b6/7bd9/24fc.jpg
         * type :
         * tag_color :
         * hrefType : 2
         */
        private String hrefTarget;
        private String title;
        private String tag;
        private String _index;
        private String image;
        private String type;
        private String tag_color;
        private String hrefType;

        public void setHrefTarget(String hrefTarget) {
            this.hrefTarget = hrefTarget;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void set_index(String _index) {
            this._index = _index;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setTag_color(String tag_color) {
            this.tag_color = tag_color;
        }

        public void setHrefType(String hrefType) {
            this.hrefType = hrefType;
        }

        public String getHrefTarget() {
            return hrefTarget;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String get_index() {
            return _index;
        }

        public String getImage() {
            return image;
        }

        public String getType() {
            return type;
        }

        public String getTag_color() {
            return tag_color;
        }

        public String getHrefType() {
            return hrefType;
        }
    }
}
