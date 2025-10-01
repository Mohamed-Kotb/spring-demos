package com.example.grpc_healthcare;

import org.springframework.stereotype.Service;

import com.example.grpc_healthcare.HealthcareProto.DashboardUpdate;
import com.example.grpc_healthcare.HealthcareProto.VitalData;

import io.grpc.stub.StreamObserver;

@Service
public class DashboardServiceImpl extends DashboardServiceGrpc.DashboardServiceImplBase {
    @Override
    public StreamObserver<VitalData> liveDashboard(StreamObserver<DashboardUpdate> responseObserver) {
        return new StreamObserver<VitalData>() {
            @Override
            public void onNext(VitalData data) {
                String message = String.format("Received vitals for patient %s: HR=%d, BP=%d/%d, O2=%d%%",
                        data.getPatientId(),
                        data.getHeartRate(),
                        data.getBloodPressureSystolic(),
                        data.getBloodPressureDiastolic(),
                        data.getOxygenSaturation());
                
                System.out.printf("Server recived message :: %S",message);
                System.out.println();

                DashboardUpdate update = DashboardUpdate.newBuilder()
                        .setPatientId(data.getPatientId())
                        .setUpdateMessage(message)
                        .setTimestamp(System.currentTimeMillis())
                        .build();

                responseObserver.onNext(update);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in liveDashboard: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
