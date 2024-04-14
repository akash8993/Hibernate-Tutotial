package com.akash.productData.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class CustomRandomIdGenerator  implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        Random random= new Random();
        long id=0;
        random.nextLong(1000000);
        id= random.nextLong();
        return id;
    }
}
