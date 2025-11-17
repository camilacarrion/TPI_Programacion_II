package main;

import entities.CodigoBarras;
import entities.Producto;
import service.ProductoService;
import service.ProductoServiceImpl;

import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final ProductoService productoService = new ProductoServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {

        int opcion = 0;

        do {
            System.out.println(" SISTEMA DE GESTIÓN DE PRODUCTOS ");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Debe ingresar un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductos();
                case 3 -> buscarPorNombre();
                case 4 -> eliminarProducto();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }

        } while (opcion != 5);
    }

    private void crearProducto() {

        try {
            System.out.println("\n--- Crear Producto ---");

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }

            System.out.print("Precio: ");
            String precioStr = scanner.nextLine().trim();
            if (!precioStr.matches("\\d+(\\.\\d+)?")) {
                System.out.println("El precio debe ser un número válido.");
                return;
            }
            Double precio = Double.parseDouble(precioStr);

            System.out.print("Stock: ");
            String stockStr = scanner.nextLine().trim();
            if (!stockStr.matches("\\d+")) {
                System.out.println("El stock debe ser un número entero válido.");
                return;
            }
            Integer stock = Integer.parseInt(stockStr);

            System.out.print("Código de barras: ");
            String numeroCB = scanner.nextLine().trim();
            if (numeroCB.isEmpty()) {
                System.out.println("El código de barras no puede estar vacío.");
                return;
            }

            CodigoBarras cb = new CodigoBarras(null, numeroCB, false);
            Producto p = new Producto(null, nombre, precio, stock, cb, false);

            productoService.crearProducto(p);

            System.out.println("Producto creado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
    }

    private void listarProductos() {

        try {
            System.out.println("\nLista de Productos");

            List<Producto> productos = productoService.obtenerTodos();

            if (productos.isEmpty()) {
                System.out.println("No hay productos cargados.");
                return;
            }

            productos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    private void buscarPorNombre() {

        try {
            System.out.println("\nBuscar Producto ");
            System.out.print("Ingrese nombre o parte del nombre: ");

            String nombre = scanner.nextLine().trim();

            List<Producto> productos = productoService.buscarPorNombre(nombre);

            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos con ese nombre.");
                return;
            }

            productos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {

        try {
            System.out.println("\n--- Eliminar Producto (Baja Lógica) ---");
            System.out.print("Ingrese ID del producto a eliminar: ");

            int id = Integer.parseInt(scanner.nextLine().trim());

            productoService.eliminarProducto(id);

            System.out.println("Producto eliminado correctamente.");

        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero válido.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
