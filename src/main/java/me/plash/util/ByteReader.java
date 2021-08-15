package me.plash.util;

import me.plash.struct.INodeEntry;
import me.plash.struct.base.ListBaseNode;
import me.plash.struct.base.ListNode;
import me.plash.struct.XdesEntry;
import me.plash.struct.pagecontent.SegmentHeader;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ByteReader {
    private byte[] bytes;
    private Integer PAGE_SIZE = 16 * 1000;
    private Integer index = 0;
    public static final Integer FILE_BYTECODE_FILE_MAX = 63325;

    private Map<Integer,byte[]> pageMap = new HashMap<>();

    public void readFile(String path)  {
        File classFile = new File(path);

        bytes = new byte[FILE_BYTECODE_FILE_MAX];

        try {
            FileInputStream in = new FileInputStream(classFile);
            in.read(bytes);
        } catch (FileNotFoundException e) {
            System.out.println("file not found " + path);
            e.printStackTrace();
            return ;
        } catch (IOException e) {
            System.out.println("读取 "+ path + " 失败");
            e.printStackTrace();
            return ;
        }
    }

    public byte[] read(Integer n){
        byte[] res = new byte[n];
        for(int i = 0; i < n; i ++){
            res[i] = bytes[index + i];
        }
        index += n;
        return res;
    }
    public Integer readInt(){
        byte[] res = read(4);
        ByteBuffer bb = ByteBuffer.wrap(res);
        return bb.getInt();
    }

    public Short readShort(){
        byte[] res = read(2);
        ByteBuffer bb = ByteBuffer.wrap(res);
        return bb.getShort();
    }

    public Long readLong(){
        byte[] res = read(8);
        ByteBuffer bb = ByteBuffer.wrap(res);
        return bb.getLong();
    }

    public String readStr(Integer n){
        byte[] res = read(n);
        return new String(res);
    }

    public byte[] read(Integer start, Integer n){
        byte[] res = new byte[n];
        for(int i = 0; i < n; i ++){
            res[i] = bytes[start + i];
        }
        return res;
    }

    public byte[] readPage(){
        byte[] res = read(PAGE_SIZE);
        return res;
    }

    public byte[] readPageN(Integer n){
        if(pageMap.containsKey(n)){
            return pageMap.get(n);
        }
        Integer start = n * PAGE_SIZE;
        byte[] res = read(start, PAGE_SIZE);
        return res;
    }

    public ListBaseNode readListBaseNode() {
        ListBaseNode baseNode = new ListBaseNode();
        baseNode.length = readInt();
        baseNode.first.pageNum = readInt();
        baseNode.first.offset = readShort();
        baseNode.last.pageNum = readInt();
        baseNode.last.offset = readShort();
        return baseNode;
    }

    public ListNode readListNode() {
        ListNode node = new ListNode();
        node.prev.pageNum = readInt();
        node.prev.offset = readShort();
        node.next.pageNum = readInt();
        node.next.offset = readShort();
        return node;
    }

    public XdesEntry readXdesEntry() {
        XdesEntry entry = new XdesEntry();
        entry.segmentId = readLong();
        entry.listNode = readListNode();
        entry.state = readInt();
        entry.bitMap = read(16);
        return entry;
    }

    public INodeEntry readINodeEntry() {
        INodeEntry inode = new INodeEntry();
        inode.segmentId = readLong();
        inode.NOT_FULL_N_USED = readInt();
        inode.freeList = readListBaseNode();
        inode.notFullList = readListBaseNode();
        inode.fullList = readListBaseNode();
        inode.magicNum = readInt();
        inode.fragArrEntries = new Integer[32];
        for (int i = 0; i < 32; i++) {
            inode.fragArrEntries[i] = readInt();
        }
        return inode;
    }

    public SegmentHeader readSegmentHeader() {
        SegmentHeader header = new SegmentHeader();
        header.spaceId = readInt();
        header.pageNum = readInt();
        header.offset = readShort();
        return header;
    }
}
