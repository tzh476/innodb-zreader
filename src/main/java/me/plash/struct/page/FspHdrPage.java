package me.plash.struct.page;

import lombok.Getter;
import me.plash.struct.pagecontent.FileSpaceHeader;
import me.plash.struct.XdesEntry;


@Getter
public class FspHdrPage extends Page {

    public FileSpaceHeader fileSpaceHeader;
    public XdesEntry[] XDESEntrys;
    public byte[] empty;

}
