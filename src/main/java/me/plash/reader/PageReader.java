package me.plash.reader;

import me.plash.struct.page.IbufBitmapPage;
import me.plash.struct.pagecontent.FileHeader;
import me.plash.struct.pagecontent.FileTrailer;
import me.plash.util.ByteReader;

public abstract class PageReader {

    public FileTrailer readFileTrailer(ByteReader reader) {
        FileTrailer trailer = new FileTrailer();
        trailer.checkSum = reader.readInt();
        trailer.lsn = reader.readInt();
        return trailer;
    }

    public FileHeader readFileHeader(ByteReader reader) {
        //byte[] bytes = byteReader.read(38);
        FileHeader fileHeader = new FileHeader();
        fileHeader.CHKSUM = reader.readInt();
        fileHeader.FIL_PAGE_OFFSET = reader.readInt();
        fileHeader.FIL_PAGE_PREV = reader.readInt();
        fileHeader.FIL_PAGE_NEXT = reader.readInt();
        fileHeader.FIL_PAGE_LSN = reader.readLong();
        fileHeader.FIL_PAGE_TYPE = reader.readShort();
        fileHeader.FIL_PAGE_FILE_FLUSH_LSN = reader.readLong();
        fileHeader.FIL_PAGE_ARCH_LOG_NO_OR_SPACE_ID = reader.readInt();
        return fileHeader;
    }

}
