package test.tollparking.utils;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

  final ObjectMapper defaultObjectMapper;


  public MyObjectMapperProvider() {
    defaultObjectMapper = createDefaultMapper();
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return defaultObjectMapper;
  }

  private static ObjectMapper createDefaultMapper() {
    final ObjectMapper result = new ObjectMapper();
    result.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"));
    result.configure(SerializationFeature.INDENT_OUTPUT, true);
    result.setSerializationInclusion(Include.NON_NULL);
    return result;
  }

}