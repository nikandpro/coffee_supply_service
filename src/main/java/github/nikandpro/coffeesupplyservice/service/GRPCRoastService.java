package github.nikandpro.coffeesupplyservice.service;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GRPCRoastService{

    private final RoastService roastService;


}
