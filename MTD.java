import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MDT {

	private String[] alfabeto;
	private String[] alfabetoComp;
 	private int noEst;
	private int[] estFin;
	private Transicion[][] tabla;
	//private String nom = "";
	//private String dir;
	//JIJIJIJA

	
	Set<String> setAlfabeto = new HashSet<>();
	int estados = 0;

	//public MDT(String[] alfabeto, int noEst, int[] estFin, int[][] tabla, String dir) {
	public MDT(String[] alfabeto, int noEst, String[] alfabetoComp,int[] estFin, Transicion[][] tabla) {
		super();
		this.alfabeto = alfabeto;
		this.noEst = noEst;
		this.alfabetoComp=alfabetoComp;
		this.estFin = estFin;
		this.tabla = tabla;
		//this.dir = dir;
	}

	public MDT() {

	}

	public MDT(String txt) {
		llenarAFD(txt);
	}

	public String[] getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(String[] alfabeto) {
		this.alfabeto = alfabeto;
	}

	public String[] getAlfabetoComp() {
		return alfabetoComp;
	}

	public void setAlfabetoComp(String[] alfabetoComp) {
		this.alfabetoComp = alfabetoComp;
	}

	public int getNoEst() {
		return noEst;
	}

	public void setNoEst(int noEst) {
		this.noEst = noEst;
	}

	public int[] getEstFin() {
		return estFin;
	}

	public void setEstFin(int[] estFin) {
		this.estFin = estFin;
	}

	public Transicion[][] getTabla() {
		return tabla;
	}

	public void setTabla(Transicion[][] tabla) {
		this.tabla = tabla;
	}

	// public String getNom() {
	// 	return nom;
	// }

	// public void setNom(String nom) {
	// 	this.nom = nom;
	// }

	// public String getDir() {
	// 	return dir;
	// }

	// public void setDir(String dir) {
	// 	this.dir = dir;
	// }

	public String concatAlfa() {
		String concat = "";
		int aux;

		for (aux = 0; aux < this.alfabeto.length - 1; aux++) {
			concat += this.alfabeto[aux] + ",";
		}
		concat += this.alfabeto[aux];
		return concat;
	}

	public void imprime() {
		// Todo este cochinero es para imprimir
		// Aux se usa para las iteraciones en los ciclos for, para que se puedan usar
		// afuera del ciclo
		int aux;
		// System.out.println("AFD: " + getNom());
		// System.out.println("Direccion: " + dir);
		System.out.print("alfabeto:");
		// Imprime todos los elementos del alfabeto menos el ultimo, para que no se
		// muestre la coma al final
		for (aux = 0; aux < alfabeto.length - 1; aux++) {
			System.out.print(alfabeto[aux] + ",");
		}
		System.out.print(alfabeto[aux]);
		System.out.println("alfabeto Completo:");
		for (aux = 0; aux < alfabetoComp.length - 1; aux++) {
			System.out.print(alfabetoComp[aux] + ",");
		}
		// Imprime el ultimo elemento del alfabeto
		System.out.print(alfabetoComp[aux]);
		System.out.print("\n" + "noEst:" + noEst + "\n" + "estFin:");
		int[] auxFin = getEstFin();
		// Imprime todos los elementos de los estados finales menos el ultimo, para que
		// no se muestre la coma al final
		for (aux = 0; aux < auxFin.length - 1; aux++) {
			System.out.print(auxFin[aux] + ",");

		}
		// Imprime el ultimo elemento de los estados finales
		System.out.print(auxFin[aux] + "\n");

		System.out.println("Tabla de transicion: ");
		Transicion[][] mat = getTabla();
		// Recorre la matriz para imprimirla
		for (int i = 0; i < estados; i++) {
			for (int k = 0; k < alfabeto.length; k++) {
				System.out.print(mat[i][k] + " ");
			}
			System.out.println();
		}
	}

	public void llenarAFD(String txt) {
		try (BufferedReader br = new BufferedReader(new FileReader(txt))) {

			// File archivo = new File(txt);
			// String nombreArchivo = archivo.getName();
			// String regex = "^\\d+";
			// nombreArchivo = nombreArchivo.replaceFirst(regex, "");
			// int pos = nombreArchivo.lastIndexOf(".");
			// if (pos > 0) {
			// 	nombreArchivo = nombreArchivo.substring(0, pos);
			// }

			// setNom(nombreArchivo);
			// setDir(txt);

			// LECTURA DE LA PRIMERA LINEA DEL TXT
			String line;
			// Lee explicitamente la primera linea del .txt y separa los carácteres por
			// comas " , "
			// Para validar el hecho de que no se pueden repetir caracteres en el alfabeto
			// se implemento
			// la función Hash la cual va almacenando cada caracter dentro de este, y cuando
			// detecte un caracter repetido
			// devolverá un valor booleana falso y parará el código.
			if ((line = br.readLine()) != null) {
				this.alfabeto = line.split("`");
				Set<String> setAlfabeto = new HashSet<String>();
				for (String c : this.alfabeto) {
					if (!setAlfabeto.add(c)) {
						System.err.println("Error: el alfabeto contiene caracteres repetidos");
						System.out.println("Carácter repetido: " + c);
						return;
					}
				}
			}
			setAlfabeto(this.alfabeto);

			// LECTURA DE LA SEGUNDA LINEA DEL TXT
			String line2 = br.readLine();
			try {
				if (line2 != null) {
					// convertir la línea a un entero
					estados = Integer.parseInt(line2.trim());

					// Validación donde verifica si la cantidad de estados es igual o mayor a 2, ya
					// que no
					// es valido tener un solo estado.
					if (estados < 2) {
						System.err.println("El número de estados debe ser mayor o igual a 2");
						return;
					}
					// else {
					// Imprime el num de estados
					// System.out.println(estados);
					// }
				}
			} catch (NumberFormatException e) {
				System.err.println("ERROR: La cantidad de estados deben ser numéricos");
				return;
			}
			setNoEst(estados);

			// LECTURA DE LA TERCERA LINEA DEL TXT
			String line3 = br.readLine();

			if (line3 != null) {
				String[] aceptacion = line3.split(",");
				// crea un arreglo de enteros del mismo tamaño que el arreglo de cadenas
				// "aceptacion"
				int[] arregloNums = new int[aceptacion.length];

				try {
					for (int i = 0; i < arregloNums.length; i++) {
						// Convierte los strings de aceptacion a entero
						arregloNums[i] = Integer.parseInt(aceptacion[i]);
						if (arregloNums[i] > estados) {
							System.err.println("ERROR: El estado de aceptación es mayor que la cantidad de estados");
							return;
						} else if (arregloNums[i] <= 0) {
							System.err.println("ERROR: Los estados de aceptación deben ser mayor o igual a 0");
							return;
						}
						// else {
						// Imprime los estados de aceptacion
						// System.out.println(arregloNums[i]);
						// }
					}
				} catch (NumberFormatException e) {
					System.err.println("ERROR: Los estados de aceptación deben ser numéricos");
					return;
				}
				setEstFin(arregloNums);

				// LECTURA DE LA CUARTA LINEA EN ADELANTE)

				int fila = 0;
				int j2 = 0;

				while ((line3 = br.readLine()) != null) {
					String[] transicion = line3.split(" ");
					for (int j = 0; j < transicion.length; j++) {
						j2 = transicion.length;
						if (j2 != this.alfabeto.length) {
							System.err.println(
									"Los valores de las columnas son diferentes, en el renglón: " + (fila + 1));
							return;
						}
					}
					fila++;
				}

				if (estados != fila) {
					System.err.println("Son una cantidad diferente \n" + "Deben ser: " + estados + " renglones\ny hay: "
							+ fila + " renglones");
					return;
				} else {
					// Se inicializa otro BufferedReader debido a que necesitamos leer el
					// archivo desde el inicio
					BufferedReader reader = new BufferedReader(new FileReader(txt));
					// Se establece que la matriz será del tamaño de los estados y de la longitud
					// del alfabeto
					int[][] matriz = new int[estados][alfabeto.length];

					// Omite las líneas que no nos interesan, en esta ocasión
					// omitimos hasta la cuarta linea
					int skipLine = 3;
					for (int i = 0; i < skipLine - 1; i++) {
						reader.readLine();
					}

					int renglon = 0;
					String line4 = reader.readLine();
					int[] transicionInt = new int[alfabeto.length];

					// El txt se va recorriendo linea por linea a partir de la cuarta linea
					try {
						while ((line4 = reader.readLine()) != null) {
							// Los datos leidos de la linea son separados por espacios y almacenados en el
							// arreglo "transicion"
							String[] transicion = line4.split(" ");
							// Recorre transición con la longitud del arreglo "Transicion"
							for (int k = 0; k < transicion.length; k++) {
								// Convierte el arreglo de cadenas en arreglo de enteros
								transicionInt[k] = Integer.parseInt(transicion[k]);
								// Compara el elemento en la posicion 'k' con el numero de estados, sí es mayor
								// o igual al numero de estados
								// manda mensaje de error
								if (transicionInt[k] >= estados) {
									System.err.println(
											"ERROR: Revisa la tabla de transición, hay una transición mayor a la cantidad de estados, en el renglón: "
													+ (renglon + 1));
									return;
								} else if (transicionInt[k] < 0) {
									System.err.println(
											"ERROR: Revisa la tabla de transición, hay una transición negativa, en el renglón: "
													+ (renglon + 1));
									return;
								} else {
									matriz[renglon][k] = transicionInt[k];
								}

							}
							renglon++;
						}
					} catch (NumberFormatException e) {
						System.err.println("ERROR: Los estados de transición deben ser numéricos");
						return;
					}
					setTabla(matriz);

					// imprimir la matriz
					// for (int i = 0; i < estados; i++) {
					// for (int k = 0; k < alfabeto.length; k++) {
					// System.out.print(matriz[i][k] + " ");
					// }
					// System.out.println();
					// }
					reader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean Validar(String cadenaTex) {
		// Retorna verdadero si se cumple la cadena con el patron del AFD, si no cumple
		// Falso
		boolean resultado = true;
		// Almacena el número del estado en el que se encuentra mientras se recorre la
		// matríz
		int estadoActual = 0;
		// Se utiliza para contar la cantidad de veces que algún caracter de la cadena
		// se encuentra en el alfabeto
		int auxContador = 0;
		// Es la matriz (tabla de transiciones)
		Transicion[][] mat = getTabla();

		// Para facilitar el uso de nuestro alfabeto se convierte de String a Char
		// aqui creamos el arreglo alfabetoChar
		char[] alfabetoChar = new char[alfabeto.length];
		// Convierte nuestro String a Char almacenandolo en un arreglo
		for (int i = 0; i < alfabeto.length; i++) {
			alfabetoChar[i] = alfabeto[i].charAt(0);
		}

		// Se recorre la cadena
		for (int i = 0; i < cadenaTex.length(); i++) {
			// Se recorre el alfabeto
			for (int j = 0; j < alfabetoChar.length; j++) {
				// Compara que el caracter de la cadena en la iteracion "i"
				// y coincida con el caracter del alfabeto en la iteracion "j"
				if (cadenaTex.charAt(i) == alfabetoChar[j]) {
					// Incrementa auxContador porque tanto el caracter de alfabeto
					// como el caracter de la cadena son iguales
					auxContador++;
					break;
				}
			}
		}
		// Verifica que el valor de auxContador que sea diferente
		// de las veces que se encontró el caracter del alfabeto en la cadena,
		// para continuar con la validacion es necesario que sean iguales
		if (auxContador != cadenaTex.length()) {
			resultado = false;
		} else {
			// Se encarga de recorrer la cadena verticalmente (filas)
			for (int i = 0; i < cadenaTex.length(); i++) {
				// Se encarga de recorrer la cadena horizontalmente (columnas)
				for (int j = 0; j < alfabetoChar.length; j++) {
					// Almacena el estado actual de la posición establecida por las iteraciones
					if (cadenaTex.charAt(i) == alfabetoChar[j]) {
						estadoActual = mat[estadoActual][j];
						break;
					}
				}
			}
			// Se encarga de repetir la validación según el número de
			// estados finales
			for (int i = 0; i < getEstFin().length; i++) {
				// valida que el estado actual de cuando se está recorriendo la matriz
				// no sea el estado final, si son iguales regresa verdadero, de lo contrario
				// falso
				if (estadoActual != getEstFin()[i]) {
					resultado = false;
				} else {
					resultado = true;
					break;
				}
			}
		}
		return resultado;
	}
}