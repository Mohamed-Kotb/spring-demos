package com.example.grpc_healthcare_client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.grpc_healthcare_client.HealthcareProto.DashboardUpdate;
import com.example.grpc_healthcare_client.HealthcareProto.VitalData;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@SpringBootApplication
public class GrpcHealthcareClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcHealthcareClientApplication.class, args);
		try {
			exec();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void exec() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        DashboardServiceGrpc.DashboardServiceStub stub = DashboardServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<VitalData> requestObserver = stub.liveDashboard(new StreamObserver<DashboardUpdate>() {
            @Override
            public void onNext(DashboardUpdate update) {
                System.out.println("Received update: " + update.getUpdateMessage());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed.");
                latch.countDown();
            }
        });

        for (int i = 0; i < 15; i++) {
            VitalData data = VitalData.newBuilder()
                    .setPatientId("P001")
                    .setHeartRate(70 + i)
                    .setBloodPressureSystolic(120 + i)
                    .setBloodPressureDiastolic(80 + i)
                    .setOxygenSaturation(95 + i)
                    .setTimestamp(System.currentTimeMillis())
                    .build();
            requestObserver.onNext(data);
            Thread.sleep(1000);
        }

        requestObserver.onCompleted();
        latch.await(5, TimeUnit.SECONDS);
        channel.shutdown();
    }

}
