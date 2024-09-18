/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import Logica_Operaciones_BD.OperacionesCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int opcionM = 0;
        do {
            opcionM = menuOpciones();
            switch (opcionM) {
                case 1:
                    // Completar la lógica para mostrar a consola la cantidad de facturas registradas
                    try {
                        int cantFacturas = OperacionesCRUD.getInstance().obtenerCantFacturasGeneradas();
                        System.out.println("Cantidad de facturas registradas: " + cantFacturas);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Completar la lógica para mostrar a consola la lista de nombres de productos en stock
                    try {
                        List<String> nombresProductos = OperacionesCRUD.getInstance().obtenerListadoNombresProductosStock();
                        System.out.println("Listado de nombres de productos en stock:");
                        for (String nombre : nombresProductos) {
                            System.out.println(nombre);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.exit(0);
            }
            
            System.out.println("Presione Enter para continuar...");
            System.in.read();
        } while (true);
    }
    
    public static int menuOpciones() {
        int opcionM = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Mostrar cantidad de facturas registradas");
            System.out.println("2. Mostrar lista de nombres de productos en stock");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcionM = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        
        return opcionM;
    }
}