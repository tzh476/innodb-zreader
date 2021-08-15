package me.plash.struct.base;

public class ListNode {
    public PageOffset prev;
    public PageOffset next;
    public ListNode(){
        prev = new PageOffset();
        next = new PageOffset();
    }
}
