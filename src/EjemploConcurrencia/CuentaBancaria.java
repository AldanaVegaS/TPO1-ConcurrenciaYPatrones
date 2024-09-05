package EjemploConcurrencia;

public class CuentaBancaria {
	
	private int montoActual;
	
	public CuentaBancaria() {
	}

	public CuentaBancaria(int montoActual) {
		this.montoActual = montoActual;
	}

	public synchronized void realizarTransferencia(int montoATransferir, String nombre) {
		String aux="Hilo - "+nombre+"\n";
		if(montoATransferir<= montoActual) {
		aux = "Monto antes de realizar transferencia: "+montoActual+"\n";
		montoActual-= montoATransferir;
		aux+= "Monto luego de realizar transferencia: " + montoActual;
	}else {
		aux="Dinero insuficiente. No se pudo realizar la transferencia";
	}
		System.out.println(aux);
	}
	
	public synchronized void depositar(int montoRecibido, String nombre) {
		String aux = "Hilo - "+nombre+"\n"+"Monto antes de recibir deposito: "+montoActual+"\n";
		montoActual+= montoRecibido;
		aux+= "Monto luego de recibir deposito: " + montoActual;
		System.out.println(aux);
	}
	
}
