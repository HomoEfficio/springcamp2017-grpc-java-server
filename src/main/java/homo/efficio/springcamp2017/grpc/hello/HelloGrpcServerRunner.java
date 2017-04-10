package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.BindableService;
import io.grpc.Server;

import java.io.IOException;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 10.
 */
public class HelloGrpcServerRunner {

    public static void main(String[] args) throws IOException, InterruptedException {

        final int port = 54321;
        final BindableService helloService = new HelloGrpcServerService();

        HelloGrpcServer server = new HelloGrpcServer(port, helloService);

        server.start();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> server.shutdown())
        );
    }
}
