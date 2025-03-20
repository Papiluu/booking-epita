package dev.xdbe.booking.model.booking;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import dev.xdbe.booking.helper.CryptoHelper;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute;
    }
    
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        
        if (dbData.length() <= 8) {
            return dbData;
        }
        
        String firstFour = dbData.substring(0, 4);
        String lastFour = dbData.substring(dbData.length() - 4);
        String masked = "*".repeat(dbData.length() - 8);
        
        return firstFour + masked + lastFour;
    }
}