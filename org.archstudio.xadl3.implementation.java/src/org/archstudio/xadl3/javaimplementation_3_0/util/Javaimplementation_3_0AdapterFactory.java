/**
 */
package org.archstudio.xadl3.javaimplementation_3_0.util;

import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package
 * @generated
 */
public class Javaimplementation_3_0AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static Javaimplementation_3_0Package modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Javaimplementation_3_0AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Javaimplementation_3_0Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Javaimplementation_3_0Switch<Adapter> modelSwitch = new Javaimplementation_3_0Switch<Adapter>() {
		@Override
		public Adapter caseClassPathEntry(ClassPathEntry object) {
			return createClassPathEntryAdapter();
		}

		@Override
		public Adapter caseJavaClass(JavaClass object) {
			return createJavaClassAdapter();
		}

		@Override
		public Adapter caseJavaImplementation(JavaImplementation object) {
			return createJavaImplementationAdapter();
		}

		@Override
		public Adapter caseImplementation(Implementation object) {
			return createImplementationAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry
	 * <em>Class Path Entry</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry
	 * @generated
	 */
	public Adapter createClassPathEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass
	 * <em>Java Class</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass
	 * @generated
	 */
	public Adapter createJavaClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation <em>Java Implementation</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation
	 * @generated
	 */
	public Adapter createJavaImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.archstudio.xadl3.implementation_3_0.Implementation
	 * <em>Implementation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation
	 * @generated
	 */
	public Adapter createImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // Javaimplementation_3_0AdapterFactory
