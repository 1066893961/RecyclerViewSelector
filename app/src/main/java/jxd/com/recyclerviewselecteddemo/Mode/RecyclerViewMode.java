package jxd.com.recyclerviewselecteddemo.Mode;

/**
 * Created by 46123 on 2018/2/1.
 * 数据模型
 */

public class RecyclerViewMode {

    /**
     * 用一个编号去判断Item在List中的唯一性
     */
    public String No;

    /**
     * 名称
     */
    public String name;

    /**
     * 图片地址
     */
    public String picUrl;

    /**
     * 在服务端返回的数据模型中额外自己写一个Boolean值的判断值
     */
    public boolean isCheck;
}
