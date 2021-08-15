package me.plash.reader;

import me.plash.struct.INodeEntry;
import me.plash.struct.base.ListNode;
import me.plash.struct.page.InodePage;
import me.plash.util.ByteReader;

public class INodePageReader extends PageReader {
    public static Integer EMPTY_SIZE = 6;
    public static Integer INODE_ENTRY_SIZE = 85;

    public InodePage readPage(ByteReader byteReader) {
        InodePage inodePage = new InodePage();
        inodePage.fileHeader = readFileHeader(byteReader);
        inodePage.inodePageList = readInodePageList(byteReader);
        inodePage.iNodeEntries = readINodeEntries(byteReader);
        inodePage.empty = readEmpty(byteReader);
        inodePage.fileTrailer = readFileTrailer(byteReader);
        return inodePage;
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
