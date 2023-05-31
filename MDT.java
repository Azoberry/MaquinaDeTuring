import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class MDT {

	private Set<Character> alfabeto = new LinkedHashSet<>();
	private int noEstados;
	private Set<Character> simbolosCinta = new LinkedHashSet<>(); //gamma
	private int[] estadosFinales;
	private Transicion[][] tabla;

	public MDT(File archivo) {
		validarYLlenarAlfabeto(archivo);
		validarYLlenarNoEstados(archivo);
		validarYLlenarSimbolosCinta(archivo);
	}

	public Set<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Set<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public int getNoEstados() {
		return noEstados;
	}

	public void setNoEstados(int noEstados) {
		this.noEstados = noEstados;
	}

	public Set<Character> getSimbolosCinta() {
		return simbolosCinta;
	}

	public void setSimbolosCinta(Set<Character> simbolosCinta) {
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
	}

	//Metodo para imprimir el alfabeto
	public void imprimirAlfabeto() {
		if (this.alfabeto.isEmpty()) {
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
		if (this.simbolosCinta.isEmpty()) {
			System.out.println("\nLos simbolos de la cinta estan vacios");
			return;
		}
		System.out.print("\nSimbolos de la cinta: ");
		for (Character c : this.simbolosCinta) {
			System.out.print(c + " ");
		}
	}

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
			Set<Character> auxAlfabeto = new LinkedHashSet<>();
			if ((linea1 = br.readLine()) != null) {
				String[] caracteres = linea1.replaceAll("\\s", "").split(",");

				for (String c : caracteres) {
					if (c.length() > 1) {
						System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila+1) + "\nLa cadena debe ser de un caracter");
						System.err.println("Cadena erronea: " + c);
						br.close();
						return;
					} else {
						char caracter = c.charAt(0);
						if (!auxAlfabeto.add(caracter)) {
							System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila+1) + "\nSe repite un caracter en el alfabeto");
							System.err.println("Caracter erroneo: " + caracter);
							br.close();
							return;
						}					
					}
					fila++;
				}
			}
			setAlfabeto(auxAlfabeto);
			br.close();
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
					System.err.println("ERROR en la linea: " + linea + "\nEl número de estados debe ser igual o mayor a 2");
					br.close();
					return;
					}
				}			
				br.close();
			} catch (NumberFormatException e) {
				System.err.println("ERROR en la linea: " + linea + "\nLa cantidad de estados deben de ser numéricos");
				br.close();
				return;
			}
			setNoEstados(estadosInt);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Metodo para leer la tercera linea, validar los simbolos de la cinta y si todo esta en orden, llenar los simbolos de la cinta.
	public void validarYLlenarSimbolosCinta(File archivo) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

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
			se implemento la función Hash la cual va almacenando cada caracter
			dentro de este, y cuando se repita un caracter este devolvera un valor
			booleano "false".
			• Para validar que la cadena si sea de un caracter, se va evaluando si
			la longitud de la cadena es mayor a 1.
			• Para validar que los simbolos de la cinta tengan el mismo orden y los mismos elementos que el alfabeto
			se convirtio el LinkedHashSet los simbolos de la cinta y del alfabeto a un array de caracteres
			respectivamente, donde estos se iran comparando para realizar la validacion.*/

			/*El LinkedHashSet sirve para mantener el orden de insercion de los elementos
			ya que al convertirse en array, asi nos aseguramos que se mantendra el orden. */
			Set<Character> auxSimbolosCinta = new LinkedHashSet<>();	
			
			if ((linea3 = br.readLine()) != null) {
				String[] caracteres = linea3.replaceAll("\\s", "").split(",");
				
				for (String c : caracteres) {
					if (c.length() > 1) {
						System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila1+1) + "\nLa cadena debe ser de un caracter");
						System.err.println("Cadena erronea: " + c);
						br.close();
						return;
					} else {
						char caracter = c.charAt(0);
						if (!auxSimbolosCinta.add(caracter)){
							System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila1+1) + "\nSe repite un caracter de los simbolos de la cinta");
							System.err.println("Caracter erroneo: " + caracter);
							br.close();
							return;
						}
					}
					fila1++;
				}
			}
				//Se convierten los LinkedHashSet a un array de caracteres para poder compararlos
				Character[] arrayAlfabeto = this.alfabeto.toArray(new Character[0]);
				Character[] arrayAuxSimbolosCinta = auxSimbolosCinta.toArray(new Character[0]);

				for (int i = 0; i < arrayAlfabeto.length; i++) {
					if (arrayAlfabeto[i] != arrayAuxSimbolosCinta[i]) {
						System.err.println("ERROR en la linea: " + (linea) + " en la columna: "+ (fila2+1) + "\nLos simbolos de la cinta no coinciden con el alfabeto");
						br.close();
						return;
					}
					fila2++;
				}
			setSimbolosCinta(auxSimbolosCinta);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}