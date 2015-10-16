import java.io.FileInputStream;
import java.util.Properties;
import security.RSA;
import security.SSLSocket;

public class SSLClient {

    // SSL network socket
    private SSLSocket s;

    public SSLClient( String host, int port, String filename ) throws Exception
    {
        Properties localProperties = new Properties();
        FileInputStream localFileInputStream = new FileInputStream( filename
            + ".txt" );
        localProperties.load( localFileInputStream );
        localFileInputStream.close();
        
        String str = localProperties.getProperty( "company" );
        
        RSA.PublicKey localPublicKey = new RSA.PublicKey(
            localProperties.getProperty( "server.public_key" ).getBytes() );
        
        security.RSA.PrivateKey localPrivateKey = new security.RSA.PrivateKey(
            localProperties.getProperty( "private_key" ).getBytes() );
        byte b = (byte) Integer.parseInt( localProperties.getProperty( "pattern" ) );
        int i = Integer.parseInt( localProperties.getProperty( "ndatabytes" ) );
        int j = Integer.parseInt( localProperties.getProperty( "ncheckbytes" ) );
        int k = Integer.parseInt( localProperties.getProperty( "k" ) );

        security.Hash localHash = new security.Hash( i, j, b, k );

        byte[] arrayOfByte1 = RSA.cipher( str.getBytes(), localPrivateKey );
        byte[] arrayOfByte2 = security.OneTimeKey.newKey( i + j + 1 );

        byte[] arrayOfByte3 = RSA.cipher( arrayOfByte2, localPublicKey );

        byte[] arrayOfByte4 = RSA.cipher( filename.getBytes(), localPublicKey );
        s = new SSLSocket( host, port, arrayOfByte4, arrayOfByte1,
            arrayOfByte3, arrayOfByte2, localHash );
    }

    // data transfer
    public void execute() throws Exception
    {
        int c, k = 0, i = 0;

        // read data from keyboard until end of file
        while( (c = System.in.read()) != -1 )
        {

            // send it to server
            s.getOutputStream().write( c );
            // if carriage return, flush stream
            if( (char) c == '\n' || (char) c == '\r' )
                s.getOutputStream().flush();
            ++k;
        }
        s.getOutputStream().flush();

        // read until end of file or same number of characters
        // read from server
        while( (c = s.getInputStream().read()) != -1 )
        {
            System.out.write( c );
            if( ++i == k ) break;
        }
        System.out.println();
        System.out.println( "wrote " + i + " bytes" );
        s.close();
    }

    public static void main( String[] args ) throws Exception
    {
        if( args.length != 3 )
        {
            System.out.println( "java SSLClient <host> <port> <name>" );
            System.exit( 1 );
        }

        String host = args[0];
        int port = Integer.parseInt( args[1] );
        String filename = args[2];
        SSLClient client = new SSLClient( host, port, filename );
        client.execute();
    }
}
