import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MDT {

	private char[] alfabeto;
	private int noEstados;
	private char[] simbolosCinta;
	private int[] estadosFinales;
	private Transicion[][] tabla;

	public MDT(File archivo) {
		validarYLlenarAlfabeto(archivo);
		validarYLlenarNoEstados(archivo);
		validarYLlenarSimbolosCinta(archivo);
		validarYLlenarEstadosFinales(archivo);
		validarTamanoTabla(archivo);
		llenarTabla(archivo);
	}

	public char[] getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(char[] alfabeto) {
		this.alfabeto = alfabeto;
	}

	public int getNoEstados() {
		return noEstados;
	}

	public void setNoEstados(int noEstados) {
		this.noEstados = noEstados;
	}

	public char[] getSimbolosCinta() {
		return simbolosCinta;
	}

	public void setSimbolosCinta(char[] simbolosCinta) {
		this.simbolosCinta = simbolosCinta;
	}

	public int[] getEstadosFinales() {
		return estadosFinales;
	}

	public void setEstadosFinales(int[] estadosFinales) {
		this.estadosFinales = estadosFinales;
	}

	public Transicion[][] getTabla() {
		return tabla;
	}

	public void setTabla(Transicion[][] tabla) {
		this.tabla = tabla;
	}

	public void imprimirMDT() {
		imprimirAlfabeto();
		imprimirNoEstados();
		imprimirSimbolosCinta();
		imprimirEstadosFinales();
		imprimirTabla();
	}

	//Metodo para imprimir el alfabeto
	public void imprimirAlfabeto() {
		if (this.alfabeto == null) {
			System.out.println("\nEl alfabeto esta vacio");
			return;
		}
		System.out.print("\nAlfabeto: ");
		for (Character c : this.alfabeto) {
			System.out.print(c + " ");
		}
	}

	//Metodo para imprimir el numero de estados
	public void imprimirNoEstados() {
		if (this.noEstados == 0) {
			System.out.println("\nEl numero de estados esta vacio");
			return;
		}
		System.out.print("\nNumero de estados: " + this.noEstados);
	}

	//Metodo para imprimir los simbolos de la cinta
	public void imprimirSimbolosCinta() {
		if (this.simbolosCinta == null) {
			System.out.println("\nLos simbolos de la cinta estan vacios");
			return;
		}
		System.out.print("\nSimbolos de la cinta: ");
		for (Character c : this.simbolosCinta) {
			System.out.print(c + " ");
		}
	}

	//Metodo para imprimir los estados de aceptacion
	public void imprimirEstadosFinales() {
		if (this.estadosFinales == null) {
			System.out.println("\nLos estados de aceptacion estan vacios");
			return;
		}
		System.out.print("\nEstados de aceptacion: ");
		for (int i = 0; i < this.estadosFinales.length; i++) {
			System.out.print(this.estadosFinales[i] + " ");
		}
	}

	//Metodo para imprimir la tabla
	public void imprimirTabla() {
		if (this.tabla == null) {
			System.out.println("\nLa tabla esta vacia");
			return;
		}
		System.out.println("\nTabla: ");
		for (int i = 0; i < this.tabla.length; i++) {
			for (int j = 0; j < this.tabla[i].length; j++) {
				System.out.print(this.tabla[i][j].toString() + " ");
			}
			System.out.println();
		}
	}

	private Set<Character> auxAlfabeto = new LinkedHashSet<>();
	//Metodo para leer la primera linea, validar el alfabeto y si todo esta en orden, llenar el alfabeto.
	public void validarYLlenarAlfabeto(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			// LECTURA DE LA PRIMERA LINEA DEL TXT
			String linea1;
			int fila = 0;
			int linea = 1;
			/*Se lee la primera linea del .txt y separa los carácteres por comas.
			• Para validar el hecho de que no se pueden repetir caracteres en el alfabeto
			se implemento la función Hash la cual va almacenando cada caracter
			dentro de este, y cuando se repita un caracter este devolvera un valor
			booleano "false".
			• Para validar que la cadena si sea de un caracter, se va evaluando si
			la longitud de la cadena es mayor a 1. */
			String[] caracteres;
			char[] caracteresFinal;

			if ((linea1 = br.readLine()) != null) {
				caracteres = linea1.replaceAll("\\s", "").split(",");

				for (String c : caracteres) {
					if (c.length() > 1) {
						System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila+1) + "\nLa cadena debe ser de un solo caracter");
						System.err.println("Cadena erronea: " + c);
						auxAlfabeto.clear();
						br.close();
						return;
					} else {
						char caracter = c.charAt(0);
						if (!auxAlfabeto.add(caracter)) {
							System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila+1) + "\nSe repite un caracter en el alfabeto");
							System.err.println("Caracter erroneo: " + caracter);
							auxAlfabeto.clear();
							br.close();
							return;
						}					
					}
					fila++;
				}
				caracteresFinal = new char[caracteres.length];
				for(fila = 0; fila < caracteres.length; fila++) {
					caracteresFinal[fila] = caracteres[fila].charAt(0);
				}
				setAlfabeto(caracteresFinal);
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Metodo para leer la segunda linea, validar el numero de estados y si todo esta en orden, llenar el numero de estados.
	public void validarYLlenarNoEstados(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			//Saltamos la primera linea del .txt
			br.readLine();
			// LECTURA DE LA SEGUNDA LINEA DEL TXT
			String linea2;
			int linea = 2;
			/*Se lee la segunda linea del .txt y se convierte a un entero.
			• Para validar que la cadena si sea un numero, se implemento un try-catch.
			• Para validar que la cantidad de estados sea mayor a 2, se hizo una condicion.*/
			int estadosInt = 0;
			try {
				if ((linea2 = br.readLine()) != null) {
					estadosInt = Integer.parseInt(linea2.trim());
					
					/* Validación donde verifica si la cantidad de estados es igual o mayor a 2, ya
					que no es valido tener un solo estado.*/
					if (estadosInt < 2) {
					System.err.println("ERROR en la linea: " + linea + "\nLa cantidad de estados debe ser igual o mayor a 2");
					br.close();
					return;
					}
				}			
				br.close();
			} catch (NumberFormatException e) {
				System.err.println("ERROR en la linea: " + linea + "\nLa cantidad de estados debe de ser un solo numero");
				br.close();
				return;
			}
			setNoEstados(estadosInt);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Set<Character> auxSimbolosCinta = new LinkedHashSet<>();
	//Metodo para leer la tercera linea, validar los simbolos de la cinta y si todo esta en orden, llenar los simbolos de la cinta.
	public void validarYLlenarSimbolosCinta(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

			//Valida si el alfabeto esta vacio o no
			if (this.alfabeto == null) {
				System.err.println("ERROR: El alfabeto esta vacio, no es posible ingresar los simbolos de la cinta");
					br.close();
					return;
			}

			//Saltamos las dos primeras lineas del .txt
			int skipLine = 2;
			for (int i = 0; i < skipLine; i++) {
				br.readLine();
			}

			// LECTURA DE LA TERCERA LINEA DEL TXT
			String linea3;
			int fila1 = 0;
			int fila2 = 0;
			int linea = 3;

			/*Se lee la tercera linea del .txt y se separa por comas.
			• Para validar el hecho de que no se pueden repetir caracteres en los simbolos de la cinta
			se implemento la función LinkedHashSet la cual va almacenando cada caracter
			dentro de este, y cuando se repita un caracter este devolvera un valor
			booleano "false".
			• Para validar que la cadena si sea de un caracter, se va evaluando si
			la longitud de la cadena es mayor a 1.
			• Para validar que los simbolos de la cinta tengan el mismo orden y los mismos elementos que el alfabeto
			se convirtio el LinkedHashSet los simbolos de la cinta y del alfabeto a un array de caracteres
			respectivamente, donde estos se iran comparando para realizar la validacion.*/	
			
			if ((linea3 = br.readLine()) != null) {
				String[] caracteres = linea3.replaceAll("\\s", "").split(",");
				
				for (String c : caracteres) {
					if (c.length() > 1) {
						System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila1+1) + "\nLa cadena debe ser de un caracter");
						System.err.println("Cadena erronea: " + c);
						br.close();
						auxAlfabeto.clear();
						auxSimbolosCinta.clear();
						return;
					} else {
						char caracter = c.charAt(0);
						if (!auxSimbolosCinta.add(caracter)){
							System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila1+1) + "\nSe repite un caracter de los simbolos de la cinta");
							System.err.println("Caracter erroneo: " + caracter);
							auxAlfabeto.clear();
							auxSimbolosCinta.clear();
							br.close();
							return;
						}
					}
					fila1++;
				}
			}

			//Se convierten los LinkedHashSet a un array de caracteres para poder compararlos
			Character[] arrayAlfabeto = auxAlfabeto.toArray(new Character[0]);
			Character[] arrayAuxSimbolosCinta = auxSimbolosCinta.toArray(new Character[0]);

			for (int i = 0; i < arrayAlfabeto.length; i++) {
				if (arrayAlfabeto[i] != arrayAuxSimbolosCinta[i]) {
					System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila2+1) + "\nLos simbolos de la cinta no coinciden con el alfabeto");
					auxAlfabeto.clear();
					auxSimbolosCinta.clear();
					br.close();
					return;
				}
				fila2++;
			}

			char[] caracteresFinal = new char[arrayAuxSimbolosCinta.length];

			//Se convierte el array de Character a un array de char
			for (fila1 = 0; fila1 < arrayAuxSimbolosCinta.length; fila1++) {
				caracteresFinal[fila1] = arrayAuxSimbolosCinta[fila1];
			}
			setSimbolosCinta(caracteresFinal);

			auxAlfabeto.clear();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Metodo para leer la cuarta linea, validar los estados de aceptacion y si todo esta en orden, llenar los estados de aceptacion.
	public void validarYLlenarEstadosFinales(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			//Saltamos las tres primeras lineas del .txt
			int skipLine = 3;
			for (int i = 0; i < skipLine; i++) {
				br.readLine();
			}

			// LECTURA DE LA CUARTA LINEA DEL TXT
			String linea4;
			int linea = 4;
			int fila = 0;
			/*Se lee la segunda linea del .txt y se convierte a un entero.
			• Para validar que la cadena si sea un numero, se implemento un try-catch.
			• Para validar que el estado de aceptacion sea mayor a 2, se hizo una condicion.
			• Para validar que el estado de aceptacion sea mayor a 0, se hizo una condicion
			• Para validar que los estados de aceptacion no se repitan, se uso un HashSet*/
			try {
				Set<Integer> auxEstadosAceptacion = new HashSet<>();
				if ((linea4 = br.readLine()) != null) {
					String[] cadena = linea4.replaceAll("\\s", "").split(",");			
					
					for (String c : cadena) {
						int estadoFinalInt = Integer.parseInt(c);

						if(estadoFinalInt >= this.noEstados) {
							System.err.println("ERROR en la linea: " + linea + " en la columna: " + (fila+1) + "\nEl estado de aceptacion no puede ser mayor o igual al número de estados");
							br.close();
							return;
						} else if (estadoFinalInt <= 0) {
							System.err.println("ERROR en la linea: " + linea + " en la columna: " + (fila+1) + "\nEl estado de aceptacion debe ser mayor a 0");
							br.close();
							return;
						} else if(!auxEstadosAceptacion.add(estadoFinalInt)){
							System.err.println("ERROR en la linea: " + linea + " en la columna: " + (fila+1) + "\nSe repite un estado de aceptacion");
							System.err.println("Estado de aceptacion erroneo: " + estadoFinalInt);
							br.close();
							return;
						}
						fila++;
					}
					auxEstadosAceptacion.clear();

					int[] arrayEstadosFinales = new int[cadena.length];
					for (fila = 0; fila < cadena.length; fila++) {
						arrayEstadosFinales[fila] = Integer.parseInt(cadena[fila]);
					}
					setEstadosFinales(arrayEstadosFinales);	
					br.close();
				}
			} catch (NumberFormatException e) {
				System.err.println("ERROR en la linea: " + linea + "\nLos estados de aceptacion debe de ser numericos");
				br.close();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Bandera para ver si ya fue evaluada la tabla o no
	boolean tablaValida = false;

	//Metodo para leer de la quinta linea en adelante para validarlo.
	public void validarTamanoTabla(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			//Saltamos las cuatro primeras lineas del .txt
			int skipLine = 4;
			for (int i = 0; i < skipLine; i++) {
				br.readLine();
			}

			// LECTURA DE LA QUINTA LINEA DEL TXT EN ADELANTE
			String linea5;
			int linea = 0;

			while ((linea5 = br.readLine()) != null) {
				String[] transicion = linea5.replaceAll("\\s", "").split("`");
				int longitudColumnas = transicion.length;

				if (longitudColumnas != this.simbolosCinta.length) 
				{
					System.err.println("ERROR en la linea: " + (linea+1) + " de la tabla\nEl numero de columnas no coincide con el numero de simbolos de de la cinta");
					br.close();
					return;
				}
				linea++;
			}

			if(linea != this.noEstados) {
				System.err.println("ERROR: El numero de lineas no coincide con el numero de estados\nHay "+ linea + " lineas y deberia de haber " + this.noEstados + " lineas");
				br.close();
				return;
			}
			tablaValida = true;
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Metodo para leer de la quinta linea en adelante y llenar la tabla.
	public void llenarTabla (File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			
			//Valida si el alfabeto esta vacio o no
			if (tablaValida == false) {
				System.err.println("ERROR: El tamano de la tabla no es correcto, favor de corregirla");
					br.close();
					return;
			}

			//Saltamos las cuatro primeras lineas del .txt
			int skipLine = 4;
			for (int i = 0; i < skipLine; i++) {
				br.readLine();
			}
			
			// LECTURA DE LA QUINTA LINEA DEL TXT EN ADELANTE
			String linea5;
			int linea = 0;
			int i = 0;
			Transicion[][] matriz = new Transicion[this.noEstados][this.simbolosCinta.length];

			try {
				while ((linea5 = br.readLine()) != null) {
					String [] transicion = linea5.replaceAll("\\s", "").split("`");
	
					for(i = 0; i < transicion.length; i++) {
						//Verifica que el elemento este entre corchetes
						if (!transicion[i].startsWith("{") || !transicion[i].endsWith("}")) {
							System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i + 1) + "\nLa estructura del elemento es incorrecta\nDebe de ser asi: {estado, caracter, movimiento}");
							br.close();
							return;
						}
						//Elimina los corchetes de la cadena
						String elemento = transicion[i].substring(1, transicion[i].length() - 1);
						//Separa los valores por comas
						String[] valores = elemento.split(",");

						//Verifica que el numero de elementos sea 3
						if (valores.length != 3) {
							System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl numero de elementos es incorrecto\nDebe de ser asi: {estado, caracter, movimiento}");
							br.close();
							return;
						}
						//Convierte el primer valor a entero
						int estado = Integer.parseInt(valores[0]);

						//Verifica que la cadena sea de un solo caracter
						if (valores[1].length() > 1) {
							System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (i+1) + "\nLa cadena debe ser de un solo caracter\nCadena erronea: "+valores[1]);
							br.close();
							return;
						}
						//Convierte el caracter del segundo valor a char
						char escribir = valores[1].charAt(0);

						// Convierte el tercer valor a un elemento del enum de Movimiento
						Transicion.Movimiento movimiento = Transicion.Movimiento.valueOf(valores[2]);
	
						if (estado >= this.noEstados) {
							System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl estado no puede ser mayor o igual al numero de estados");
							br.close();
							return;
						} else if (estado < 0) {
							System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl estado no puede ser negativo");
							br.close();
							return;
						} else if (!auxSimbolosCinta.contains(escribir)) {
							System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl caracter: " + escribir + " no se encuentra en los simbolos de la cinta");
							br.close();
							auxSimbolosCinta.clear();
							return;
						} else {
							matriz[linea][i] = new Transicion(estado, escribir, movimiento);
						}
					}
					linea++;
				}
			} catch(NumberFormatException e) {
				System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl estado debe ser numerico");
				br.close();
				return;
			} catch (IndexOutOfBoundsException e) {
				System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl caracter a escribir esta fuera de rango");
				return;
			} catch(IllegalArgumentException e) {
				System.err.println("ERROR en la linea: " + (linea+1) + " en la columna: " + (i+1) + "\nEl movimiento estado no es valido");
				br.close();
				return;
			}
			auxSimbolosCinta.clear();
			br.close();
			setTabla(matriz);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}