package Metrics;

import java.util.List;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

@SuppressWarnings("rawtypes")
public class WeightedMethods extends VoidVisitorAdapter {

	public void visit(ClassOrInterfaceDeclaration n, Object arg) {

		int counterMethods = 0;
		int counterConstructors = 0;

		List<MethodDeclaration> methods = n.getMethods();
		List<ConstructorDeclaration> constructors = n.getConstructors();

		System.out.println("Class Name: " + n.getName());
		System.out.println("");
		for (int i = 0; i < methods.size(); i++) {
			counterMethods++;
		}

		for (int j = 0; j < constructors.size(); j++) {
			counterConstructors++;
		}
		System.out.println("Number of Methods: " + (counterMethods + counterConstructors));
		System.out.println("");

		if (counterMethods + counterConstructors <= 20) {
			System.out.println(
					"The amount of methods in this class is less than or equal to the limit of 20 methods per class, Well done!");
		} else {
			System.out.println(
					"The amount of methods in this class is more than the limit of 20 methods per class, This suggests that your class is too large!");
		}
	}
}