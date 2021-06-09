package fr.umlv.calc;

import java.util.Iterator;
import java.util.Objects;

public sealed interface Expr {
	/**
	 * Return the value of an Expr type
	 * @return Expr's value
	 */
	int eval();
	
	record Value(int value) implements Expr {
		@Override
		public int eval() {
			return value;
		}

		@Override
		public String toString() {
			return value + "";
		}
	}
	
	sealed abstract class BinOp implements Expr {
		private final Expr left;
		private final Expr right;
		private final char operator;
		
		/* Constructor */
		public BinOp(Expr left, Expr right, char operator) {
			this.left = Objects.requireNonNull(left);
			this.right = Objects.requireNonNull(right);
			this.operator = Objects.requireNonNull(operator);
		}
		
		@Override
		public int eval() {
			return operator == '+' ? left.eval() + right.eval() : left.eval() - right.eval();
		}
		
		@Override
		public String toString() {
			return operator == '+' ? "(" + left + " + " + right + ")" : "(" + left + " - " + right + ")";
		}
		
		public static final class Add extends BinOp {
			/* Constructor */
			public Add(Expr left, Expr right) {
				super(left, right, '+');
			}
		}
		
		public static final class Sub extends BinOp {
			/* Constructor */
			public Sub(Expr left, Expr right) {
				super(left, right, '-');
			}
		}
	}
	
	static Expr parse(Iterator<String> scanner) {
        if (scanner.hasNext()) {
            String next = scanner.next();
            return switch (next) {
                case "+" -> new BinOp.Add(parse(scanner), parse(scanner));
                case "-" -> new BinOp.Sub(parse(scanner), parse(scanner));
                default  -> new Value(Integer.parseInt(next));
            };
        }
        
        return null;
    }
}

