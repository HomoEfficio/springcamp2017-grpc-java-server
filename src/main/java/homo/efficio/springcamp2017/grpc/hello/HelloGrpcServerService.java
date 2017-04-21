package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 10.
 */
public class HelloGrpcServerService extends HelloSpringCampGrpc.HelloSpringCampImplBase {

    private final Logger logger = Logger.getLogger(HelloGrpcServerService.class.getName());

    @Override
    public void unaryHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("[트럼프] 요청 받음 : " + request.getRequest());

        // 1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        HelloResponse helloResponse =
                HelloResponse.newBuilder()
                             .setResponse("입금했다 미친XX야!!")
                             .build();

        // 응답 시작
        responseObserver.onNext(helloResponse);
        // 응답 시작 후 1초 후에 응답 완료된다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        // 응답 완료
        responseObserver.onCompleted();
    }
}
