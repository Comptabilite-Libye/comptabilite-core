/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DevPointSystem.Comptabilite.Config.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map; 
import org.springframework.core.ResolvableType;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.util.Assert;

/**
 *
 * @author Administrateur
 * @param <T> message Type
 */
public class JsonDeserializer<T> implements Deserializer<T> {

    protected final ObjectMapper objectMapper;

    protected final Class<T> targetType;

    private volatile ObjectReader reader;

    protected JsonDeserializer() {
        this((Class<T>) null);
    }

    protected JsonDeserializer(ObjectMapper objectMapper) {
        this(null, objectMapper);
    }

    public JsonDeserializer(Class<T> targetType) {
        this(targetType, new ObjectMapper());
        this.objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @SuppressWarnings("unchecked")
    public JsonDeserializer(Class<T> targetType, ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null.");
        this.objectMapper = objectMapper;
        if (targetType == null) {
            targetType = (Class<T>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
        }
        Assert.notNull(targetType, "'targetType' cannot be resolved.");
        this.targetType = targetType;
    }
 
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No-op
    }
 
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (this.reader == null) {
            this.reader = this.objectMapper.readerFor(this.targetType);
        }
        try {

            return this.reader.readValue(data);
        } catch (IOException e) {
            throw new SerializationFailedException("Can't deserialize data [" + Arrays.toString(data)
                    + "] from topic [" + topic + "]", e);
        }
    }
 
    public void close() {
        // No-op
    }

    @Override
    public T deserialize(InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
