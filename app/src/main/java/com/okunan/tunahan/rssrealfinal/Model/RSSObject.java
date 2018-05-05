package com.okunan.tunahan.rssrealfinal.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TUNAHAN on 05.01.2018.
 */


public class RSSObject
{
    public String status ;
    public Feed feed ;
    public static   List<Item> myItems;
    public static   Item myItem;

    public RSSObject(String status, Feed feed, List<Item> items) {
        this.status = status;
        this.feed = feed;
        this.items = items;
    }
    private RSSObject(){
        myItems=new ArrayList<>();
    }
    public  List<Item> items ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public  List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static Item getItem(String guid){
        for (Item item:myItems){
            if (item.guid.equals(guid)){
                return item;
            }
        }
        return null;
    }
}