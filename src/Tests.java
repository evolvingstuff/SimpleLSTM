import java.util.Random;

import com.evolvingstuff.DistractedSequenceRecall;
import com.evolvingstuff.SimpleLSTM;
import static com.evolvingstuff.util.*;

import org.junit.Test;

public class Tests {
	
	
	//@Test
	public void testDistractedSequenceRecall() throws Exception {
        
        System.out.println("Test of DistractedSequenceRecall\n");
        
        Random r = new Random(1234);
        DistractedSequenceRecall task = new DistractedSequenceRecall(r);

        int cell_blocks = 15;
        SimpleLSTM slstm = new SimpleLSTM(r, task.GetObservationDimension(), task.GetActionDimension(), cell_blocks);
        
        for (int epoch = 0; epoch < 5000; epoch++) {
                double fit = task.EvaluateFitnessSupervised(slstm);
                if (epoch % 10 == 0)
                        System.out.println("["+epoch+"] error = " + (1 - fit));
        }
        System.out.println("done.");
	}
	
	
	//@Test
	public void testXOR() throws Exception {
		
		System.out.println("Test of XOR\n");
		
		Random r = new Random(1234);
		
		double[][] inputs = {
				{0, 0},
				{1, 1},
				{0, 1},
				{1, 0},
				{1, 1}
		};
		double[][] outputs = {
				{0},
				{0},
				{1},
				{1},
				{0}
		};
		
		int cell_blocks = 3;
		SimpleLSTM slstm = new SimpleLSTM(r, 2, 1, cell_blocks);
		//slstm.Reset();
		
		for (int epoch = 0; epoch < 10000; epoch++) {

			double e=0;
			for (int i=0; i<inputs.length; i++) {
				double[] actual_output = slstm.Next(inputs[i], outputs[i]);
				e += getSquaredError(actual_output, outputs[i]);
			}
			if (epoch % 100 == 0)
				System.out.println("["+epoch+"] error = " +  e);
		}
		
		System.out.println("done.");
		
		for (int i=0; i<inputs.length; i++) {
			double[] actual_output = slstm.Next(inputs[i]);
			System.out.println(join(actual_output) +" vs " +join(outputs[i]));
		}
		
		System.out.println("tests");
		System.out.println(join(slstm.Next(new double[]{0,0.1})));
		System.out.println(join(slstm.Next(new double[]{0,0.9})));
		System.out.println(join(slstm.Next(new double[]{0.9,0.9})));
		System.out.println(join(slstm.Next(new double[]{0.2,0.6})));
		System.out.println(join(slstm.Next(new double[]{1,0.5})));
		
	}
	
	@Test
	public void testSin() throws Exception {
		
		System.out.println("Test of sin(x)\n");
		
		Random r = new Random(1234);
		
		int N = 30;
		int k=5;
		
		double[][] inputs = new double[N][];
		double[][] outputs = new double[N][];
		for (int i=0; i<N; i++){
			inputs[i] = sin(seq(i, k, 1));
			outputs[i] = sin(seq(i+k, 1, 1));
			//System.out.println(util.join(inputs[i]));
			//System.out.println(util.join(outputs[i]));
		}
		
		int cell_blocks = 10;
		SimpleLSTM slstm = new SimpleLSTM(r, k, 1, cell_blocks);
		//slstm.Reset();
		
		for (int epoch = 0; epoch < 10000; epoch++) {
			double e=0;
			for (int i=0; i<inputs.length; i++) {
				double[] actual_output = slstm.Next(inputs[i], outputs[i]);
				e += getSquaredError(actual_output, outputs[i]);
			}
			if (epoch % 100 == 0)
				System.out.println("["+epoch+"] error = " +  e);
		}
		
		System.out.println("done.");
		
		for (int i=0; i<inputs.length; i++) {
			double[] actual_output = slstm.Next(inputs[i]);
			System.out.println(join(actual_output) +" vs " +join(outputs[i]));
		}
		
		System.out.println("tests");
		System.out.println(join(slstm.Next(sin(seq(N, k, 1)))) + " Vs "+ join(sin(seq(N+k, 1, 1))));
	}
	
	

}
