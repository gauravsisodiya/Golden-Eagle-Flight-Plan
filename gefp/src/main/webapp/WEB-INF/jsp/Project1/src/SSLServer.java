import java.io.FileInputStream;
import java.util.Properties;
import security.SSLServerSocket;
import security.SSLSocket;

public class SSLServer implements Runnable {

    private security.RSA.PrivateKey serverPrivateKey;
    private Properties prop;
    private SSLServerSocket server;
    private int port;

    
    public SSLServer() throws Exception
    {
        String str1 = System.getProperty( "server.private_key" );
        if( str1 == null ) str1 = System.getProperty("user.dir")+"/private_key.txt";
        FileInputStream localFileInputStream = new FileInputStream( str1 );
        serverPrivateKey = new security.RSA.PrivateKey( localFileInputStream );
        localFileInputStream.close();

        String str2 = System.getProperty( "server.users" );
        if( str2 == null ) str2 = System.getProperty("user.dir")+"/users.txt";
        localFileInputStream = new FileInputStream( str2 );
        prop = new Properties();
        prop.load( localFileInputStream );
        localFileInputStream.close();

        String str3 = System.getProperty( "server.port" );
        if( str3 != null )
        {
            port = Integer.parseInt( str3 );
        }
        else
            port = 5000;
        server = new SSLServerSocket( port, serverPrivateKey, prop );
    }

    public class RequestHandler implements Runnable {

        private SSLSocket socket;

        public RequestHandler( SSLSocket paramSSLSocket )
        {
            socket = paramSSLSocket;
        }

        public void run()
        {
            try
            {
                System.out.println( "connect ..." );

                int i;
                while( (i = socket.getInputStream().read()) != -1 )
                {
                    if( (i >= 97) && (i <= 122) )
                    {
                        i -= 32;
                    }
                    else if( (i >= 65) && (i <= 90) )
                    {
                        i += 32;
                    }

                    socket.getOutputStream().write( i );

                    if( socket.getInputStream().available() == 0 )
                    {
                        socket.getOutputStream().flush();
                    }
                }
                socket.getOutputStream().flush();
                socket.close();
                System.out.println( "disconnect ..." );
                return;
            }
            catch( Exception localException )
            {
                System.out.println( "HANDLER: " + localException );
            }
        }
    }

    public void run()
    {
        try
        {
            for( ;; )
            {
                new Thread( new SSLServer.RequestHandler(
                    (SSLSocket) server.accept() ) ).run();
            }
        }
        catch( Exception localException )
        {
            System.out.println( "SERVER: " + localException );
        }
    }

    public static void main( String args[] ) throws Exception
    {
        new SSLServer().run();
    }
}
