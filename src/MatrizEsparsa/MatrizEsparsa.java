package MatrizEsparsa;

public class MatrizEsparsa<T> {

    /**
     * Propriedades.
     */
    private int linhas;

    private int colunas;

    private int numeroDeElementos;

    private Celula head;

    /**
     * Célula.
     */
    private class Celula {

        private T item;

        private int linha;

        private int coluna;

        private Celula proximo = null;

        private Celula(T item, int x, int y) {

            this.item = item;
            this.linha = x;
            this.coluna = y;
        }
    }

    /**
     * Construtor.
     */
    MatrizEsparsa(int l, int c) {

        this.linhas = l;
        this.colunas = c;
        this.head = null;
        this.numeroDeElementos = 0;
    }

    /**
     * Método para verificação de matriz vazia.
     */
    public boolean is_empty() {

        return this.numeroDeElementos == 0;
    }

    /**
     * Método de busca de elemento.
     */
    private Celula find(int x, int y) {

        if (this.is_empty()) {
            return null;
        }

        Celula anterior = this.head;

        if (anterior.linha == x && anterior.coluna == y) {
            return anterior;
        } else {
            while (anterior.proximo != null) {
                if (anterior.proximo.linha == x && anterior.proximo.coluna == y) {
                    return anterior.proximo;
                } else {
                    anterior = anterior.proximo;
                }
            }
            
            return null;
            
        }
    }

    /**
     * Método para encontrar e retornar o item do elemento.
     */
    public T get(int x, int y) {

        Celula i = find(x, y);
        if (i == null) {
            return null;
        }
        return i.item;
    }

    /**
     * Método para add novo.
     */
    public void add(T item, int x, int y) {

        if ((x < 1) || (y < 1) || item == null) {
            throw new IllegalArgumentException("Item nulo ou coordenadas inválidas.");
        }

        if ((x > this.linhas) || (y > this.colunas)) {
            throw new IllegalArgumentException("Coordenadas superiores as da matriz existente.");
        }

        boolean idem = false;

        if (this.head == null) {

            Celula no = new Celula(item, x, y);
            this.head = no;
            this.numeroDeElementos++;
        } else {

            Celula anterior = this.head;

            if (anterior.linha == x) {

                if (anterior.coluna <= y) {

                    if (anterior.coluna == y) {

                        anterior.item = item;
                        this.numeroDeElementos++;
                    } else {

                        while (anterior.proximo != null) {

                            if (anterior.proximo.linha == x) {

                                if (anterior.proximo.coluna < y) {

                                    anterior = anterior.proximo;
                                } else if (anterior.proximo.coluna == y) {
                                    idem = true;
                                    break;
                                } else {
                                    break;
                                }
                            } else if (anterior.proximo.linha < x) {

                                anterior = anterior.proximo;
                            } else {

                                break;
                            }
                        }

                        if (idem) {

                            anterior.proximo.item = item;
                        } else {

                            Celula no = new Celula(item, x, y);
                            no.proximo = anterior.proximo;
                            anterior.proximo = no;
                            this.numeroDeElementos++;
                        }
                    }
                } else {

                    Celula no = new Celula(item, x, y);
                    no.proximo = anterior;
                    this.head = no;
                    this.numeroDeElementos++;
                }
            } else if (anterior.linha > x) {

                Celula no = new Celula(item, x, y);
                no.proximo = anterior;
                this.head = no;
                this.numeroDeElementos++;
            } else {

                while (anterior.proximo != null) {

                    if (anterior.proximo.linha == x) {

                        if (anterior.proximo.coluna < y) {

                            anterior = anterior.proximo;
                        } else if (anterior.proximo.coluna == y) {
                            idem = true;
                            break;
                        } else {
                            break;
                        }
                    } else if (anterior.proximo.linha < x) {

                        anterior = anterior.proximo;
                    } else {

                        break;
                    }
                }

                if (idem) {

                    anterior.proximo.item = item;
                } else {

                    Celula no = new Celula(item, x, y);
                    no.proximo = anterior.proximo;
                    anterior.proximo = no;
                    this.numeroDeElementos++;
                }
            }
        }
    }

    /**
     * Método para add novo inversamente (de baixo para cima e da direita para a
     * esquerda).
     */
    public void addInverse(T item, int x, int y) {

        if ((x < 1) || (y < 1) || item == null) {
            throw new IllegalArgumentException("Item nulo ou coordenadas inválidas.");
        }

        if ((x > this.linhas) || (y > this.colunas)) {
            throw new IllegalArgumentException("Coordenadas superiores as da matriz existente.");
        }
        
        Celula anterior = this.head;
        
        if (anterior == null) {
            
            Celula no = new Celula(item, x, y);
            this.head = no;
            this.numeroDeElementos++;
        } else {
            
            if (anterior.linha == x && anterior.coluna == y) {
                
                anterior.item = item;
                return;
            } else {
                
                Celula no = new Celula(item, x, y);
                no.proximo = this.head;
                this.head = no;
                this.numeroDeElementos++;
                return;
            }
        }
    }

    /**
     * Método para remover um elemento.
     */
    public T remove(int x, int y) {

        if (this.is_empty()) {
            return null;
        }

        Celula anterior = this.head;
        Celula retrn = anterior;

        if (anterior.linha == x && anterior.coluna == y) {
            head = anterior.proximo;
            this.numeroDeElementos--;
            return retrn.item;
        } else {
            while (anterior.proximo != null) {
                if (anterior.proximo.linha == x && anterior.proximo.coluna == y) {
                    retrn = anterior.proximo;
                    anterior.proximo = anterior.proximo.proximo;
                    this.numeroDeElementos--;
                    return retrn.item;
                } else {
                    anterior = anterior.proximo;
                }
            }

            if (anterior.proximo == null) {
                throw new NullPointerException("Item não foi encontrado.");
            } else {
                return null;
            }
        }
    }

    /**
     * Get int linhas.
     */
    public int getLinhas() {

        return this.linhas;
    }

    /**
     * Set int linhas.
     */
    public void setLinhas(int linhas) {

        if (linhas != this.linhas && linhas >= 0) {
            this.linhas = linhas;
        }
    }

    /**
     * Get int colunas.
     */
    public int getColunas() {

        return this.colunas;
    }

    /**
     * Set int colunas.
     */
    public void setColunas(int colunas) {

        if (colunas != this.colunas && colunas >= 0) {
            this.colunas = colunas;
        }
    }

    @Override
    public String toString() {

        if (this.is_empty()) {
            return "Matriz Esparsa Vazia.";
        }

        StringBuilder sb = new StringBuilder();
        Celula p = this.head;

        for (int i = 1; i <= this.linhas; i++) {

            for (int j = 1; j <= this.colunas; j++) {

                if ((p != null) && (p.linha == i && p.coluna == j)) {

                    sb.append(p.item.toString());
                    p = p.proximo;
                } else {

                    sb.append("0");
                }

                sb.append("\t");
            }
            if (sb.length() > 2) {

                sb.delete(sb.length() - 1, sb.length());
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}
