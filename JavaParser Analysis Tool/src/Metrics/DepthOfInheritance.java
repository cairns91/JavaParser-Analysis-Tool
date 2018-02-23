package Metrics;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

@SuppressWarnings("rawtypes")
public class DepthOfInheritance extends VoidVisitorAdapter {

	@SuppressWarnings("unchecked")
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {

		List<ClassOrInterfaceType> extend = n.getExtendedTypes();
		List<ClassOrInterfaceType> implement = n.getImplementedTypes();

		int counterExtends = 0;
		int counterImplements = 0;
		int counterInitial = 1;
		System.out.println("Class Name: " + n.getName());
		System.out.println("");

		if (extend.isEmpty()) {
			System.out.println("Inherit from SuperClass?: " + " No");
			System.out.println("");
		} else {
			System.out.println("Inherit from SuperClass?: " + " Yes");

			for (ClassOrInterfaceType v : n.getExtendedTypes()) {
				counterExtends++;
				System.out.println("Inherited Classes: " + v.getNameAsString());
				System.out.println("");
			}
		}
		super.visit(n, arg);
		if (implement.isEmpty()) {
			System.out.print("Inherit from Interface?: " + " No");
			System.out.println("");
		} else {
			System.out.println("Inherit from Interface?: " + " Yes");
			for (ClassOrInterfaceType v : n.getImplementedTypes()) {
				counterImplements++;
				System.out.println("Inherited Classes: " + v.getNameAsString());
				System.out.println("");
			}
		}
		System.out.println("Depth of Inheritance: " + (counterExtends + counterImplements + counterInitial));
		System.out.println("");
		if ((counterExtends + counterImplements + counterInitial) <= 5) {
			System.out.println("Depth of Inheritance is less than or equal to the recommended DIT of 5, Well Done! ");
		} else {
			System.out.println(
					"Depth of Inheritance is greater than or equal to the recommended DIT of 5, your class may be too complex! ");
		}
		super.visit(n, arg);
	}
}
