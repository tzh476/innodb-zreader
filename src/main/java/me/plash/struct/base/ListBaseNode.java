package me.plash.struct.base;

public class ListBaseNode {
    public Integer length;
    public PageOffset first;
    public PageOffset last;
    public ListBaseNode(){
        first = new PageOffset();
        last = new PageOffset();
    }
}
