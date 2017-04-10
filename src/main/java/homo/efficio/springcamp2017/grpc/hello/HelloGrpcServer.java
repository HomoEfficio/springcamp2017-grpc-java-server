package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 10.
 */
public class HelloGrpcServer {

    private final Logger logger = Logger.getLogger(HelloGrpcServer.class.getName());

    private final Server server;

    public HelloGrpcServer(int port, BindableService service) throws IOException {

        this.server = ServerBuilder.forPort(port)
                .addService(service)
                .build()
                .start();
        logger.info("Hello gRPC 서버 시작!!! on " + port);
    }

    public Server getServer() {
        return server;
    }
}
