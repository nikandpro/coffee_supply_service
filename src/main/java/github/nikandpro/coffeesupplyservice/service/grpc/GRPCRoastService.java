package github.nikandpro.coffeesupplyservice.service.grpc;

import com.github.grpccommon.RoastRequest;
import com.github.grpccommon.RoastServerGrpc;
import com.google.protobuf.Empty;
import github.nikandpro.coffeesupplyservice.dto.RoastDto;
import github.nikandpro.coffeesupplyservice.mapper.RoastMapper;
import github.nikandpro.coffeesupplyservice.service.RoastService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GRPCRoastService extends RoastServerGrpc.RoastServerImplBase {

    private final RoastService roastService;
    private final RoastMapper roastMapper;

    @Override
    public void addRoastInfo(RoastRequest request, StreamObserver<Empty> responseObserver) {
        RoastDto roastDto = roastMapper.toDto(request);
        roastService.save(roastDto);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<RoastRequest> addRoastStreamInfo(StreamObserver<Empty> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(RoastRequest roastRequest) {
                RoastDto roastDto = roastMapper.toDto(roastRequest);
                roastService.save(roastDto);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Empty.newBuilder().build());
                responseObserver.onCompleted();
            }
        };
    }
}
