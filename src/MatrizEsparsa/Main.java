package MatrizEsparsa;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);
    private static MatrizEsparsaController control = new MatrizEsparsaController();
    private static String file;
    private static String regex = "[*?:<>*].*";

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {
        int option = 1;
        do {
            try {

                System.out.println("<================================ MENU ================================>");
                System.out.println("<============================== Trabalho1 =============================>");
                System.out.println("1. Ler Arquivo .PGM e Gerar MatrizEsparsa (ARQUIVO PADRÃO DO TRABALHO)");
                System.out.println("2. Ler Arquivo .PGM e Gerar MatrizEsparsa (NOVO ARQUIVO)");
                System.out.println("0. Sair");
                System.out.println("<============================== ========= =============================>");
                System.out.print(": ");
                option = in.nextInt();

                System.out.print("\n");

                switch (option) {
                    case 0:
                        break;
                    case 1:
                        Main.file = "Trabalho1.pgm";
                        readFile();
                        break;
                    case 2:
                        try {

                            System.out.println("<============= Ler Arquivo .PGM P2 =============>");
                            System.out.println("Digite Abaixo o Endereço do Arquivo (Ex. C:\\Users\\Filipe\\CEFET\\AED1\\Trabalho1\\Trabalho1.pgm)");
                            System.out.print("\n");
                            System.out.print(": ");
                            Main.file = in.next();

                            if (Main.file.matches(regex)) {
                                throw new IllegalArgumentException();
                            }

                            readFile();

                        } catch (InputMismatchException | IllegalArgumentException e) {

                            System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                            System.out.print("Vamos tentar novamente.\n\n");

                        }
                        break;
                    default:
                        menu();
                        break;
                }

            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);
        System.exit(0);
    }

    private static void submenu() throws IOException {
        int option = 1;
        do {
            try {
                System.out.println("<================ SUBMENU =================>");
                System.out.println("<=============== Trabalho1 ==============>");
                System.out.println("1. Visualizar Matriz");
                System.out.println("2. Inserir Borda de 3px color white");
                System.out.println("3. Inverter cores da Imagem");
                System.out.println("4. Rotacionar a Imagem 90º");
                System.out.println("5. Salvar Arquivo");
                System.out.println("6. Sair");
                System.out.println("0. Voltar");
                System.out.println("<=============== ========= ==============>");
                System.out.print(": ");
                option = in.nextInt();

                System.out.print("\n");

                switch (option) {
                    case 0:
                        menu();
                        break;
                    case 1:
                        viewMatrix();
                        break;
                    case 2:
                        insertBorder();
                        break;
                    case 3:
                        colorInvert();
                        break;
                    case 4:
                        rotate90();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        break;
                    default:
                        submenu();
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 6);
        System.exit(0);
    }

    private static void readFile() throws IOException {

        control.cria_matriz(Main.file);
        System.out.println("Arquivo carregado com sucesso!");
        submenu();
    }

    private static void viewMatrix() throws IOException {
        int option = 1;
        System.out.println("<============= Matriz da Imagem (View Normal - com os zeros) =============>");
        System.out.print("\n");
        System.out.println(control.toString());
        do {
            try {

                System.out.println("0. Voltar");
                System.out.println("<=============== ======================== ==============>");
                System.out.print(": ");
                option = in.nextInt();

                System.out.print("\n");

                switch (option) {
                    case 0:
                        submenu();
                        break;
                    default:
                        viewMatrix();
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);
    }

    private static void insertBorder() throws IOException {
        int option = 1;
        do {
            try {
                System.out.println("<============= Imagem (Com Borda de 3px) =============>");
                System.out.print("\n");
                System.out.println("1. Inserir Borda (3px)");
                System.out.println("0. Voltar");
                System.out.println("<=============== ======================== ==============>");
                System.out.print(": ");
                option = in.nextInt();
                if (option == 1) {

                    System.out.print("\n");
                    control.insertBorder();
                    System.out.println("Borda inserida com sucesso!");
                    System.out.print("\n");
                    System.out.println(control.toString());
                    System.out.println("0. Voltar");
                    System.out.println("<=============== ======================== ==============>");
                    System.out.print(": ");
                    option = in.nextInt();

                    System.out.print("\n");

                    switch (option) {
                        case 0:
                            submenu();
                            break;
                        default:
                            insertBorder();
                            break;
                    }
                } else {
                    submenu();
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);

    }

    private static void colorInvert() throws IOException {
        int option = 1;
        do {
            try {
                System.out.println("<============= Imagem (Com Cores Invertidas) =============>");
                System.out.print("\n");
                System.out.println("1. Inverter Cores");
                System.out.println("0. Voltar");
                System.out.println("<=============== ======================== ==============>");
                System.out.print(": ");
                option = in.nextInt();
                if (option == 1) {

                    System.out.print("\n");
                    control.colorInvert();
                    System.out.println("Cores invertidas com sucesso!");
                    System.out.print("\n");
                    System.out.println(control.toString());
                    System.out.println("0. Voltar");
                    System.out.println("<=============== ======================== ==============>");
                    System.out.print(": ");
                    option = in.nextInt();

                    System.out.print("\n");

                    switch (option) {
                        case 0:
                            submenu();
                            break;
                        default:
                            colorInvert();
                            break;
                    }
                } else {
                    submenu();
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);
    }

    private static void rotate90() throws IOException {
        int option = 1;
        do {
            try {
                System.out.println("<============= Imagem (Rotacionada 90º) =============>");
                System.out.print("\n");
                System.out.println("1. Rotacionar 90º");
                System.out.println("0. Voltar");
                System.out.println("<=============== ======================== ==============>");
                System.out.print(": ");
                option = in.nextInt();
                if (option == 1) {

                    System.out.print("\n");
                    control.rotate90();
                    System.out.println("Imagem rotacionada com sucesso!");
                    System.out.print("\n");
                    System.out.println(control.toString());
                    System.out.println("0. Voltar");
                    System.out.println("<=============== ======================== ==============>");
                    System.out.print(": ");
                    option = in.nextInt();

                    System.out.print("\n");

                    switch (option) {
                        case 0:
                            submenu();
                            break;
                        default:
                            rotate90();
                            break;
                    }
                } else {
                    submenu();
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);
    }

    private static void save() throws IOException {
        int option = 1;
        do {
            try {
                System.out.println("<============= Salvar Arquivo =============>");
                System.out.print("\n");
                System.out.println("1. Salvar");
                System.out.println("2. Salvar Como...");
                System.out.println("0. Voltar");
                System.out.println("<=============== ======================== ==============>");
                System.out.print(": ");
                option = in.nextInt();

                System.out.print("\n");

                switch (option) {
                    case 0:
                        submenu();
                        break;
                    case 1:
                        control.saveArquivo(Main.file);
                        System.out.print("\n\nArquivo salvo com sucesso!\n\n");
                        save();
                        break;
                    case 2:
                        try {
                            System.out.println("Digite abaixo o caminho completo do novo arquivo (Ex. C:\\Users\\Filipe\\CEFET\\AED1\\Trabalho1\\Trabalho1.pgm)");
                            System.out.print("\n");
                            System.out.print(": ");
                            String file = in.next();

                            if (file.matches(regex)) {
                                throw new IllegalArgumentException();
                            }

                            control.saveArquivo(file);
                            System.out.print("\n\nArquivo salvo com sucesso!\n\n");

                        } catch (InputMismatchException | IllegalArgumentException e) {

                            System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                            System.out.print("Vamos tentar novamente.\n\n");

                        }
                        save();
                        break;
                    default:
                        save();
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {

                System.out.print("\nHouve algum erro na digitação das entradas anteriores!!!\n");
                System.out.print("Vamos tentar novamente.\n\n");
                in.next();
            }
        } while (option != 0);
    }
}
