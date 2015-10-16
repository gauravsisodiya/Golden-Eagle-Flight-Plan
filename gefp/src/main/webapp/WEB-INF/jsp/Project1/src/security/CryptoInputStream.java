package security;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream {

    protected Hash H;

    protected byte[] K;

    protected byte[] buffer;

    protected int pointer;

    public CryptoInputStream( InputStream paramInputStream,
        byte[] paramArrayOfByte, Hash paramHash )
    {
        super( paramInputStream );
        H = paramHash;
        K = paramArrayOfByte;
        pointer = 0;
    }

    public int read() throws IOException
    {
        if( pointer == 0 )
        {
            int i = 0;
            byte[] arrayOfByte = new byte[H.getPacketSize()];
            for( int k = 0; k < H.getPacketSize(); k++ )
            {
                int m = in.read();
                if( m == -1 )
                {
                    if( k == 0 ){ return -1; }
                    throw new IOException( "Error in reading data" );
                }
                arrayOfByte[(i++)] = ((byte) m);
            }

            try
            {
                arrayOfByte = OneTimeKey.xor( arrayOfByte, K );
                buffer = H.unpack( arrayOfByte );
            }
            catch( RuntimeException localRuntimeException )
            {
                System.out.println( localRuntimeException );
            }
            catch( Exception localException )
            {
                throw new IOException( "error in reading" );
            }
        }

        int i = buffer[pointer];
        int j = buffer.length;
        pointer = ((pointer + 1) % j);
        return i;
    }

    public int read( byte[] paramArrayOfByte, int paramInt1, int paramInt2 )
        throws IOException
    {
        if( paramArrayOfByte == null ){ throw new NullPointerException(
            "Empty Buffer" ); }

        int k = H.getPacketSize();
        int m = H.getNumberOfDataBytes();

        int i = paramInt1 / m;
        int j = paramInt1 % m;

        int n = (paramInt2 + j) / k;
        int i2 = (paramInt2 + j) % k;

        if( i2 != 0 )
        {
            n++;
        }
        byte[] arrayOfByte2 = new byte[n * k];
        int i3 = i * k;
        int i4 = n * k;
        try
        {
            if( super.available() >= i4 )
            {
                int i1 = in.read( arrayOfByte2, i3, i4 );
                if( i1 == -1 ) return i1;
                byte[] arrayOfByte1 = OneTimeKey.xor( arrayOfByte2, K );
                arrayOfByte1 = H.unpack( arrayOfByte1 );
                System.arraycopy( arrayOfByte1, 0, paramArrayOfByte, 0,
                    arrayOfByte1.length );
                return i1 / k * m;
            }
            return 0;
        }
        catch( Exception localException )
        {
            System.out.println( "Error in decryptinge" );
        }
        return 0;
    }

    public long skip( long paramLong ) throws IOException
    {
        for( long l = 0L; l < paramLong; l += 1L )
            if( read() == -1 ) return l;
        return paramLong;
    }

    public int available() throws IOException
    {
        int i = super.available();
        return i / H.getPacketSize() * H.getNumberOfDataBytes();
    }
}
