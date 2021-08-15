package me.plash.struct;

import me.plash.struct.base.ListBaseNode;

/**
 * 用来描述一个段的信息
 * 段由一些零散的页(fragArrEntries)和一些完整的区(3个ListBaseNode)组成，在物理上并不连续
 */
public class INodeEntry {
    public Long segmentId;
    /**
     * //这个字段指的是在 NOT_FULL 链表中已经使用了多少个页面。下次从 NOT_FULL 链表分配空闲页面时可以直接
     *     根据这个字段的值定位到。而不用从链表中的第一个页面开始遍历着寻找空闲页面
     */
    public Integer NOT_FULL_N_USED;
    public ListBaseNode freeList;
    public ListBaseNode notFullList;
    public ListBaseNode fullList;
    public Integer magicNum;
    public Integer[] fragArrEntries;
}
