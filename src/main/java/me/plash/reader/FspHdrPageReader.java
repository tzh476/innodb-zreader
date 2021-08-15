package me.plash.reader;

import me.plash.struct.pagecontent.FileSpaceHeader;
import me.plash.struct.XdesEntry;
import me.plash.struct.page.FspHdrPage;
import me.plash.util.ByteReader;

public class FspHdrPageReader extends PageReader{
    public static Integer EMPTY_SIZE = 5986;
    public FspHdrPage readPage(ByteReader byteReader) {
        FspHdrPage fspHdrPage = new FspHdrPage();
        fspHdrPage.fileHeader = readFileHeader(byteReader);
        fspHdrPage.fileSpaceHeader = readFileSpaceHeader(byteReader);
        fspHdrPage.XDESEntrys = readXdesEntries(byteReader);
        fspHdrPage.empty = readEmpty(byteReader);
        fspHdrPage.fileTrailer = readFileTrailer(byteReader);
        return fspHdrPage;
    }



    private byte[] readEmpty(ByteReader reader) {
        return reader.read(EMPTY_SIZE);
    }

    private XdesEntry[] readXdesEntries(ByteReader reader) {
        Integer entryNum = 256;
        XdesEntry[] entries = new XdesEntry[entryNum];
        for (int i = 0; i < entryNum; i++) {
            entries[i] = reader.readXdesEntry();
        }
        return entries;
    }

    public FileSpaceHeader readFileSpaceHeader(ByteReader reader) {
        //byte[] bytes = byteReader.read(112);
        FileSpaceHeader spaceHeader = new FileSpaceHeader();
        spaceHeader.spaceId = reader.readInt();
        spaceHeader.notUsed = reader.readInt();
        spaceHeader.size = reader.readInt();
        spaceHeader.freeLimit = reader.readInt();
        spaceHeader.spaceFlags = reader.readInt();
        spaceHeader.FRAG_N_USED = reader.readInt();
        spaceHeader.freeList = reader.readListBaseNode();
        spaceHeader.freeFragList = reader.readListBaseNode();
        spaceHeader.fullFragList = reader.readListBaseNode();
        spaceHeader.nextUnusedSegmentId = reader.readLong();
        spaceHeader.segInodeFreeList = reader.readListBaseNode();
        spaceHeader.segInodeFullList = reader.readListBaseNode();
        return spaceHeader;
    }
}
