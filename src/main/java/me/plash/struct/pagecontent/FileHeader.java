package me.plash.struct.pagecontent;

public class FileHeader {
    public Integer CHKSUM;//4
    public Integer FIL_PAGE_OFFSET;//4
    public Integer FIL_PAGE_PREV;//4
    public Integer FIL_PAGE_NEXT;//4
    public Long FIL_PAGE_LSN;//8
    public Short FIL_PAGE_TYPE;//2
    public Long FIL_PAGE_FILE_FLUSH_LSN;//8
    public Integer FIL_PAGE_ARCH_LOG_NO_OR_SPACE_ID;//4

    @Override
    public String toString() {
        return "FileHeader{" +
                "CHKSUM=" + CHKSUM +
                ", FIL_PAGE_OFFSET=" + FIL_PAGE_OFFSET +
                ", FIL_PAGE_PREV=" + FIL_PAGE_PREV +
                ", FIL_PAGE_NEXT=" + FIL_PAGE_NEXT +
                ", FIL_PAGE_LSN=" + FIL_PAGE_LSN +
                ", FIL_PAGE_TYPE=" + FIL_PAGE_TYPE +
                ", FIL_PAGE_FILE_FLUSH_LSN=" + FIL_PAGE_FILE_FLUSH_LSN +
                ", FIL_PAGE_ARCH_LOG_NO_OR_SPACE_ID=" + FIL_PAGE_ARCH_LOG_NO_OR_SPACE_ID +
                '}';
    }
}
