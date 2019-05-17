/**
 * @Package: indi.shaw.coco.menu
 * @author: shaw
 * @date: 2019年4月17日 下午11:11:27
 */
package indi.shaw.coco.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * @author shaw
 *
 */
public class AddressSerializerFactory extends SerializerFactory {

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#withAdditionalSerializers(com.fasterxml.jackson.databind.ser.Serializers)
	 */
	@Override
	public SerializerFactory withAdditionalSerializers(Serializers additional) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#withAdditionalKeySerializers(com.fasterxml.jackson.databind.ser.Serializers)
	 */
	@Override
	public SerializerFactory withAdditionalKeySerializers(Serializers additional) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#withSerializerModifier(com.fasterxml.jackson.databind.ser.BeanSerializerModifier)
	 */
	@Override
	public SerializerFactory withSerializerModifier(BeanSerializerModifier modifier) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#createSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.JavaType)
	 */
	@Override
	public JsonSerializer<Object> createSerializer(SerializerProvider prov, JavaType baseType) throws JsonMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#createTypeSerializer(com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.JavaType)
	 */
	@Override
	public TypeSerializer createTypeSerializer(SerializationConfig config, JavaType baseType) throws JsonMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.SerializerFactory#createKeySerializer(com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer)
	 */
	@Override
	public JsonSerializer<Object> createKeySerializer(SerializationConfig config, JavaType baseType, JsonSerializer<Object> defaultImpl) throws JsonMappingException {
		// TODO Auto-generated method stub
		return null;
	}

}
