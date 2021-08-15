package me.plash.reader;

import me.plash.struct.page.IbufBitmapPage;
import me.plash.util.ByteReader;

public class IbufBitmapPageReader extends PageReader {
    private static Integer CHANGE_BUFFER_BITMAP_SIZE = 8192;
    private static Integer EMPTY_SIZE = 8146;

    public IbufBitmapPage readPage(ByteReader reader){
        IbufBitmapPage page = new IbufBitmapPage();
        page.fileHeader = readFileHeader(reader);
        page.changeBufferBitmap = readChangeBufferBitmap(reader);
        page.empty = readEmpty(reader);
        page.fileTrailer = readFileTrailer(reader);
        return page;
    }

    private byte[] readChangeBufferBitmap(ByteReader byteReader) {
        return byteReader.read(CHANGE_BUFFER_BITMAP_SIZE);
    }


    private byte[] readEmpty(ByteReader reader) {
        return reader.read(EMPTY_SIZE);
    }

}
