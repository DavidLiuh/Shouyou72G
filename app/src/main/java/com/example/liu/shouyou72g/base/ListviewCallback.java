package com.example.liu.shouyou72g.base;

/**
 * Created by liu on 2016/6/27.
 * 在线程中刷新listview用到的回调
 */
public interface ListviewCallback {

    /**更新listview的条目
     * @param obj listview更新到的数据的集合
     */
    public void updateListview(Object obj);

}
