package security;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream {

    protected Hash H;
    protected byte[] K;
    private byte[] buffer;
    private int pointer;

    public CryptoOutputStream( OutputStream paramOutputStream,
        byte[] paramArrayOfByte, Hash paramHash )
    {
        super( paramOutputStream );
        H = paramHash;
        K = paramArrayOfByte;
        int i = paramHash.getNumberOfDataBytes();
        buffer = new byte[i];
        pointer = 0;
    }

    public void write( int paramInt ) throws IOException
    {
        buffer[(pointer++)] = ((byte) paramInt);
        if( pointer == buffer.length )
        {
            pointer = 0;
            write( buffer, 0, buffer.length );
        }
    }

    public void write( byte[] paramArrayOfByte, int paramInt1, int paramInt2 )
        throws IOException
    {
        byte[] arrayOfByte1 = new byte[paramInt2];
        System.arraycopy( paramArrayOfByte, paramInt1, arrayOfByte1, 0,
            paramInt2 );
        try
        {
            byte[] arrayOfByte2 = H.pack( arrayOfByte1 );
            arrayOfByte2 = OneTimeKey.xor( arrayOfByte2, K );
            out.write( arrayOfByte2 );
        }
        catch( Exception localException )
        {
            System.out.println( localException );
        }
    }

    protected void shallowFlush() throws IOException
    {
        if( pointer != 0 )
        {
            write( buffer, 0, pointer );
            pointer = 0;
        }
    }

    public void flush() throws IOException
    {
        if( pointer != 0 ) shallowFlush();
        super.flush();
    }
}
