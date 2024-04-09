package tests;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import us.lsi.common.List2;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;
import us.lsi.recursivos.problemasdelistas.ProblemasDeListas;

public class Test2 {

	private static Integer nMin = 2; // n mínimo para el cálculo de potencia
	private static Integer nMax = 5000; // n máximo para el cálculo de potencia
	private static Integer nIncr = 2; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 50; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 150; // número de iteraciones para warmup

	public static void genDataMgSort(Integer umbral) {
		String file = "ficheros_generados/" + umbral + ".txt";
		Function<Integer, Long> f1 = GenData
				.time(t -> ProblemasDeListas.mergeSort(List2.listDoubleAleatoria(t, -20, 20), umbral));
//		Integer tMin,Integer tMax,Integer tInc,Integer numIter,Integer numIterWarmup2
		GenData.tiemposEjecucionGeometrica(f1, file, nMin, nMax, nIncr, nIter, nIterWarmup);
	}

	public static void showMgSort(String file) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		Fit pl = PowerLog.of(List.of(Pair.of(1, 1.), Pair.of(3, 0.)));
		pl.fit(data);
		System.out.println(pl.getExpression());
		System.out.println(pl.getEvaluation().getRMS());
		MatPlotLib.show(file, pl.getFunction(), pl.getExpression());
	}

	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/1.txt", "ficheros_generados/4.txt", "ficheros_generados/16.txt",
						"ficheros_generados/64.txt", "ficheros_generados/256.txt"),
				List.of("Umbral 1", "Umbral 4", "Umbral 16", "Umbral 64", "Umbral 256"));
	}

	public static void main(String[] args) {
		genDataMgSort(1);
		genDataMgSort(4);
		genDataMgSort(16);
		genDataMgSort(64);
		genDataMgSort(256);

		showMgSort("ficheros_generados/1.txt");
		showMgSort("ficheros_generados/4.txt");
		showMgSort("ficheros_generados/16.txt");
		showMgSort("ficheros_generados/64.txt");
		showMgSort("ficheros_generados/256.txt");

		showCombined();
	}

}