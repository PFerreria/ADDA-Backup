package tests;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejs.Ex1;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.Polynomial;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class Test1 {

	private static Integer nMin = 500; // n mínimo para el cálculo de potencia
	private static Integer nMax = 5000; // n máximo para el cálculo de potencia
	private static Integer nIncr = 250; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 500; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 1000; // número de iteraciones para warmup

	public static void genDataRBI() {
		String file = "ficheros_generados/RBI.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Ex1.ex1BI_recursive(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup2
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void genDataIBI() {
		String file = "ficheros_generados/IBI.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Ex1.ex1BI_iterative(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void genDataRD() {
		String file = "ficheros_generados/RD.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Ex1.ex1D_recursive(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void genDataID() {
		String file = "ficheros_generados/ID.txt";
		Function<Integer, Long> f1 = GenData.time(t -> Ex1.ex1D_iterative(t));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup
		GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void showGraphD(String file, String title) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), title + pl.getExpression());
	}

	public static void showGraphBI(String file, String title) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = Polynomial.of(2);
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), title + pl.getExpression());
	}

	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/RBI.txt", "ficheros_generados/IBI.txt", "ficheros_generados/RD.txt",
						"ficheros_generados/ID.txt"),
				List.of("Recursive-BigInteger", "Iterative-BigInteger", "Recursive-Double", "Iterative-Double"));
	}

	public static void showCombinedOnlyDouble() {
		MatPlotLib.showCombined("Tiempos", List.of("ficheros_generados/RD.txt", "ficheros_generados/ID.txt"),
				List.of("Recursive-Double", "Iterative-Double"));
	}

	public static void main(String[] args) {
		genDataRBI();
		genDataIBI();
		genDataRD();
		genDataID();

		showGraphBI("ficheros_generados/RBI.txt", "Recursive-BigInteger: ");
		showGraphBI("ficheros_generados/IBI.txt", "Iterative-BigInteger: ");
		showGraphD("ficheros_generados/RD.txt", "Recursive-Double: ");
		showGraphD("ficheros_generados/ID.txt", "Iterative-Double: ");

		showCombined();
		showCombinedOnlyDouble();
	}

}