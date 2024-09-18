/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Operaciones_BD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public final class OperacionesCRUD 
{
    private static OperacionesCRUD lainstance = new OperacionesCRUD();
    private Connection conexion;

    private OperacionesCRUD() 
    {         
    }
    
    public static OperacionesCRUD getInstance()
    {
        return lainstance;
    }
    
    private void iniciarConexionBD()
    {
        this.conexion = ConexionBD.iniciarConexion();
    }
    
    private void cerrarConexionBD() throws SQLException 
    {
        if (this.conexion != null && !this.conexion.isClosed()) //valida si aun está abierta la conexion BD
        {
            this.conexion.close();
        }
    }    
    
    // Inciso a
    public int obtenerCantFacturasGeneradas() throws SQLException
    {
        // 1. Conectar a la base de datos
        this.iniciarConexionBD(); 
        
        // 2. Variable para almacenar el total de facturas almacenadas en la base de datos.
        int cantFacturas = 0; 
        
        // 3. Definir espacio de trabajo para la declaración y ejecución de la consulta SQL
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 4. Definir el texto String de la consulta SQL.
            String query = "select count(*) as cant_facturas from facturas f";
            
            // 5. Ejecutar la consulta y almacenar en el objeto ResultSet
            stmt = this.conexion.createStatement();
            rs = stmt.executeQuery(query);
            
            // 6. Recorrer el objeto ResultSet mediante un while y para cada iteración resolver:
            while (rs.next()) {
                // 6.1 En la única iteración, obtener y almacenar el dato de la cantidad de facturas que arrojó la consulta SQL            
                cantFacturas = rs.getInt("cant_facturas");
            }
        } finally {
            // 7. Cerrar ResultSet y Statement
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        // 8. Cerrar la conexion a la base de datos    
        this.cerrarConexionBD();
        
        // 9. Retornar el total de facturas que está almacenado en la base de datos
        return cantFacturas;
    }
    
    // Inciso b
    public List<String> obtenerListadoNombresProductosStock() throws SQLException
    {
        // 1. Conectar a la base de datos
        this.iniciarConexionBD();
        
        // 2. Crear el vector para almacenar la lista de nombres de productos en stock resultante de la consulta SQL a la base de datos.
        List<String> listadoNombresProductosStock = new ArrayList<>();  
        
        // 3. Definir espacio de trabajo para la declaración y ejecución de la consulta SQL
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 4. Definir el texto String de la consulta SQL.
            String query = "select p.descripcion as nombres_productos from stock_productos p";
            
            // 5. Ejecutar la consulta y almacenar en el objeto ResultSet
            stmt = this.conexion.createStatement();
            rs = stmt.executeQuery(query);
            
            // 6. Recorrer el objeto ResultSet mediante un while y para cada iteración resolver:
            while (rs.next()) {
                // 6.1 Obtener el nombre del producto de la fila actual del objeto ResultSet
                String nombreProducto = rs.getString("nombres_productos");
                
                // 6.2 Almacenar en el vector creado en el paso 2. el valor de nombre de producto de la fila actual del objeto ResultSet
                listadoNombresProductosStock.add(nombreProducto);
            }
        } finally {
            // 7. Cerrar ResultSet y Statement
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        // 8. Cerrar la conexion a la base de datos       
        this.cerrarConexionBD();
        
        // 9. Retornar el objeto vector con la lista de nombres de productos almacenados en la base de datos
        return listadoNombresProductosStock;
    }  
}
