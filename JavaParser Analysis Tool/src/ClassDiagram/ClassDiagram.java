package ClassDiagram;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

@SuppressWarnings("rawtypes")
public class ClassDiagram extends VoidVisitorAdapter {

	@SuppressWarnings("unchecked")
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {

		List<ClassOrInterfaceType> extend = n.getExtendedTypes();
		List<ClassOrInterfaceType> implement = n.getImplementedTypes();

		System.out.println("Class Name: " + n.getName());
		if (implement.isEmpty()) {
			System.out.print("Class Implements: " + "N/A");
		} else {
			System.out.print("Class Implements: " + implement);
		}
		System.out.println("");
		if (extend.isEmpty()) {
			System.out.print("Class Extends: " + "N/A");
		} else {
			System.out.println("Class Extend: " + extend);
		}
		System.out.println("");
		super.visit(n, arg);
	}

	public void visit(MethodDeclaration n, Object a) {

		System.out.println("");
		System.out.println("Method Type: " + n.getType());
		System.out.println("Name: " + n.getName());
	}

	public void visit(FieldDeclaration n, Object a) {

		System.out.println("");
		System.out.println("Field Type is: " + n.getElementType());
		for (VariableDeclarator v : n.getVariables()) {
			System.out.println("Name: " + v.getName());
		}
	}

	public void visit(VariableDeclarationExpr n, Object a) {

		System.out.println("");
		System.out.println("Var Type is: " + n.getElementType());
		for (VariableDeclarator v : n.getVariables()) {
			System.out.println("Name: " + v.getName());
		}
	}
}