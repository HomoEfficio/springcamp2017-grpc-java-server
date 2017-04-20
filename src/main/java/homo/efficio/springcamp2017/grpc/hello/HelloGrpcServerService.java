package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
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

        // 1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        HelloResponse helloResponse =
                HelloResponse.newBuilder()
                             .setWelcomeMessage("Unary Hello " + request.getClientName())
                             .build();

        // 응답 시작
        responseObserver.onNext(helloResponse);

        // 응답 시작 후 1초 후에 응답 완료된다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // 응답 완료
        responseObserver.onCompleted();
    }

//    @Override
//    public void serverStreamingHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
//        logger.info("Server Streaming 메시지 왔다: " + request.getClientName());
//        HelloResponse response =
//                HelloResponse.newBuilder()
//                             .setWelcomeMessage("Server Streaming Hello " + request.getClientName())
//                             .build();
//
//        // Server Streaming이면 responseObserver.onNext()를 두 번 이상 호출할 수 있다.
//        responseObserver.onNext(response);
//        responseObserver.onNext(response);
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }

    @Override
    public void serverStreamingHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Server Streaming 메시지 왔다: " + request.getClientName());

        // 1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        HelloResponse response =
                HelloResponse.newBuilder()
                        .setWelcomeMessage("Server Streaming Hello " + request.getClientName())
                        .build();

        // Server Streaming이면 responseObserver.onNext()를 두 번 이상 호출할 수 있다.
        responseObserver.onNext(response);
        responseObserver.onNext(response);
        responseObserver.onNext(response);

        // 응답 시작 후 1초 후에 응답 완료된다고 가정
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // 응답 완료
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest> clientStreamingHello(StreamObserver<HelloResponse> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            StringBuilder sb = new StringBuilder();
            @Override
            public void onNext(HelloRequest request) {
                logger.info("Client Streaming 메시지 왔다: " + request.getClientName());

                sb.append("Client Streaming Hello " + request.getClientName())
                  .append("\n============================\n");

                // 1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "Client Streaming requestObserver.onError() 호출");
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(HelloResponse.newBuilder().setWelcomeMessage(sb.toString()).build());
                // server는 Streaming이 아니므로 responseObserver.onNext()를 2회 이상 호출할 수 없음.
                // 아래와 같이 2회 이상 호출하면 responseObserver.onError() 호출됨
//                responseObserver.onNext(HelloResponse.newBuilder().setWelcomeMessage(sb.toString()).build());

                // 응답 시작 후 1초 후에 응답 완료된다고 가정
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                responseObserver.onCompleted();
            }
        };
    }

}
