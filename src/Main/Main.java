package Main;
import Matrice.Matrice;
import Rational.Rational;

/*public class Main {

	public static void main(String[] args) {
		Matrice A = new Matrice(new long[][] {
			{ 1, 2, 3},
			{ 4, 5, 6}, 
			
			
			});
		Matrice B = new Matrice(new long[][] { 
			{ 500 }, 
			{ 500} ,
			{ 0},
			});
		
		Matrice C = new Matrice(new long[][] {
			{ 7, 8 },
			{ 9, 10 },
			{ 11, 12 },
		
			});
		
		Matrice N = new Matrice(new long[][]{{1, 0, 0,}, {0, 1, 0}, {1, 1, -1,}});
		Matrice D = A.transpose();
		//Matrice B = A.times(A.transpose());
		//Matrice C = B.inverse();
		//Matrice sum = A.plus(B);
		Matrice prod = A.times(C);
		//C.transvection(0,1,new Rational(2));
		//C.multiplyRow(0, new Rational(2));
		//System.out.println(sum);
		var test = N.inverse();
		var res = N.resoud(B);
		//Matrice test = N.inverse();
		System.out.println(prod);
		System.out.println(D);
		System.out.println(C);
		System.out.println(N);
		System.out.println(test);
		System.out.println(res);
		
		
			
		
		
		//Matrice D = A.transpose().times(A);
		try {
			//Matrice E = D.inverse();
			//System.out.println(E);
		} catch (ArithmeticException e) {
		//	System.out.println("D n'a pas d'inverse");
		}
	}

}*/

public class Main {
public static void main(String[] args) {
    Matrice A = new Matrice(new long[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 13 } });
    Matrice B = A.times(A.transpose());
    Matrice C = B.inverse();
    System.out.println(C);
    Matrice D = A.transpose().times(A);
    System.out.println(D);
    try {
        Matrice E = D.inverse();
        System.out.println(E);
    } catch (ArithmeticException e) {
        System.out.println("D n'a pas d'inverse");
    }
}
}
