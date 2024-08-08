//package com.example;
//
//import org.hibernate.engine.jdbc.CharacterStream;
//import org.hibernate.engine.jdbc.internal.CharacterStreamImpl;
//import org.hibernate.type.descriptor.WrapperOptions;
//import org.hibernate.type.descriptor.java.AbstractClassJavaType;
//import org.hibernate.type.descriptor.java.DataHelper;
//import org.hibernate.type.descriptor.java.JavaType;
//import org.hibernate.type.descriptor.jdbc.JdbcType;
//import org.hibernate.type.descriptor.jdbc.JdbcTypeIndicators;
//import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;
//import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;
//import org.hibernate.type.spi.TypeConfiguration;
//
//import java.io.Reader;
//import java.io.StringReader;
//import java.sql.Clob;
//import java.util.UUID;
//
///**
// * @see org.hibernate.type.descriptor.java.UUIDJavaType
// */
//public class UUIDAsStringJavaType extends AbstractClassJavaType<String> {
//    public static final UUIDAsStringJavaType INSTANCE = new UUIDAsStringJavaType();
//
//    public UUIDAsStringJavaType() {
//        super( String.class );
//    }
//
//    public String toString(String value) {
//        return value;
//    }
//
//    public String fromString(CharSequence string) {
//        return string.toString();
//    }
//
//    @Override
//    public JdbcType getRecommendedJdbcType(JdbcTypeIndicators stdIndicators) {
//        final TypeConfiguration typeConfiguration = stdIndicators.getTypeConfiguration();
//        final JdbcTypeRegistry stdRegistry = typeConfiguration.getJdbcTypeRegistry();
//
////        if ( stdIndicators.isLob() ) {
////            return stdIndicators.isNationalized()
////                    ? stdRegistry.getDescriptor( Types.NCLOB )
////                    : stdRegistry.getDescriptor( Types.CLOB );
////        }
////        else if ( stdIndicators.isNationalized() ) {
////            return stdRegistry.getDescriptor( Types.NVARCHAR );
////        }
////
////        return super.getRecommendedJdbcType( stdIndicators );
//        return UUIDJdbcType.INSTANCE;
//    }
//
//    @SuppressWarnings("unchecked")
//    public <X> X unwrap(String value, Class<X> type, WrapperOptions options) {
//        if ( value == null ) {
//            return null;
//        }
//        if (UUID.class.isAssignableFrom( type ) ) {
//            return (X)  UUID.fromString(value);
//        }
//        if ( String.class.isAssignableFrom( type ) ) {
//            return (X) value;
//        }
//        if ( Reader.class.isAssignableFrom( type ) ) {
//            return (X) new StringReader( value );
//        }
//        if ( CharacterStream.class.isAssignableFrom( type ) ) {
//            return (X) new CharacterStreamImpl( value );
//        }
//        // Since NClob extends Clob, we need to check if type is an NClob
//        // before checking if type is a Clob. That will ensure that
//        // the correct type is returned.
//        if ( DataHelper.isNClob( type ) ) {
//            return (X) options.getLobCreator().createNClob( value );
//        }
//        if ( Clob.class.isAssignableFrom( type ) ) {
//            return (X) options.getLobCreator().createClob( value );
//        }
//
//        throw unknownUnwrap( type );
//    }
//
//    public <X> String wrap(X value, WrapperOptions options) {
//        if ( value == null ) {
//            return null;
//        }
//        if (value instanceof String) {
//            return (String) value;
//        }
//        if (value instanceof UUID) {
//            return value.toString();
//        }
//        if (value instanceof char[]) {
//            return new String( (char[]) value );
//        }
//        if (value instanceof Reader) {
//            return DataHelper.extractString( (Reader) value );
//        }
//        if (value instanceof Clob) {
//            return DataHelper.extractString( (Clob) value );
//        }
//
//        throw unknownWrap( value.getClass() );
//    }
//
//    @Override
//    public boolean isWider(JavaType<?> javaType) {
//        switch ( javaType.getJavaType().getTypeName() ) {
//            case "char":
//            case "char[]":
//            case "java.lang.Character":
//            case "java.lang.Character[]":
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    @Override
//    public <X> String coerce(X value, CoercionContext coercionContext) {
//        return wrap( value, null );
//    }
//}
