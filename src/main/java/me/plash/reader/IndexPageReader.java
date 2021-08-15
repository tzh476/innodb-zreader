package me.plash.reader;

import me.plash.struct.INodeEntry;
import me.plash.struct.base.ListNode;
import me.plash.struct.page.IndexPage;
import me.plash.struct.page.InodePage;
import me.plash.struct.pagecontent.IndexPageHeader;
import me.plash.util.ByteReader;

public class IndexPageReader extends PageReader {
    public static Integer EMPTY_SIZE = 6;
    public static Integer INODE_ENTRY_SIZE = 85;

    public IndexPage readPage(ByteReader byteReader) {
        IndexPage indexPage = new IndexPage();
        indexPage.fileHeader = readFileHeader(byteReader);
        indexPage.pageHeader = readIndexPageHeader(byteReader);
        indexPage.fileTrailer = readFileTrailer(byteReader);
        return indexPage;
    }

    /**
     *     public Short PAGE_N_DIR_SLOTS	;//	2	字节	在页目录中的槽数量
     *     public Short PAGE_HEAP_TOP	;//	2	字节	还未使用的空间最小地址，也就是说从该地址之后就是	Free	Space
     *     public Short PAGE_N_HEAP	;//	2	字节	本页中的记录的数量（包括最小和最大记录以及标记为删除的记录）
     *     public Short PAGE_FREE	;//	2	字节 第一个已经标记为删除的记录地址（各个已删除的记录通过 next_record 也会组成一个单链表，这个单链表中的记录可以被重新利用
     *     public Short PAGE_GARBAGE	;//	2	字节	已删除记录占用的字节数
     *     public Short PAGE_LAST_INSERT	;//	2	字节	最后插入记录的位置
     *     public Short PAGE_DIRECTION	;//	2	字节	记录插入的方向
     *     public Short PAGE_N_DIRECTION	;//	2	字节	一个方向连续插入的记录数量
     *     public Short PAGE_N_RECS	;//	2	字节	该页中记录的数量（不包括最小和最大记录以及被标记为删除的记录）
     *     public Long PAGE_MAX_TRX_ID	;//	8	字节	修改当前页的最大事务ID，该值仅在二级索引中定义
     *     public Short PAGE_LEVEL	;//	2	字节	当前页在B+树中所处的层级
     *     public Long PAGE_INDEX_ID	;//	8	字节	索引ID，表示当前页属于哪个索引
     *     public SegmentHeader PAGE_BTR_SEG_LEAF	;//	10	字节	B+树叶子段的头部信息，仅在B+树的Root页定义
     *     public SegmentHeader PAGE_BTR_SEG_TOP	;//	10	字节	B+树非叶子段的头部信息，仅在B+树的Root页定义
     */
    private IndexPageHeader readIndexPageHeader(ByteReader reader) {
        IndexPageHeader header = new IndexPageHeader();
        header.PAGE_N_DIR_SLOTS = reader.readShort();
        header.PAGE_HEAP_TOP = reader.readShort();
        header.PAGE_N_HEAP = reader.readShort();
        header.PAGE_FREE = reader.readShort();
        header.PAGE_GARBAGE = reader.readShort();
        header.PAGE_LAST_INSERT = reader.readShort();
        header.PAGE_DIRECTION = reader.readShort();
        header.PAGE_N_DIRECTION = reader.readShort();
        header.PAGE_N_RECS = reader.readShort();
        header.PAGE_MAX_TRX_ID = reader.readLong();
        header.PAGE_LEVEL = reader.readShort();
        header.PAGE_INDEX_ID = reader.readLong();
        header.PAGE_BTR_SEG_LEAF = reader.readSegmentHeader();
        header.PAGE_BTR_SEG_TOP = reader.readSegmentHeader();
        return header;
    }

    private INodeEntry[] readINodeEntries(ByteReader reader) {
        INodeEntry[] iNodeEntries = new INodeEntry[INODE_ENTRY_SIZE];
        for (int i = 0; i < INODE_ENTRY_SIZE; i++) {
            iNodeEntries[i] = reader.readINodeEntry();
        }
        return iNodeEntries;
    }

    private ListNode readInodePageList(ByteReader byteReader) {
        ListNode inodePageList = byteReader.readListNode();
        return inodePageList;
    }


    private byte[] readEmpty(ByteReader reader) {
        return reader.read(EMPTY_SIZE);
    }

}
