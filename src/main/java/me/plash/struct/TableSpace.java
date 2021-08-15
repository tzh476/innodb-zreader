package me.plash.struct;

import me.plash.struct.page.FspHdrPage;
import me.plash.struct.page.IbufBitmapPage;
import me.plash.struct.page.InodePage;

public class TableSpace {
    public FspHdrPage fspHdrPage;
    public IbufBitmapPage ibufBitmapPage;
    public InodePage inodePage;

}
