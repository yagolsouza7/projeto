package application.view;

public class herancaController {

    class Funcionario {
        protected String nome;
        protected float salario_base;

        public Funcionario(String nome, float salario_base) {
            this.nome = nome;
            this.salario_base = salario_base;
        }

        public float calcularSalario() {
            return salario_base;
        }
    }

    class Gerente extends Funcionario {
        private float bonus;

        public Gerente(String nome, float salario_base, float bonus) {
            super(nome, salario_base);
            this.bonus = bonus;
        }

        @Override
        public float calcularSalario() {
            return salario_base + bonus;
        }
    }

    class Desenvolvedor extends Funcionario {
        private int horas_extras;

        public Desenvolvedor(String nome, float salario_base, int horas_extras) {
            super(nome, salario_base);
            this.horas_extras = horas_extras;
        }

        @Override
        public float calcularSalario() {
            return salario_base + (horas_extras * 50);
        }
    }

    class Estagiario extends Funcionario {

        public Estagiario(String nome, float salario_base) {
            super(nome, salario_base);
        }

        @Override
        public float calcularSalario() {
            return salario_base * 0.8f;
        }
    }

    public static class Main {
        public static void main(String[] args) {

            herancaController h = new herancaController();

            Funcionario f1 = h.new Gerente("Ana", 5000, 1500);
            Funcionario f2 = h.new Desenvolvedor("João", 3000, 10);
            Funcionario f3 = h.new Estagiario("Marcos", 2000);

            Funcionario[] funcionarios = { f1, f2, f3 };

            for (Funcionario f : funcionarios) {
                System.out.println(f.nome + " - Salário final: R$ " + f.calcularSalario());
            }
        }
    }
}

