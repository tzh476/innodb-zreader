package me.plash.reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import me.plash.struct.TableSpace;
import me.plash.struct.page.FspHdrPage;
import me.plash.struct.page.IbufBitmapPage;
import me.plash.struct.page.InodePage;
import me.plash.struct.page.PageType;
import me.plash.util.ByteReader;
import me.plash.util.Check;

@Slf4j
public class TableSpaceReader {
    public static void readTableSpace(String path){
        ByteReader byteReader = new ByteReader();
        byteReader.readFile(path);
        TableSpace tableSpace = new TableSpace();
        tableSpace.fspHdrPage = readFspHdrPage(byteReader);
        tableSpace.ibufBitmapPage = readIbufBitmapPage(byteReader);
        tableSpace.inodePage = readINodePage(byteReader);
       // tableSpace.inodePage = readINodePage(byteReader);

        System.out.println(JSON.toJSONString(tableSpace, SerializerFeature.PrettyFormat));

    }

    private static FspHdrPage readFspHdrPage(ByteReader byteReader){
        FspHdrPageReader reader = new FspHdrPageReader();
        FspHdrPage page = reader.readPage(byteReader);
        Check.checkPageType(page, PageType.PAGE_TYPE_FSP_HDR);
        return page;
    }

    private static IbufBitmapPage readIbufBitmapPage(ByteReader byteReader){
        IbufBitmapPageReader reader = new IbufBitmapPageReader();
        IbufBitmapPage page = reader.readPage(byteReader);
        Check.checkPageType(page, PageType.PAGE_IBUF_BITMAP);
        return page;
    }

    private static InodePage readINodePage(ByteReader byteReader){
        INodePageReader reader = new INodePageReader();
        InodePage page = reader.readPage(byteReader);
        Check.checkPageType(page, PageType.PAGE_INODE);
        return page;
    }
}
