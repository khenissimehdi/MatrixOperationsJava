package Matrice;

import java.util.Arrays;
import java.util.Optional;

import Rational.Rational;

public class Matrice {

	private final int n; /* nombre de lignes */
	private final int m; /* nombre de colonnes */
	private final Rational[][] coeff; /* liste des coefficients */

	/**
	 * Création d'une matrice
	 * 
	 * @param coeff coefficients de la matrice
	 */
	public Matrice(Rational[][] coeff) {
		n = coeff.length;
		m = coeff[0].length;
		this.coeff = coeff;
	}

	/**
	 * Création d'une matrice
	 * 
	 * @param coeff coefficients de la matrice, donnés comme long
	 */
	public Matrice(long[][] coeff) {
		n = coeff.length;
		m = coeff[0].length;
		this.coeff = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				this.coeff[i][j] = new Rational(coeff[i][j]);
			}
		}
	}

	public Matrice(Matrice a, Matrice b) {
		if (a.m != a.n && b.m != b.n && a.n != b.n && a.m != b.m) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}

		n = a.n;
		m = a.n * 2;
		this.coeff = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j < m / 2) {
					this.coeff[i][j] = a.coeff[i][j];
				} else {
					this.coeff[i][j] = b.coeff[i][j - (m / 2)];
				}
			}
		}
	}

	private Matrice splitIdentity() {
		Rational[][] coef = new Rational[this.n][this.m / 2];

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				if (j >= m / 2) {
					coef[i][j - m / 2] = this.coeff[i][j];
				}
			}
		}

		Matrice result = new Matrice(coef);
		return result;
	}

	public Rational[][] getCoef() {
		return this.coeff;
	}

	/**
	 * Calcul de la somme matricielle this + M (si les dimensions de this et M
	 * l'autorisent)
	 * 
	 * @param M matrice à ajouter : tableau n x m
	 * @return somme this + M : tableau n x m
	 */
	public Matrice plus(Matrice M) {
		if (n != M.n || m != M.m) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}

		Rational[][] sum = new Rational[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum[i][j] = coeff[i][j].plus(M.coeff[i][j]);

			}
		}

		return new Matrice(sum);
	}

	/**
	 * Calcul du produit matriciel this M (si les dimensions de this et M
	 * l'autorisent)
	 * 
	 * @param M matrice à multiplier : tableau m x p
	 * @return produit this M : tableau n x p
	 */
	public Matrice times(Matrice M) {

		if (m != M.n) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}

		int p = M.m;
		Rational[][] prod = new Rational[n][p];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < p; j++) {
				prod[i][j] = Rational.ZERO;
				for (int k = 0; k < m; k++) {

					prod[i][j] = prod[i][j].plus(coeff[i][k].times(M.coeff[k][j]));

				}

			}
		}
		return new Matrice(prod);
	}

	/**
	 * Calcul de la transposée de this
	 * 
	 * @return transposée de this : tableau m x n
	 */
	public Matrice transpose() {
		Rational[][] trans = new Rational[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				trans[i][j] = coeff[j][i];

			}

		}

		return new Matrice(trans);
	}

	/**
	 * Échange les lignes i et j de la matrice
	 * 
	 * @param i première ligne à échanger
	 * @param j deuxième ligne à échanger
	 */
	private void swapRows(int i, int j) {
		/** Remplir ici le code manquant */
		var tmp = this.coeff[i];
		this.coeff[i] = this.coeff[j];
		this.coeff[j] = tmp;

	}

	/**
	 * Ajoute a fois la ligne i de this à sa ligne j
	 * 
	 * 
	 * @param i ligne à ajouter (multiplée par a)
	 * @param j ligne à laquelle on ajoute a fois la ligne j
	 * @param a scalaire par lequel on multiplie la ligne i quand on l'ajoute
	 */
	private void transvection(int i, int j, Rational a) {
		for (int k = 0; k < coeff[j].length; k++) {

			coeff[j][k] = coeff[j][k].plus(coeff[i][k].times(a));

		}

	}

	/**
	 * Mutiplie par a la ligne i de this
	 * 
	 * @param i ligne à multiplier par a
	 * @param a scalaire par lequel on multiplie la ligne i
	 */
	public void multiplyRow(int i, Rational a) {
		for (int k = 0; k < coeff[i].length; k++) {
			coeff[i][k] = coeff[i][k].times(a);

		}
	}

	/**
	 * Calcul de la matrice identité de mêmes dimensions que this (si les dimensions
	 * de this l'autorisent)
	 * 
	 * @return matrice identité : tableau n x n
	 */
	public Matrice identity() {
		if (m != n) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}
		Rational[][] id = new Rational[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				id[i][j] = (i == j) ? Rational.ONE : Rational.ZERO;

			}
		}

		return new Matrice(id);
	}

	/**
	 * Calcul d'une copie de this
	 * 
	 * @return copie de this : tableau n x m
	 */
	public Matrice clone() {
		Rational[][] clone = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				clone[i][j] = coeff[i][j];
			}
		}
		return new Matrice(clone);
	}

	private int max(Matrice m, int r) {
		var M = m.clone();
		var indice = 0;
		Rational max = M.coeff[0][r];

		for (int j = 0; j < M.m; j++) {
			if (M.coeff[j][r].isSup(max))

				indice = j;

		}
		return indice;

	}

	public Matrice addId() {
		Rational[][] clone = new Rational[n * 2][m];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {

				// Add '1' at the diagonal places of
				// the matrix to create a identity matirx
				if (j == (i + 2)) {
					clone[i][j] = Rational.ONE;

				}

			}
		}

		return new Matrice(clone);

	}

	public Matrice inverse() {
		// Rational[][] clone = new Rational[n][m];

		var clone = this.clone();
		var id = this.identity();
		var concat = new Matrice(clone,id);

		int r = -1;

		for (int j = 0; j < concat.n; j++) {
			var k = j;
		

			if (!(concat.coeff[k][j].equals(Rational.ZERO))) {

				r = r + 1;
				concat.multiplyRow(k, Rational.ONE.divide(concat.coeff[k][j]));
				if (k != r) {

					concat.swapRows(k, r);
				}
				for (int i = 0; i < concat.n; i++) {
					if (i != r) {
						concat.transvection(r, i, new Rational(-1).times(concat.coeff[i][j]));

					}

				}
			} else {
				throw new ArithmeticException("Non réversible");
			}

		}

		return concat.splitIdentity();

	}

	
	
	/**
	 * Résoud une équation linéaire matricielle
	 * 
	 * @param this est une matrice m x n
	 * @param b    vecteur m x 1 que l'on veut obtenir
	 * 
	 * @return vecteur colonne a tel que this * a = b : tableau n x 1
	 */
	public Matrice resoud(Matrice b) {
        if (m != b.n) {
            throw new IllegalArgumentException("Dimensions incorrectes");
        }
        
        Matrice result = this;
        
        try {
            result = result.inverse().times(b);
        } catch(ArithmeticException e) {
            throw new ArithmeticException("Pas de solution");
        }
        
        return result;
    }
	@Override
	public String toString() {
		return Arrays.deepToString(coeff);
	}

}
