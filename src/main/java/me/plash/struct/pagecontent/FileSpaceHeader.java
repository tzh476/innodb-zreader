package me.plash.struct.pagecontent;

import me.plash.struct.base.ListBaseNode;

public class FileSpaceHeader {
    public Integer spaceId;//2
    public Integer notUsed;
    public Integer size;
    public Integer freeLimit;
    public Integer spaceFlags;
    public Integer FRAG_N_USED;
    public ListBaseNode freeList;
    public ListBaseNode freeFragList;
    public ListBaseNode fullFragList;
    public Long nextUnusedSegmentId;
    public ListBaseNode segInodeFullList;
    public ListBaseNode segInodeFreeList;

    @Override
    public String toString() {
        return "FileSpaceHeader{" +
                "spaceId=" + spaceId +
                ", notUsed=" + notUsed +
                ", size=" + size +
                ", freeLimit=" + freeLimit +
                ", spaceFlags=" + spaceFlags +
                ", FRAG_N_USED=" + FRAG_N_USED +
                ", freeList=" + freeList +
                ", freeFragList=" + freeFragList +
                ", fullFragList=" + fullFragList +
                ", nextUnusedSegmentId=" + nextUnusedSegmentId +
                ", segInodeFullList=" + segInodeFullList +
                ", segInodeFreeList=" + segInodeFreeList +
                '}';
    }
}
