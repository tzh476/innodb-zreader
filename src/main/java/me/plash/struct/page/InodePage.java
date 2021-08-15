package me.plash.struct.page;

import me.plash.struct.INodeEntry;
import me.plash.struct.base.ListNode;

public class InodePage extends Page {
    public ListNode inodePageList;//存储INODE链表中，当前节点的上一个页和下一个页
    public INodeEntry[] iNodeEntries;
    public byte[] empty;
}
