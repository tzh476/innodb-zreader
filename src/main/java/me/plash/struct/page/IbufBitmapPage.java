package me.plash.struct.page;

import lombok.Getter;


@Getter
public class IbufBitmapPage extends Page {
    public byte[] changeBufferBitmap;//8192 byte,4bits per page
    public byte[] empty;//8146 bytes
}
