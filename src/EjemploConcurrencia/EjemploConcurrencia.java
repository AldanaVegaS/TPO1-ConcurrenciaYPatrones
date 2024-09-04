package EjemploConcurrencia;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EjemploConcurrencia {
	public static void main(String[] args) {

		ScheduledExecutorService executorTransferencias = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService executorDepositos = Executors.newScheduledThreadPool(3);
		CuentaBancaria cuenta = new CuentaBancaria(5000);

		Runnable task1 = () -> {
			cuenta.realizarTransferencia(500, Thread.currentThread().getName());
		};

		Runnable task2 = () -> {
			cuenta.depositar(500, Thread.currentThread().getName());
		};

		executorTransferencias.scheduleAtFixedRate(task1, 1, 2, TimeUnit.SECONDS);
		executorDepositos.scheduleAtFixedRate(task2, 1, 2, TimeUnit.SECONDS);

		executorTransferencias.schedule(() -> {
			executorTransferencias.shutdown();
			System.out.println("Executor de transferencias cerrado.");
		}, 10, TimeUnit.SECONDS);

		executorDepositos.schedule(() -> {
			executorDepositos.shutdown();
			System.out.println("Executor de depositos cerrado.");
		}, 15, TimeUnit.SECONDS);

	}
}
