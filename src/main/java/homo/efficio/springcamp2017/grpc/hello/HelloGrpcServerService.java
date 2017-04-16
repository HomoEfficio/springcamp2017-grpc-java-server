package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017. 4. 10.
 */
public class HelloGrpcServerService extends HelloSpringCampGrpc.HelloSpringCampImplBase {

    private final Logger logger = Logger.getLogger(HelloGrpcServerService.class.getName());

//    @Override
//    public void unaryHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
//        logger.info("Unary 메시지 왔다: " + request.getClientName());
//        HelloResponse helloResponse = HelloResponse.newBuilder().setWelcomeMessage("Unary Hello " + request.getClientName()).build();
//        responseObserver.onNext(helloResponse);
//        responseObserver.onCompleted();
//    }

    // Async Unary 용 코드
    // Async 동작 확인을 위해 Thread.sleep()으로 지연 시간을 준 것 외에는 Blocking Unary와 동일
    @Override
    public void unaryHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Unary 메시지 왔다: " + request.getClientName());

        // Async Unary 테스트: 1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        HelloResponse helloResponse =
                HelloResponse.newBuilder()
                             .setWelcomeMessage("Unary Hello " + request.getClientName())
                             .build();

        // 응답 시작
        responseObserver.onNext(helloResponse);

        // Async Unary 테스트: 응답 시작 후 1초 후에 응답 완료된다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // 응답 완료
        responseObserver.onCompleted();
    }
}
