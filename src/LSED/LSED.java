package LSED;

// Lista Simplesmente Encadiada Dinâmica
public class LSED<T> {

    private Celula head;
    private int size;

    private class Celula {

        T item;
        Celula prox;
    }

    // Fazer Lista Vazia
    public LSED() {
        head = null;
        size = 0;
    }

    public void add(T item) {
        if (item == null) {
            throw new NullPointerException("Não é permitido a inserção de elementos nulos.");
        }

        Celula novo = new Celula();
        novo.item = item;
        novo.prox = head;
        head = novo;
        size++;
    }

    public void addEnd(T item) {
        if (item == null) {
            throw new NullPointerException("Não permitido a inserção de elementos nulos.");
        }

        if (head == null) {
            head = new Celula();
            head.item = item;
        } else {
            Celula aux = head;
            while (aux.prox != null) {
                aux = aux.prox;
            }
            aux.prox = new Celula();
            aux.prox.item = item;
        }
        size++;
    }

    public void addPos(T item, int pos) {
        if (item == null) {
            throw new NullPointerException("Não permitido a inserção de elementos nulos.");
        } else if (pos > size + 1 || pos <= 0) {
            throw new NullPointerException("Posição de inserção inexistente.");
        }

        Celula novo = new Celula();
        if (pos == 1) {
            novo.prox = head;
            head = novo;
        } else {
            Celula aux = head;
            int i = 1;
            while (aux != null && i < pos - 1) {
                aux = aux.prox;
                i++;
            }
            novo.prox = aux.prox;
            aux.prox = novo;
        }
        novo.item = item;
        size++;
    }

    public T remPos(int pos) {
        if (pos > size || pos <= 0) {
            throw new NullPointerException("Posição de remoção inexistente.");
        }

        int i = 1;
        Celula aux = head;
        Celula old;
        if (pos == 1) {
            old = head;
            head = head.prox;
            size--;
        } else {
            while (i < pos - 1) {
                aux = aux.prox;
                i++;
            }
            old = aux.prox;
            aux.prox = aux.prox.prox;
            size--;
        }
        return old.item;
    }

    public T get(int pos) {
        if (pos > size || pos <= 0) {
            throw new NullPointerException("Posição inexistente.");
        }

        int i = 1;
        Celula aux = head;
        Celula obj;
        if (pos == 1) {
            obj = head;
        } else {
            while (i < pos) {
                aux = aux.prox;
                i++;
            }
            obj = aux;
        }
        return obj.item;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Celula aux = head;
        sb.append("[");
        while (aux != null) {
            sb.append(aux.item);
            sb.append(" ");
            aux = aux.prox;
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
