package com.bhz.lht.ovirtdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.ovirt.engine.sdk4.Connection;
import org.ovirt.engine.sdk4.builders.VmBuilder;

import static org.ovirt.engine.sdk4.ConnectionBuilder.connection;
import org.ovirt.engine.sdk4.services.SystemService;
import org.ovirt.engine.sdk4.services.VmsService;

import java.util.List;
import org.ovirt.engine.sdk4.types.Vm;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("the demo app is running ... ...");
		// Create a connection to the server:
		Connection connection = connection().url("https://ovirt-engine.lht.com/ovirt-engine/api").user("admin@internal")
				.password("konghong").trustStoreFile("/Users/liuhongtian/Documents/Workspace-VSCode/masaas2/ovirt-demo/truststore.jks").build();

		// Get the reference to the system service:
		SystemService systemService = connection.systemService();
		// Get the reference to the "vms" service:
		VmsService vmsService = systemService.vmsService();

		// Retrieve the virtual machines:
		List<Vm> vms = vmsService.list().send().vms();

		// Print the names and identifiers of the virtual machines:
		for (Vm vm : vms) {
			System.out.printf("%s: %s%n", vm.id(), vm.name());
		}

		// Always remember to close the connection when finished:
		connection.close();

	}

}
