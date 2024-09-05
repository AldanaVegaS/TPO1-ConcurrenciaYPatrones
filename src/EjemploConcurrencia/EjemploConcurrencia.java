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
			int transferir = (int) (( (Math.random())*30) +1 )*1000;
			System.out.println("Transferir "+transferir);

			cuenta.realizarTransferencia(transferir, Thread.currentThread().getName());
		};

		Runnable task2 = () -> {
			int depositar = (int) (( (Math.random())*20) +1 )*1000;
			System.out.println("Depositar "+depositar);
			cuenta.depositar(depositar, Thread.currentThread().getName());
		};

		executorTransferencias.scheduleAtFixedRate(task1, 1, 5, TimeUnit.SECONDS);
		executorDepositos.scheduleAtFixedRate(task2, 1, 5, TimeUnit.SECONDS);

		executorTransferencias.schedule(() -> {
			executorTransferencias.shutdown();
			System.out.println("Executor de transferencias cerrado.");
		}, 20, TimeUnit.SECONDS);

		executorDepositos.schedule(() -> {
			executorDepositos.shutdown();
			System.out.println("Executor de depositos cerrado.");
		}, 25, TimeUnit.SECONDS);

	}
}
