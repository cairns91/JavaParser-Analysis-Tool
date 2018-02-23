package BadSmells;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

@SuppressWarnings("rawtypes")
public class LargeClass extends VoidVisitorAdapter {

	int counterMethods = 0;
	int counterConstructors = 0;
	int counterFields = 0;

	public void visit(ClassOrInterfaceDeclaration n, Object arg) {

		List<MethodDeclaration> methods = n.getMethods();
		List<ConstructorDeclaration> constructors = n.getConstructors();
		List<FieldDeclaration> fields = n.getFields();

		System.out.println("Class Name: " + n.getName());
		System.out.println("");
		for (int i = 0; i < methods.size(); i++) {
			counterMethods++;
		}
		for (int j = 0; j < constructors.size(); j++) {
			counterConstructors++;
		}
		for (int i = 0; i < fields.size(); i++) {
			counterFields++;
		}
		System.out.println("Number of Fields: " + (counterFields));
		System.out.println("Number of Methods: " + (counterMethods + counterConstructors));
		System.out.println("");

		if (counterMethods + counterConstructors + counterFields > 20) {
			System.out.println(
					"The total amount of methods and fields exceed 20, Consider breaking your class down into smaller classes!");
		} else {
			System.out.println("The total amount of methods and fields do not exceed 20, Well Done!");
		}
	}
}