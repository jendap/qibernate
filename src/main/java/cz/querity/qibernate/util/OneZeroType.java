package cz.querity.qibernate.util;

import java.io.Serializable;

import org.hibernate.dialect.Dialect;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.DiscriminatorType;
import org.hibernate.type.PrimitiveType;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.java.BooleanTypeDescriptor;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

/**
 * A type that maps between {@link java.sql.Types#CHAR CHAR(1)} and
 * {@link Boolean} (using '1' and '0')
 *
 * @author Kocour
 */
public class OneZeroType extends AbstractSingleColumnStandardBasicType<Boolean>
		implements PrimitiveType<Boolean>, DiscriminatorType<Boolean> {
	private static final long serialVersionUID = 6884389132844629065L;

	public static final OneZeroType INSTANCE = new OneZeroType();

	public OneZeroType() {
		super(CharTypeDescriptor.INSTANCE, new BooleanTypeDescriptor('1', '0'));
	}

	@Override
	public String getName() {
		return "one_zero";
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class getPrimitiveClass() {
		return boolean.class;
	}

	@Override
	public Boolean stringToObject(final String xml) throws Exception {
		return this.fromString(xml);
	}

	@Override
	public Serializable getDefaultValue() {
		return Boolean.FALSE;
	}

	@Override
	public String objectToSQLString(final Boolean value, final Dialect dialect)
			throws Exception {
		return StringType.INSTANCE.objectToSQLString(value.booleanValue() ? "Y"
				: "N", dialect);
	}
}
