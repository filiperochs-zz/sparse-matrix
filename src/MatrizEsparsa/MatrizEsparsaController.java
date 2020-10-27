package MatrizEsparsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import LSED.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MatrizEsparsaController {

    private MatrizEsparsa<Integer> matriz;
    private int linhas, colunas, maxVal;

    /**
     * Método para gerar uma matriz esparsa.
     */
    public void cria_matriz(String file) throws IOException {
        
        LSED<String> linhasArquivo = loadArquivo(file);

        System.out.println("");

        if (!linhasArquivo.get(1).equals("P2")) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado.");
        }

        String[] aux = linhasArquivo.get(2).split(" ");

        this.colunas = Integer.parseInt(aux[0]);
        this.linhas = Integer.parseInt(aux[1]);

        this.maxVal = Integer.parseInt(linhasArquivo.get(3));

        this.matriz = new MatrizEsparsa(linhas, colunas);

        int linha, coluna, elementoAux;
        for (int i = 4; i <= linhasArquivo.size(); i++) {
            aux = linhasArquivo.get(i).split("\t");
            for (int j = 0; j < aux.length; j++) {
                linha = i - 3;
                coluna = j + 1;
                elementoAux = Integer.parseInt(aux[j]);
                if (elementoAux != 0) {
                    this.matriz.add(elementoAux, linha, coluna);
                }
            }
        }
    }

    /**
     * Método para carregar os dados do arquivo e transformar em uma Lista de
     * Strings. Cada elemento da lista é uma linha.
     */
    private LSED<String> loadArquivo(String file) throws IOException {
        LSED<String> conteudo = new LSED();

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;

            while ((str = in.readLine()) != null) {
                conteudo.addEnd(str.trim());
            }
            in.close();
        } catch (IOException ioe) {
            
            throw new IOException("Ocorreu um erro durante a leitura do arquivo.");
        }

        return conteudo;
    }

    /**
     * Método para carregar os dados do arquivo e transformar em uma Lista de
     * Strings. Cada elemento da lista é uma linha.
     */
    public void saveArquivo(String file) throws IOException {

        try {
            
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.append("P2\n");
            out.append(Integer.toString(this.colunas) + " ");
            out.append(Integer.toString(this.linhas) + "\n");
            out.append(Integer.toString(this.maxVal) + "\n");
            out.append(toString());
            out.close();
        } catch (IOException ioe) {
            
            throw new IOException("Ocorreu um erro durante a escrita do arquivo.");
        }
    }

    /**
     * Método para retornar o valor de maxVal -> O maior valor da escala de
     * cinza possível.
     */
    public int getMaxVal() {
        return this.maxVal;
    }

    /**
     * Método inserir borda de 3px na imagem.
     */
    public void insertBorder() {
        int[] linhas = {1, 2, 3, this.linhas - 2, this.linhas - 1, this.linhas};
        int[] colunas = {1, 2, 3, this.colunas - 2, this.colunas - 1, this.colunas};
        int lin = 0;

        for (int i = 1; i <= this.linhas; i++) {
            int col = 0;
            for (int j = 1; j <= this.colunas; j++) {

                if (i == linhas[lin] || j == colunas[col]) {

                    if (this.matriz.get(i, j) != null) {
                        this.matriz.remove(i, j);
                    }

                    this.matriz.add(255, i, j);
                    col++;
                }
            }
            if (i == linhas[lin]) {

                lin++;
            }
        }
    }

    /**
     * Método inverter cores da imagem.
     */
    public void colorInvert() {
        int val;
        for (int i = 1; i <= this.linhas; i++) {
            for (int j = 1; j <= this.colunas; j++) {
                if (this.matriz.get(i, j) != null) {
                    val = 255 - this.matriz.get(i, j);

                    if (val == 0) {
                        this.matriz.remove(i, j);
                    } else {
                        this.matriz.add(val, i, j);
                    }
                } else {
                    this.matriz.add(255, i, j);
                }

            }
        }
    }

    /**
     * Método inverter cores da imagem.
     */
    public void rotate90() {

        MatrizEsparsa<Integer> aux = new MatrizEsparsa(this.colunas, this.linhas);

        int coluna = this.linhas, linha = this.colunas;
        for (int j = this.colunas; j >= 1; j--) {

            for (int i = 1; i <= this.linhas; i++) {

                if (this.matriz.get(i, j) != null) {

                    aux.addInverse(this.matriz.get(i, j), j, ((this.linhas + 1) - i));
                }
            }
        }
        int aux2 = this.linhas;
        this.linhas = this.colunas;
        this.colunas = aux2;

        this.matriz = aux;
    }

    /**
     * Método toString da Classe MatrizEsparsa.
     */
    @Override
    public String toString() {
        return this.matriz.toString();
    }
}
