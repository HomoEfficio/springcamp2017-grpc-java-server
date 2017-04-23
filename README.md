# Spring Camp 2017 gRPC

Spring Camp 2017의 **gㅏ벼운 RPC, gRPC**의 예제 코드와 발표에 다 담지 못한 나머지 예제들

## gRPC 예제 코드 - 서버

### 브랜치

- master 브랜치: gRPC의 4가지 스트리밍 모두를 다루는 예제 포함
- comic 브랜치: 발표에 사용된 코믹 버전 - Async Unary Call만 포함

### 환경 구성

- JDK 7+
-  .proto 파일 편집 플러그인(필수는 아님)
    - IntelliJ: Protobuf Support
    - Eclipse: https://marketplace.eclipse.org/category/free-tagging/grpc/title

### 코드 보기

- 최초 커밋부터 순서대로 체크아웃하면서 보면 gRPC 프로그램 작성 순서와 함께 더 쉽게 이해할 수 있음

### 실행

- HelloGrpcServerRunner.java 파일의 메인 메서드로 gRPC 서버 실행

### 슬라이드

- [Spring Camp 2017 - gㅏ벼운 RPC, gRPC (약 8M)](https://github.com/HomoEfficio/dev-tips/blob/master/SpringCamp2017%20-%20g%E3%85%8F%E1%84%87%E1%85%A7%E1%84%8B%E1%85%AE%E1%86%AB%20RPC%2C%20gRPC.pdf)
