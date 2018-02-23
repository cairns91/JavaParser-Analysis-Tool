import java.io.FileInputStream;

import java.util.Scanner;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import BadSmells.LargeClass;
import ClassDiagram.ClassDiagram;
import Metrics.DepthOfInheritance;
import Metrics.WeightedMethods;

public class Driver {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		Boolean stop = false;
		while (!stop) {
			System.out.println("");
			System.out.println("Please choose the form of analysis: ");
			System.out.println("1: Class Diagram ");
			System.out.println("2: Metrics ");
			System.out.println("3: Bad Smells ");

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			if (input.equals("1")) {
				FileInputStream in = new FileInputStream("Files\\Account.java");
				CompilationUnit cu;
				try {
					cu = JavaParser.parse(in);
				} finally {
					in.close();
				}
				new ClassDiagram().visit(cu, null);
				continue;
			}
			if (input.equals("2")) {
				Boolean metric = false;
				while (!metric) {
					System.out.println("Select ");
					System.out.println("1: Depth of Inheritance ");
					System.out.println("2. Number of Methods ");
					String metricChoice = scan.nextLine();

					if (metricChoice.equals("1")) {
						FileInputStream in = new FileInputStream("Files\\Iterator.java");
						CompilationUnit cu;
						try {
							cu = JavaParser.parse(in);
						} finally {
							in.close();
						}
						new DepthOfInheritance().visit(cu, null);
						metric = true;
						continue;
					}
					if (metricChoice.equals("2")) {
						FileInputStream in = new FileInputStream("Files\\Game.java");
						CompilationUnit cu;
						try {
							cu = JavaParser.parse(in);
						} finally {
							in.close();
						}
						new WeightedMethods().visit(cu, null);
						metric = true;
						break;
					}
				}
			}
			if (input.equals("3")) {
				Boolean badSmell = false;
				while (!badSmell) {
					System.out.println("Select ");
					System.out.println("1: Large Class ");
					String badSmellChoice = scan.nextLine();

					if (badSmellChoice.equals("1")) {
						FileInputStream in = new FileInputStream("Files\\Model.java");
						CompilationUnit cu;
						try {
							cu = JavaParser.parse(in);
						} finally {
							in.close();
						}
						new LargeClass().visit(cu, null);
						continue;
					}
				}
			}
		}
	}
}
