import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraDonaGabrielinha {

    public double[] calcularValorTotal(int quantidadePlantas, double valorUnitario){
        double valorBruto = quantidadePlantas * valorUnitario;
        double valorDesconto = 0.0;

        if (quantidadePlantas > 10) {
            valorDesconto = valorBruto * 0.05;
            valorBruto -= valorDesconto;
            System.out.printf ("Desconto de 5%% aplicado! Valor do desconto: R$ %.2f%n", valorDesconto);
        }
        return new double[]{valorBruto, valorDesconto};
    }

    public void registrarVenda (int quantidade, double valorTotal, double descontoAplicado){
        try (FileWriter fileWriter = new FileWriter("vendas.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter)){
                
            printWriter.printf("Quantidade: %d | Valor Total: R$ %.2f | Desconto: R$ %.2f%n", quantidade, valorTotal, descontoAplicado);
            System.out.println("Venda registrada com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao registrar a venda: " + e.getMessage());
            }
    }

    public static void main (string[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraDonaGabrielinha calculadora = new CalculadoraDonaGabrielinha();

        System.out.println("--- Calculadora de Vendas Dona Gabrielinha ---");

        while (true) {
            try {
                System.out.print("Digite a quantidade de plantas: ");
                int quantidade = scanner.nextInt();

                System.out.print("Digite o valor unitário da planta: ");
                double valor = scanner.nextDouble();

                if (quantidade < 0 || valor < 0){
                    System.out.println("A quantidade e o valor unitário devem ser números positivos. Tente novamente.");
                    continue;
                }
            double[] resultado = calculadora.calcularValorTotal(quantidade, valor);
            double valorFinal = resultado[0];
            double desconto = resultado[1];

            System.out.printf("\nValor total a pagar: R$ %.2f%n", valorFinal);

            calculadora.registrarVenda(quantidade, valorFinal, desconto);

            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, digite um número válido");
                scanner.next(); 
                continue;
            }

            System.out.print("\nDeseja registrar outra venda? (S/N): ");
            String continuar = scanner.next();

            if (!continuar.equalsIgnoreCase("S")) {
                System.out.println("Obrigado por usar a calculadora! Volte sempre.");
                break;
            }
        }
        scanner.close();
    }

}

