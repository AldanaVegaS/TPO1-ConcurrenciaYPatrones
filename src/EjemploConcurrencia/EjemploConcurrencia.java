package EjemploConcurrencia;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EjemploConcurrencia {
	public static void main(String[] args) {

		ScheduledExecutorService executorTransferencias = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService executorDepositos = Executors.newScheduledThreadPool(6);
		CuentaBancaria cuenta = new CuentaBancaria(5000);

		Runnable task1 = () -> {
			int aux = (int) ((Math.random() * 30) + 1) * 1000;
			cuenta.realizarTransferencia(aux, Thread.currentThread().getName());
		};

		Runnable task2 = () -> {
			int aux = (int) ((Math.random() * 30) + 1) * 1000;
			cuenta.depositar(aux, Thread.currentThread().getName());
		};

		executorTransferencias.scheduleAtFixedRate(task1, 300, 500, TimeUnit.MILLISECONDS);
		executorDepositos.scheduleAtFixedRate(task2, 300, 500, TimeUnit.MILLISECONDS);

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
