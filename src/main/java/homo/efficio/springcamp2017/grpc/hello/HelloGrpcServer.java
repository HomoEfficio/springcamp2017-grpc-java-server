package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 10.
 */
public class HelloGrpcServer {

    private final Logger logger = Logger.getLogger(HelloGrpcServer.class.getName());

    private final int port;
    private final Server server;

    public HelloGrpcServer(int port, BindableService service) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                                   .addService(service)
                                   .build();
    }

    public void start() throws IOException, InterruptedException {
        this.server.start();
        logger.info("트럼프가 " + port + "포트에서 리스닝 중..");
        this.server.awaitTermination();
    }

    public void shutdown() {
        System.err.println("트럼프 서버 종료...");
        server.shutdown();
        System.err.println("트럼프 서버 종료 완료");
    }
}
