import java.util.Scanner;

public class CurrencyConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.print("""
                ************************************************
                Bienvenido/a al Conversor de Divisas V2
                Seleccione una opción:
                  1) USD a EUR
                  2) EUR a USD
                  3) USD a GBP
                  4) GBP a USD
                  5) USD a JPY
                  6) JPY a USD
                  7) Salir
                ************************************************
            """);
            System.out.print("Ingrese su elección: ");
            choice = scanner.nextInt();
            
            if (choice == 7) {
                System.out.println("Gracias por usar el Conversor de Divisas. ¡Adiós!");
            } else if (choice < 1 || choice > 7) {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            } else {
                System.out.print("Ingrese el monto a convertir: ");
                double amount = scanner.nextDouble();
                
                try {
                    CurrencyConverter.performConversion((int) choice, amount);
                } catch (Exception e) {
                    System.out.println("Ocurrió un error durante la conversión: " + e.getMessage());
                }
            }
        } while (choice != 7);
    }
}
