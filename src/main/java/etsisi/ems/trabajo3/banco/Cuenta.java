package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

public class Cuenta {
	protected String mNumero;
	protected String mTitular;
	protected Vector<Movimiento> mMovimientos;

	public Cuenta(String numero, String titular) {
		mNumero = numero;
		mTitular = titular;
		mMovimientos = new Vector<Movimiento>();
	}
	
	public void aniadirMov(double x, String concepto) {
		Movimiento m = new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(x);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		this.mMovimientos.addElement(m);
	}
	
	public void ingresar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		aniadirMov(x, "Ingresar efectivo");
	}

	public void retirar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		aniadirMov(x, "Retirada de efectivo");

	}

	public void ingresar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		aniadirMov(x, concepto);
	}

	public void retirar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		aniadirMov(x, concepto);
	}

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.elementAt(i);
			r += m.getImporte();			
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.addElement(m);
	}
}
